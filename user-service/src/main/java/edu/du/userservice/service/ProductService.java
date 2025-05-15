package edu.du.userservice.service;

import edu.du.userservice.dto.ProductDetailDto;
import edu.du.userservice.entity.TsgB001;
import edu.du.userservice.repository.TsgB001Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final TsgB001Repository productRepo;

    public ProductDetailDto findProductByBarcode(String barcodeNumber) {
        TsgB001 product = productRepo.findByBarcodeNumber(barcodeNumber)
                .orElse(null);

        if (product == null) return null;

        return new ProductDetailDto(product);
    }
}