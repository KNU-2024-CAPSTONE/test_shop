package testshop.test_shop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import testshop.test_shop.dto.ProductResponse;

@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    String category;

    @NotNull
    String name;

    @NotNull
    int price;

    public Product(){}

    public Product(String category, String name, int price){
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public ProductResponse mapToRespone(){
        return new ProductResponse(this.category, this.name);
    }
}