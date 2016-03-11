package org.zhangmz.pickles.helper.vo;

import java.util.Date;

import org.zhangmz.pickles.orm.model.Enduser;

/**
 * 
 * @ClassName:Enduser.java
 * @Description:
 * @author:张孟志
 * @date:2016年3月11日下午6:46:49
 * @version V1.0
 * 说明：终端用户对象的核心信息，用于终端显示
 */
public class EnduserElement {
	
    private Long id;
    private String phone;
    private String name;
    private Date registerDate;
    
    public EnduserElement() {
		super();
	}
    
	public EnduserElement(Enduser enduser) {
		super();
		this.id = enduser.getId();
		this.phone = enduser.getPhone();
		this.name = enduser.getName();
		this.registerDate = enduser.getRegisterDate();
	}
	
	public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getRegisterDate() {
        return registerDate;
    }
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}