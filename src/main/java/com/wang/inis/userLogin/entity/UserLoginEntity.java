package com.wang.inis.userLogin.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.File;
import java.util.Date;

@Entity
public class UserLoginEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private String password;

    private String userName;

    private String dob;

    private File image;

    private Date createDate;

    protected UserLoginEntity(){}

    public UserLoginEntity(String email, String password, String userName, String dob, File image) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.dob = dob;
        this.image = image;
    }


    public UserLoginEntity(String email, String password, String userName, String dob, File image, Date createDate) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.dob = dob;
        this.image = image;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "UserLoginEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", dob='" + dob + '\'' +
                ", image=" + image +
                ", createDate=" + createDate +
                '}';
    }
}
