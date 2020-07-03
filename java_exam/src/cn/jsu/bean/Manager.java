package cn.jsu.bean;

public class Manager {
    private int m_id;
    private String m_name;
    private String m_no;
    private String m_sex;
    private String m_username;
    private String m_password;
    private String m_phone;
    private String m_place;
    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_no() {
        return m_no;
    }

    public void setM_no(String m_no) {
        this.m_no = m_no;
    }

    public String getM_sex() {
        return m_sex;
    }

    public void setM_sex(String m_sex) {
        this.m_sex = m_sex;
    }

    public String getM_username() {
        return m_username;
    }

    public void setM_username(String m_username) {
        this.m_username = m_username;
    }

    public String getM_password() {
        return m_password;
    }

    public void setM_password(String m_password) {
        this.m_password = m_password;
    }

    public String getM_phone() {
        return m_phone;
    }

    public void setM_phone(String m_phone) {
        this.m_phone = m_phone;
    }

    public String getM_place() {
        return m_place;
    }

    public void setM_place(String m_place) {
        this.m_place = m_place;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "m_id=" + m_id +
                ", m_name='" + m_name + '\'' +
                ", m_no='" + m_no + '\'' +
                ", m_sex='" + m_sex + '\'' +
                ", m_username='" + m_username + '\'' +
                ", m_password='" + m_password + '\'' +
                ", m_phone='" + m_phone + '\'' +
                ", m_place='" + m_place + '\'' +
                '}';
    }
}
