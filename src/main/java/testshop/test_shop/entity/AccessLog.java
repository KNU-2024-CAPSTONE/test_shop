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
    private Long id;

    @NotNull
    private LocalDateTime accessTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    public AccessLog() {}

    public AccessLog(LocalDateTime accessTime, Member member){
        this.accessTime = accessTime;
        this.member = member;
    }

    public AccessLogResponse mapToResponse(){
        return new AccessLogResponse(this.member.getEmail(), this.member.getGender(), this.member.getAge(), this.accessTime);
    }
}
