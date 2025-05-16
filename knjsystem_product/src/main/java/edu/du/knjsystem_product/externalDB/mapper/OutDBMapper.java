package edu.du.knjsystem_product.externalDB.mapper;

import edu.du.knjsystem_product.externalDB.dto.CertOutDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OutDBMapper {
    List<CertOutDto> selectCertOutAll();
}
