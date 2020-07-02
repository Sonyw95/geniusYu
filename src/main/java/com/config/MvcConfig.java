package com.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;

@Configuration // 해당 클래스는 스프링 설정 클래스 입니다.
@EnableWebMvc // 스프링 MVC를 활성화 합니다 MVC에 필요한 다양한 설정을 생성합니다, 내부적으로  다양한 빈설정을 추가해준다
public class MvcConfig implements WebMvcConfigurer {
    //WebMvcConfigurer : 이 인터페이스는 스프링 MVC의 개별설정을 조정할 때 사용한다.


    @Override
    // xml의 dispatcher 의 경로 / 로 주었을 때 jsp/html/css 등 올바르게 처리하기 위한 설정
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("boad/LoadPage");
    }


    @Override
    // jsp를 이용해서 실행결과를 보기 위한 설정입니다.
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/view/",".jsp");
    }

    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
        ms.setBasename("message.label");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }



}
