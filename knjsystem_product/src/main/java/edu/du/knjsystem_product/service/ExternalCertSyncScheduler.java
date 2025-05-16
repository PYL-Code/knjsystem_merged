package edu.du.knjsystem_product.service;

import edu.du.knjsystem_product.dto.CertInsertDto;
import edu.du.knjsystem_product.mapper.CertInsertMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
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
@RequiredArgsConstructor
public class ExternalCertSyncScheduler {

    private final CertInsertMapper certInsertMapper;

    // 매 시간 0분마다 실행 (cron 형식: 초 분 시 일 월 요일)
    @Scheduled(cron = "0 0 0 1/1 * *")
    public void fetchAndSaveCertInfo() {
        try {
            URL url = new URL("http://localhost:9876/external/api/cert-info?key=123456");
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(url.openStream());

            NodeList certs = doc.getElementsByTagName("certOutDto");
            List<CertInsertDto> certList = new ArrayList<>();

            for (int i = 0; i < certs.getLength(); i++) {
                Element e = (Element) certs.item(i);
                CertInsertDto dto = new CertInsertDto();
                dto.setRcvSeq(getTag(e, "rcvSeq"));
                dto.setModSeq(getTag(e, "modSeq"));
                dto.setPrdrGrpNo(getTag(e, "prdrGrpNo"));
                dto.setRegSeq(getTag(e, "regSeq"));
                dto.setStdCertNo(getTag(e, "stdCertNo"));
                dto.setCertGbCd(getTag(e, "certGbCd"));
                dto.setCertGbNm(getTag(e, "certGbNm"));
                dto.setCertStrtDt(getTag(e, "certStrtDt"));
                dto.setCertEndDt(getTag(e, "certEndDt"));
                dto.setFrmrCertStt(getTag(e, "frmrCertStt"));
                dto.setCertStateNm(getTag(e, "certStateNm"));
                dto.setPsnGroupGb(getTag(e, "psnGroupGb"));
                dto.setPsnGroupNm(getTag(e, "psnGroupNm"));
                dto.setPrdrGrpNm(getTag(e, "prdrGrpNm"));
                dto.setRepItemCd(getTag(e, "repItemCd"));
                dto.setRepItemNm(getTag(e, "repItemNm"));
                dto.setFrmrNo(getTag(e, "frmrNo"));
                dto.setFrmrNm(getTag(e, "frmrNm"));
                dto.setItemCd(getTag(e, "itemCd"));
                dto.setItemNm(getTag(e, "itemNm"));
                dto.setRegistDate(getTag(e, "registDate"));
                dto.setStatus(getTag(e, "status"));
                certList.add(dto);
            }

            for (CertInsertDto dto : certList) {
                certInsertMapper.insertCertFromExternal(dto);
            }

            System.out.println("자동 인증정보 동기화 완료됨. 총 " + certList.size() + "건");

        } catch (Exception e) {
            System.err.println("자동 인증정보 동기화 실패: " + e.getMessage());
        }
    }

    private String getTag(Element e, String tag) {
        return e.getElementsByTagName(tag).getLength() > 0 ?
                e.getElementsByTagName(tag).item(0).getTextContent() : null;
    }
}
