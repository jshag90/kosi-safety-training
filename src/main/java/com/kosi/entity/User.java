package com.kosi.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    /**
     * 로그인 아이디
     */
    @Column(name = "username", unique = true, columnDefinition = "VARCHAR(50) COMMENT '로그인 아이디'")
    private String username;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "name", columnDefinition = "VARCHAR(50) COMMENT '사용자 이름'")
    private String name;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "birthday", length = 50)
    private String birthday;

    @Column(name = "phoneNumber", length = 50)
    private String phoneNumber;

    @Column(name = "companyName", length = 50)
    private String companyName;

    @Column(name = "companyNumber", length = 50)
    private String companyNumber;

    /**
     * 개인정보 수집 및 이용 동의 여부
     */
    @Column(name = "agree_personal_info_collection")
    private boolean agreePersonalInfoCollection;

    /**
     * 개인정보 제3자 제공 동의 여부
     */
    @Column(name = "agree_personal_info_third_party")
    private boolean agreePersonalInfoThirdParty;

    @Column(name = "activated")
    private boolean activated;

}
