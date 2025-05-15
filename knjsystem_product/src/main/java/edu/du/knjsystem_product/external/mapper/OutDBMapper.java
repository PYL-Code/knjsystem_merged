package edu.du.knjsystem_product.external.mapper;

import edu.du.knjsystem_product.external.dto.CertOutDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OutDBMapper {
    List<CertOutDto> selectCertOutAll();
}
