package br.gov.go.sefaz.hrs.validation.business;

import org.springframework.stereotype.Component;

import br.gov.go.sefaz.hrs.model.Region;
import br.gov.go.sefaz.javaee.commons.exception.SefazValidationException;

/**
 * <b>Secretaria de Estado da Fazenda de Goiás</b></br>
 * Supervisão de Arquitetura e Prospecção Tecnológica</br>
 * <i>SEFAZ-GO JavaEE Sample Project Archetype</i><br/><br/>
 * 
 * Region Service Validator.
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
@Component
public class RegionBusinessValidator {
	
	/**
	 * Business rules validation related to save operation.
	 * 
	 * @param region Object to be validated
	 * @throws SefazValidationException Validation errors
	 */
	public void validateSave(Region region) throws SefazValidationException {
		/*** Here the business rules will be validated ***/
	}
}