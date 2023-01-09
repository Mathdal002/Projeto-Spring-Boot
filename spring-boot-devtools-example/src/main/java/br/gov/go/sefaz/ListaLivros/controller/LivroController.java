package br.gov.go.sefaz.ListaLivros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.gov.go.sefaz.ListaLivros.entity.Livro;
import br.gov.go.sefaz.ListaLivros.repository.LivroRepository;

@Controller
@RequestMapping("/Mathd")
public class LivroController {
	
	private LivroRepository livroRepository;
	
	@Autowired
	public LivroController(LivroRepository livroRepository) {
		this.livroRepository = livroRepository;
	}
	
	@RequestMapping(value = "/{autor}", method = RequestMethod.GET)
	public String listaLivros(@PathVariable("autor") String autor, Model model) {
		List<Livro> listaLivros = livroRepository.findByAutor(autor);
		if (listaLivros != null) {
			model.addAllAttributes(listaLivros);
		}
		return "listaLivros";
	}
	
	@RequestMapping(value = "/{autor}", method = RequestMethod.POST)
	public String adicionaLivrosAutor(@PathVariable("autor")String autor, Livro livro) {
		livro.setAutor(autor);
		livroRepository.save(livro);
		return "redirect:/{autor}";
	}
}
