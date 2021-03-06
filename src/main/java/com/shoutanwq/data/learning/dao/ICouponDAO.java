package com.shoutanwq.data.learning.dao;

import com.shoutanwq.data.learning.models.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICouponDAO extends JpaRepository<Coupon, String> {
}
