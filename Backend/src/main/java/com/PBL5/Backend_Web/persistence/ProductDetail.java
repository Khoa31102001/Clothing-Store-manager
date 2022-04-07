package com.PBL5.Backend_Web.persistence;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "product_details")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail {

    @EmbeddedId
    private Id id;

    @Column(name = "image",length = 255 ,nullable = false)
    private String image;



    @Column(name = "price",nullable = false,precision = 9,scale = 2)
    private Double price;


    @Column(name = "amount",nullable = false)
    private Double amount;

    @Transient
    private MultipartFile file;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id",insertable = false,updatable = false,nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id", referencedColumnName = "size_id",insertable = false,updatable = false,nullable = false)
    private Size size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", referencedColumnName = "store_id",updatable = false,nullable = false)
    private Store store;


    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Id getId() {
        return id;
    }

    public void setId(final Id id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(final Product product) {
        this.product = product;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(final Size size) {
        this.size = size;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(final Store store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "ProductDetail{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", product=" + product +
                ", size=" + size +
                ", store=" + store +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDetail)) return false;
        ProductDetail that = (ProductDetail) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getImage(), that.getImage()) && Objects.equals(getPrice(), that.getPrice()) && Objects.equals(getAmount(), that.getAmount()) && Objects.equals(getProduct(), that.getProduct()) && Objects.equals(getSize(), that.getSize()) && Objects.equals(getStore(), that.getStore());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getImage(), getPrice(), getAmount(), getProduct(), getSize(), getStore());
    }

    @Embeddable
    @NoArgsConstructor
    public static class Id implements Serializable {
        private static final long serialVersionUID = 497944234449548718L;

        @Column(name = "product_id",nullable = false,length = 255)
        private String product_id;
        @Column(name = "size_id",nullable = false,length = 255)
        private String size_id;



        public Id(String product_id, String size_id) {
            this.product_id = product_id;
            this.size_id = size_id;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getSize_id() {
            return size_id;
        }

        public void setSize_id(String size_id) {
            this.size_id = size_id;
        }

        @Override
        public String toString() {
            return "Id{" +
                    "product_id='" + product_id + '\'' +
                    ", size_id='" + size_id + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Id)) return false;
            Id id = (Id) o;
            return Objects.equals(getProduct_id(), id.getProduct_id()) && Objects.equals(getSize_id(), id.getSize_id());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getProduct_id(), getSize_id());
        }
    }
}
