package org.oregami.dropwizard;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;


public class OregamiGuiceInjector {

	private static final Injector INJECTOR = createInjector();
	
	private static Injector createInjector() {
    	return Guice.createInjector(
    			new OregamiGuiceModule(), new JpaPersistModule("data")
    	);
    }

	public static Injector get() {
		return INJECTOR;
	}
}