package com.PBL5.Backend_Web.persistence;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "account")
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@ToString
public class Account {

    @Id
    @Column(name = "account_id", length = 255, nullable = false)
    private String id;

    @Column(name = "username", length = 255, nullable = false, unique = true)
    private String name;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "is_activate", nullable = false)
    private Boolean is_activate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", updatable = false, nullable = false)
    private Role role;

    @OneToOne(mappedBy = "account",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private User user;

    @PrePersist
    public void prePersist() {
            is_activate =true;
    }

    public Account(String id, String name, String password, Role role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public Account(String id, String name, String password, final Role role, final User user) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.user = user;
    }


    public Boolean getIs_activate() {
        return is_activate;
    }

    public void setIs_activate(Boolean is_activate) {
        this.is_activate = is_activate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIs_activate() {
        return is_activate;
    }

    public void setIs_activate(boolean is_activate) {
        this.is_activate = is_activate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(getId(), account.getId()) && Objects.equals(getName(), account.getName()) && Objects.equals(getPassword(), account.getPassword()) && Objects.equals(isIs_activate(), account.isIs_activate()) && Objects.equals(getRole(), account.getRole()) && Objects.equals(getUser(), account.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPassword(), isIs_activate(), getRole(), getUser());
    }
}
