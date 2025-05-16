package edu.du.userservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetExternalBarcodeDto {
    private String barcode;
    private String productName;
    private String productType;
    private String productStatus;
    private String handlerName;
}
