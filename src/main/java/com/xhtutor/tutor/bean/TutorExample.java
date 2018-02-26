package com.xhtutor.tutor.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TutorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TutorExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPhotoIsNull() {
            addCriterion("photo is null");
            return (Criteria) this;
        }

        public Criteria andPhotoIsNotNull() {
            addCriterion("photo is not null");
            return (Criteria) this;
        }

        public Criteria andPhotoEqualTo(String value) {
            addCriterion("photo =", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotEqualTo(String value) {
            addCriterion("photo <>", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoGreaterThan(String value) {
            addCriterion("photo >", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("photo >=", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLessThan(String value) {
            addCriterion("photo <", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLessThanOrEqualTo(String value) {
            addCriterion("photo <=", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLike(String value) {
            addCriterion("photo like", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotLike(String value) {
            addCriterion("photo not like", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoIn(List<String> values) {
            addCriterion("photo in", values, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotIn(List<String> values) {
            addCriterion("photo not in", values, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoBetween(String value1, String value2) {
            addCriterion("photo between", value1, value2, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotBetween(String value1, String value2) {
            addCriterion("photo not between", value1, value2, "photo");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andLastLoginIsNull() {
            addCriterion("last_login is null");
            return (Criteria) this;
        }

        public Criteria andLastLoginIsNotNull() {
            addCriterion("last_login is not null");
            return (Criteria) this;
        }

        public Criteria andLastLoginEqualTo(Date value) {
            addCriterion("last_login =", value, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginNotEqualTo(Date value) {
            addCriterion("last_login <>", value, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginGreaterThan(Date value) {
            addCriterion("last_login >", value, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginGreaterThanOrEqualTo(Date value) {
            addCriterion("last_login >=", value, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginLessThan(Date value) {
            addCriterion("last_login <", value, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginLessThanOrEqualTo(Date value) {
            addCriterion("last_login <=", value, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginIn(List<Date> values) {
            addCriterion("last_login in", values, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginNotIn(List<Date> values) {
            addCriterion("last_login not in", values, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginBetween(Date value1, Date value2) {
            addCriterion("last_login between", value1, value2, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginNotBetween(Date value1, Date value2) {
            addCriterion("last_login not between", value1, value2, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andRealNameIsNull() {
            addCriterion("real_name is null");
            return (Criteria) this;
        }

        public Criteria andRealNameIsNotNull() {
            addCriterion("real_name is not null");
            return (Criteria) this;
        }

        public Criteria andRealNameEqualTo(String value) {
            addCriterion("real_name =", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotEqualTo(String value) {
            addCriterion("real_name <>", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameGreaterThan(String value) {
            addCriterion("real_name >", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameGreaterThanOrEqualTo(String value) {
            addCriterion("real_name >=", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLessThan(String value) {
            addCriterion("real_name <", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLessThanOrEqualTo(String value) {
            addCriterion("real_name <=", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLike(String value) {
            addCriterion("real_name like", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotLike(String value) {
            addCriterion("real_name not like", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameIn(List<String> values) {
            addCriterion("real_name in", values, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotIn(List<String> values) {
            addCriterion("real_name not in", values, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameBetween(String value1, String value2) {
            addCriterion("real_name between", value1, value2, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotBetween(String value1, String value2) {
            addCriterion("real_name not between", value1, value2, "realName");
            return (Criteria) this;
        }

        public Criteria andGenderIsNull() {
            addCriterion("gender is null");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("gender is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(String value) {
            addCriterion("gender =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(String value) {
            addCriterion("gender <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(String value) {
            addCriterion("gender >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(String value) {
            addCriterion("gender >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(String value) {
            addCriterion("gender <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(String value) {
            addCriterion("gender <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLike(String value) {
            addCriterion("gender like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotLike(String value) {
            addCriterion("gender not like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<String> values) {
            addCriterion("gender in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<String> values) {
            addCriterion("gender not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(String value1, String value2) {
            addCriterion("gender between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(String value1, String value2) {
            addCriterion("gender not between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andQqIsNull() {
            addCriterion("qq is null");
            return (Criteria) this;
        }

        public Criteria andQqIsNotNull() {
            addCriterion("qq is not null");
            return (Criteria) this;
        }

        public Criteria andQqEqualTo(String value) {
            addCriterion("qq =", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotEqualTo(String value) {
            addCriterion("qq <>", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThan(String value) {
            addCriterion("qq >", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThanOrEqualTo(String value) {
            addCriterion("qq >=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThan(String value) {
            addCriterion("qq <", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThanOrEqualTo(String value) {
            addCriterion("qq <=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLike(String value) {
            addCriterion("qq like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotLike(String value) {
            addCriterion("qq not like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqIn(List<String> values) {
            addCriterion("qq in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotIn(List<String> values) {
            addCriterion("qq not in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqBetween(String value1, String value2) {
            addCriterion("qq between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotBetween(String value1, String value2) {
            addCriterion("qq not between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andCurrDistrIdIsNull() {
            addCriterion("curr_distr_id is null");
            return (Criteria) this;
        }

        public Criteria andCurrDistrIdIsNotNull() {
            addCriterion("curr_distr_id is not null");
            return (Criteria) this;
        }

        public Criteria andCurrDistrIdEqualTo(Integer value) {
            addCriterion("curr_distr_id =", value, "currDistrId");
            return (Criteria) this;
        }

        public Criteria andCurrDistrIdNotEqualTo(Integer value) {
            addCriterion("curr_distr_id <>", value, "currDistrId");
            return (Criteria) this;
        }

        public Criteria andCurrDistrIdGreaterThan(Integer value) {
            addCriterion("curr_distr_id >", value, "currDistrId");
            return (Criteria) this;
        }

        public Criteria andCurrDistrIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("curr_distr_id >=", value, "currDistrId");
            return (Criteria) this;
        }

        public Criteria andCurrDistrIdLessThan(Integer value) {
            addCriterion("curr_distr_id <", value, "currDistrId");
            return (Criteria) this;
        }

        public Criteria andCurrDistrIdLessThanOrEqualTo(Integer value) {
            addCriterion("curr_distr_id <=", value, "currDistrId");
            return (Criteria) this;
        }

        public Criteria andCurrDistrIdIn(List<Integer> values) {
            addCriterion("curr_distr_id in", values, "currDistrId");
            return (Criteria) this;
        }

        public Criteria andCurrDistrIdNotIn(List<Integer> values) {
            addCriterion("curr_distr_id not in", values, "currDistrId");
            return (Criteria) this;
        }

        public Criteria andCurrDistrIdBetween(Integer value1, Integer value2) {
            addCriterion("curr_distr_id between", value1, value2, "currDistrId");
            return (Criteria) this;
        }

        public Criteria andCurrDistrIdNotBetween(Integer value1, Integer value2) {
            addCriterion("curr_distr_id not between", value1, value2, "currDistrId");
            return (Criteria) this;
        }

        public Criteria andCurrAddrIsNull() {
            addCriterion("curr_addr is null");
            return (Criteria) this;
        }

        public Criteria andCurrAddrIsNotNull() {
            addCriterion("curr_addr is not null");
            return (Criteria) this;
        }

        public Criteria andCurrAddrEqualTo(String value) {
            addCriterion("curr_addr =", value, "currAddr");
            return (Criteria) this;
        }

        public Criteria andCurrAddrNotEqualTo(String value) {
            addCriterion("curr_addr <>", value, "currAddr");
            return (Criteria) this;
        }

        public Criteria andCurrAddrGreaterThan(String value) {
            addCriterion("curr_addr >", value, "currAddr");
            return (Criteria) this;
        }

        public Criteria andCurrAddrGreaterThanOrEqualTo(String value) {
            addCriterion("curr_addr >=", value, "currAddr");
            return (Criteria) this;
        }

        public Criteria andCurrAddrLessThan(String value) {
            addCriterion("curr_addr <", value, "currAddr");
            return (Criteria) this;
        }

        public Criteria andCurrAddrLessThanOrEqualTo(String value) {
            addCriterion("curr_addr <=", value, "currAddr");
            return (Criteria) this;
        }

        public Criteria andCurrAddrLike(String value) {
            addCriterion("curr_addr like", value, "currAddr");
            return (Criteria) this;
        }

        public Criteria andCurrAddrNotLike(String value) {
            addCriterion("curr_addr not like", value, "currAddr");
            return (Criteria) this;
        }

        public Criteria andCurrAddrIn(List<String> values) {
            addCriterion("curr_addr in", values, "currAddr");
            return (Criteria) this;
        }

        public Criteria andCurrAddrNotIn(List<String> values) {
            addCriterion("curr_addr not in", values, "currAddr");
            return (Criteria) this;
        }

        public Criteria andCurrAddrBetween(String value1, String value2) {
            addCriterion("curr_addr between", value1, value2, "currAddr");
            return (Criteria) this;
        }

        public Criteria andCurrAddrNotBetween(String value1, String value2) {
            addCriterion("curr_addr not between", value1, value2, "currAddr");
            return (Criteria) this;
        }

        public Criteria andBirthCityIdIsNull() {
            addCriterion("birth_city_id is null");
            return (Criteria) this;
        }

        public Criteria andBirthCityIdIsNotNull() {
            addCriterion("birth_city_id is not null");
            return (Criteria) this;
        }

        public Criteria andBirthCityIdEqualTo(Integer value) {
            addCriterion("birth_city_id =", value, "birthCityId");
            return (Criteria) this;
        }

        public Criteria andBirthCityIdNotEqualTo(Integer value) {
            addCriterion("birth_city_id <>", value, "birthCityId");
            return (Criteria) this;
        }

        public Criteria andBirthCityIdGreaterThan(Integer value) {
            addCriterion("birth_city_id >", value, "birthCityId");
            return (Criteria) this;
        }

        public Criteria andBirthCityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("birth_city_id >=", value, "birthCityId");
            return (Criteria) this;
        }

        public Criteria andBirthCityIdLessThan(Integer value) {
            addCriterion("birth_city_id <", value, "birthCityId");
            return (Criteria) this;
        }

        public Criteria andBirthCityIdLessThanOrEqualTo(Integer value) {
            addCriterion("birth_city_id <=", value, "birthCityId");
            return (Criteria) this;
        }

        public Criteria andBirthCityIdIn(List<Integer> values) {
            addCriterion("birth_city_id in", values, "birthCityId");
            return (Criteria) this;
        }

        public Criteria andBirthCityIdNotIn(List<Integer> values) {
            addCriterion("birth_city_id not in", values, "birthCityId");
            return (Criteria) this;
        }

        public Criteria andBirthCityIdBetween(Integer value1, Integer value2) {
            addCriterion("birth_city_id between", value1, value2, "birthCityId");
            return (Criteria) this;
        }

        public Criteria andBirthCityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("birth_city_id not between", value1, value2, "birthCityId");
            return (Criteria) this;
        }

        public Criteria andIdCardNumIsNull() {
            addCriterion("id_card_num is null");
            return (Criteria) this;
        }

        public Criteria andIdCardNumIsNotNull() {
            addCriterion("id_card_num is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardNumEqualTo(String value) {
            addCriterion("id_card_num =", value, "idCardNum");
            return (Criteria) this;
        }

        public Criteria andIdCardNumNotEqualTo(String value) {
            addCriterion("id_card_num <>", value, "idCardNum");
            return (Criteria) this;
        }

        public Criteria andIdCardNumGreaterThan(String value) {
            addCriterion("id_card_num >", value, "idCardNum");
            return (Criteria) this;
        }

        public Criteria andIdCardNumGreaterThanOrEqualTo(String value) {
            addCriterion("id_card_num >=", value, "idCardNum");
            return (Criteria) this;
        }

        public Criteria andIdCardNumLessThan(String value) {
            addCriterion("id_card_num <", value, "idCardNum");
            return (Criteria) this;
        }

        public Criteria andIdCardNumLessThanOrEqualTo(String value) {
            addCriterion("id_card_num <=", value, "idCardNum");
            return (Criteria) this;
        }

        public Criteria andIdCardNumLike(String value) {
            addCriterion("id_card_num like", value, "idCardNum");
            return (Criteria) this;
        }

        public Criteria andIdCardNumNotLike(String value) {
            addCriterion("id_card_num not like", value, "idCardNum");
            return (Criteria) this;
        }

        public Criteria andIdCardNumIn(List<String> values) {
            addCriterion("id_card_num in", values, "idCardNum");
            return (Criteria) this;
        }

        public Criteria andIdCardNumNotIn(List<String> values) {
            addCriterion("id_card_num not in", values, "idCardNum");
            return (Criteria) this;
        }

        public Criteria andIdCardNumBetween(String value1, String value2) {
            addCriterion("id_card_num between", value1, value2, "idCardNum");
            return (Criteria) this;
        }

        public Criteria andIdCardNumNotBetween(String value1, String value2) {
            addCriterion("id_card_num not between", value1, value2, "idCardNum");
            return (Criteria) this;
        }

        public Criteria andMajorIsNull() {
            addCriterion("major is null");
            return (Criteria) this;
        }

        public Criteria andMajorIsNotNull() {
            addCriterion("major is not null");
            return (Criteria) this;
        }

        public Criteria andMajorEqualTo(String value) {
            addCriterion("major =", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorNotEqualTo(String value) {
            addCriterion("major <>", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorGreaterThan(String value) {
            addCriterion("major >", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorGreaterThanOrEqualTo(String value) {
            addCriterion("major >=", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorLessThan(String value) {
            addCriterion("major <", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorLessThanOrEqualTo(String value) {
            addCriterion("major <=", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorLike(String value) {
            addCriterion("major like", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorNotLike(String value) {
            addCriterion("major not like", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorIn(List<String> values) {
            addCriterion("major in", values, "major");
            return (Criteria) this;
        }

        public Criteria andMajorNotIn(List<String> values) {
            addCriterion("major not in", values, "major");
            return (Criteria) this;
        }

        public Criteria andMajorBetween(String value1, String value2) {
            addCriterion("major between", value1, value2, "major");
            return (Criteria) this;
        }

        public Criteria andMajorNotBetween(String value1, String value2) {
            addCriterion("major not between", value1, value2, "major");
            return (Criteria) this;
        }

        public Criteria andTutorTypeIsNull() {
            addCriterion("tutor_type is null");
            return (Criteria) this;
        }

        public Criteria andTutorTypeIsNotNull() {
            addCriterion("tutor_type is not null");
            return (Criteria) this;
        }

        public Criteria andTutorTypeEqualTo(Integer value) {
            addCriterion("tutor_type =", value, "tutorType");
            return (Criteria) this;
        }

        public Criteria andTutorTypeNotEqualTo(Integer value) {
            addCriterion("tutor_type <>", value, "tutorType");
            return (Criteria) this;
        }

        public Criteria andTutorTypeGreaterThan(Integer value) {
            addCriterion("tutor_type >", value, "tutorType");
            return (Criteria) this;
        }

        public Criteria andTutorTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("tutor_type >=", value, "tutorType");
            return (Criteria) this;
        }

        public Criteria andTutorTypeLessThan(Integer value) {
            addCriterion("tutor_type <", value, "tutorType");
            return (Criteria) this;
        }

        public Criteria andTutorTypeLessThanOrEqualTo(Integer value) {
            addCriterion("tutor_type <=", value, "tutorType");
            return (Criteria) this;
        }

        public Criteria andTutorTypeIn(List<Integer> values) {
            addCriterion("tutor_type in", values, "tutorType");
            return (Criteria) this;
        }

        public Criteria andTutorTypeNotIn(List<Integer> values) {
            addCriterion("tutor_type not in", values, "tutorType");
            return (Criteria) this;
        }

        public Criteria andTutorTypeBetween(Integer value1, Integer value2) {
            addCriterion("tutor_type between", value1, value2, "tutorType");
            return (Criteria) this;
        }

        public Criteria andTutorTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("tutor_type not between", value1, value2, "tutorType");
            return (Criteria) this;
        }

        public Criteria andUnivIdIsNull() {
            addCriterion("univ_id is null");
            return (Criteria) this;
        }

        public Criteria andUnivIdIsNotNull() {
            addCriterion("univ_id is not null");
            return (Criteria) this;
        }

        public Criteria andUnivIdEqualTo(Integer value) {
            addCriterion("univ_id =", value, "univId");
            return (Criteria) this;
        }

        public Criteria andUnivIdNotEqualTo(Integer value) {
            addCriterion("univ_id <>", value, "univId");
            return (Criteria) this;
        }

        public Criteria andUnivIdGreaterThan(Integer value) {
            addCriterion("univ_id >", value, "univId");
            return (Criteria) this;
        }

        public Criteria andUnivIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("univ_id >=", value, "univId");
            return (Criteria) this;
        }

        public Criteria andUnivIdLessThan(Integer value) {
            addCriterion("univ_id <", value, "univId");
            return (Criteria) this;
        }

        public Criteria andUnivIdLessThanOrEqualTo(Integer value) {
            addCriterion("univ_id <=", value, "univId");
            return (Criteria) this;
        }

        public Criteria andUnivIdIn(List<Integer> values) {
            addCriterion("univ_id in", values, "univId");
            return (Criteria) this;
        }

        public Criteria andUnivIdNotIn(List<Integer> values) {
            addCriterion("univ_id not in", values, "univId");
            return (Criteria) this;
        }

        public Criteria andUnivIdBetween(Integer value1, Integer value2) {
            addCriterion("univ_id between", value1, value2, "univId");
            return (Criteria) this;
        }

        public Criteria andUnivIdNotBetween(Integer value1, Integer value2) {
            addCriterion("univ_id not between", value1, value2, "univId");
            return (Criteria) this;
        }

        public Criteria andIntroIsNull() {
            addCriterion("intro is null");
            return (Criteria) this;
        }

        public Criteria andIntroIsNotNull() {
            addCriterion("intro is not null");
            return (Criteria) this;
        }

        public Criteria andIntroEqualTo(String value) {
            addCriterion("intro =", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotEqualTo(String value) {
            addCriterion("intro <>", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroGreaterThan(String value) {
            addCriterion("intro >", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroGreaterThanOrEqualTo(String value) {
            addCriterion("intro >=", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroLessThan(String value) {
            addCriterion("intro <", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroLessThanOrEqualTo(String value) {
            addCriterion("intro <=", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroLike(String value) {
            addCriterion("intro like", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotLike(String value) {
            addCriterion("intro not like", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroIn(List<String> values) {
            addCriterion("intro in", values, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotIn(List<String> values) {
            addCriterion("intro not in", values, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroBetween(String value1, String value2) {
            addCriterion("intro between", value1, value2, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotBetween(String value1, String value2) {
            addCriterion("intro not between", value1, value2, "intro");
            return (Criteria) this;
        }

        public Criteria andIdCardPicIsNull() {
            addCriterion("id_card_pic is null");
            return (Criteria) this;
        }

        public Criteria andIdCardPicIsNotNull() {
            addCriterion("id_card_pic is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardPicEqualTo(String value) {
            addCriterion("id_card_pic =", value, "idCardPic");
            return (Criteria) this;
        }

        public Criteria andIdCardPicNotEqualTo(String value) {
            addCriterion("id_card_pic <>", value, "idCardPic");
            return (Criteria) this;
        }

        public Criteria andIdCardPicGreaterThan(String value) {
            addCriterion("id_card_pic >", value, "idCardPic");
            return (Criteria) this;
        }

        public Criteria andIdCardPicGreaterThanOrEqualTo(String value) {
            addCriterion("id_card_pic >=", value, "idCardPic");
            return (Criteria) this;
        }

        public Criteria andIdCardPicLessThan(String value) {
            addCriterion("id_card_pic <", value, "idCardPic");
            return (Criteria) this;
        }

        public Criteria andIdCardPicLessThanOrEqualTo(String value) {
            addCriterion("id_card_pic <=", value, "idCardPic");
            return (Criteria) this;
        }

        public Criteria andIdCardPicLike(String value) {
            addCriterion("id_card_pic like", value, "idCardPic");
            return (Criteria) this;
        }

        public Criteria andIdCardPicNotLike(String value) {
            addCriterion("id_card_pic not like", value, "idCardPic");
            return (Criteria) this;
        }

        public Criteria andIdCardPicIn(List<String> values) {
            addCriterion("id_card_pic in", values, "idCardPic");
            return (Criteria) this;
        }

        public Criteria andIdCardPicNotIn(List<String> values) {
            addCriterion("id_card_pic not in", values, "idCardPic");
            return (Criteria) this;
        }

        public Criteria andIdCardPicBetween(String value1, String value2) {
            addCriterion("id_card_pic between", value1, value2, "idCardPic");
            return (Criteria) this;
        }

        public Criteria andIdCardPicNotBetween(String value1, String value2) {
            addCriterion("id_card_pic not between", value1, value2, "idCardPic");
            return (Criteria) this;
        }

        public Criteria andStuCertPicIsNull() {
            addCriterion("stu_cert_pic is null");
            return (Criteria) this;
        }

        public Criteria andStuCertPicIsNotNull() {
            addCriterion("stu_cert_pic is not null");
            return (Criteria) this;
        }

        public Criteria andStuCertPicEqualTo(String value) {
            addCriterion("stu_cert_pic =", value, "stuCertPic");
            return (Criteria) this;
        }

        public Criteria andStuCertPicNotEqualTo(String value) {
            addCriterion("stu_cert_pic <>", value, "stuCertPic");
            return (Criteria) this;
        }

        public Criteria andStuCertPicGreaterThan(String value) {
            addCriterion("stu_cert_pic >", value, "stuCertPic");
            return (Criteria) this;
        }

        public Criteria andStuCertPicGreaterThanOrEqualTo(String value) {
            addCriterion("stu_cert_pic >=", value, "stuCertPic");
            return (Criteria) this;
        }

        public Criteria andStuCertPicLessThan(String value) {
            addCriterion("stu_cert_pic <", value, "stuCertPic");
            return (Criteria) this;
        }

        public Criteria andStuCertPicLessThanOrEqualTo(String value) {
            addCriterion("stu_cert_pic <=", value, "stuCertPic");
            return (Criteria) this;
        }

        public Criteria andStuCertPicLike(String value) {
            addCriterion("stu_cert_pic like", value, "stuCertPic");
            return (Criteria) this;
        }

        public Criteria andStuCertPicNotLike(String value) {
            addCriterion("stu_cert_pic not like", value, "stuCertPic");
            return (Criteria) this;
        }

        public Criteria andStuCertPicIn(List<String> values) {
            addCriterion("stu_cert_pic in", values, "stuCertPic");
            return (Criteria) this;
        }

        public Criteria andStuCertPicNotIn(List<String> values) {
            addCriterion("stu_cert_pic not in", values, "stuCertPic");
            return (Criteria) this;
        }

        public Criteria andStuCertPicBetween(String value1, String value2) {
            addCriterion("stu_cert_pic between", value1, value2, "stuCertPic");
            return (Criteria) this;
        }

        public Criteria andStuCertPicNotBetween(String value1, String value2) {
            addCriterion("stu_cert_pic not between", value1, value2, "stuCertPic");
            return (Criteria) this;
        }

        public Criteria andTeachCertPicIsNull() {
            addCriterion("teach_cert_pic is null");
            return (Criteria) this;
        }

        public Criteria andTeachCertPicIsNotNull() {
            addCriterion("teach_cert_pic is not null");
            return (Criteria) this;
        }

        public Criteria andTeachCertPicEqualTo(String value) {
            addCriterion("teach_cert_pic =", value, "teachCertPic");
            return (Criteria) this;
        }

        public Criteria andTeachCertPicNotEqualTo(String value) {
            addCriterion("teach_cert_pic <>", value, "teachCertPic");
            return (Criteria) this;
        }

        public Criteria andTeachCertPicGreaterThan(String value) {
            addCriterion("teach_cert_pic >", value, "teachCertPic");
            return (Criteria) this;
        }

        public Criteria andTeachCertPicGreaterThanOrEqualTo(String value) {
            addCriterion("teach_cert_pic >=", value, "teachCertPic");
            return (Criteria) this;
        }

        public Criteria andTeachCertPicLessThan(String value) {
            addCriterion("teach_cert_pic <", value, "teachCertPic");
            return (Criteria) this;
        }

        public Criteria andTeachCertPicLessThanOrEqualTo(String value) {
            addCriterion("teach_cert_pic <=", value, "teachCertPic");
            return (Criteria) this;
        }

        public Criteria andTeachCertPicLike(String value) {
            addCriterion("teach_cert_pic like", value, "teachCertPic");
            return (Criteria) this;
        }

        public Criteria andTeachCertPicNotLike(String value) {
            addCriterion("teach_cert_pic not like", value, "teachCertPic");
            return (Criteria) this;
        }

        public Criteria andTeachCertPicIn(List<String> values) {
            addCriterion("teach_cert_pic in", values, "teachCertPic");
            return (Criteria) this;
        }

        public Criteria andTeachCertPicNotIn(List<String> values) {
            addCriterion("teach_cert_pic not in", values, "teachCertPic");
            return (Criteria) this;
        }

        public Criteria andTeachCertPicBetween(String value1, String value2) {
            addCriterion("teach_cert_pic between", value1, value2, "teachCertPic");
            return (Criteria) this;
        }

        public Criteria andTeachCertPicNotBetween(String value1, String value2) {
            addCriterion("teach_cert_pic not between", value1, value2, "teachCertPic");
            return (Criteria) this;
        }

        public Criteria andPayAccountIsNull() {
            addCriterion("pay_account is null");
            return (Criteria) this;
        }

        public Criteria andPayAccountIsNotNull() {
            addCriterion("pay_account is not null");
            return (Criteria) this;
        }

        public Criteria andPayAccountEqualTo(String value) {
            addCriterion("pay_account =", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountNotEqualTo(String value) {
            addCriterion("pay_account <>", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountGreaterThan(String value) {
            addCriterion("pay_account >", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountGreaterThanOrEqualTo(String value) {
            addCriterion("pay_account >=", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountLessThan(String value) {
            addCriterion("pay_account <", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountLessThanOrEqualTo(String value) {
            addCriterion("pay_account <=", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountLike(String value) {
            addCriterion("pay_account like", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountNotLike(String value) {
            addCriterion("pay_account not like", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountIn(List<String> values) {
            addCriterion("pay_account in", values, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountNotIn(List<String> values) {
            addCriterion("pay_account not in", values, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountBetween(String value1, String value2) {
            addCriterion("pay_account between", value1, value2, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountNotBetween(String value1, String value2) {
            addCriterion("pay_account not between", value1, value2, "payAccount");
            return (Criteria) this;
        }

        public Criteria andUniversityIsNull() {
            addCriterion("university is null");
            return (Criteria) this;
        }

        public Criteria andUniversityIsNotNull() {
            addCriterion("university is not null");
            return (Criteria) this;
        }

        public Criteria andUniversityEqualTo(String value) {
            addCriterion("university =", value, "university");
            return (Criteria) this;
        }

        public Criteria andUniversityNotEqualTo(String value) {
            addCriterion("university <>", value, "university");
            return (Criteria) this;
        }

        public Criteria andUniversityGreaterThan(String value) {
            addCriterion("university >", value, "university");
            return (Criteria) this;
        }

        public Criteria andUniversityGreaterThanOrEqualTo(String value) {
            addCriterion("university >=", value, "university");
            return (Criteria) this;
        }

        public Criteria andUniversityLessThan(String value) {
            addCriterion("university <", value, "university");
            return (Criteria) this;
        }

        public Criteria andUniversityLessThanOrEqualTo(String value) {
            addCriterion("university <=", value, "university");
            return (Criteria) this;
        }

        public Criteria andUniversityLike(String value) {
            addCriterion("university like", value, "university");
            return (Criteria) this;
        }

        public Criteria andUniversityNotLike(String value) {
            addCriterion("university not like", value, "university");
            return (Criteria) this;
        }

        public Criteria andUniversityIn(List<String> values) {
            addCriterion("university in", values, "university");
            return (Criteria) this;
        }

        public Criteria andUniversityNotIn(List<String> values) {
            addCriterion("university not in", values, "university");
            return (Criteria) this;
        }

        public Criteria andUniversityBetween(String value1, String value2) {
            addCriterion("university between", value1, value2, "university");
            return (Criteria) this;
        }

        public Criteria andUniversityNotBetween(String value1, String value2) {
            addCriterion("university not between", value1, value2, "university");
            return (Criteria) this;
        }

        public Criteria andTeachCityIdIsNull() {
            addCriterion("teach_city_id is null");
            return (Criteria) this;
        }

        public Criteria andTeachCityIdIsNotNull() {
            addCriterion("teach_city_id is not null");
            return (Criteria) this;
        }

        public Criteria andTeachCityIdEqualTo(Integer value) {
            addCriterion("teach_city_id =", value, "teachCityId");
            return (Criteria) this;
        }

        public Criteria andTeachCityIdNotEqualTo(Integer value) {
            addCriterion("teach_city_id <>", value, "teachCityId");
            return (Criteria) this;
        }

        public Criteria andTeachCityIdGreaterThan(Integer value) {
            addCriterion("teach_city_id >", value, "teachCityId");
            return (Criteria) this;
        }

        public Criteria andTeachCityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("teach_city_id >=", value, "teachCityId");
            return (Criteria) this;
        }

        public Criteria andTeachCityIdLessThan(Integer value) {
            addCriterion("teach_city_id <", value, "teachCityId");
            return (Criteria) this;
        }

        public Criteria andTeachCityIdLessThanOrEqualTo(Integer value) {
            addCriterion("teach_city_id <=", value, "teachCityId");
            return (Criteria) this;
        }

        public Criteria andTeachCityIdIn(List<Integer> values) {
            addCriterion("teach_city_id in", values, "teachCityId");
            return (Criteria) this;
        }

        public Criteria andTeachCityIdNotIn(List<Integer> values) {
            addCriterion("teach_city_id not in", values, "teachCityId");
            return (Criteria) this;
        }

        public Criteria andTeachCityIdBetween(Integer value1, Integer value2) {
            addCriterion("teach_city_id between", value1, value2, "teachCityId");
            return (Criteria) this;
        }

        public Criteria andTeachCityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("teach_city_id not between", value1, value2, "teachCityId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}