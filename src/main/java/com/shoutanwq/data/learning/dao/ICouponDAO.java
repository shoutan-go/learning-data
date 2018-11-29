package com.shoutanwq.data.learning.dao;

import com.shoutanwq.data.learning.models.Clazz;
import com.shoutanwq.data.learning.models.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ICouponDAO extends JpaRepository<Coupon, UUID> {
}
