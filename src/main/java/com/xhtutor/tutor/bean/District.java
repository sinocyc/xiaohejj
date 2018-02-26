package com.xhtutor.tutor.bean;

public class District {
    private Integer id;

    private String code;

    private String name;

    private Integer cityId;
    
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "District [id=" + id + ", code=" + code + ", name=" + name + ", cityId=" + cityId + ", city=" + city
				+ "]";
	}
	
}