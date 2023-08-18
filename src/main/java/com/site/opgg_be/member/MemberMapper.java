package com.site.opgg_be.member;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {
    @Select("select * from member where id=#{id} and password=#{password}")
    public MemberDTO login(MemberDTO dto);

    @Insert("insert into member values (#{id}, #{name},#{birthday},#{password})")
    public int join (MemberDTO member);

    @Select("select count(*) from member where id=#{id}")
    public int idcheck(MemberDTO dto);


}
