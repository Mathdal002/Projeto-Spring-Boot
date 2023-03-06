package br.gov.go.sefaz.GestaoFesta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.gov.go.sefaz.GestaoFesta.model.Convidado;
import br.gov.go.sefaz.GestaoFesta.repository.Convidados;

@Controller
@RequestMapping("/convidados")
public class ConvidadosController {
	@Autowired
	private Convidados convidados;
	
	@GetMapping("/convidados")
	public ModelAndView Listar() {
		ModelAndView modelAndView = new ModelAndView("ListaConvidados");
		modelAndView.addObject("Convidado", convidados.findAll());
		return modelAndView;
	}
	
	@PostMapping
	public String salvar(Convidado convidado) {
		this.convidados.save(convidado);
		return "redirect:/convidados";
	}
}
