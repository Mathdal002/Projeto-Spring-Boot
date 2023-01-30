package br.gov.go.sefaz.hrs.web.config;

public class AppWebConfigurationParameters {

	private AppWebConfigurationParameters() {
	}
	
	public static final String PACKAGE_CONTROLLER				= 	"br.gov.go.sefaz.hrs.web.controller";
	public static final String PACKAGE_SUPPORT					= 	"br.gov.go.sefaz.hrs.web.support";
	public static final String PACKAGE_VALIDATION				= 	"br.gov.go.sefaz.hrs.validation";
	
	public static final String[] WEB_IGNORING_PATHS				= new String[]{"/resources/**", "/jasmine/**", "/auth/**", "/errors/**","/health/**"};
}