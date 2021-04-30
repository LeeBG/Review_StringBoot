package com.cos.myjpa.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long>{
	
//	1.namingQuery
//	User findByUsername(String username);
//	 작동 =>Select* from user where username=?1
//	findBy는 문법이고 뒤에 붙는 username
	
//	[예제] select * from user where username = ?1 and password = ?2 => 파라미터가 2개 필요함
	User findByUsernameAndPassword(String username, String password); 
//	너무 복잡한것은 namingquery 쓰지 않는것을 추천한다. (And, Or, findBy 정도만)
	
	//2.nativeQuery 
//	[예제] select * from user where username = ?1 and password = ?2 => 파라미터가 2개 필요함
	@Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2",nativeQuery = true)
	User mLogin(String username, String password);
	
	//3.동적쿼리 - QueryDSL
	//JSP(Select절 Where부분을 조건절을 문자열 변수를 통해서 바꿔줌)
}
