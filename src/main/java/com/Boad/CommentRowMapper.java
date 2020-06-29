package com.Boad;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

// 마찬가지로 댓글 테이블의 내용을 저장.
public class CommentRowMapper implements RowMapper {
    @Override
    public CommentVo mapRow(ResultSet resultSet, int i) throws SQLException {
        CommentVo commentVo = new CommentVo(
                resultSet.getString("nickname"),
                resultSet.getString("comment"),
                resultSet.getTimestamp("comment_date").toLocalDateTime(),
                resultSet.getInt("board_index"),
                resultSet.getInt("comment_index")
        );
        return commentVo;
    }
}
