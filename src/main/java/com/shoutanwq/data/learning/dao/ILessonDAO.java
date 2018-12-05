package com.shoutanwq.data.learning.dao;

import com.shoutanwq.data.learning.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author jingsi.liu
 * @Date 2018/12/2
 */
@Repository
public interface ILessonDAO extends JpaRepository<Lesson, String> {
    List<Lesson> findAllByCourse(String course);

    int countAllByCourse(String course);
}
