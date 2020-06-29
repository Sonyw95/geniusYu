package com.Boad;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
//BoadDao의 익명클래스 선언과 다르게 RowMapper의 인터페이스를 구현한 클래스.
public class BoardRowMapper implements RowMapper<BoadListVo> {
    @Override
    //ResultSet을 불러와 칼럼의 값을 객체에 저장.
    public BoadListVo mapRow(ResultSet resultSet, int i) throws SQLException {
        BoadListVo bol = new BoadListVo(
                resultSet.getString("nickname"),
                resultSet.getString("board_title"),
                resultSet.getString("board_area"),
                resultSet.getInt("board_index"),
                resultSet.getTimestamp("REG_DATE").toLocalDateTime(),
                resultSet.getString("BOARD_Type")
                );
        return bol;
    }
}
