package com.shoutanwq.data.learning.dao;

import com.shoutanwq.data.learning.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author jingsi.liu
 * @Date 2018/12/2
 */
@Repository
public interface IOrderDAO extends JpaRepository<Order, String> {

    List<Order> findAllByClazz(String id);
}
