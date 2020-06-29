package com.Boad;

import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



// ViewBoad에서 쓴 댓글의 내용을 해당 vo에 저장하는 클래스 입니다.
public class CommentVo {
    @NotEmpty(message = "댓글을 입력해 주세요 !")
    private String comment;
    private String nickname,title, boad_host;
    private String localDateTime;
    private int CommentIndex, board_index;

    public CommentVo(){

    }

    public CommentVo(String nickname, String comment, LocalDateTime localDateTime, int commentIndex, int board_index) {
        this.nickname = nickname;
        this.comment = comment;
        CommentIndex = commentIndex;
        this.board_index = board_index;
        this.localDateTime=localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:MM"));

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBoad_host() {
        return boad_host;
    }

    public void setBoad_host(String boad_host) {
        this.boad_host = boad_host;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = localDateTime;
    }

    public int getCommentIndex() {
        return CommentIndex;
    }

    public void setCommentIndex(int commentIndex) {
        CommentIndex = commentIndex;
    }

    public int getBoard_index() {
        return board_index;
    }

    public void setBoard_index(int board_index) {
        this.board_index = board_index;
    }
}
