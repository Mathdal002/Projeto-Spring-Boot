package br.gov.go.sefaz.hrs.repository.jdbc.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import br.gov.go.sefaz.hrs.model.Country;
import br.gov.go.sefaz.hrs.model.Region;

/**
 * <b>Secretaria de Estado da Fazenda de Goiás</b></br>
 * Supervisão de Arquitetura e Prospecção Tecnológica</br>
 * <i>SEFAZ-GO JavaEE Sample Project Archetype</i><br/><br/>
 * 
 * Region JDBC Row Mapper.
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
public class RegionJdbcResultSetExtractor implements ResultSetExtractor<List<Region>> {

	@Override
	public List<Region> extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		Map<Integer, Region> regionsMap = new HashMap<Integer, Region>();
		
		while (rs.next()) {
			
			Integer regionId = rs.getInt("REGION_ID");
			Region region = regionsMap.get(regionId);
			
			if (region == null) {
				// Region data
				region = new Region();
				region.setId(regionId);
				region.setName(rs.getString("REGION_NAME"));
				
				regionsMap.put(regionId, region);
			}
			
			// Country data
			Country country = null;
			
			String countryId = rs.getString("COUNTRY_ID");
			
			if (countryId != null) {
				
				country = new Country();
				country.setId(countryId);
				country.setName(rs.getString("COUNTRY_NAME"));
				country.setRegion(region);
			}
			
			addCountry(country, region);
		}
		
		return new ArrayList<Region>(regionsMap.values());
	}
	
	private void addCountry(Country country, Region region){
		
		List<Country> countries = region.getCountries();
		
		if (countries == null) {
			countries = new ArrayList<Country>();
		}
		
		countries.add(country);
		region.setCountries(countries);
	}
}