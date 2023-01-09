package br.gov.go.sefaz.ListaLivros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.go.sefaz.ListaLivros.entity.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
	
	List<Livro> findByAutor(String autor);
	List<Livro> findByTitulo(String titulo);
	Livro findByIsbn(String isbn);

}
