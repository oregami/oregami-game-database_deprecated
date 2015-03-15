package org.oregami.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class MailConfiguration extends Configuration {
	
    @NotEmpty
    @JsonProperty
    private final String host;
    
    @NotEmpty
    @JsonProperty
    private final String port;
    
	@NotEmpty
    @JsonProperty
    private final String username;

    @NotEmpty
    @JsonProperty
    private final String password; 
    
    public MailConfiguration(@JsonProperty("username") String username, @JsonProperty("password") String password, @JsonProperty("port") String port, @JsonProperty("host") String host) {
    	this.username = username;
    	this.password = password;
    	this.port = port;
    	this.host = host;
    }
    
    public String getHost() {
		return host;
	}    
    
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

    
    public String getPort() {
		return port;
	}

}