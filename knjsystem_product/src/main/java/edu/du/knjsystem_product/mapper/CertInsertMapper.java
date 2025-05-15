package edu.du.knjsystem_product.mapper;

import edu.du.knjsystem_product.dto.CertInsertDto;
import edu.du.knjsystem_product.external.dto.CertOutDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CertInsertMapper {

    @Insert("""
    MERGE INTO T_SG_A003 (
        RCV_SEQ, MOD_SEQ, PRDR_GRP_NO, REG_SEQ, STD_CERT_NO,
        CERT_GB_CD, CERT_GB_NM, CERT_STRT_DT, CERT_END_DT,
        FRMR_CERT_STT, CERT_STATE_NM, PSN_GROUP_GB, PSN_GROUP_NM,
        PRDR_GRP_NM, REP_ITEM_CD, REP_ITEM_NM,
        FRMR_NO, FRMR_NM, ITEM_CD, ITEM_NM,
        REGIST_DATE, STATUS
    ) KEY (STD_CERT_NO, REG_SEQ)
    VALUES (
        #{rcvSeq}, #{modSeq}, #{prdrGrpNo}, #{regSeq}, #{stdCertNo},
        #{certGbCd}, #{certGbNm}, #{certStrtDt}, #{certEndDt},
        #{frmrCertStt}, #{certStateNm}, #{psnGroupGb}, #{psnGroupNm},
        #{prdrGrpNm}, #{repItemCd}, #{repItemNm},
        #{frmrNo}, #{frmrNm}, #{itemCd}, #{itemNm},
        #{registDate}, #{status}
    )
""")
    void insertCertFromExternal(CertInsertDto dto);
}