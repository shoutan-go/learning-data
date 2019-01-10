package com.shoutanwq.data.learning.shell;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.shoutanwq.data.learning.dao.*;
import com.shoutanwq.data.learning.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author jingsi.liu
 * @Date 2018/12/2
 */
@ShellComponent
public class ClassDataApp {

    @Autowired
    ICouponDAO couponDAO;

    @Autowired
    IClazzDAO clazzDAO;

    @Autowired
    IOrderDAO orderDAO;

    @Autowired
    IUserProfileDAO userProfileDAO;

    @Autowired
    IGoInfoDAO goInfoDAO;

    @Autowired
    IGoMoveDAO goMoveDAO;

    @Autowired
    ICourseDAO courseDAO;

    @ShellMethod("生成课程")
    public String createClass(String course,
                              @ShellOption(defaultValue = "https://shoutan-images.oss-cn-qingdao" +
                                      ".aliyuncs.com/course1/class1.jpg?x-oss-process=style/thumb-150w") String teacher,
                              @ShellOption(defaultValue = "[0,0,2,4,7,9,11,14,16,18,21,23,25,28,30,32,35,37,39,42," +
                                      "44]") String validatedIn,
                              @ShellOption(defaultValue = ShellOption.NULL, help = "default today") String beginAt,
                              @ShellOption(defaultValue = "39900") int price) throws ParseException {
        Optional<Course> courseOptional = courseDAO.findById(course);
        if (courseOptional.isPresent()) {
            Date begin = new Date();
            if (!StringUtils.isEmpty(beginAt)) {
                begin = new SimpleDateFormat("yyyy-MM-dd").parse(beginAt);
            }
            Clazz newClz = new Clazz();
            newClz.setTeacher(teacher);
            newClz.setCourse(course);
            newClz.setValidatedIn(validatedIn);
            newClz.setPrice(price);
            newClz.setBeginAt(begin);
            Clazz savedClazz = clazzDAO.saveAndFlush(newClz);
            return savedClazz.getId();
        } else {
            return "CourseId 不存在";
        }
    }

    @ShellMethod("生成对应班级二维码")
    public String classQrcode(String classId, @ShellOption(defaultValue = "1") int count,
                              @ShellOption(defaultValue = "./qrcode") String filePath) throws WriterException,
            IOException {
        Optional<Clazz> byId = clazzDAO.findById(classId);
        if (byId.isPresent()) {
            List<String> retList = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                Coupon coupon = new Coupon();
                coupon.setClazz(classId);
                Coupon savedCoupon = couponDAO.saveAndFlush(coupon);
                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                BitMatrix bitMatrix = qrCodeWriter.encode(String.format("http://course.shoutanwq.com/coupon/%s",
                        savedCoupon.getId()), BarcodeFormat.QR_CODE, 128, 128);
                File folder = new File(filePath);
                folder.mkdirs();
                Path path = FileSystems.getDefault().getPath(String.format("%s/%s.%s", filePath, savedCoupon.getId(),
                        "png"));
                MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            }
            openFolder(filePath);
            return "OK";
        } else {
            throw new IllegalArgumentException("classId 不存在");
        }
    }

    private void openFolder(String path) throws IOException {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.indexOf("win") >= 0) {
            Runtime.getRuntime().exec(String.format("Explorer.exe \"%s\"", path));
        } else {
            Runtime.getRuntime().exec(String.format("open %s", path));
        }
    }

    @ShellMethod("获取班级内对局信息")
    public List<String> studentInfo(String id) {
        List<Order> orders = orderDAO.findAllByClazz(id);
        List<String> students = orders.parallelStream().map(order -> order.getUser()).collect(Collectors.toList());
        List<UserProfile> userProfiles = userProfileDAO.findAllByUserIdIn(students);
        Map<String, UserProfile> userProfileMap = userProfiles.stream().collect(Collectors.toMap(UserProfile::getUserId,
                Function.identity()));
        List<String> output = new ArrayList<>();
        List<GoInfo> allByBlackInOrWhiteIn = goInfoDAO.findAllByBlackInOrWhiteIn(students, students);
        Map<String, List<GoInfo>> listBlackMap =
                allByBlackInOrWhiteIn.stream().filter(goInfo -> goInfo.getBlack() != null).collect(Collectors.groupingBy(GoInfo::getBlack));
        Map<String, List<GoInfo>> listWhiteMap =
                allByBlackInOrWhiteIn.stream().filter(goInfo -> goInfo.getWhite() != null).collect(Collectors.groupingBy(GoInfo::getWhite));
        Map<String, GoMove> goMoveMap =
                goMoveDAO.findAllById(allByBlackInOrWhiteIn.stream().map(GoInfo::getId).collect(Collectors.toList())).stream().collect(Collectors.toMap(GoMove::getId, Function.identity()));
        output.add("用户名,对局数,总有效对局,获胜,失败");
        for (String studentId : students) {
            int valid = 0;
            int win = 0;
            int lose = 0;
            List<GoInfo> playedGame = new ArrayList<>();
            List<GoInfo> goInfos = listBlackMap.get(studentId);
            if (goInfos != null) playedGame.addAll(goInfos);
            goInfos = listWhiteMap.get(studentId);
            if (goInfos != null) playedGame.addAll(goInfos);
            for (GoInfo goInfo : playedGame) {
                if (!StringUtils.isEmpty(goInfo.getResult())) {
                    valid++;
                    if (studentId.equalsIgnoreCase(goInfo.getBlack())) {
                        if (goInfo.getResult().startsWith("B+")) {
                            win++;
                        } else {
                            lose++;
                        }
                    } else if (studentId.equalsIgnoreCase(goInfo.getWhite())) {
                        if (goInfo.getResult().startsWith("W+")) {
                            win++;
                        } else {
                            lose++;
                        }
                    }
                } else {
                    GoMove goMove = goMoveMap.get(goInfo.getId());
                    if (!goMove.getMove().equalsIgnoreCase("[]")) {
                        valid++;
                    }
                }
            }
            output.add(String.format("%s,%s,%s,%s,%s", userProfileMap.get(studentId).getDisplayName(), playedGame.size(),
                    valid, win, lose));
        }
        return output;
    }
}
