package com.mmc.springbootdemo.dao;

import com.mmc.springbootdemo.model.JpaUser;
import org.springframework.data.repository.CrudRepository;

public interface JpaRepository extends CrudRepository<JpaUser, Integer> {
}
