package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	public User findByUsername(String username);
}
