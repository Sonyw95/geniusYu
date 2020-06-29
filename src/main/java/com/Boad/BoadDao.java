package com.Boad;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import sun.dc.pr.PRError;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// 게시판에 관련된 sql 처리용 .
public class BoadDao {
    // Datasourc, Connection, Statemement, ResultSet등 직접 사용하지 않고 jdbcTemplate를 이용하여 간편하게 쿼리를 이용할수 있음.
    // DB랑 연결을 헤야하는데 해당 DB연결은 DB연결클래스 내부에 빈 객체로 주입받거나, 내부에서 새롭게 생성하여 받아도 된다.
    // 현재 사용한 방식은 DB연결 내부 빈 객체로 주입.
    // 구조적인 반복을 줄이기 위해 ( 커넥션, 클로즈 ) 이 두 패턴을 엮은 jdbcTemplate를 제공.
    private JdbcTemplate jdbcTemplate;

    public BoadDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 게시판 페이징을 위한 생성자 처음 게시판에 접속시 Default 값인 0 번부터 9개까지의 게시판을 리턴.
    public List<BoadListVo> listflush(int startNumber) {
        //   jdbcTemplate.query( 1번: 쿼리문인데 ?표가 포함되어있다 헤당 물음표는 마지막 파라미터 인자로 ?숫자만큼 순서대로 기입하면돔, 2번: RowMapper의 인터페이스를 구현 BoarRowMapper로
        //   ResultSet에서 데이터를 읽어와 설정한 객체로 변환해주는 기능을 제공. 여기서 설정한 객체는 List의 BoadListVo .
        List<BoadListVo> first_end_list = jdbcTemplate.query("select * from BLOG_BOARD order by board_index desc limit ?, 9", new BoardRowMapper(), startNumber);
        return first_end_list;
    }
    // 댓글의 리스트를 리턴하는 생성자. 위와 동일합니다.
    public List<CommentVo> commentList(int index){
        List<CommentVo> boad_comment = jdbcTemplate.query("select * from BLOG_COMMENT where board_index=?", new CommentRowMapper(),index);
        return boad_comment;
    }

    // 테크 게시판 페이지를 가저옵니다 위와 동일합니다.
    public List<BoadListVo> Typelist(int startNumber) {
        List<BoadListVo> list = jdbcTemplate.query("select * from BLOG_BOARD  where board_type ='테크' order by board_index desc limit ?, 10", new BoardRowMapper(), startNumber);
        return list;
    }

    // 게시판 글을 보기 위해서 게시판 1개의 내용을 리턴하는 생성자 인데 조건으로 해당 게시판의 번호가 붙어있다.
    public List<BoadListVo> SearchByBoad(int boad_index) {
        List<BoadListVo> list = jdbcTemplate.query("SELECT * FROM BLOG_BOARD WHERE BOARD_INDEX=?", new BoardRowMapper(), boad_index);
        return list;
    }

