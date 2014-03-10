package org.oregami.dropwizard;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;

public class OregamiConfiguration extends Configuration {


	
    @Valid
    @JsonProperty
    private boolean initBaseLists = false;

    @Valid
    @JsonProperty
    private boolean initGames = false;
	
    public void setInitBaseLists(boolean initBaseLists) {
		this.initBaseLists = initBaseLists;
	}

	public void setInitGames(boolean initGames) {
		this.initGames = initGames;
	}

	public boolean isInitBaseLists() {
		return initBaseLists;
	}

	public boolean isInitGames() {
		return initGames;
	}

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
