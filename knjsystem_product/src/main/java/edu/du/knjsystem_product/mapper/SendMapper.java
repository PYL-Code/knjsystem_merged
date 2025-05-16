package edu.du.knjsystem_product.mapper;

import edu.du.knjsystem_product.dto.SendExternalBarcodeDto;
import edu.du.knjsystem_product.dto.SendExternalCertDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SendMapper {
    SendExternalBarcodeDto selectExternalBarcodeInfoByBarcodeNo(@Param("barcodeNo") String barcodeNo);

    SendExternalCertDto selectExternalCertInfoByBarcodeId(@Param("barcodeId") Long barcodeId);
}
