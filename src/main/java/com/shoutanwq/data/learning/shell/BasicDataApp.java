package com.shoutanwq.data.learning.shell;

import com.shoutanwq.data.learning.dao.*;
import com.shoutanwq.data.learning.models.Clazz;
import com.shoutanwq.data.learning.models.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Optional;


@ShellComponent
public class BasicDataApp {

    @Autowired
    IClazzDAO clazzDAO;

    @Autowired
    ICouponDAO couponDAO;

    @Autowired
    ICourseDAO courseDAO;

    @Autowired
    IGoInfoDAO goInfoDAO;

    @Autowired
    IGoMoveDAO goMoveDAO;

    @Autowired
    ILessonDAO lessonDAO;

    @Autowired
    IMatchGameDAO matchGameDAO;

    @Autowired
    IMatchInfoDAO matchInfoDAO;

    @Autowired
    IOrderDAO orderDAO;

    @Autowired
    IUserClaimDAO userClaimDAO;

    @Autowired
    IUserDAO userDAO;

    @Autowired
    IUserProfileDAO userProfileDAO;

    @ShellMethod("Get class information")
    public String clazz(String id) {
        Optional<Clazz> byId = clazzDAO.findById(id);
        return byId.get().toString();
    }

    @ShellMethod("Get coupon information")
    public String coupon(String id) {
        Optional<Coupon> byId = couponDAO.findById(id);
        return byId.get().toString();
    }

}
