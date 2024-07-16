package com.ohgiraffers.section06.compositekey.subsection01.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable // 임베드 될 수 있는 복합 키타입을 지정할 때 사용하는 어노테이션
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberPk implements Serializable {

    @Column(name="member_no")
    private int memberNo;

    @Column(name="member_id")
    private String memberId;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass()) return false;
        MemberPk memberPk = (MemberPk) obj;
        return memberNo == memberPk.memberNo && Objects.equals(memberId, memberPk.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberNo, memberId);
    }
}
