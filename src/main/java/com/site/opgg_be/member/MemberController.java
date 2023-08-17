package com.site.opgg_be.member;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    private MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @PostMapping("/page/login")
    public MemberDTO login(@RequestBody MemberDTO dto){
        MemberDTO tmp = memberService.login(dto);
        return tmp;
    }
    @PostMapping("/page/join")
    public int join (@RequestBody MemberDTO member){
        int tmp = memberService.join(member);
        return tmp;
    }

}
