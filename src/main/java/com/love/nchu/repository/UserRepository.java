package com.love.nchu.repository;

import com.love.nchu.demain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {


    User findUserByUsername(String username);

    User findUserByEmail(String email);

    @Transactional
    @Modifying
    @Query("delete from User u where u.username=?1")
    int deleteUserByUsername(String username);

    @Query("select u.username from User u")
    List<String> getAllUsernmae();


    @Query("select u.username from User u where u.role='ordinary' and u.enabled= 1 ")
    List<String> getAllOrdinaryUsernmae();

}
