package com.Member;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DaoMember {
    private JdbcTemplate jdbcTemplate;

    public DaoMember(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public MemberVo SearchByEmail(String email) {
        List<MemberVo> list = jdbcTemplate.query("SELECT * FROM BLOG_MEMBER WHERE email=?", new MemberRowMap(), email);
        return list.isEmpty() ? null : list.get(0);
    }
    public MemberVo SearchByNickname(String nickname) {
        List<MemberVo> list = jdbcTemplate.query("SELECT * FROM BLOG_MEMBER WHERE NICKNAME=?", new MemberRowMap(), nickname);
        return list.isEmpty() ? null : list.get(0);
    }

    public void memberUpdate(String oneline, String nickname) {
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstm = connection.prepareStatement("UPDATE BLOG_MEMBER SET ONELINE =? where nickname = ?");
                pstm.setString(1, oneline);
                pstm.setString(2, nickname);
                return pstm;
            }
        });
    }
    public void leaveMember(String email){
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstm = connection.prepareStatement("DELETE from BLOG_MEMBER WHERE email = ?");
                pstm.setString(1, email);
                return pstm;
            }
        });
    }

    public void MemberInsert(final MemberVo memberVo) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstm = connection.prepareStatement(
                        "insert into BLOG_MEMBER(password, email, nickname,ONELINE, Reg_Date) values (?,?,?,?,now())", new String[]{"BOARD_INDEX"});
                pstm.setString(1, memberVo.getPassword());
                pstm.setString(2, memberVo.getEmail());
                pstm.setString(3, memberVo.getNickname());
                pstm.setString(4, memberVo.getOneline());
                return pstm;
            }
        }, keyHolder);
    }

}
