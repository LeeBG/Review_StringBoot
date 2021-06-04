package com.cos.jwtex1.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);	//나중에 UserDetails에서 써야함.
}
