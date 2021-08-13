package com.example.now.Model.Object;

import com.google.gson.annotations.SerializedName;

public class Ward {

    @Override
    public String toString() {
        return "Ward{" +
                "districtId='" + districtId + '\'' +
                ", wardId='" + wardId + '\'' +
                ", wardName='" + wardName + '\'' +
                ", wardType='" + wardType + '\'' +
                '}';
    }

    public Ward(String districtId, String wardId, String wardName, String wardType) {
        this.districtId = districtId;
        this.wardId = wardId;
        this.wardName = wardName;
        this.wardType = wardType;
    }

    @SerializedName("district_id")
    private String districtId;
    @SerializedName("ward_id")
    private String wardId;
    @SerializedName("ward_name")
    private String wardName;
    @SerializedName("ward_type")
    private String wardType;

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getWardType() {
        return wardType;
    }

    public void setWardType(String wardType) {
        this.wardType = wardType;
    }
}
