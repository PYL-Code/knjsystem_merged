package edu.du.knjsystem_product.external.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "certList")
public class CertOutListWrapper {

    private List<CertOutDto> certs;

    @XmlElement(name = "certOutDto")
    public List<CertOutDto> getCerts() {
        return certs;
    }

    public void setCerts(List<CertOutDto> certs) {
        this.certs = certs;
    }
}