package com.PBL5.Backend_Web.persistence;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "role")
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Role {

    @Id
    @Column(name = "role_id", length = 255, nullable = false)
    private String id;
    @Column(name = "role_name", length = 255, nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
    private List<Account> account;



    public Role(String id, String name, final List<Account> account) {
        this.id = id;
        this.name = name;
        this.account = account;
    }

    public List<Account> getAccount() {
        return account;
    }

    public void setAccount(final List<Account> account) {
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return Objects.equals(getId(), role.getId()) && Objects.equals(getName(), role.getName()) && Objects.equals(getAccount(), role.getAccount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAccount());
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", account=" + account +
                '}';
    }
}
