package org.oregami.dropwizard;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;

public class OregamiConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty
    private final PhantomJSConfiguration phantomJSConfiguration = new PhantomJSConfiguration();

	public PhantomJSConfiguration getPhantomJSConfiguration() {
		return phantomJSConfiguration;
	}
	
    @Valid
    @NotNull
    @JsonProperty
	private MailConfiguration mailConfiguration;
    
    public MailConfiguration getMailConfiguration() {
		return mailConfiguration;
	};
	
}
