package org.oregami.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

public class OregamiConfiguration extends Configuration {

    public class DatabaseConfiguration {
        @NotNull
        private String driverClass = null;
        private String user = null;
        private String password = "";
        @NotNull
        private String url = null;
        @NotNull
        private Map<String, String> properties = Maps.newLinkedHashMap();

        public String getDriverClass() {
            return driverClass;
        }

        public void setDriverClass(String driverClass) {
            this.driverClass = driverClass;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Map<String, String> getProperties() {
            return properties;
        }

        public void setProperties(Map<String, String> properties) {
            this.properties = properties;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    @JsonProperty
    private DatabaseConfiguration databaseConfiguration = null;

    public DatabaseConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }

    public void setDatabaseConfiguration(DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
    }

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
