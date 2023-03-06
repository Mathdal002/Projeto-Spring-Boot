package br.gov.go.sefaz.accesscontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AccessControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccessControlApplication.class, args);
	}

}
