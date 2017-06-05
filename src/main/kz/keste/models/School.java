package kz.keste.models;

public class School {

    private int id;
    private String schoolNameRu;
    private String schoolNameKz;
    private String schoolNameEn;
    private String adminEmail;
    private String password;
    private String address;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }


    public String getSchoolNameRu() {
        return schoolNameRu;
    }

    public void setSchoolNameRu(String schoolNameRu) {
        this.schoolNameRu = schoolNameRu;
    }

    public String getSchoolNameKz() {
        return schoolNameKz;
    }

    public void setSchoolNameKz(String schoolNameKz) {
        this.schoolNameKz = schoolNameKz;
    }

    public String getSchoolNameEn() {
        return schoolNameEn;
    }

    public void setSchoolNameEn(String schoolNameEn) {
        this.schoolNameEn = schoolNameEn;
    }
}
