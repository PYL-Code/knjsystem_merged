package edu.du.knjsystem_product.externalDB.service;

import edu.du.knjsystem_product.externalDB.dto.CertOutDto;
import edu.du.knjsystem_product.externalDB.mapper.OutDBMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OutDBService {
    private final OutDBMapper outDBMapper;

    public List<CertOutDto> selectCertOutAll() {
        return outDBMapper.selectCertOutAll();
    }
}
