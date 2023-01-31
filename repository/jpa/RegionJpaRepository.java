package br.gov.go.sefaz.hrs.repository.jpa;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import br.gov.go.sefaz.hrs.model.Region;

/**
 * <b>Secretaria de Estado da Fazenda de Goiás</b></br>
 * Supervisão de Arquitetura e Prospecção Tecnológica</br>
 * <i>SEFAZ-GO JavaEE Sample Project Archetype</i><br/><br/>
 * 
 * Region Repository.
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
public interface RegionJpaRepository extends JpaRepository<Region, Integer> {

	/**
	 * Finds a Region by Name.
	 * 
	 * @param name Name of the record to be retrieved.
	 * @return The Region record with the parameter specified.
	 */
	List<Region> findByNameContainingIgnoreCase(String name);
	
	/**
	 * Finds all Regions.
	 * 
	 * @return All Regions available on database.
	 */
	@QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	public List<Region> findAll();
}