package com.site.opgg_be.member;

import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
    private MemberMapper mapper;

    public MemberServiceImpl(MemberMapper mapper)
    {
        this.mapper = mapper;
    }

    @Override
    public MemberDTO login(MemberDTO dto) {
        MemberDTO res = mapper.login(dto);
        return res;
    }

    @Override
    public int join(MemberDTO member) {
        int res = mapper.join(member);
        return res;
    }

    @Override
    public int idcheck(MemberDTO dto) {
        int res = mapper.idcheck(dto);
        return res;
    }
}
