package br.gov.go.sefaz.hrs.repository.jdbc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.gov.go.sefaz.hrs.model.Region;
import br.gov.go.sefaz.hrs.repository.jdbc.RegionJdbcRepository;
import br.gov.go.sefaz.hrs.repository.jdbc.extractor.RegionJdbcResultSetExtractor;

/**
 * <b>Secretaria de Estado da Fazenda de Goiás</b></br>
 * Supervisão de Arquitetura e Prospecção Tecnológica</br>
 * <i>SEFAZ-GO JavaEE Sample Project Archetype</i><br/><br/>
 * 
 * Employee Repository (JDBC Template Implementation).
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
@Repository("regionJdbcRepository")
public class RegionJdbcTemplateRepository implements RegionJdbcRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Region> findAll(){
		
		StringBuilder query = new StringBuilder();
		
		query
			.append("SELECT R.REGION_ID, R.REGION_NAME, C.COUNTRY_ID, C.COUNTRY_NAME FROM REGIONS R ")
			.append("	LEFT OUTER JOIN COUNTRIES C ")
			.append("		ON R.REGION_ID = C.REGION_ID ")
			.append("	ORDER BY REGION_ID, COUNTRY_ID ");
			
		return jdbcTemplate.query(query.toString(), new RegionJdbcResultSetExtractor());
	}
	
		
	@Override
	public Long count() {
		return jdbcTemplate.queryForObject("SELECT COUNT(REGION_ID) FROM REGIONS", Long.class);
	}
}