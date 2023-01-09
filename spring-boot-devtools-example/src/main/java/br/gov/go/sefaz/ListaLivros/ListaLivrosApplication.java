package br.gov.go.sefaz.ListaLivros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"br.gov.go.sefaz.ListaLivros.entity" })
@EnableJpaRepositories(basePackages = {"br.gov.go.sefaz.ListaLivros.repository"})
@ComponentScan(basePackages = {"br.gov.go.sefaz.ListaLivros.controller"})
public class ListaLivrosApplication { 
	
     public static void main(String[] args) {
            SpringApplication.run(ListaLivrosApplication.class, args);
      }
}