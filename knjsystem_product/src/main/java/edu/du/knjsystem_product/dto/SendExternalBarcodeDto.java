package edu.du.knjsystem_product.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SendExternalBarcodeDto {
    private String barcode;
    private String productName;
    private String productType;
    private String productStatus;
    private String handlerName;
}
