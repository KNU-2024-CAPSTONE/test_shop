package testshop.test_shop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import testshop.test_shop.dto.ProductResponse;

import java.time.LocalDate;

@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String category;

    @NotNull
    private String name;

    @NotNull
    private int price;

    @NotNull
    LocalDate postDate;

    public Product(){}

    public Product(String category, String name, int price, LocalDate postDate){
        this.category = category;
        this.name = name;
        this.price = price;
        this.postDate = postDate;
    }
}
