package com.Boad;

import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


// PreparedStatementCreator를 내부 클래스가 아닌 인터페이스로 상속받아 구현 시킨 클래스 입니다.
public class DeleteBoardPstmt implements PreparedStatementCreator {
    private String title, name;
    public DeleteBoardPstmt(String t, String n){
        this.name=n;
        this.title=t;
    }
    @Override
    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM BLOG_BOARD WHERE BOARD_TITLE =? AND NICKNAME=?");
        pstm.setString(1, title);
        pstm.setString(2, name);
        return pstm;
    }
}
