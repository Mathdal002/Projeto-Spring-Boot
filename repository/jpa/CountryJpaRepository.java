package br.gov.go.sefaz.hrs.repository.jpa;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import br.gov.go.sefaz.hrs.model.Country;
import br.gov.go.sefaz.hrs.model.Region;

/**
 * <b>Secretaria de Estado da Fazenda de Goiás</b></br>
 * Supervisão de Arquitetura e Prospecção Tecnológica</br>
 * <i>SEFAZ-GO JavaEE Sample Project Archetype</i><br/><br/>
 * 
 * Country Repository.
 * 
 * @version 
 * <ul>
 * <li>$LastChangedRevision: 664 $</li>
 * <li>$LastChangedBy: SEFAZGO\thiago-sc $</li>
 * <li>$LastChangedDate: 2017-03-21 19:31:41 -0300 (Ter, 21 mar 2017) $</li>
 * </ul>
 * 
 * @author Thiago-SC and Renato-RS
 * 
 */
@Repository
public interface CountryJpaRepository extends JpaRepository<Country, String> {

	/**
	 * Finds the Countries by Name.
	 * 
	 * @param name Name of the records to be retrieved.
	 * @return The Countries with the parameter specified.
	 */
	List<Country> findByNameContainingIgnoreCase(String name);
	
	/**
	 * Finds the Countries by Region.
	 * 
	 * @param region Region of the records to be retrieved.
	 * @return The Countries with the parameter specified.
	 */
	@QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	List<Country> findByRegion(Region region);
	
	/**
	 * Finds all Countries.
	 * 
	 * @return All Countries available on database.
	 */
	@QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	public List<Country> findAll();
}