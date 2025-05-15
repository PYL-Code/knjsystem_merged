package edu.du.userservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "TSG_B001")
public class TsgB001 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String barcodeNumber;   // 바코드 번호
    private String productName;     // 상품명
    private String productType;     // 상품 구분
    private String productStatus;   // 상품 상태
    private String handlerName;     // 취급자명

    @OneToMany(mappedBy = "tsgB001", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TsgB002> certifications;
}