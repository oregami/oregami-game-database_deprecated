package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

public class JPAInitializer {

	@Inject
	public JPAInitializer(final PersistService service) {
		service.start();
	}
}