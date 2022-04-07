package com.PBL5.Backend_Web.persistence;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name="product", uniqueConstraints= @UniqueConstraint(name = "UniqueProductNameColor",columnNames = {"product_Name","color"}))
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product {

    @Id
    @Column(name = "product_id",length = 255)
    private String id;

    @Column(name="product_Name",length = 255,nullable = false)
    private String name;

    @Column(name = "color",length = 255,nullable = false)
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_Type_id", referencedColumnName = "product_Type_id",insertable = false,updatable = false)
    private ProductType productType;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<ProductDetail> productDetails;



    public Product(String id, String name, String color,final ProductType productType,final List<ProductDetail> productDetails) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.productType = productType;
        this.productDetails = productDetails;
    }

    public List<ProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(final List<ProductDetail> productDetails) {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(final ProductType productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", productType=" + productType +
                ", productDetails=" + productDetails +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(getId(), product.getId()) && Objects.equals(getName(), product.getName()) && Objects.equals(getColor(), product.getColor()) && Objects.equals(getProductType(), product.getProductType()) && Objects.equals(getProductDetails(), product.getProductDetails());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getColor(), getProductType(), getProductDetails());
    }
}
