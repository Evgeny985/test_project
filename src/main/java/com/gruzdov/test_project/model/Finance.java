package com.gruzdov.test_project.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "finances")
public class Finance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date publicationDate;

    @Column(name = "instrument")
    private String instrument;

    @Column(name = "coast")
    private BigDecimal coast;
}
