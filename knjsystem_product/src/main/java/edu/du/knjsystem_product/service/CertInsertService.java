package edu.du.knjsystem_product.service;

import edu.du.knjsystem_product.dto.CertInsertDto;
import edu.du.knjsystem_product.mapper.CertInsertMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CertInsertService {

    private final CertInsertMapper certInsertMapper;

    public CertInsertService(CertInsertMapper certInsertMapper) {
        this.certInsertMapper = certInsertMapper;
    }

    @Transactional
    public void insertCertData(CertInsertDto dto) {
        certInsertMapper.insertCertFromExternal(dto);
    }
}