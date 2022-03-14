package com.longfor.shopping.common;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@ToString
@Data
public class RoleOrgDto implements Serializable {
    private String roleCode;
    private String roleName;
    private String roleLabelName;
    private String roleType;
    private String channelType;

    public String getChannelType() {
        return roleLabelName!=null?roleLabelName.split("-")[0]:"";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleOrgDto that = (RoleOrgDto) o;
        return getChannelType().equals(that.getChannelType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getChannelType());
    }
}