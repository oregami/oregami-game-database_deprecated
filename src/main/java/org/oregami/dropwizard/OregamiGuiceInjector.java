package org.oregami.dropwizard;

import com.google.inject.Guice;
import com.google.inject.Injector;


public class OregamiGuiceInjector {

	private static final Injector INJECTOR = createInjector();
	
	private static Injector createInjector() {
    	return Guice.createInjector(
    			new OregamiGuiceModule()
    	);
    }

	public static Injector get() {
		return INJECTOR;
	}
}