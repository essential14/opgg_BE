package com.site.opgg_be.member;

public interface MemberService {

    public MemberDTO login(MemberDTO dto);
    public int join(MemberDTO member);
    public int idcheck(MemberDTO dto);

}
