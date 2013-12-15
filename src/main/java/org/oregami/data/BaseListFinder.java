package org.oregami.data;

import org.oregami.dropwizard.OregamiService;
import org.oregami.entities.datalist.DemoContentType;
import org.oregami.entities.datalist.GameEntryType;
import org.oregami.entities.datalist.ReleaseGroupReason;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.Transactional;
import com.google.inject.persist.jpa.JpaPersistModule;

public class BaseListFinder {

	private static BaseListFinder instance = null;
	
	public static BaseListFinder instance() {
		if (instance==null) {
			JpaPersistModule jpaPersistModule = new JpaPersistModule(OregamiService.JPA_UNIT);
			Injector injector = Guice.createInjector(jpaPersistModule);
			instance = injector.getInstance(BaseListFinder.class);
			PersistService persistService = injector.getInstance(PersistService.class);
			persistService.start();
		}
		return instance;
	}
	
	@Inject
	GameEntryTypeDao gameEntryTypeDao; 
	
	@Inject
	ReleaseGroupReasonDao releaseGroupReasonDao;

	@Inject
	DemoContentTypeDao demoContentTypeDao;
	
	@Transactional
	public GameEntryType getGameEntryType(String value) {
		return gameEntryTypeDao.findByName(value);
	}

	@Transactional
	public ReleaseGroupReason getReleaseGroupReason(String value) {
		return releaseGroupReasonDao.findByName(value);
	}
	
	@Transactional
	public DemoContentType getDemoContentType(String value) {
		return demoContentTypeDao.findByName(value);
	}
	
}
