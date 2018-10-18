package com.zjts.broadband;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.zjts.broadband.*.dao")
public class BroadbandApplication {

	public static void main(String[] args) {
		SpringApplication.run(BroadbandApplication.class, args);
	}
}
