package com.xhtutor.tutor.bean;

import java.util.Date;

public class Order {
    private Integer id;

    private String code;

    private Integer subjectId;

    private Integer gradeId;

    private String contactName;

    private String phone;

    private Integer distrId;

    private String detailAddr;

    private String message;

    private String price;

    private Integer status;

    private Integer stuId;

    private Date updateTime;

    private Integer cityId;
    
    private Subject subject;
    
    private Grade grade;
    
    private District district;
    
    private City city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getDistrId() {
        return distrId;
    }

    public void setDistrId(Integer distrId) {
        this.distrId = distrId;
    }

    public String getDetailAddr() {
        return detailAddr;
    }

    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr == null ? null : detailAddr.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", code=" + code + ", subjectId=" + subjectId + ", gradeId=" + gradeId
				+ ", contactName=" + contactName + ", phone=" + phone + ", distrId=" + distrId + ", detailAddr="
				+ detailAddr + ", message=" + message + ", price=" + price + ", status=" + status + ", stuId=" + stuId
				+ ", updateTime=" + updateTime + ", cityId=" + cityId + ", subject=" + subject + ", grade=" + grade
				+ ", district=" + district + ", city=" + city + "]";
	}
    
}