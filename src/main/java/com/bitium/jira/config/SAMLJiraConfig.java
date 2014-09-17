package com.bitium.jira.config;

import org.apache.commons.lang.StringUtils;

import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.bitium.saml.SAMLConfig;

public class SAMLJiraConfig implements SAMLConfig {
	
	private PluginSettings pluginSettings;

	private String defaultBaseURL;
	
	public static final String ENTITY_ID_SETTING = "saml2.entityId";
	public static final String LOGIN_URL_SETTING = "saml2.loginUrl";
	public static final String LOGOUT_URL_SETTING = "saml2.logoutUrl";
	public static final String X509_CERTIFICATE_SETTING = "saml2.x509Certificate";
	public static final String IDP_REQUIRED_SETTING = "saml2.idpRequired";
	
	public void setPluginSettingsFactory(PluginSettingsFactory pluginSettingsFactory) {
		this.pluginSettings = pluginSettingsFactory.createGlobalSettings();
	}
	
	public void setLoginUrl(String loginUrl) {
		pluginSettings.put(LOGIN_URL_SETTING, loginUrl);
	}

	public void setLogoutUrl(String logoutUrl) {
		pluginSettings.put(LOGOUT_URL_SETTING, logoutUrl);		
	}

	public void setEntityId(String entityId) {
		pluginSettings.put(ENTITY_ID_SETTING, entityId);		
	}

	public void setX509Certificate(String x509Certificate) {
		pluginSettings.put(X509_CERTIFICATE_SETTING, x509Certificate);		
	}

	public void setIdpRequired(String idpRequired) {
		pluginSettings.put(IDP_REQUIRED_SETTING, idpRequired);		
	}
	
	public String getIdpRequired() {
		return StringUtils.defaultString((String)pluginSettings.get(IDP_REQUIRED_SETTING));
	}
	
	public boolean getIdpRequiredFlag() {
		if (StringUtils.defaultString((String)pluginSettings.get(IDP_REQUIRED_SETTING)).equals("true")) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getLoginUrl() {
		return StringUtils.defaultString((String)pluginSettings.get(LOGIN_URL_SETTING));
	}

	public String getLogoutUrl() {
		return StringUtils.defaultString((String)pluginSettings.get(LOGOUT_URL_SETTING));
	}

	public String getIdpEntityId() {
		return StringUtils.defaultString((String)pluginSettings.get(ENTITY_ID_SETTING));
	}

	public String getX509Certificate() {
		return StringUtils.defaultString((String)pluginSettings.get(X509_CERTIFICATE_SETTING));
	}

	public void setDefaultBaseUrl(String defaultBaseURL) {
		this.defaultBaseURL = defaultBaseURL;		
	}
	
	public String getAlias() {
		return "confluenceSAML";
	}

	public String getBaseUrl() {
		return StringUtils.defaultString(defaultBaseURL);
	}

	public String getSpEntityId() {
		return defaultBaseURL + "/" + getAlias();
	}

}
