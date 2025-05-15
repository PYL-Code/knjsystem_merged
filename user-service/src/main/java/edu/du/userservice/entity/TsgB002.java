package edu.du.userservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TSG_B002")
public class TsgB002 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String certificationCode;
    private String farmerName;
    private String certificationStatus;
    private String productCodeName;
    private String certificationAgency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barcode_product_id")
    private TsgB001 tsgB001;
}