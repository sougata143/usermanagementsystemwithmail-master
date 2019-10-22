package com.easybusiness.usermanagement.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.easybusiness.usermanagement.DTO.UserDTO;
import com.easybusiness.usermanagement.entity.User;

import com.easybusiness.usermanagement.repository.CustomRepository.UserCustomRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {
	
	
    Optional<User> findById(Long i);

    List<User> findByUserName(String userName);

    // custom query example and return a stream
    @Modifying
    @Query("select u from User u where u.userName = :userName")
    Stream<User> findByUserNameReturnStream(@Param("userName") String userName);
    
    @Query("select u from User u where u.userName = :userName")
    List<User> findByUserNameCustom(@Param("userName") String userName);
    
    @Modifying
    void updateUser(User user);

	UserDTO save(UserDTO user);
	
	
	
	
    
    

}
