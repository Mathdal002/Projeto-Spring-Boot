package br.gov.go.sefaz.GestaoFesta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.go.sefaz.GestaoFesta.model.Convidado;

public interface Convidados extends JpaRepository<Convidado, Long> {
	
}
