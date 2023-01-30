package br.gov.go.sefaz.hrs.web.support;

import org.apache.commons.lang3.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

@Configuration
@PropertySources({
	@PropertySource(value="classpath:/sefazgo-security.properties", encoding= CharEncoding.UTF_8),
	@PropertySource(value="classpath:/br/gov/go/sefaz/hrs/hrs-security.properties", encoding= CharEncoding.UTF_8)
})
public class AppPropertiesSupport {
	
	@Autowired
	private Environment env;

	public String getProperty(String key){
		return env.getProperty(key);
	}
	
	public <T> T getProperty(String key, Class<T> targetType){
		return env.getProperty(key, targetType);
	}
}