package com.Member;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRowMap implements RowMapper<MemberVo> {
    @Override
    public MemberVo mapRow(ResultSet resultSet, int i) throws SQLException {
        MemberVo memberVo= new MemberVo(
                resultSet.getString("password"),
                resultSet.getString("nickname"),
                resultSet.getString("Email"));
        memberVo.setLocalDateTime(  resultSet.getTimestamp("Reg_Date").toLocalDateTime());
        return memberVo;
    }
}
