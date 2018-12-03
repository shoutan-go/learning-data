package com.shoutanwq.data.learning.dao;

import com.shoutanwq.data.learning.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author jingsi.liu
 * @Date 2018/12/2
 */
@Repository
public interface IUserProfileDAO extends JpaRepository<UserProfile, String> {

    List<UserProfile> findAllByUserIdIn(List<String> userIds);
}
