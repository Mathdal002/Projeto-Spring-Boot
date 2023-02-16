package br.gov.go.sefaz.EventoApp.controller;

import org.springframework.stereotype.Controller;

@Controller("/")
public class IndexController {
	public String index() {
		return "index";
	}
}
