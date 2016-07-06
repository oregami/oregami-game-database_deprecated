package org.oregami.data;

import org.oregami.entities.datalist.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseListFinder {

	@Autowired
	GameEntryTypeDao gameEntryTypeDao;

	@Autowired
	ReleaseGroupReasonDao releaseGroupReasonDao;

	@Autowired
	ReleaseTypeDao releaseTypeDao;

	@Autowired
	TitleTypeDao titleTypeDao;

	@Autowired
	DemoContentTypeDao demoContentTypeDao;

    @Autowired
    ScriptDao scriptDao;

    @Autowired
    ReleaseStateDao releaseStateDao;

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

    public ReleaseState getReleaseState(String value) {
        return releaseStateDao.findByName(value);
    }

	public DemoContentType getDemoContentType(String value) {
		return demoContentTypeDao.findByName(value);
	}

    public Script getScript(String value) {
        return scriptDao.findByExactName(value);
    }

}
