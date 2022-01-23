package com.skripsi.howtotrade;

import com.skripsi.howtotrade.model.Topic;

import java.io.IOException;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@AutoConfigureMybatis
@MappedTypes(Topic.class)
@MapperScan("com.skripsi.howtotrade.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class HowtotradeApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(HowtotradeApplication.class, args);
		openHomePage();
	}
	
	private static void openHomePage() throws IOException {
		Runtime rt = Runtime.getRuntime();
		rt.exec("rundll32 url.dll,FileProtocolHandler " + "http://localhost:8080");
	}
}
