package com.test.NaceApplication.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity


public class Nace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private Integer level;
    private String code;
    private String parent;
    private String description;
    private String thisItemIncludes;
    private String  thisItemAlsoIncludes;
    private String rulings;
    private String thisItemExcludes;
    private String referenceToIsisRev4;


}
