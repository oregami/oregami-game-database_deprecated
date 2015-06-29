package org.oregami.data;

import com.google.inject.persist.Transactional;
import org.oregami.entities.datalist.*;
import org.oregami.util.StartHelper;

public class BaseListFiller {

	private boolean initialized;

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

	public static <T> T getInstance(Class<T> c) {
		return StartHelper.getInstance(c);
	}

	private void initSoftwarePlatformType() {
		getInstance(SoftwarePlatformTypeDao.class).save(new SoftwarePlatformType(SoftwarePlatformType.BUILT_IN));
		getInstance(SoftwarePlatformTypeDao.class).save(new SoftwarePlatformType(SoftwarePlatformType.DESKTOP));
		getInstance(SoftwarePlatformTypeDao.class).save(new SoftwarePlatformType(SoftwarePlatformType.MOBILE));
		getInstance(SoftwarePlatformTypeDao.class).save(new SoftwarePlatformType(SoftwarePlatformType.SPECIAL_SOFTWARE));
	}

	private void initHardwarePlatformType() {
		getInstance(HardwarePlatformTypeDao.class).save(new HardwarePlatformType(HardwarePlatformType.CONSOLES_EUROPE_NORTHAMERICA));
		getInstance(HardwarePlatformTypeDao.class).save(new HardwarePlatformType(HardwarePlatformType.CONSOLES_NON_EUROPE_NORTHAMERICA));
		getInstance(HardwarePlatformTypeDao.class).save(new HardwarePlatformType(HardwarePlatformType.HANDHELDS_EUROPE_NORTHAMERICA));
		getInstance(HardwarePlatformTypeDao.class).save(new HardwarePlatformType(HardwarePlatformType.HANDHELDS_NON_EUROPE_NORTHAMERICA));
		getInstance(HardwarePlatformTypeDao.class).save(new HardwarePlatformType(HardwarePlatformType.HOME_COMPUTERS_ASIA));
		getInstance(HardwarePlatformTypeDao.class).save(new HardwarePlatformType(HardwarePlatformType.HOME_COMPUTERS_EUROPE_NORTHAMERICA));
		getInstance(HardwarePlatformTypeDao.class).save(new HardwarePlatformType(HardwarePlatformType.MOBILE));
		getInstance(HardwarePlatformTypeDao.class).save(new HardwarePlatformType(HardwarePlatformType.NO_HARDWARE));
	}


	private void initGameEntryType() {
		getInstance(GameEntryTypeDao.class).save(new GameEntryType(GameEntryType.ADD_ON_NOT_SIGNIFICANT));
		getInstance(GameEntryTypeDao.class).save(new GameEntryType(GameEntryType.ADD_ON_SIGNIFICANT));
		getInstance(GameEntryTypeDao.class).save(new GameEntryType(GameEntryType.COMPILATION));
		getInstance(GameEntryTypeDao.class).save(new GameEntryType(GameEntryType.EPISODE));
		getInstance(GameEntryTypeDao.class).save(new GameEntryType(GameEntryType.EPISODIC_GAME));
		getInstance(GameEntryTypeDao.class).save(new GameEntryType(GameEntryType.GAME));
	}

	private void initDemoContentType() {
		getInstance(DemoContentTypeDao.class).save(new DemoContentType(DemoContentType.ABSOLUTE_PLAY_COUNT_LIMIT));
		getInstance(DemoContentTypeDao.class).save(new DemoContentType(DemoContentType.ABSOLUTE_PLAY_TIME_LIMIT));
		getInstance(DemoContentTypeDao.class).save(new DemoContentType(DemoContentType.CONTENT_LIMIT));
		getInstance(DemoContentTypeDao.class).save(new DemoContentType(DemoContentType.LEVEL_CAP));
		getInstance(DemoContentTypeDao.class).save(new DemoContentType(DemoContentType.SAVING_DISABLED));
		getInstance(DemoContentTypeDao.class).save(new DemoContentType(DemoContentType.SCORE_CAP));
		getInstance(DemoContentTypeDao.class).save(new DemoContentType(DemoContentType.TECH_DEMO));
		getInstance(DemoContentTypeDao.class).save(new DemoContentType(DemoContentType.TIME_LIMIT));
	}

