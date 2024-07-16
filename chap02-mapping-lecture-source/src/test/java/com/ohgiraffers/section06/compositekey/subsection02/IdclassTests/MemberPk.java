package com.ohgiraffers.section06.compositekey.subsection02.IdclassTests;


import jakarta.persistence.Column;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberPk {

    private int memberNo;
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
