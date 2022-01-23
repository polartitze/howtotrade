package com.skripsi.howtotrade;

import com.skripsi.howtotrade.model.Topic;

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

	public static void main(String[] args) {
		SpringApplication.run(HowtotradeApplication.class, args);
	}

}
