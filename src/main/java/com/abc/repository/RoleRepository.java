package com.abc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {

}
