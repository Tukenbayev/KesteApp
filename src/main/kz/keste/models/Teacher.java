package kz.keste.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Teacher {

    @SerializedName("phone")
    @Expose
    private Long phone;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("schoolId")
    @Expose
    private Long schoolId;
    @SerializedName("subjects")
    @Expose
    private List<Subject> subjects = null;

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

}