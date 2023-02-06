package com.example.config.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import com.example.board.view.BoardDownLoadView;


@Configuration //어노테이션으로 처리가 안 되는것들은 따로 Configuration 어노테이션으로 처리한다.
@ComponentScan
public class ViewConfig {

	@Bean
	public BeanNameViewResolver viewResolver(){
		BeanNameViewResolver bnvResolver = new BeanNameViewResolver();
		bnvResolver.setOrder(1);
		return bnvResolver;
	}
	
	@Bean("download")
	public View boardDownLoadView() {
		return new BoardDownLoadView();
	}
}