    // 페이징을 하기위한 쿼리문, 테크 게시판의 COUNT(*)  갯수를 리턴하는 함수 입니다.
    public int TypeCount() {
        List<Integer> list = jdbcTemplate.query(
                // 여기선 특이하게 내부 익명클래스로 선언하여서 RowMapper를 이용하고 있습니다.
                // 익명 클래스로 선언한 이유? 위 처럼 여러게의 값을 받는 것이 아니고 하나의 결과값만 받기에 내무 익명 클래스로 선언.
                "SELECT COUNT(*) FROM BLOG_BOARD where board_type ='테크'", new RowMapper<Integer>() {
                    @Override
                    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                        return resultSet.getInt(1);
                    }
                });
        return list.get(0);
    }
    // 위와 동일 전체 게시판을 페이징하기 위한 쿼리문.
    public int listCount() {
        // 위와 다르게 짧은게 특징이다. jdbcTemplate의 queryForObject 메서드는 실행하여 행의 결과가 한개일 경우 사용할 수 있는 메서드.
        // 1번 파라미터는 결과가 한개인 쿼리문과 2번파라미터는 그 쿼리문의 결과 값의 타입을 지정.
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM BLOG_BOARD",Integer.class);

        return count;
    }

    // 게시글을 등록하기 위한 생성자 입니다.
    public int insert(BoadWriteVo boadWriteVo) {
        // KeyHolder의 GeneratedKeyHolder란 무엇인가? 해당 게시판은 인덱스 번호가 자동으로 생성되는 테이블 입니다.
        // 해당 테이블의 자동으로 생성되는 키값을 구현하기 위해 선언 하는 클래스 입니다.
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int index;
        // update() 메서드는 PrepareStatementCreator()파라미터와 , keyholder파라미터 총 2개의 파라미터를 갖는다.
        // PreparedStatemenet를 내부 익명클래스로 선언하여 1번 파라미터를 받고 있다.
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                // 여기서 중요합니다. Connection의 preapredStatement() 생성자를 이용해서 Pstm객체를 생성을 하는데.. 두번째 파라미터는 String의 배열인 {"BOARD_INDEX}를 주었다.
                // 해당 파라미터는 자동 생성되는 칼럼 목록을 지정할 때 사용합니다. 즉 테이블의 자동으로 생성되는 Board_index 칼럼이므로 파라미터 값으로 Board_index를 주었다.
                PreparedStatement pstm = connection.prepareStatement("insert into BLOG_BOARD(nickname,board_title,board_type,board_area,reg_date) values(?,?,?,?,now())",
                        new String[]{"BOARD_INDEX"});
                pstm.setString(1, boadWriteVo.getNickname());
                pstm.setString(2, boadWriteVo.getTitle());
                pstm.setString(3, boadWriteVo.getBoadType());
                pstm.setString(4, boadWriteVo.getArea_Boad());
                return pstm;
            }
        }, keyHolder);
        // 두번째 파라미터로 keyholder객체리를 전달, PrepareStaemenet 를 실행한 후 자동으로 생성된 값을 keyholder에 보관!
        // 보관된 값을 getKey() 생성자를 이용하여 추출. 해당 메서드는 Number를 리턴합니다.
        Number boadIndex = keyHolder.getKey();
        // Number의 값을 int형으로 형변환 하기 위해서 사용합니다.
        index = boadIndex.intValue();

        return index;
    }

    // 게시판을 삭제하기 위한 생성자
    public void delete(String title, String name) {
        // 위와 다르게 내부 익명 클래스가 아닌, PrepareSatementcreator()를 인터페스로 구현.
        // 해당 게시판을 지우기 위해 제목과 게시글 주인의 이름을 파라미터 인자로 받습니다.
        jdbcTemplate.update(new DeleteBoardPstmt(title, name));
    }

    // 게시글 수정을 위한 생성자
    // 위의 위생성자와 동일 한 내용입니다.
    public void Update(BoadWriteVo boadWriteVo) {
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstm = connection.prepareStatement("UPDATE BLOG_BOARD SET BOARD_AREA =? and BOARD_TYPE=? WHERE BOARD_TITLE=? and NICKNAME=?");
                pstm.setString(1, boadWriteVo.getArea_Boad());
                pstm.setString(2, boadWriteVo.getBoadType());
                pstm.setString(3, boadWriteVo.getTitle());
                pstm.setString(4, boadWriteVo.getNickname());
                return pstm;
            }
        });
    }

    // 댓글 입력을 위한 생성자 입니다.
    // 이역시 위와 동일 합니다.
    public void comment(CommentVo commentVo) {
        //                                                                              이름 타이틀 타입, 내용
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstm = connection.prepareStatement("insert into BLOG_COMMENT( nickname, board_index,comment, comment_date) value (?,?,?,now())",
                        new String[]{"comment_index"});
                pstm.setString(1, commentVo.getNickname());
                pstm.setInt(2,commentVo.getBoard_index());
                pstm.setString(3, commentVo.getComment());

                return pstm;
            }
        });
    }
}