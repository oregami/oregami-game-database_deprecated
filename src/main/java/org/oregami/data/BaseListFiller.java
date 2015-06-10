package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import org.oregami.entities.datalist.*;
import org.oregami.util.StartHelper;

public class BaseListFiller {

	private static BaseListFiller instance;

	private boolean initialized;

	@Inject
	private GameEntryTypeDao gameEntryTypeDao;

	@Inject
	private ReleaseTypeDao releaseTypeDao;

	@Inject
	private DemoContentTypeDao demoContentTypeDao;

	@Inject
	private TitleTypeDao titleTypeDao;

	@Inject
	private ScriptDao scriptDao;

	@Inject
	private HardwarePlatformTypeDao hardwarePlatformTypeDao;

	@Inject
	private SoftwarePlatformTypeDao softwarePlatformTypeDao;

	@Inject
	private ReleaseGroupReasonDao releaseGroupReasonDao;


    @Transactional
	public void initBaseLists() {
		if (!initialized) {
            if (StartHelper.getInstance(BaseListFinder.class).getTitleType(TitleType.ORIGINAL_TITLE)==null) {
                initGameEntryType();
                initDemoContentType();
                initReleaseType();
                initTitleType();
                initScript();
				initHardwarePlatformType();
				initSoftwarePlatformType();
				initReleaseGroupReason();
                initialized = true;
            }
		}
	}

	private void initSoftwarePlatformType() {
		softwarePlatformTypeDao.save(new SoftwarePlatformType(SoftwarePlatformType.BUILT_IN));
		softwarePlatformTypeDao.save(new SoftwarePlatformType(SoftwarePlatformType.DESKTOP));
		softwarePlatformTypeDao.save(new SoftwarePlatformType(SoftwarePlatformType.MOBILE));
		softwarePlatformTypeDao.save(new SoftwarePlatformType(SoftwarePlatformType.SPECIAL_SOFTWARE));
	}

	private void initHardwarePlatformType() {
		hardwarePlatformTypeDao.save(new HardwarePlatformType(HardwarePlatformType.CONSOLES_EUROPE_NORTHAMERICA));
		hardwarePlatformTypeDao.save(new HardwarePlatformType(HardwarePlatformType.CONSOLES_NON_EUROPE_NORTHAMERICA));
		hardwarePlatformTypeDao.save(new HardwarePlatformType(HardwarePlatformType.HANDHELDS_EUROPE_NORTHAMERICA));
		hardwarePlatformTypeDao.save(new HardwarePlatformType(HardwarePlatformType.HANDHELDS_NON_EUROPE_NORTHAMERICA));
		hardwarePlatformTypeDao.save(new HardwarePlatformType(HardwarePlatformType.HOME_COMPUTERS_ASIA));
		hardwarePlatformTypeDao.save(new HardwarePlatformType(HardwarePlatformType.HOME_COMPUTERS_EUROPE_NORTHAMERICA));
		hardwarePlatformTypeDao.save(new HardwarePlatformType(HardwarePlatformType.MOBILE));
		hardwarePlatformTypeDao.save(new HardwarePlatformType(HardwarePlatformType.NO_HARDWARE));
	}


	private void initGameEntryType() {
		gameEntryTypeDao.save(new GameEntryType(GameEntryType.ADD_ON_NOT_SIGNIFICANT));
		gameEntryTypeDao.save(new GameEntryType(GameEntryType.ADD_ON_SIGNIFICANT));
		gameEntryTypeDao.save(new GameEntryType(GameEntryType.COMPILATION));
		gameEntryTypeDao.save(new GameEntryType(GameEntryType.EPISODE));
		gameEntryTypeDao.save(new GameEntryType(GameEntryType.EPISODIC_GAME));
		gameEntryTypeDao.save(new GameEntryType(GameEntryType.GAME));
	}

	private void initDemoContentType() {
		demoContentTypeDao.save(new DemoContentType(DemoContentType.ABSOLUTE_PLAY_COUNT_LIMIT));
		demoContentTypeDao.save(new DemoContentType(DemoContentType.ABSOLUTE_PLAY_TIME_LIMIT));
		demoContentTypeDao.save(new DemoContentType(DemoContentType.CONTENT_LIMIT));
		demoContentTypeDao.save(new DemoContentType(DemoContentType.LEVEL_CAP));
		demoContentTypeDao.save(new DemoContentType(DemoContentType.SAVING_DISABLED));
		demoContentTypeDao.save(new DemoContentType(DemoContentType.SCORE_CAP));
		demoContentTypeDao.save(new DemoContentType(DemoContentType.TECH_DEMO));
		demoContentTypeDao.save(new DemoContentType(DemoContentType.TIME_LIMIT));
	}

	private void initReleaseType() {
		releaseTypeDao.save(new ReleaseType(ReleaseType.NATIVE_DEVELOPMENT));
		releaseTypeDao.save(new ReleaseType(ReleaseType.EMULATOR_RELEASE));
		releaseTypeDao.save(new ReleaseType(ReleaseType.PORT));
	}

	private void initTitleType() {
		titleTypeDao.save(new TitleType(TitleType.ORIGINAL_TITLE));
		titleTypeDao.save(new TitleType(TitleType.ABBREVIATION));
		titleTypeDao.save(new TitleType(TitleType.BUDGET_RELEASE_TITLE));
		titleTypeDao.save(new TitleType(TitleType.DEVELOPMENT_TITLE));
		titleTypeDao.save(new TitleType(TitleType.INOFFICIAL_TITLE));
		titleTypeDao.save(new TitleType(TitleType.RE_RELEASE_TITLE));
	}

	private void initScript() {
		scriptDao.save(new Script(Script.LATIN));
		scriptDao.save(new Script(Script.ARABIC));
		scriptDao.save(new Script(Script.CHINESE));
		scriptDao.save(new Script(Script.CYRILLIC));
		scriptDao.save(new Script(Script.GREEK));
		scriptDao.save(new Script(Script.HEBREW));
		scriptDao.save(new Script(Script.JAPANESE));
		scriptDao.save(new Script(Script.KOREAN));
	}

	private void initReleaseGroupReason() {
		releaseGroupReasonDao.save(new ReleaseGroupReason(ReleaseGroupReason.ORIGINAL));
		releaseGroupReasonDao.save(new ReleaseGroupReason(ReleaseGroupReason.CENSORED));
		releaseGroupReasonDao.save(new ReleaseGroupReason(ReleaseGroupReason.DEMO_PROMO));
		releaseGroupReasonDao.save(new ReleaseGroupReason(ReleaseGroupReason.ENHANCED));
		releaseGroupReasonDao.save(new ReleaseGroupReason(ReleaseGroupReason.REMAKE));
	}


    public void reset() {
        initialized = false;
    }


}
