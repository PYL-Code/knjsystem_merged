package edu.du.userservice.dto;

import edu.du.userservice.entity.TsgB001;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProductDetailDto {

    private String barcodeNumber;
    private String productName;
    private String productType;
    private String productStatus;
    private String handlerName;

    private List<CertificationDto> certifications;

    public ProductDetailDto(TsgB001 entity) {
        this.barcodeNumber = entity.getBarcodeNumber();
        this.productName = entity.getProductName();
        this.productType = entity.getProductType();
        this.productStatus = entity.getProductStatus();
        this.handlerName = entity.getHandlerName();
        this.certifications = entity.getCertifications().stream()
                .map(CertificationDto::new)
                .collect(Collectors.toList());
    }
}