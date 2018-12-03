package com.shoutanwq.data.learning.dao;

import com.shoutanwq.data.learning.models.GoInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author jingsi.liu
 * @Date 2018/12/2
 */
@Repository
public interface IGoInfoDAO extends JpaRepository<GoInfo, String> {

    List<GoInfo> findAllByBlackInOrWhiteIn(List<String> bids, List<String> wids);
}