	private void initReleaseType() {
		getInstance(ReleaseTypeDao.class).save(new ReleaseType(ReleaseType.NATIVE_DEVELOPMENT));
		getInstance(ReleaseTypeDao.class).save(new ReleaseType(ReleaseType.EMULATOR_RELEASE));
		getInstance(ReleaseTypeDao.class).save(new ReleaseType(ReleaseType.PORT));
	}

	private void initTitleType() {
		getInstance(TitleTypeDao.class).save(new TitleType(TitleType.ORIGINAL_TITLE));
		getInstance(TitleTypeDao.class).save(new TitleType(TitleType.ABBREVIATION));
		getInstance(TitleTypeDao.class).save(new TitleType(TitleType.BUDGET_RELEASE_TITLE));
		getInstance(TitleTypeDao.class).save(new TitleType(TitleType.DEVELOPMENT_TITLE));
		getInstance(TitleTypeDao.class).save(new TitleType(TitleType.INOFFICIAL_TITLE));
		getInstance(TitleTypeDao.class).save(new TitleType(TitleType.RE_RELEASE_TITLE));
	}

	private void initScript() {
		getInstance(ScriptDao.class).save(new Script(Script.LATIN));
		getInstance(ScriptDao.class).save(new Script(Script.ARABIC));
		getInstance(ScriptDao.class).save(new Script(Script.CHINESE));
		getInstance(ScriptDao.class).save(new Script(Script.CYRILLIC));
		getInstance(ScriptDao.class).save(new Script(Script.GREEK));
		getInstance(ScriptDao.class).save(new Script(Script.HEBREW));
		getInstance(ScriptDao.class).save(new Script(Script.JAPANESE));
		getInstance(ScriptDao.class).save(new Script(Script.KOREAN));
	}

	private void initReleaseGroupReason() {
		getInstance(ReleaseGroupReasonDao.class).save(new ReleaseGroupReason(ReleaseGroupReason.ORIGINAL));
		getInstance(ReleaseGroupReasonDao.class).save(new ReleaseGroupReason(ReleaseGroupReason.CENSORED));
		getInstance(ReleaseGroupReasonDao.class).save(new ReleaseGroupReason(ReleaseGroupReason.DEMO_PROMO));
		getInstance(ReleaseGroupReasonDao.class).save(new ReleaseGroupReason(ReleaseGroupReason.ENHANCED));
		getInstance(ReleaseGroupReasonDao.class).save(new ReleaseGroupReason(ReleaseGroupReason.REMAKE));
	}


	private void initUnReleasedState() {
		getInstance(UnReleasedStateDao.class).save(new UnReleasedState(UnReleasedState.DEVELOPMENT_CANCELLED));
		getInstance(UnReleasedStateDao.class).save(new UnReleasedState(UnReleasedState.IN_DEVELOPMENT));
		getInstance(UnReleasedStateDao.class).save(new UnReleasedState(UnReleasedState.VAPORWARE));
	}

	private void initReleaseState() {
		getInstance(ReleaseStateDao.class).save(new ReleaseState(ReleaseState.EMULATOR_RELEASE));
		getInstance(ReleaseStateDao.class).save(new ReleaseState(ReleaseState.NATIVE_DEVELOPMENT));
		getInstance(ReleaseStateDao.class).save(new ReleaseState(ReleaseState.PORT));
	}

	private void initCensorshipType() {
		getInstance(CensorshipTypeDao.class).save(new CensorshipType(CensorshipType.AUDIO_CENSORSHIP));
		getInstance(CensorshipTypeDao.class).save(new CensorshipType(CensorshipType.GAMEPLAY_CENSORSHIP_OTHER));
		getInstance(CensorshipTypeDao.class).save(new CensorshipType(CensorshipType.GAMEPLAY_CENSORSHIP_VIOLENCE));
		getInstance(CensorshipTypeDao.class).save(new CensorshipType(CensorshipType.SCRIPT_CENSORSHIP));
		getInstance(CensorshipTypeDao.class).save(new CensorshipType(CensorshipType.VISUAL_CENSORSHIP_GORE));
		getInstance(CensorshipTypeDao.class).save(new CensorshipType(CensorshipType.VISUAL_CENSORSHIP_NUDISM));
		getInstance(CensorshipTypeDao.class).save(new CensorshipType(CensorshipType.VISUAL_CENSORSHIP_OTHER));
	}

