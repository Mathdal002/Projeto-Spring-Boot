package br.gov.go.sefaz.hrs.validation.input;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.gov.go.sefaz.hrs.model.Country;
import br.gov.go.sefaz.hrs.validation.business.CountryBusinessValidator;
import br.gov.go.sefaz.javaee.commons.exception.SefazValidationException;

@Component
public class CountryInputValidator implements Validator {

	@Autowired
	CountryBusinessValidator countryBusinessValidator;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Country.class.equals(clazz);
	}
	
	@Override
	public void validate(Object object, Errors errors) {
		/*** View validations ***/
		Country country = (Country) object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "hrs.input.country.id.notEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "hrs.input.country.name.notEmpty");
		if(country.getRegion() == null || StringUtils.isEmpty(country.getRegion().getId())) {
			errors.rejectValue("region.id", "hrs.input.country.region.notEmpty");
		}
	}
	
	public void validateSave(Country country, Errors errors) {
		/*** View validations ***/
		this.validate(country, errors);
		if(!errors.hasErrors()) {
			/*** Business validations ***/
			try {
				this.countryBusinessValidator.validateSave(country);
			} catch(SefazValidationException e) {
				errors.rejectValue(e.getFieldId(), e.getMessageKey());
			}
		}
	}
}