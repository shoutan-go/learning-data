package com.shoutanwq.data.learning.dao;

import com.shoutanwq.data.learning.models.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClazzDAO extends JpaRepository<Clazz, String> {

}
