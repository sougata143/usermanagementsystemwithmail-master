package com.easybusiness.usermanagement.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.easybusiness.usermanagement.DTO.UserDTO;
import com.easybusiness.usermanagement.entity.User;
import com.easybusiness.usermanagement.repository.UserRepository;

/*
 * DAO class for USER_DETAILS table
 */

@Component
public class UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);
    @Autowired
    DataSource dataSource;

    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> findAll() throws Exception {
	LOGGER.info("DATASOURCE = " + dataSource);
	List<User> userList = new ArrayList<User>();
	for (User user : userRepository.findAll()) {
	    userList.add(user);

	}
	return userList;

    }
    
    @Transactional
    public List<User> findAllOrdered(String userName) throws Exception{
    	List<User> userList = new ArrayList<User>();
    	for(User user : userRepository.getAllUserGroupByUName(userName)) {
    		userList.add(user);
    	}
    	
    	return userList;
    }

    @Transactional(readOnly = true)
    public Optional<User> findByUserName(String userName) {
    	System.out.println(LOGGER.isTraceEnabled());
	return userRepository.findByUserNameReturnStream(userName).findFirst();
    }

    @Transactional(readOnly = true)
    public User findUserById(Long id) {
	return (User) userRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<User> findByUserNameStream(String userName) {
	/*try (Stream<User> stream = userRepository.findByUserNameReturnStream(userName)) {
	    stream.forEach(x -> {
		LOGGER.info("User : " + x);
	    });
	}*/
    	
    	return userRepository.findByUserNameCustom(userName);

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(User user) {
	userRepository.addUser(user);
	LOGGER.info("User added successfully " + user.toString());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(Long userId) {
	userRepository.deleteUser(userId);
	LOGGER.info("User with id " + userId + " deleted successfully ");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(User user) {
	userRepository.updateUser(user);
	LOGGER.info("User added successfully " + user.toString());
    }
    
    @Transactional(readOnly = true)
    public int findCountOfUserName(String userName) {
	return userRepository.countOfUserNames(userName);
    }
    
    @Transactional
    public void save(User user) {
    	userRepository.save(user);
    }
    
    @Transactional
    public void saveEntity(User user) {
    	userRepository.save(user);
    }
    
    @Transactional
    public void storedProc(Long long1, Long inLocationId) {
    	userRepository.userCreateProc(long1, inLocationId);
    }
    
    
    
    

}
