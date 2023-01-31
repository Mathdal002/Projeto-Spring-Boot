package br.gov.go.sefaz.hrs.validation.input;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.gov.go.sefaz.hrs.model.Region;
import br.gov.go.sefaz.hrs.validation.business.RegionBusinessValidator;
import br.gov.go.sefaz.javaee.commons.exception.SefazValidationException;

@Component
public class RegionInputValidator implements Validator {

	@Autowired
	RegionBusinessValidator regionBusinessValidator;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Region.class.equals(clazz);
	}
	
	@Override
	public void validate(Object object, Errors errors) {
		/*** View validations ***/
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "hrs.input.region.name.notEmpty");
	}
	
	public void validateSave(Region region, Errors errors) {
		/*** View validations ***/
		this.validate(region, errors);
		if(!errors.hasErrors()) {
			/*** Business validations ***/
			try {
				this.regionBusinessValidator.validateSave(region);
			} catch(SefazValidationException e) {
				errors.rejectValue(e.getFieldId(), e.getMessageKey());
			}
		}
	}
}