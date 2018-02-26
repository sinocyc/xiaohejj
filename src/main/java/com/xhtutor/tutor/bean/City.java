package com.xhtutor.tutor.bean;

public class City {
    private Integer id;

    private String code;

    private String name;

    private Integer provId;
    
    private Province province;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getProvId() {
        return provId;
    }

    public void setProvId(Integer provId) {
        this.provId = provId;
    }

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", code=" + code + ", name=" + name + ", provId=" + provId + ", province=" + province
				+ "]";
	}
    
}