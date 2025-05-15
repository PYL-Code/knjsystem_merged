package edu.du.userservice.repository;

import edu.du.userservice.entity.TsgB001;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TsgB001Repository extends JpaRepository<TsgB001, Long> {
    Optional<TsgB001> findByBarcodeNumber(String barcodeNumber);
}