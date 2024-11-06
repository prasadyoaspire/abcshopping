package com.abc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

	Optional<User>  findByUsernameOrEmail(String username,String email);	
	
}
