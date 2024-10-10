package testshop.test_shop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import testshop.test_shop.dto.PurchaseInfoResponse;

import java.time.LocalDateTime;

@Getter
@Entity
public class PurchaseLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    LocalDateTime purchaseTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    Product product;

    @NotNull
    int quantity;

    @NotNull
    int starCount;

    public PurchaseLog() {}

    public PurchaseLog(LocalDateTime purchaseTime, Member member, Product product, int quantity, int starCount){
        this.purchaseTime = purchaseTime;
        this.member = member;
        this.product = product;
        this.quantity = quantity;
        this.starCount = starCount;
    }

    public int getTotalPrice(){
        return this.product.price * this.quantity;
    }

    public PurchaseInfoResponse mapToResponse(){
        return new PurchaseInfoResponse(this.member.email,
                this.member.gender,
                this.member.getAge(),
                new PurchaseInfoResponse.Product(this.product.category, this.product.name, this.product.price),
                this.quantity,
                this.starCount,
                getTotalPrice(),
                this.purchaseTime);
    }
}
