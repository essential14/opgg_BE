package com.site.opgg_be.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MemberController {
    private MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @PostMapping("/page/login")
    public MemberDTO login(@RequestBody MemberDTO dto){
        MemberDTO tmp = memberService.login(dto);
        System.out.println("tmp :" + tmp);
        return tmp;

    }
    @PostMapping("/page/join")
    public int join (@RequestBody MemberDTO dto){
        int tmp = memberService.join(dto);
        return tmp;
    }

    @PostMapping("/page/idcheck")
    public int idcheck(@RequestBody MemberDTO dto){
        int tmp = memberService.idcheck(dto);
        return tmp;
    }
}
