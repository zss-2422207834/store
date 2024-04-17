package com.cy.store.entity;

import java.io.Serializable;
import java.util.Objects;

public class District implements Serializable {
    public Integer id;
    public String parentCode;

    public String code;

    public String name;

    @Override
    public String toString() {
        return "District{" +
                "id=" + id +
                ", parentCode='" + parentCode + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof District)) return false;
        District district = (District) o;
        return Objects.equals(getId(), district.getId()) && Objects.equals(getParentCode(), district.getParentCode()) && Objects.equals(getCode(), district.getCode()) && Objects.equals(getName(), district.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getParentCode(), getCode(), getName());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
