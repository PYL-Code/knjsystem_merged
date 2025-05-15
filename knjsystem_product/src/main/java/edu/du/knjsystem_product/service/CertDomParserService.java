package edu.du.knjsystem_product.service;

import edu.du.knjsystem_product.external.dto.CertOutDto;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class CertDomParserService {

    public List<CertOutDto> fetchCertInfoFromExternal() {
        List<CertOutDto> list = new ArrayList<>();

        try {
            URL url = new URL("http://localhost:9876/external/api/cert-info?key=123456");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(url.openStream());

            NodeList certs = doc.getElementsByTagName("certOutDto");
            for (int i = 0; i < certs.getLength(); i++) {
                Element e = (Element) certs.item(i);

                CertOutDto dto = new CertOutDto();
                dto.setStdCertNo(getValue(e, "stdCertNo"));
                dto.setFrmrNm(getValue(e, "frmrNm"));
                dto.setItemNm(getValue(e, "itemNm"));
                dto.setRepItemNm(getValue(e, "repItemNm"));
                dto.setPrdrGrpNm(getValue(e, "prdrGrpNm"));
                dto.setCertGbNm(getValue(e, "certGbNm"));
                dto.setCertStateNm(getValue(e, "certStateNm"));
                dto.setCertStrtDt(getValue(e, "certStrtDt"));
                dto.setCertEndDt(getValue(e, "certEndDt"));
                dto.setRegistDate(getValue(e, "registDate"));

                list.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    private String getValue(Element e, String tag) {
        NodeList n = e.getElementsByTagName(tag);
        if (n.getLength() > 0 && n.item(0).getFirstChild() != null) {
            return n.item(0).getFirstChild().getNodeValue();
        }
        return null;
    }

}
