package com.shoutanwq.data.learning.shell;

import com.shoutanwq.data.learning.dao.ICourseDAO;
import com.shoutanwq.data.learning.dao.ILessonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author jingsi.liu
 * @Date 2018/12/5
 */
@ShellComponent
public class CourseDataApp {
    @Autowired
    ICourseDAO courseDAO;

    @Autowired
    ILessonDAO lessonDAO;

    @ShellMethod("获取所有课程")
    public List<String> courses() {
        return courseDAO.findAll().stream().map(course -> course.getId()).collect(Collectors.toList());
    }

    @ShellMethod("获取课程信息")
    public String course(String id) {
        return String.format("有 %s 节课", lessonDAO.countAllByCourse(id));
    }
}
