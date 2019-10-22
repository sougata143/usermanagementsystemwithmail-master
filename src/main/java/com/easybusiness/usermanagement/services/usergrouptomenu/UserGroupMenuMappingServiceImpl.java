package com.easybusiness.usermanagement.services.usergrouptomenu;

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

import com.easybusiness.usermanagement.DTO.MenuDTO;
import com.easybusiness.usermanagement.DTO.UserGroupDTO;
import com.easybusiness.usermanagement.DTO.UserGroupMenuDTO;
import com.easybusiness.usermanagement.dao.MenuDao;
import com.easybusiness.usermanagement.dao.UserGroupDao;
import com.easybusiness.usermanagement.dao.UserGroupMenuDao;
import com.easybusiness.usermanagement.entity.Menu;
import com.easybusiness.usermanagement.entity.UserGroup;
import com.easybusiness.usermanagement.entity.UserGroupMenu;


/*
 * Service and RestController class for UserGroupMenuMapping
 */
@RestController
@RequestMapping("/easybusiness/usergroupmenu/")
public class UserGroupMenuMappingServiceImpl implements UserGroupMenuMappingService {

    @Autowired
    UserGroupMenuDao userGroupMenuDao;

    @Autowired
    UserGroupDao userGroupDao;

    @Autowired
    MenuDao menuDao;

    
    /*
     * (non-Javadoc)
     * @see com.easybusiness.usermanagement.services.usergrouptomenu.UserGroupMenuMappingService#getUserGroupMenuBymappingId(java.lang.Long)
     * fetching usergroup menu mapping by mapping id
     * GET method for USER_GRP_MENU_MASTER table with param mappingid
     */
    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getUserGroupMenuByMappingId/{mappingid}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UserGroupMenuDTO> getUserGroupMenuBymappingId(@PathVariable("mappingid") Long mappingId) {

	UserGroupMenu userGroupMenuEntity = userGroupMenuDao.findUserGroupMenuById(mappingId).get();
	UserGroupMenuDTO userGroupMenuDto = prepareUserGroupMenuDTO(userGroupMenuEntity);
	return new ResponseEntity<UserGroupMenuDTO>(userGroupMenuDto, HttpStatus.FOUND);

    }

    
    /*
     * (non-Javadoc)
     * @see com.easybusiness.usermanagement.services.usergrouptomenu.UserGroupMenuMappingService#getUserGroupMenuByGroupId(java.lang.Long)
     * fetching usergroup menu mapping by groupid
     * GET method for USER_GRP_MENU_MASTER table with param groupid
     */
    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getUserGroupMenuByGroupId/{groupid}", method = RequestMethod.GET)
    @ResponseBody
    public List<UserGroupMenuDTO> getUserGroupMenuByGroupId(@PathVariable("groupid") Long groupId) {

	UserGroup userGroupEntity = userGroupDao.findUserGroupById(groupId).get();
	List<UserGroupMenu> userGroupMenuEntityList = userGroupMenuDao.findUserGroupMenuByUserGroup(userGroupEntity);
	List<UserGroupMenuDTO> userGroupMenuList = new ArrayList<UserGroupMenuDTO>();
	userGroupMenuEntityList.forEach(userGroupMenuEntity -> {
	    UserGroupMenuDTO userGroupMenuDTO = new UserGroupMenuDTO();
	    userGroupMenuDTO = prepareUserGroupMenuDTO(userGroupMenuEntity);
	    userGroupMenuList.add(userGroupMenuDTO);

	});
	return userGroupMenuList;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.easybusiness.usermanagement.services.usergrouptomenu.UserGroupMenuMappingService#getUserGroupMenuByGroupName(java.lang.String)
     * fetching usergroup menu mapping by groupname
     * GET method for USER_GRP_MENU_MASTER table with param groupname
     */
    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getUserGroupMenuByGroupName/{groupname}", method = RequestMethod.GET)
    @ResponseBody
    public List<UserGroupMenuDTO> getUserGroupMenuByGroupName(@PathVariable("groupname") String groupName) {

	UserGroup userGroupEntity = userGroupDao.findByUserGroupName(groupName);
	List<UserGroupMenu> userGroupMenuEntityList = userGroupMenuDao.findUserGroupMenuByUserGroup(userGroupEntity);
	List<UserGroupMenuDTO> userGroupMenuList = new ArrayList<UserGroupMenuDTO>();
	userGroupMenuEntityList.forEach(userGroupMenuEntity -> {
	    UserGroupMenuDTO userGroupMenuDTO = new UserGroupMenuDTO();
	    userGroupMenuDTO = prepareUserGroupMenuDTO(userGroupMenuEntity);
	    userGroupMenuList.add(userGroupMenuDTO);

	});
	return userGroupMenuList;
    }


    /*
     * (non-Javadoc)
     * @see com.easybusiness.usermanagement.services.usergrouptomenu.UserGroupMenuMappingService#getUserGroupMenuByMenuId(java.lang.Long)
     * fetching usergroup menu mapping by menuid
     * GET method for USER_GRP_MENU_MASTER table with param menuid
     */
    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getUserGroupMenuByMenuId/{menuid}", method = RequestMethod.GET)
    @ResponseBody
    public List<UserGroupMenuDTO> getUserGroupMenuByMenuId(@PathVariable("menuid") Long menuId) {

	Menu menuEntity = menuDao.findMenuById(menuId).get();
	List<UserGroupMenu> userGroupMenuEntityList = userGroupMenuDao.findUserGroupMenuByMenu(menuEntity);
	List<UserGroupMenuDTO> userGroupMenuList = new ArrayList<UserGroupMenuDTO>();
	userGroupMenuEntityList.forEach(userGroupMenuEntity -> {
	    UserGroupMenuDTO userGroupMenuDTO = new UserGroupMenuDTO();
	    userGroupMenuDTO = prepareUserGroupMenuDTO(userGroupMenuEntity);
	    userGroupMenuList.add(userGroupMenuDTO);

	});
	return userGroupMenuList;
    }

    
    /*
     * (non-Javadoc)
     * @see com.easybusiness.usermanagement.services.usergrouptomenu.UserGroupMenuMappingService#persistUserGroupMenu(com.easybusiness.usermanagement.DTO.UserGroupMenuDTO)
     * mapping usergroup to menu
     * POST method for USER_GRP_MENU_MASTER table with request body UserGroupMenuDTO
     */
    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "mapUserGroupMenu", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UserGroupMenuDTO> persistUserGroupMenu(@RequestBody UserGroupMenuDTO userGroupMenuDTO) {

	UserGroupMenu userGroupMenu = new UserGroupMenu();
	userGroupMenu.setFromDate(userGroupMenuDTO.getFromDate());
	userGroupMenu.setIsEnable(userGroupMenuDTO.getIsEnable());
	Menu menu = menuDao.findMenuById(userGroupMenuDTO.getMenuItem().getId()).get();
	userGroupMenu.setMenuItem(menu);
	userGroupMenu.setModifiedBy(userGroupMenuDTO.getModifiedBy());
	userGroupMenu.setModifiedOn(userGroupMenuDTO.getModifiedOn());
	userGroupMenu.setToDate(userGroupMenuDTO.getToDate());
	UserGroup userGroup = userGroupDao.findUserGroupById(userGroupMenuDTO.getUserGroup().getId()).get();
	userGroupMenu.setUserGroup(userGroup);

	userGroupMenuDao.addUserGroupMenu(userGroupMenu);
	return new ResponseEntity<UserGroupMenuDTO>(userGroupMenuDTO, HttpStatus.CREATED);

    }

    
    /*
     * (non-Javadoc)
     * @see com.easybusiness.usermanagement.services.usergrouptomenu.UserGroupMenuMappingService#destroyUserGroupMenu(java.lang.Long)
     * deleting usergroup to menu mapping by mapping id
     * PUT method for USER_GRP_MENU_MASTER table with param mapping id
     * this method does not delete data from the database rather it disables an entry by making isEnable field 0
     */
    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "deleteUserGroupMenuItem/{mappingid}", method = RequestMethod.PUT)
    @ResponseBody
    public void destroyUserGroupMenu(@PathVariable("mappingid") Long mappingId) {

	userGroupMenuDao.deleteUserGroupMenu(mappingId);
    }

