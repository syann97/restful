package com.github.syann97.restful.myrestfulservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.syann97.restful.myrestfulservice.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
