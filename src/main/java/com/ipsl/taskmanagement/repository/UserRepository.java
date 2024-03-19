package com.ipsl.taskmanagement.repository;

import com.ipsl.taskmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT us FROM User us WHERE us.username = :username and us.password =:password")
    User findByUsernameAndPassword(String username, String password);
}
