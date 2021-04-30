package com.cos.myjpa.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository 내부적으로 IoC에 등록됨(생략가능) 			//메모리에 띄우기 위해 @Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	
}