    @Override
    public List<UserGroupMenuDTO> getFieldEq(Class<UserGroupMenuDTO> type, String propertyName, Object value,
	    int offset, int size) {
	return null;
    }

    private UserGroupMenuDTO prepareUserGroupMenuDTO(UserGroupMenu userGroupMenuEntity) {
	UserGroupMenuDTO userGroupMenuDto = new UserGroupMenuDTO();
	userGroupMenuDto.setFromDate(userGroupMenuEntity.getFromDate());
	userGroupMenuDto.setId(userGroupMenuEntity.getId());
	userGroupMenuDto.setIsEnable(userGroupMenuEntity.getIsEnable());
	MenuDTO menuDto = prepareMenuDTO(userGroupMenuEntity);
	userGroupMenuDto.setMenuItem(menuDto);
	userGroupMenuDto.setModifiedBy(userGroupMenuEntity.getModifiedBy());
	userGroupMenuDto.setModifiedOn(userGroupMenuEntity.getModifiedOn());
	userGroupMenuDto.setToDate(userGroupMenuEntity.getToDate());
	userGroupMenuDto.setUserGroup(prepareUserGroupDTO(userGroupMenuEntity.getUserGroup()));
	return userGroupMenuDto;
    }

    private MenuDTO prepareMenuDTO(UserGroupMenu userGroupMenuEntity) {
	MenuDTO menuDto = new MenuDTO();
	menuDto.setId(userGroupMenuEntity.getMenuItem().getId());
	menuDto.setMenuName(userGroupMenuEntity.getMenuItem().getMenuName());
	menuDto.setModifiedBy(userGroupMenuEntity.getMenuItem().getModifiedBy());
	menuDto.setModifiedTime(userGroupMenuEntity.getMenuItem().getModifiedTime());
	menuDto.setMenuIconName(userGroupMenuEntity.getMenuItem().getMenuIconName());
	return menuDto;
    }

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

}
