package com.shoutanwq.data.learning.shell;

import com.shoutanwq.data.learning.dao.IClazzDAO;
import com.shoutanwq.data.learning.dao.ICouponDAO;
import com.shoutanwq.data.learning.models.Clazz;
import com.shoutanwq.data.learning.models.Coupon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Optional;
import java.util.UUID;

@ShellComponent
public class DataApp {

    @Autowired
    IClazzDAO clazzDAO;

    @Autowired
    ICouponDAO couponDAO;

    @ShellMethod("Get class information")
    public String clazz(String classId){
        Optional<Clazz> byId = clazzDAO.findById(UUID.fromString(classId.trim()));
        return byId.get().toString();
    }
    @ShellMethod("Get coupon information")
    public String coupon(String couponId){
        Optional<Coupon> byId = couponDAO.findById(UUID.fromString(couponId.trim()));
        return byId.get().toString();
    }
}
