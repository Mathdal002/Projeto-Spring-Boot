package br.gov.go.sefaz.hrs.repository.jdbc;

import java.util.List;

import br.gov.go.sefaz.hrs.model.Region;

/**
 * <b>Secretaria de Estado da Fazenda de Goiás</b></br>
 * Supervisão de Arquitetura e Prospecção Tecnológica</br>
 * <i>SEFAZ-GO JavaEE Sample Project Archetype</i><br/><br/>
 * 
 * Region Repository (JDBC).
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
public interface RegionJdbcRepository {

	/**
	 * Finds all Regions
	 * @return All Regions available on database.
	 */
	public List<Region> findAll();
	
	/**
	 * Returns the number of Region available.
	 * @return the number of Region
	 */
	public Long count();
}