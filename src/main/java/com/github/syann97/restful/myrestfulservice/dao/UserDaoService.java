package com.github.syann97.restful.myrestfulservice.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.github.syann97.restful.myrestfulservice.bean.User;


public interface UserDaoService {
	public List<User> findAll();

	public User save (User user);

	public User findOne(int id);
}
