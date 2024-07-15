package com.ohgiraffers.section05.access.subsection01.field;


import jakarta.persistence.*;
import lombok.*;


@Entity(name="member_section05_sub01")
@Table(name="tbl_member_section05_sub01")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Access(AccessType.PROPERTY) // 모든 필드에 대해서 필드 직접 접근 방식을 사용
public class Member {

    @Id
    @Column(name = "member_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.PROPERTY)
    private int memberNo;

    @Column(name ="member_id")
    @Access(AccessType.PROPERTY)
    private String memberId;

    @Column(name ="member_pwd")
    @Access(AccessType.PROPERTY)
    private String memberPwd;

    @Column(name ="nickname")
    @Access(AccessType.PROPERTY)
    private String nickname;

}