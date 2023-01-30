package br.gov.go.sefaz.hrs.web.constants;

public class ControllerMapping{
	
	private ControllerMapping() {
	}
	
	// Used as welcome file (path)
	public static final String WELCOME_PATH_ROOT						= 	"/";
	
	public static final String PATH_ACTION_NEW							= 	"/form";
	public static final String PATH_ACTION_EDIT							= 	"/form/{id}";
	public static final String PATH_ACTION_SAVE							= 	"/save";
	public static final String PATH_ACTION_DELETE						= 	"/delete/{id}";
	
	public static final String MODEL_ERROR_DETAILS_NAME 				= 	"errorDetails";
	
	public static final String DASHBOARD_PATH_ROOT						= 	"/dashboard";
	public static final String DASHBOARD_VIEW							= 	"index";
	public static final String DASHBOARD_MODEL_DTO						= 	"dashboardDto";
	
	public static final String COUNTRY_BUSINESS_NAME 					=	"Country";
	public static final String COUNTRY_PATH_ROOT						= 	"/countries";
	public static final String COUNTRY_VIEW_FORM 						= 	"country/country-form";
	public static final String COUNTRY_VIEW_LIST 						= 	"country/country-list";
	public static final String COUNTRY_VIEW_FORM_REDIRECT 				= 	"redirect:/countries/form";
	public static final String COUNTRY_VIEW_LIST_REDIRECT 				= 	"redirect:/countries";
	public static final String COUNTRY_MODEL_ENTITY_NAME 				= 	"country";
	public static final String COUNTRY_MODEL_ENTITIES_NAME 				= 	"countries";
	
	public static final String REGION_BUSINESS_NAME 					=	"Region";
	public static final String REGION_PATH_ROOT							= 	"/regions";
	public static final String REGION_VIEW_FORM 						= 	"region/region-form";
	public static final String REGION_VIEW_LIST 						= 	"region/region-list";
	public static final String REGION_VIEW_FORM_REDIRECT 				= 	"redirect:/regions/form";
	public static final String REGION_VIEW_LIST_REDIRECT 				= 	"redirect:/regions";
	public static final String REGION_MODEL_ENTITY_NAME 				= 	"region";
	public static final String REGION_MODEL_ENTITIES_NAME 				= 	"regions";
	
	public static final String AUTH_PATH_ROOT							= 	"/auth";
	public static final String AUTH_PATH_LOGIN 							= 	"/login";
	public static final String AUTH_PATH_LOGIN_FORM						= 	"/login-form";
	public static final String AUTH_PATH_LOGIN_ERROR					= 	"/login/error";
	public static final String AUTH_PATH_LOGOUT_SUCCESS					= 	"/logout/success";
	public static final String AUTH_PATH_LOGOUT_CONCURRENCY				= 	"/logout/concurrency";
	public static final String AUTH_PATH_DENIED 						= 	"/denied";
	public static final String AUTH_VIEW_LOGIN 							= 	"auth/login";
	public static final String AUTH_VIEW_LOGIN_FORM						= 	"auth/login-form";
	public static final String AUTH_VIEW_LOGOUT_SUCCESS					= 	"auth/logout-success";
	public static final String AUTH_VIEW_LOGOUT_CONCURRENCY				= 	"auth/logout-concurrency";
	public static final String AUTH_VIEW_DENIED							= 	"auth/access-denied";
	public static final String AUTH_MODEL_LOGIN_ERROR_DETAILS_NAME 		= 	"loginErrorDetails";
	
	public static final String ERRORS_PATH_ROOT							= 	"/errors";
	public static final String ERRORS_PATH_403							= 	"/403";
	public static final String ERRORS_PATH_404							= 	"/404";
	public static final String ERRORS_PATH_405							= 	"/405";
	public static final String ERRORS_PATH_500							= 	"/500";
	public static final String ERRORS_VIEW_PAGE_NOT_FOUND				= 	"errors/page-not-found";
	public static final String ERRORS_VIEW_METHOD_NOT_SUPPORTED			= 	"errors/method-not-supported";
	public static final String ERRORS_VIEW_INTERNAL_ERROR				= 	"errors/internal-error";
	
	public static final String PROFILE_PATH_ROOT						= 	"/profiles";
	public static final String PROFILE_ACTION_INFO						= 	"/info";
	public static final String PROFILE_VIEW_PROFILE						= 	"profile/profile-info";
	
	public static final String USER_MODEL_USUARIO_AUTENTICADO_NAME 		= 	"usuarioAutenticado";
	public static final String USER_MODEL_USUARIO_AUTENTICADO_DETAILS	= 	"usuarioAutenticadoDetails";
	
	public static final String SETTINGS_PATH_ROOT						=	"/settings";
	public static final String SETTINGS_PATH_INFORMATION				=	"/information";
	public static final String SETTINGS_VIEW_INFORMATION				=	"settings/setting";
	
	public static final String COMPONENTS_PATH_ROOT						=	"/components";
	public static final String COMPONENTS_PATH_TEST						=	"/test";
	public static final String COMPONENTS_VIEW_LIST 					=	"component/component-list";
	public static final String COMPONENTS_VIEW_TEST 					=	"component/component-test";
	
}