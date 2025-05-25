package com.ssafy.ticket_moa_app;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@EnableCaching
@SpringBootApplication
@MapperScan(basePackages = "com.ssafy.ticket_moa_app.dao")
public class TicketMoaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketMoaAppApplication.class, args);
	}

	@Bean
	public OpenAPI postsApi() {
		Info info = new Info()
				.title("TicketMoa Rest API")
				.description("<h3>[티켓모아] 에서 제공되는 Rest api의 문서 제공</h3><br>")
				.contact(new Contact().name("ssafy").email("ssafy@ssafy.com"))
				.license(new License().name("SSAFY License").url("https://www.ssafy.com/ksp/jsp/swp/etc/swpPrivacy.jsp"))
				.version("1.0");

		return new OpenAPI()
				.info(info);
	}


}
