//package org.oregami.data;
//
//import com.google.inject.Inject;
//import org.oregami.entities.datalist.*;
//
//public class BaseListFinder {
//
//	@Inject
//	GameEntryTypeDao gameEntryTypeDao;
//
//	@Inject
//	ReleaseGroupReasonDao releaseGroupReasonDao;
//
//	@Inject
//	ReleaseTypeDao releaseTypeDao;
//
//	@Inject
//	TitleTypeDao titleTypeDao;
//
//	@Inject
//	DemoContentTypeDao demoContentTypeDao;
//
//    @Inject
//    ScriptDao scriptDao;
//
//	public GameEntryType getGameEntryType(String value) {
//		return gameEntryTypeDao.findByName(value);
//	}
//
//	public ReleaseType getReleaseType(String value) {
//		return releaseTypeDao.findByName(value);
//	}
//
//	public TitleType getTitleType(String value) {
//		return titleTypeDao.findByName(value);
//	}
//
//	public ReleaseGroupReason getReleaseGroupReason(String value) {
//		return releaseGroupReasonDao.findByName(value);
//	}
//
//	public DemoContentType getDemoContentType(String value) {
//		return demoContentTypeDao.findByName(value);
//	}
//
//    public Script getScript(String value) {
//        return scriptDao.findByExactName(value);
//    }
//
//}
