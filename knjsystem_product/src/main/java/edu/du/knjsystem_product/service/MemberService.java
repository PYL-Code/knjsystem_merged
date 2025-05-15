package edu.du.knjsystem_product.service;

import edu.du.knjsystem_product.dto.GetExternalMember;
import edu.du.knjsystem_product.dto.MemberDto;
import edu.du.knjsystem_product.mapper.MemberMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public MemberDto getMemberByNo(@Param("no") Long no) {
        return memberMapper.selectMemberByNo(no);
    }

    public void saveOrUpdate (GetExternalMember dto) {
        if(memberMapper.existByLoginId(dto.getLoginId()) > 0) {
            memberMapper.updateUser(dto);
        } else {
            memberMapper.insertUser(dto);
        }
    }
}
