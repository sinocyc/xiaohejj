package com.xhtutor.tutor.bean;

import java.util.Date;
import java.util.List;

public class Tutor {
    private Integer id;

    private String userName;

    private String password;

    private String photo;

    private Integer status;

    private Date lastLogin;

    private String realName;

    private String gender;

    private String phone;

    private String email;

    private String qq;

    private Integer currDistrId;

    private String currAddr;

    private Integer birthCityId;

    private String idCardNum;

    private String major;

    private Integer tutorType;

    private Integer univId;

    private String intro;

    private String idCardPic;

    private String stuCertPic;

    private String teachCertPic;

    private String payAccount;

    private String university;

    private Integer teachCityId;
    
    private List<Subject> subjectList;
    
    private List<Grade> gradeList;
    
    private List<District> distrList;
    
    private List<Mode> modeList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public Integer getCurrDistrId() {
        return currDistrId;
    }

    public void setCurrDistrId(Integer currDistrId) {
        this.currDistrId = currDistrId;
    }

    public String getCurrAddr() {
        return currAddr;
    }

    public void setCurrAddr(String currAddr) {
        this.currAddr = currAddr == null ? null : currAddr.trim();
    }

    public Integer getBirthCityId() {
        return birthCityId;
    }

    public void setBirthCityId(Integer birthCityId) {
        this.birthCityId = birthCityId;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum == null ? null : idCardNum.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public Integer getTutorType() {
        return tutorType;
    }

    public void setTutorType(Integer tutorType) {
        this.tutorType = tutorType;
    }

    public Integer getUnivId() {
        return univId;
    }

    public void setUnivId(Integer univId) {
        this.univId = univId;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public String getIdCardPic() {
        return idCardPic;
    }

    public void setIdCardPic(String idCardPic) {
        this.idCardPic = idCardPic == null ? null : idCardPic.trim();
    }

    public String getStuCertPic() {
        return stuCertPic;
    }

    public void setStuCertPic(String stuCertPic) {
        this.stuCertPic = stuCertPic == null ? null : stuCertPic.trim();
    }

    public String getTeachCertPic() {
        return teachCertPic;
    }

    public void setTeachCertPic(String teachCertPic) {
        this.teachCertPic = teachCertPic == null ? null : teachCertPic.trim();
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount == null ? null : payAccount.trim();
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university == null ? null : university.trim();
    }

    public Integer getTeachCityId() {
        return teachCityId;
    }

    public void setTeachCityId(Integer teachCityId) {
        this.teachCityId = teachCityId;
    }

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public List<Grade> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}

	public List<District> getDistrList() {
		return distrList;
	}

	public void setDistrList(List<District> distrList) {
		this.distrList = distrList;
	}

	public List<Mode> getModeList() {
		return modeList;
	}

	public void setModeList(List<Mode> modeList) {
		this.modeList = modeList;
	}

	@Override
	public String toString() {
		return "Tutor [id=" + id + ", userName=" + userName + ", password=" + password + ", photo=" + photo
				+ ", status=" + status + ", lastLogin=" + lastLogin + ", realName=" + realName + ", gender=" + gender
				+ ", phone=" + phone + ", email=" + email + ", qq=" + qq + ", currDistrId=" + currDistrId
				+ ", currAddr=" + currAddr + ", birthCityId=" + birthCityId + ", idCardNum=" + idCardNum + ", major="
				+ major + ", tutorType=" + tutorType + ", univId=" + univId + ", intro=" + intro + ", idCardPic="
				+ idCardPic + ", stuCertPic=" + stuCertPic + ", teachCertPic=" + teachCertPic + ", payAccount="
				+ payAccount + ", university=" + university + ", teachCityId=" + teachCityId + ", subjectList="
				+ subjectList + ", gradeList=" + gradeList + ", distrList=" + distrList + ", modeList=" + modeList
				+ "]";
	}
    
}