	private void initAddOnContentType() {
		getInstance(AddOnContentTypeDao.class).save(new AddOnContentType(AddOnContentType.ADDITIONAL_LEVELS_OR_MISSIONS));
		getInstance(AddOnContentTypeDao.class).save(new AddOnContentType(AddOnContentType.CHANGES_TO_GAMERULES_OR_GAMEMECHANICS));
		getInstance(AddOnContentTypeDao.class).save(new AddOnContentType(AddOnContentType.EXTENDED_AREA_OF_GAMEPLAY));
		getInstance(AddOnContentTypeDao.class).save(new AddOnContentType(AddOnContentType.NEW_PLAYABLE_CHARACTERS));
		getInstance(AddOnContentTypeDao.class).save(new AddOnContentType(AddOnContentType.NEW_CAMPAIGNS));
		getInstance(AddOnContentTypeDao.class).save(new AddOnContentType(AddOnContentType.NEW_ENEMIES));
		getInstance(AddOnContentTypeDao.class).save(new AddOnContentType(AddOnContentType.NEW_FEATURES));
		getInstance(AddOnContentTypeDao.class).save(new AddOnContentType(AddOnContentType.NEW_ITEMS));
	}

	private void initTitleLocation() {
		getInstance(TitleLocationDao.class).save(new TitleLocation(TitleLocation.BACK_COVER));
		getInstance(TitleLocationDao.class).save(new TitleLocation(TitleLocation.FRONT_COVER));
		getInstance(TitleLocationDao.class).save(new TitleLocation(TitleLocation.DESKTOP_ICON));
		getInstance(TitleLocationDao.class).save(new TitleLocation(TitleLocation.INSTALLER));
		getInstance(TitleLocationDao.class).save(new TitleLocation(TitleLocation.LOADING_SCREEN));
		getInstance(TitleLocationDao.class).save(new TitleLocation(TitleLocation.MANUAL));
		getInstance(TitleLocationDao.class).save(new TitleLocation(TitleLocation.MEDIUM));
		getInstance(TitleLocationDao.class).save(new TitleLocation(TitleLocation.SPINE_COVER));
		getInstance(TitleLocationDao.class).save(new TitleLocation(TitleLocation.START_MENU));
		getInstance(TitleLocationDao.class).save(new TitleLocation(TitleLocation.TITLE_SCREEN));
	}

	private void initRemakeEnhancementType() {
		getInstance(RemakeEnhancementTypeDao.class).save(new RemakeEnhancementType(RemakeEnhancementType.ADDITIONAL_CONTENT));
		getInstance(RemakeEnhancementTypeDao.class).save(new RemakeEnhancementType(RemakeEnhancementType.ADDITIONAL_FMV));
		getInstance(RemakeEnhancementTypeDao.class).save(new RemakeEnhancementType(RemakeEnhancementType.ADDITIONAL_SPEECH));
		getInstance(RemakeEnhancementTypeDao.class).save(new RemakeEnhancementType(RemakeEnhancementType.BUGFREE_RELEASE));
		getInstance(RemakeEnhancementTypeDao.class).save(new RemakeEnhancementType(RemakeEnhancementType.ENHANCED_GRAPHICS));
		getInstance(RemakeEnhancementTypeDao.class).save(new RemakeEnhancementType(RemakeEnhancementType.ENHANCED_SOUND));
		getInstance(RemakeEnhancementTypeDao.class).save(new RemakeEnhancementType(RemakeEnhancementType.IMPROVED_UI));
	}

    public void reset() {
        initialized = false;
    }


}
