package com.example.demo.beans;

public class ScoreResult {
    private String Filename;
    private String Security;
    private String Comfort;
    private String Beauty;
    private String Facility_availability;

    public String getBeauty() {
        return Beauty;
    }

    public String getComfort() {
        return Comfort;
    }

    public String getFacility_availability() {
        return Facility_availability;
    }

    public String getFilename() {
        return Filename;
    }

    public void setBeauty(String beauty) {
        Beauty = beauty;
    }

    public void setComfort(String comfort) {
        Comfort = comfort;
    }

    public void setFacility_availability(String facility_availability) {
        Facility_availability = facility_availability;
    }

    public String getSecurity() {
        return Security;
    }

    public void setFilename(String filename) {
        Filename = filename;
    }

    public void setSecurity(String security) {
        Security = security;
    }

    @Override
    public String toString() {
        return "ScoreResult [Filename="+Filename+",Security="+Security+",Comfort="+Comfort+",Beauty="+Beauty+",Facility_availability="+Facility_availability+"]";
    }
}
