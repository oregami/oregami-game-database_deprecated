package org.oregami.data;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import org.oregami.dropwizard.OregamiApplication;
import org.oregami.entities.datalist.*;

public class BaseListFinder {

	private static BaseListFinder instance = null;
	
	public static BaseListFinder instance() {
		if (instance==null) {
			JpaPersistModule jpaPersistModule = new JpaPersistModule(OregamiApplication.JPA_UNIT);
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
	ReleaseTypeDao releaseTypeDao;

	@Inject
	TitleTypeDao titleTypeDao;
	
	@Inject
	DemoContentTypeDao demoContentTypeDao;
	
	public GameEntryType getGameEntryType(String value) {
		return gameEntryTypeDao.findByName(value);
	}
	
	public ReleaseType getReleaseType(String value) {
		return releaseTypeDao.findByName(value);
	}
	
	public TitleType getTitleType(String value) {
		return titleTypeDao.findByName(value);
	}
	
	public ReleaseGroupReason getReleaseGroupReason(String value) {
		return releaseGroupReasonDao.findByName(value);
	}
	
	public DemoContentType getDemoContentType(String value) {
		return demoContentTypeDao.findByName(value);
	}
	
}
