package testshop.test_shop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import testshop.test_shop.dto.AccessLogResponse;

import java.time.LocalDateTime;

@Getter
@Entity
public class AccessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    LocalDateTime accessTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    Member member;

    public AccessLog() {}

    public AccessLog(LocalDateTime accessTime, Member member){
        this.accessTime = accessTime;
        this.member = member;
    }

    public AccessLogResponse mapToResponse(){
        return new AccessLogResponse(this.member.email, this.member.gender, this.member.getAge(), this.accessTime);
    }
}
