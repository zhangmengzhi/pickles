package org.zhangmz.pickles.orm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.zhangmz.pickles.modules.utils.DiscuzHashPassword;
import org.zhangmz.pickles.modules.vo.HashPasswordResult;

@Table(name = "endusers")
public class Enduser {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column endusers.id
     *
     * @mbggenerated Fri Mar 11 09:18:47 CST 2016
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column endusers.phone
     *
     * @mbggenerated Fri Mar 11 09:18:47 CST 2016
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column endusers.name
     *
     * @mbggenerated Fri Mar 11 09:18:47 CST 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column endusers.hash_password
     *
     * @mbggenerated Fri Mar 11 09:18:47 CST 2016
     */
    private String hashPassword;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column endusers.salt
     *
     * @mbggenerated Fri Mar 11 09:18:47 CST 2016
     */
    private String salt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column endusers.register_date
     *
     * @mbggenerated Fri Mar 11 09:18:47 CST 2016
     */
    private Date registerDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column endusers.group_id
     *
     * @mbggenerated Fri Mar 11 09:18:47 CST 2016
     */
    private Integer groupId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column endusers.status
     *
     * @mbggenerated Fri Mar 11 09:18:47 CST 2016
     */
    private String status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column endusers.id
     *
     * @return the value of endusers.id
     *
     * @mbggenerated Fri Mar 11 09:18:47 CST 2016
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column endusers.id
     *
     * @param id the value for endusers.id
     *
     * @mbggenerated Fri Mar 11 09:18:47 CST 2016
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column endusers.phone
     *
     * @return the value of endusers.phone
     *
     * @mbggenerated Fri Mar 11 09:18:47 CST 2016
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column endusers.phone
     *
     * @param phone the value for endusers.phone
     *
     * @mbggenerated Fri Mar 11 09:18:47 CST 2016
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column endusers.name
     *
     * @return the value of endusers.name
     *
     * @mbggenerated Fri Mar 11 09:18:47 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column endusers.name
     *
     * @param name the value for endusers.name
     *
     * @mbggenerated Fri Mar 11 09:18:47 CST 2016
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column endusers.hash_password
     *
     * @return the value of endusers.hash_password
     *
     * @mbggenerated Fri Mar 11 09:18:47 CST 2016
     */
    public String getHashPassword() {
        return hashPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column endusers.hash_password
     *
     * @param hashPassword the value for endusers.hash_password
     *
     * @mbggenerated Fri Mar 11 09:18:47 CST 2016
     */
    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column endusers.salt
     *
     * @return the value of endusers.salt
     *
     * @mbggenerated Fri Mar 11 09:18:47 CST 2016
     */
    public String getSalt() {
        return salt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column endusers.salt
     *
     * @param salt the value for endusers.salt
     *
     * @mbggenerated Fri Mar 11 09:18:47 CST 2016
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column endusers.register_date
     *
     * @return the value of endusers.register_date
     *
     * @mbggenerated Fri Mar 11 09:18:47 CST 2016
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column endusers.register_date
     *
     * @param registerDate the value for endusers.register_date
     *
     * @mbggenerated Fri Mar 11 09:18:47 CST 2016
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column endusers.group_id
     *
     * @return the value of endusers.group_id
     *
     * @mbggenerated Fri Mar 11 09:18:47 CST 2016
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column endusers.group_id
     *
     * @param groupId the value for endusers.group_id
     *
     * @mbggenerated Fri Mar 11 09:18:47 CST 2016
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column endusers.status
     *
     * @return the value of endusers.status
     *
     * @mbggenerated Fri Mar 11 09:18:47 CST 2016
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column endusers.status
     *
     * @param status the value for endusers.status
     *
     * @mbggenerated Fri Mar 11 09:18:47 CST 2016
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /*************************************************************************
 	 * 说明：增加分页查询
 	 * 作者：张孟志
 	 * 日期：2016-03-11
 	 ************************************************************************/
    @Transient
    private Integer page = 1;

    @Transient
    private Integer rows = 10;

	public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
    
    /**
     * 接收页面的password
     * 与数据库的hash_password存在映射关系
     */
    @Transient
    private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		
		// 在这里设置hash_password成员变量
		// 设置为Discuz加密方式，为数据迁移做准备
		// 如果是Md5加密后的字符串，第二个参数为true
		// HashPasswordResult result = DiscuzHashPassword.getHashPasswordResult(password, true);
		HashPasswordResult result = DiscuzHashPassword.getHashPasswordResult(password);
		this.hashPassword = result.getHashPassword();
		this.salt = result.getSalt();
	}
}