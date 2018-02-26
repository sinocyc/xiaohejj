package com.xhtutor.tutor.bean;

import java.util.ArrayList;
import java.util.List;

public class TutorDistrExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TutorDistrExample() {
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

        public Criteria andTutorIdIsNull() {
            addCriterion("tutor_id is null");
            return (Criteria) this;
        }

        public Criteria andTutorIdIsNotNull() {
            addCriterion("tutor_id is not null");
            return (Criteria) this;
        }

        public Criteria andTutorIdEqualTo(Integer value) {
            addCriterion("tutor_id =", value, "tutorId");
            return (Criteria) this;
        }

        public Criteria andTutorIdNotEqualTo(Integer value) {
            addCriterion("tutor_id <>", value, "tutorId");
            return (Criteria) this;
        }

        public Criteria andTutorIdGreaterThan(Integer value) {
            addCriterion("tutor_id >", value, "tutorId");
            return (Criteria) this;
        }

        public Criteria andTutorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("tutor_id >=", value, "tutorId");
            return (Criteria) this;
        }

        public Criteria andTutorIdLessThan(Integer value) {
            addCriterion("tutor_id <", value, "tutorId");
            return (Criteria) this;
        }

        public Criteria andTutorIdLessThanOrEqualTo(Integer value) {
            addCriterion("tutor_id <=", value, "tutorId");
            return (Criteria) this;
        }

        public Criteria andTutorIdIn(List<Integer> values) {
            addCriterion("tutor_id in", values, "tutorId");
            return (Criteria) this;
        }

        public Criteria andTutorIdNotIn(List<Integer> values) {
            addCriterion("tutor_id not in", values, "tutorId");
            return (Criteria) this;
        }

        public Criteria andTutorIdBetween(Integer value1, Integer value2) {
            addCriterion("tutor_id between", value1, value2, "tutorId");
            return (Criteria) this;
        }

        public Criteria andTutorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("tutor_id not between", value1, value2, "tutorId");
            return (Criteria) this;
        }

        public Criteria andDistrIdIsNull() {
            addCriterion("distr_id is null");
            return (Criteria) this;
        }

        public Criteria andDistrIdIsNotNull() {
            addCriterion("distr_id is not null");
            return (Criteria) this;
        }

        public Criteria andDistrIdEqualTo(Integer value) {
            addCriterion("distr_id =", value, "distrId");
            return (Criteria) this;
        }

        public Criteria andDistrIdNotEqualTo(Integer value) {
            addCriterion("distr_id <>", value, "distrId");
            return (Criteria) this;
        }

        public Criteria andDistrIdGreaterThan(Integer value) {
            addCriterion("distr_id >", value, "distrId");
            return (Criteria) this;
        }

        public Criteria andDistrIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("distr_id >=", value, "distrId");
            return (Criteria) this;
        }

        public Criteria andDistrIdLessThan(Integer value) {
            addCriterion("distr_id <", value, "distrId");
            return (Criteria) this;
        }

        public Criteria andDistrIdLessThanOrEqualTo(Integer value) {
            addCriterion("distr_id <=", value, "distrId");
            return (Criteria) this;
        }

        public Criteria andDistrIdIn(List<Integer> values) {
            addCriterion("distr_id in", values, "distrId");
            return (Criteria) this;
        }

        public Criteria andDistrIdNotIn(List<Integer> values) {
            addCriterion("distr_id not in", values, "distrId");
            return (Criteria) this;
        }

        public Criteria andDistrIdBetween(Integer value1, Integer value2) {
            addCriterion("distr_id between", value1, value2, "distrId");
            return (Criteria) this;
        }

        public Criteria andDistrIdNotBetween(Integer value1, Integer value2) {
            addCriterion("distr_id not between", value1, value2, "distrId");
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