package com.shoutanwq.data.learning.shell;

import com.shoutanwq.data.learning.dao.ICourseDAO;
import com.shoutanwq.data.learning.dao.ILessonDAO;
import com.shoutanwq.data.learning.models.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Comparator;
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

    @ShellMethod("获取所有课程信息")
    public List<String> courses() {
        return courseDAO.findAll().stream().map(course -> course.getId()).map(id -> String.format("%s 有 %s 节课",
                id, lessonDAO.countAllByCourse(id))).collect(Collectors.toList());
    }

    @ShellMethod("获取课目信息")
    public List<String> lessons(String courseId) {
        List<Lesson> allByCourse = lessonDAO.findAllByCourse(courseId);
        return allByCourse.stream().sorted(Comparator.comparingInt(Lesson::getOrder)).map(lesson -> String.format("%s" +
                        "\t%s\t%s" +
                        "\t%s\t%s", lesson.getId(), lesson.getTitle(), lesson.getDescription(), lesson.getOrder(),
                lesson.getVideo())).collect(Collectors.toList());
    }
}
