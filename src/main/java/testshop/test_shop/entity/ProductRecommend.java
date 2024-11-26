package testshop.test_shop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class ProductRecommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDate recommendDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @NotNull
    private boolean isCart;

    @NotNull
    private boolean isPurchase;

    public ProductRecommend(){}

    public ProductRecommend(Member member, Product product, boolean isCart, boolean isPurchase){
        this.member = member;
        this.product = product;
        this.isCart = isCart;
        this.isPurchase = isPurchase;
    }
}
