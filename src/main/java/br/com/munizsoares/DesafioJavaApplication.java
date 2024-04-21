package br.com.munizsoares;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
//(scanBasePackages = {"br.com.munizsoares.controller",
//"br.com.munizsoares.dto","br.com.munizsoares.entity","br.com.munizsoares.service","br.com.munizsoares.repository"})
public class DesafioJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioJavaApplication.class, args);
	}

}
