package com.shoutanwq.data.learning.dao;

import com.shoutanwq.data.learning.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author jingsi.liu
 * @Date 2018/12/2
 */

@Repository
public interface ICourseDAO extends JpaRepository<Course, String> {
}
