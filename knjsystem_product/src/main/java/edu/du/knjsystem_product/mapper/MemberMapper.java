package edu.du.knjsystem_product.mapper;

import edu.du.knjsystem_product.dto.GetExternalMember;
import edu.du.knjsystem_product.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    MemberDto selectMemberByNo(@Param("no") Long no);

    int existByLoginId(String loginId);
    void insertUser(GetExternalMember dto);
    void updateUser(GetExternalMember dto);
}