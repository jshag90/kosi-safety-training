package com.kosi.entity;

import com.kosi.util.FaqTypeText;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "faq_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FaqType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String faqTypeText;
}
