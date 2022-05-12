package com.skripsi.howtotrade;


import java.io.IOException;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@AutoConfigureMybatis
@MapperScan("com.skripsi.howtotrade.mapper")
@ComponentScan("com.skripsi.howtotrade")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class })
public class HowtotradeApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(HowtotradeApplication.class, args);
		
//		 openHomePage();
	}
	
	private static void openHomePage() throws IOException {
		Runtime rt = Runtime.getRuntime();
		rt.exec("rundll32 url.dll,FileProtocolHandler " + "http://localhost:8080");
	}
}
