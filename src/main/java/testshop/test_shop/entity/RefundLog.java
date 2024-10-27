package testshop.test_shop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class RefundLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "purchase_log_id")
    PurchaseLog purchaseLog;

    public RefundLog(){}

    public RefundLog(PurchaseLog purchaseLog){
        this.purchaseLog = purchaseLog;
    }
}
