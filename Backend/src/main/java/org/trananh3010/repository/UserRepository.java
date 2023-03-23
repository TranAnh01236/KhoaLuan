package org.trananh3010.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.trananh3010.model.User;

public interface UserRepository extends JpaRepository<User, String>{
	@Query(value = "select top 1 * from users where account = ?1", nativeQuery=true)
	User findByAccount(String account);
}
