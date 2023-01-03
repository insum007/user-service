package com.test.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.user.entity._User;

@Repository
public interface UserRepository extends JpaRepository<_User, Long>{

	_User findByUserId(Long userId);

}
