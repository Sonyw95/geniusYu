package com.config;

import com.Boad.BoadDao;
import com.LoginService.AuthLoginService;
import com.Member.DaoMember;
import com.Member.MemberReg;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration // @Configuration 애노테이션은 해당 클래스를 스프링 설정 클래스로 지정하는 애노테이션 입니다.
public class AllConfig {

    @Bean(destroyMethod = "close")
    //	destroyMethod
    // 애플리케이션 컨텍스트를 닫을 때 Bean 인스턴스에서 호출 할 메소드의 선택적 이름 (예 close(): JDBC DataSource구현 의 메소드 또는 Hibernate SessionFactory오브젝트)입니다. API 참조.
    public DataSource dataSource() {
        // DataSourc 객체를 생성합니다.  커넥션풀 공부할 때 xml에 적었던 내용을 이제 javaClass에 써서 적용
        DataSource ds = new DataSource();
//        ds.setDriverClassName("oracle.jdbc.driver.OracleDriver"); // 맨처음 배웠던게 오라클 디비 이므로 오라클 드라이버 설정.
        ds.setInitialSize(2);
        //중요 커넥션 풀을 초기화 할대 생성할 초기 커넥션 개수를 지정한다 기본값은 10이고 현재는 2로 설정.
        ds.setMaxActive(30);
        // 커넥션풀에서 가져올 수 있는 최대 커넥션 수를 지정합니다 기본값은 100
        ds.setTestWhileIdle(true); // 유휴항 커넥션을 검사하는것을 허용한다
        ds.setMinEvictableIdleTimeMillis(1000 * 60 * 3);    // 최대 유지 시간 3분
        ds.setTimeBetweenEvictionRunsMillis(1000 * 10); // 10초 주기로 커넥션 풀이 유휴인지 확인
        return ds;
    }

    @Bean
    // @Transcaction 애노테이션을 제대로 작동시키기 위한 설정.
    //@EnableTransactionManagement 여기에 이게 필요함.
    // 동일한 방식으로 트랜젝션을 처리하기 위해
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        // 트랜잭션 연동에 사용할 dataSource를 설정한다
        tm.setDataSource(dataSource());
        return tm;
    }

    @Bean
    // 게시판안에 jdbcTemplate를 생성 했으니 해당 쿼리접속을 위해 선언.
    public BoadDao boadDao() {
        return new BoadDao(dataSource());
    }

    @Bean
    // 위와 동일.
    public DaoMember daoMember() {
        return new DaoMember(dataSource());
    }

    @Bean
    // 동일
    public MemberReg memberReg() {
        return new MemberReg(daoMember());
    }

    @Bean
    // 동일 합니다!
    public AuthLoginService authLoginService(){
        AuthLoginService authLoginService = new AuthLoginService();
        authLoginService.setDaoMember(daoMember());
        return authLoginService;
    }
}
