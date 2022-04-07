package com.PBL5.Backend_Web.persistence;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Size")
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Size {

    private enum TypeSize {
        XS, S, M, L, XXL,XL
    }
    @Id
    @Column(name = "size_id",length = 255)
    private String id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeSize type;

    @Column(name = "description",length = Integer.MAX_VALUE)
    @Type(type = "text")
    private String description;

    @OneToMany(mappedBy = "size")
    private List<ProductDetail> productDetails;

    public Size(String id, TypeSize size, String description, List<ProductDetail> productDetails) {
        this.id = id;
        this.type = size;
        this.description = description;
        this.productDetails = productDetails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TypeSize getType() {
        return type;
    }

    public void setType(TypeSize size) {
        this.type = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(final List<ProductDetail> productDetails) {
        this.productDetails = productDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Size)) return false;
        Size size1 = (Size) o;
        return Objects.equals(getId(), size1.getId()) && getType() == size1.getType() && Objects.equals(getDescription(), size1.getDescription()) && Objects.equals(getProductDetails(), size1.getProductDetails());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getDescription(), getProductDetails());
    }

    @Override
    public String toString() {
        return "Size{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", productDetails=" + productDetails +
                '}';
    }
}
