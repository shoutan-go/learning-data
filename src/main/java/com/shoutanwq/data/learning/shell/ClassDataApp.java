package com.shoutanwq.data.learning.shell;

import com.shoutanwq.data.learning.dao.*;
import com.shoutanwq.data.learning.models.GoInfo;
import com.shoutanwq.data.learning.models.GoMove;
import com.shoutanwq.data.learning.models.Order;
import com.shoutanwq.data.learning.models.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author jingsi.liu
 * @Date 2018/12/2
 */
@ShellComponent
public class ClassDataApp {

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
