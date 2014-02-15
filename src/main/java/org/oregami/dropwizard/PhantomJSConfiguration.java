package org.oregami.dropwizard;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PhantomJSConfiguration {
	
    @NotEmpty
    @JsonProperty
    private String phantomjsCommandLocation;
    
    @NotEmpty
    @JsonProperty
    private String rasterizeJSFileLocation;

	public String getPhantomjsCommandLocation() {
		return phantomjsCommandLocation;
	}

	public String getRasterizeJSFileLocation() {
		return rasterizeJSFileLocation;
	}

    

}