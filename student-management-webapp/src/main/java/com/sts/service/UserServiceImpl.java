package com.sts.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sts.entity.User;
import com.sts.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl extends BaseRepositoryImpl<User, Integer> implements UserService{
	
	@Autowired
	private UserRepository userRepository;
}
