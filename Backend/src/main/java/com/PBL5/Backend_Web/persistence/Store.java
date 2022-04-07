package com.PBL5.Backend_Web.persistence;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "store")
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Store {
    @Id
    @Column(name = "store_id",length = 255)
    private String id;

    @Column(name = "store_name",length = 255,nullable = false)
    private String name;

    @Column(name = "address",nullable = false,unique = true)
    private String address;

    @Column(name = "phone",length = 255,unique = true)
    private String phone;

    @OneToMany(mappedBy = "store")
    private List<ProductDetail> productDetails;

    public Store(String id, String name, String address, String phone, final List<ProductDetail> productDetails) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.productDetails = productDetails;
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

    public List<ProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(final List<ProductDetail> productDetails) {
        this.productDetails = productDetails;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", productDetails=" + productDetails +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Store)) return false;
        Store store = (Store) o;
        return Objects.equals(getId(), store.getId()) && Objects.equals(getName(), store.getName()) && Objects.equals(getAddress(), store.getAddress()) && Objects.equals(getPhone(), store.getPhone()) && Objects.equals(getProductDetails(), store.getProductDetails());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAddress(), getPhone(), getProductDetails());
    }
}
