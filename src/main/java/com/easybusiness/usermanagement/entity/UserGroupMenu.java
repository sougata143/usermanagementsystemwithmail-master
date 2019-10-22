package com.easybusiness.usermanagement.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USER_GRP_MENU_MASTER")
public class UserGroupMenu implements Serializable {

    private static final long serialVersionUID = 536872931329532182L;

    @Id
    @Column(name = "MAPPING_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="USER_GRP_MENU_MASTER_SEQ")
    @SequenceGenerator(name="USER_GRP_MENU_MASTER_SEQ", sequenceName="USER_GRP_MENU_MASTER_SEQ")
    private Long id;

    @ManyToOne(cascade = { CascadeType.PERSIST })
    @JoinColumn(name = "MENU_ID")
    private Menu menuItem;

    @ManyToOne(cascade = { CascadeType.PERSIST })
    @JoinColumn(name = "USER_GRP_ID")
    private UserGroup userGroup;

    @Column(name = "IS_ENABLE")
    private Long isEnable;

    @Column(name = "FROM_DATE")
    private Date fromDate;

    @Column(name = "END_DATE")
    private Date toDate;

    @Column(name = "MOD_BY")
    private String modifiedBy;

    @Column(name = "MOD_ON")
    private Date modifiedOn;

    public UserGroupMenu() {
	super();
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public UserGroup getUserGroup() {
	return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
	this.userGroup= userGroup;
    }

   

    public Menu getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(Menu menuItem) {
        this.menuItem = menuItem;
    }

    public Long getIsEnable() {
	return isEnable;
    }

    public void setIsEnable(Long isEnable) {
	this.isEnable = isEnable;
    }

    public Date getFromDate() {
	return fromDate;
    }

    public void setFromDate(Date fromDate) {
	this.fromDate = fromDate;
    }

    public Date getToDate() {
	return toDate;
    }

    public void setToDate(Date toDate) {
	this.toDate = toDate;
    }

    public String getModifiedBy() {
	return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
	this.modifiedBy = modifiedBy;
    }

    public Date getModifiedOn() {
	return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
	this.modifiedOn = modifiedOn;
    }

    @Override
    public String toString() {
	return "UserMenu [id=" + id + ",  userGroup=" + userGroup + ", menu=" + menuItem + ", isEnable=" + isEnable
		+ ", fromDate=" + fromDate + ", toDate=" + toDate + ", modifiedBy=" + modifiedBy + ", modifiedOn="
		+ modifiedOn + "]";
    }

}
