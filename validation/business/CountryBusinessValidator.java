package br.gov.go.sefaz.hrs.validation.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.gov.go.sefaz.hrs.model.Country;
import br.gov.go.sefaz.javaee.commons.exception.SefazValidationException;

/**
 * <b>Secretaria de Estado da Fazenda de Goiás</b></br>
 * Supervisão de Arquitetura e Prospecção Tecnológica</br>
 * <i>SEFAZ-GO JavaEE Sample Project Archetype</i><br/><br/>
 * 
 * Country Service Validator.
 * 
 * @version 
 * <ul>
 * <li>$LastChangedRevision: 665 $</li>
 * <li>$LastChangedBy: SEFAZGO\thiago-sc $</li>
 * <li>$LastChangedDate: 2017-03-23 18:37:53 -0300 (Qui, 23 mar 2017) $</li>
 * </ul>
 * 
 * @author Thiago-SC and Renato-RS
 * 
 */
@Component
public class CountryBusinessValidator {
	
	private List<String> cityStates;
	
	public CountryBusinessValidator() {
		this.cityStates = new ArrayList<String>();
		cityStates.add("MONACO");
		cityStates.add("VATICAN");
		cityStates.add("SINGAPORE");
	}
	
	/**
	 * Business rules validation related to save operation.
	 * 
	 * @param country Object to be validated
	 * @throws SefazValidationException Validation errors
	 */
	public void validateSave(Country country) throws SefazValidationException {
		/*** Here the business rules will be validated ***/
		// 1. In our example project, city-states will not be allowed
		if(this.cityStates.contains(country.getName().toUpperCase())) {
			throw new SefazValidationException("In our example project, city-states will not be allowed", "name" , "hrs.business.country.citystates.notAllowed");
		}
	}
}