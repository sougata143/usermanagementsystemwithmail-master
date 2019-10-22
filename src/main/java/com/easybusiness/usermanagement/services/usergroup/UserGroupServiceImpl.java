package com.easybusiness.usermanagement.services.usergroup;

import static com.easybusiness.usermanagement.constant.UserManagementConstant.USER_HOST_SERVER;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.easybusiness.usermanagement.DTO.UserGroupDTO;
import com.easybusiness.usermanagement.dao.UserGroupDao;
import com.easybusiness.usermanagement.entity.UserGroup;


/*
 * Service and RestController class for USER_GROUP_MASTER table
 */
@RestController
@RequestMapping("/easybusiness/usergroup/")
public class UserGroupServiceImpl implements UserGroupService {

    @Autowired
    UserGroupDao userGroupDao;

    
    /*
     * (non-Javadoc)
     * @see com.easybusiness.usermanagement.services.usergroup.UserGroupService#getUserGroupByName(java.lang.String)
     * Fetching UserGroup by usergroupname
     * GET method for USER_GROUP_MASTER with param usergroupname
     */
    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getByUsergroupName/{usergroupname}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UserGroupDTO> getUserGroupByName(@PathVariable("usergroupname") String userGroupName) {
	UserGroup userEntity = userGroupDao.findByUserGroupName(userGroupName);
	return new ResponseEntity<UserGroupDTO>(prepareUserGroupDTO(userEntity), HttpStatus.OK);
    }

    
    /*
     * (non-Javadoc)
     * @see com.easybusiness.usermanagement.services.usergroup.UserGroupService#getUserGroupById(java.lang.Long)
     * Fetching UserGroup by usergroupid
     * GET method for USER_GROUP_MASTER with by usergroupid
     */
    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getByUsergroupId/{usergroupid}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UserGroupDTO> getUserGroupById(@PathVariable("usergroupid") Long userGroupId) {
	UserGroup userEntity = userGroupDao.findUserGroupById(userGroupId).get();
	return new ResponseEntity<UserGroupDTO>(prepareUserGroupDTO(userEntity), HttpStatus.OK);
    }

    /*
     * preparing UserGroupDTO from UserGroup entity
     */
    private UserGroupDTO prepareUserGroupDTO(UserGroup userGroupEntity) {
	UserGroupDTO userGroupDTO = new UserGroupDTO();
	userGroupDTO.setFromDate(userGroupEntity.getFromDate());
	userGroupDTO.setId(userGroupEntity.getId());
	userGroupDTO.setIsEnable(userGroupEntity.getIsEnable());
	userGroupDTO.setModifiedBy(userGroupEntity.getModifiedBy());
	userGroupDTO.setModifiedOn(userGroupEntity.getModifiedOn());
	userGroupDTO.setToDate(userGroupEntity.getToDate());
	userGroupDTO.setUserGroupName(userGroupEntity.getUserGroupName());
	return userGroupDTO;
    }

    
    /*
     * (non-Javadoc)
     * @see com.easybusiness.usermanagement.services.usergroup.UserGroupService#persistUserGroup(com.easybusiness.usermanagement.DTO.UserGroupDTO)
     * saving UserGroup to database
     * POST method for USER_GROUP_MASTER with UserGroupDTO request body
     */
    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "addUserGroup", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UserGroupDTO> persistUserGroup(@RequestBody UserGroupDTO userGroupDTO) {

	UserGroup userGroupEntity = new UserGroup();
	userGroupEntity.setFromDate(userGroupDTO.getFromDate());
	userGroupEntity.setIsEnable(userGroupDTO.getIsEnable());
	userGroupEntity.setModifiedBy(userGroupDTO.getModifiedBy());
	userGroupEntity.setModifiedOn(userGroupDTO.getModifiedOn());
	userGroupEntity.setToDate(userGroupDTO.getToDate());
	userGroupEntity.setUserGroupName(userGroupDTO.getUserGroupName());

	userGroupDao.addUserGroup(userGroupEntity);
	return new ResponseEntity<UserGroupDTO>(userGroupDTO, HttpStatus.CREATED);

    }
    
    
    /*
     * (non-Javadoc)
     * @see com.easybusiness.usermanagement.services.usergroup.UserGroupService#updateUserGroup(com.easybusiness.usermanagement.DTO.UserGroupDTO)
     * updating UserGroup 
     * POST method for USER_GROUP_MASTER with UserGroupDTO request body
     */
    @Override
	@CrossOrigin(origins = USER_HOST_SERVER)
	@RequestMapping(value= "updateUserGroup", method = RequestMethod.POST)
	@ResponseBody
	public void updateUserGroup(@RequestBody UserGroupDTO userGroupDto) {
		//UserGroup usergroupEntity = new UserGroup();
		UserGroup userGroupDtoEntity = userGroupDao.findUserGroupById(userGroupDto.getId()).get();
		
		userGroupDtoEntity.setFromDate(userGroupDto.getFromDate());
		userGroupDtoEntity.setIsEnable(userGroupDto.getIsEnable());
		userGroupDtoEntity.setModifiedBy(userGroupDto.getModifiedBy());
		userGroupDtoEntity.setModifiedOn(userGroupDto.getModifiedOn());
		userGroupDtoEntity.setToDate(userGroupDto.getToDate());
		userGroupDtoEntity.setUserGroupName(userGroupDto.getUserGroupName());
		
		userGroupDao.updateUserGroup(userGroupDtoEntity);
		 
		
	}

    
    /*
     * (non-Javadoc)
     * @see com.easybusiness.usermanagement.services.usergroup.UserGroupService#populateUserGroupList()
     * fetching all UserGroups
     * GET method for USER_GROUP_MASTER
     */
    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getAllUserGroup", method = RequestMethod.GET)
    @ResponseBody
    public List<UserGroupDTO> populateUserGroupList() throws Exception {
	List<UserGroup> userGroupList=userGroupDao.findAll();
	List<UserGroupDTO> userGroupDTOList=new ArrayList<UserGroupDTO>();
	userGroupList.forEach(userGroup->{
	    UserGroupDTO userGroupDTO=new UserGroupDTO();
	    userGroupDTO.setFromDate(userGroup.getFromDate());
	    userGroupDTO.setId(userGroup.getId());
	    userGroupDTO.setIsEnable(userGroup.getIsEnable());
	    userGroupDTO.setModifiedBy(userGroup.getModifiedBy());
	    userGroupDTO.setModifiedOn(userGroup.getModifiedOn());
	    userGroupDTO.setToDate(userGroup.getToDate());
	    userGroupDTO.setUserGroupName(userGroup.getUserGroupName());
	    userGroupDTOList.add(userGroupDTO);
	});
	return userGroupDTOList;
    }

    
    /*
     * (non-Javadoc)
     * @see com.easybusiness.usermanagement.services.usergroup.UserGroupService#destroyUserGroup(java.lang.Long)
     * deleting UserGroup by userGroupId
     * DELETE method for USER_GROUP_MASTER with param userGroupId
     */
    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "deleteUserGroup/{userGroupId}", method = RequestMethod.DELETE)
    @ResponseBody
    public void destroyUserGroup(@PathVariable("userGroupId") Long userGroupId) {

	userGroupDao.deleteUserGroup(userGroupId);
    }

    @Override
    public List<UserGroupDTO> getFieldEq(Class<UserGroupDTO> type, String propertyName, Object value, int offset,
	    int size) {
	return null;
    }

}
