package org.oregami.data;

import org.oregami.entities.datalist.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BaseListFiller {

	private boolean initialized;

    @Autowired
    BaseListFinder baseListFinder;

    @Autowired
    SoftwarePlatformTypeDao softwarePlatformTypeDao;

    @Autowired
    HardwarePlatformTypeDao hardwarePlatformTypeDao;

    @Autowired
    GameEntryTypeDao gameEntryTypeDao;

    @Autowired
    DemoContentTypeDao demoContentTypeDao;

    @Autowired
    ReleaseTypeDao releaseTypeDao;

    @Autowired
    ReleaseStateDao releaseStateDao;

    @Autowired
    TitleTypeDao titleTypeDao;

    @Autowired
    ScriptDao scriptDao;

    @Autowired
    ReleaseGroupReasonDao releaseGroupReasonDao;

    @Autowired
    UnReleasedStateDao unReleasedStateDao;

    @Autowired
    CensorshipTypeDao censorshipTypeDao;

    @Autowired
    AddOnContentTypeDao addOnContentTypeDao;

    @Autowired
    TitleLocationDao titleLocationDao;

    @Autowired
    RemakeEnhancementTypeDao remakeEnhancementTypeDao;

    @Transactional
	public void initBaseLists() {
		if (!initialized) {
            if (baseListFinder.getTitleType(TitleType.ORIGINAL_TITLE)==null) {
                initGameEntryType();
                initDemoContentType();
                initReleaseType();
                initTitleType();
                initScript();
				initHardwarePlatformType();
				initSoftwarePlatformType();
				initReleaseGroupReason();
				initUnReleasedState();
				initReleaseState();
				initCensorshipType();
				initAddOnContentType();
				initTitleLocation();
				initRemakeEnhancementType();
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


	private void initUnReleasedState() {
		unReleasedStateDao.save(new UnReleasedState(UnReleasedState.DEVELOPMENT_CANCELLED));
		unReleasedStateDao.save(new UnReleasedState(UnReleasedState.IN_DEVELOPMENT));
		unReleasedStateDao.save(new UnReleasedState(UnReleasedState.VAPORWARE));
	}

	private void initReleaseState() {
		releaseStateDao.save(new ReleaseState(ReleaseState.EMULATOR_RELEASE));
		releaseStateDao.save(new ReleaseState(ReleaseState.NATIVE_DEVELOPMENT));
		releaseStateDao.save(new ReleaseState(ReleaseState.PORT));
	}

	private void initCensorshipType() {
		censorshipTypeDao.save(new CensorshipType(CensorshipType.AUDIO_CENSORSHIP));
		censorshipTypeDao.save(new CensorshipType(CensorshipType.GAMEPLAY_CENSORSHIP_OTHER));
		censorshipTypeDao.save(new CensorshipType(CensorshipType.GAMEPLAY_CENSORSHIP_VIOLENCE));
		censorshipTypeDao.save(new CensorshipType(CensorshipType.SCRIPT_CENSORSHIP));
		censorshipTypeDao.save(new CensorshipType(CensorshipType.VISUAL_CENSORSHIP_GORE));
		censorshipTypeDao.save(new CensorshipType(CensorshipType.VISUAL_CENSORSHIP_NUDISM));
		censorshipTypeDao.save(new CensorshipType(CensorshipType.VISUAL_CENSORSHIP_OTHER));
	}

	private void initAddOnContentType() {
		addOnContentTypeDao.save(new AddOnContentType(AddOnContentType.ADDITIONAL_LEVELS_OR_MISSIONS));
		addOnContentTypeDao.save(new AddOnContentType(AddOnContentType.CHANGES_TO_GAMERULES_OR_GAMEMECHANICS));
		addOnContentTypeDao.save(new AddOnContentType(AddOnContentType.EXTENDED_AREA_OF_GAMEPLAY));
		addOnContentTypeDao.save(new AddOnContentType(AddOnContentType.NEW_PLAYABLE_CHARACTERS));
		addOnContentTypeDao.save(new AddOnContentType(AddOnContentType.NEW_CAMPAIGNS));
		addOnContentTypeDao.save(new AddOnContentType(AddOnContentType.NEW_ENEMIES));
		addOnContentTypeDao.save(new AddOnContentType(AddOnContentType.NEW_FEATURES));
		addOnContentTypeDao.save(new AddOnContentType(AddOnContentType.NEW_ITEMS));
	}

	private void initTitleLocation() {
		titleLocationDao.save(new TitleLocation(TitleLocation.BACK_COVER));
		titleLocationDao.save(new TitleLocation(TitleLocation.FRONT_COVER));
		titleLocationDao.save(new TitleLocation(TitleLocation.DESKTOP_ICON));
		titleLocationDao.save(new TitleLocation(TitleLocation.INSTALLER));
		titleLocationDao.save(new TitleLocation(TitleLocation.LOADING_SCREEN));
		titleLocationDao.save(new TitleLocation(TitleLocation.MANUAL));
		titleLocationDao.save(new TitleLocation(TitleLocation.MEDIUM));
		titleLocationDao.save(new TitleLocation(TitleLocation.SPINE_COVER));
		titleLocationDao.save(new TitleLocation(TitleLocation.START_MENU));
		titleLocationDao.save(new TitleLocation(TitleLocation.TITLE_SCREEN));
	}

	private void initRemakeEnhancementType() {
		remakeEnhancementTypeDao.save(new RemakeEnhancementType(RemakeEnhancementType.ADDITIONAL_CONTENT));
		remakeEnhancementTypeDao.save(new RemakeEnhancementType(RemakeEnhancementType.ADDITIONAL_FMV));
		remakeEnhancementTypeDao.save(new RemakeEnhancementType(RemakeEnhancementType.ADDITIONAL_SPEECH));
		remakeEnhancementTypeDao.save(new RemakeEnhancementType(RemakeEnhancementType.BUGFREE_RELEASE));
		remakeEnhancementTypeDao.save(new RemakeEnhancementType(RemakeEnhancementType.ENHANCED_GRAPHICS));
		remakeEnhancementTypeDao.save(new RemakeEnhancementType(RemakeEnhancementType.ENHANCED_SOUND));
		remakeEnhancementTypeDao.save(new RemakeEnhancementType(RemakeEnhancementType.IMPROVED_UI));
	}

    public void reset() {
        initialized = false;
    }


}
