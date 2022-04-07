package com.PBL5.Backend_Web.persistence;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "user")
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class User{


    private enum Gender {
        M, F, N
    }
    @Id
    @Column(name = "user_id", length = 255, nullable = false)
    private String id;

    @Column(name = "firstname", length = 255, nullable = false)
    private String first_name;

    @Column(name = "lastname", length = 255, nullable = false)
    private String last_name;

    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;


    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "avatar", length = 255)
    private String avatar;

    @Transient
    private MultipartFile file;

    @Column(name = "phone", length = 255)
    private String phone;

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date created_at;

    @Column(name = "update_at")
    @Temporal(TemporalType.DATE)
    private Date update_at;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", updatable = false,unique = true)
    private Account account;

    @PrePersist
    public void before_add(){
        created_at = new Date();
        update_at = new Date();
    }
    @PreUpdate
    public void before_update(){
        update_at = new Date();
    }

    public User(String id, String first_name,
                String last_name, Date birthday,
                Gender gender, String address, String avatar, String phone,
                Date created_at, Date update_at, final Account account) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.avatar = avatar;
        this.phone = phone;
        this.created_at = created_at;
        this.update_at = update_at;
        this.account = account;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount( final Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", avatar=" + avatar +
                ", phone='" + phone + '\'' +
                ", created_at=" + created_at +
                ", update_at=" + update_at +
                ", account=" + account +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getFirst_name(), user.getFirst_name()) && Objects.equals(getLast_name(), user.getLast_name()) && Objects.equals(getBirthday(), user.getBirthday()) && getGender() == user.getGender() && Objects.equals(getAddress(), user.getAddress()) && Objects.equals(getAvatar(), user.getAvatar()) && Objects.equals(getPhone(), user.getPhone()) && Objects.equals(getCreated_at(), user.getCreated_at()) && Objects.equals(getUpdate_at(), user.getUpdate_at()) && Objects.equals(getAccount(), user.getAccount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirst_name(), getLast_name(), getBirthday(), getGender(), getAddress(), getAvatar(), getPhone(), getCreated_at(), getUpdate_at(), getAccount());
    }
}

