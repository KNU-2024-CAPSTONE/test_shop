package testshop.test_shop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
public class CouponLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private CouponLog.Status status;

    @NotNull
    private String code;

    @NotNull
    private String category;

    @NotNull
    private int discount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    Member member;

    public CouponLog() {}

    public CouponLog(CouponLog.Status status, String code, String category, int discount, Member member){
        this.status = status;
        this.code = code;
        this.category = category;
        this.discount = discount;
        this.member = member;
    }

    public enum Status {
        NOT_REGISTER,
        REGISTERED,
        USED
    };

    public void changeStatusToRegistered(){
        this.status = Status.REGISTERED;
    }
}
