package cn.jsu.bean;

public class Supplier {
    private String no;
    private String name;
    private String place;
    private String phone;


    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
