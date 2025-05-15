package edu.du.userservice.controller;

import edu.du.userservice.dto.ProductDetailDto;
import edu.du.userservice.entity.TsgB001;
import edu.du.userservice.repository.TsgB001Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final TsgB001Repository productRepo;

    // 바코드 번호로 상품 및 인증 정보 조회
    @GetMapping("/barcode/{barcodeNumber}")
    public ResponseEntity<ProductDetailDto> getByBarcode(@PathVariable("barcodeNumber") String barcodeNumber) {
        TsgB001 product = productRepo.findByBarcodeNumber(barcodeNumber)
                .orElse(null);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new ProductDetailDto(product));
    }
}