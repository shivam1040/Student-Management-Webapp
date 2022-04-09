package com.sts.repository;

import org.springframework.stereotype.Repository;

import com.sts.entity.User;

@Repository
public interface UserRepository extends BaseRepository<User, Integer> {

}
