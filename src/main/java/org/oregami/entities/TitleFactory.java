package org.oregami.entities;

import org.oregami.data.BaseListFinder;
import org.oregami.entities.datalist.Script;
import org.oregami.entities.datalist.TitleType;
import org.oregami.util.StartHelper;

/**
 * Created by sebastian on 20.05.15.
 */
public abstract class TitleFactory {

    public static PlatformTitle createPlatformTitle(Region region, TitleType titleType, Script script, Language language, String text) {
        PlatformTitle pt = new PlatformTitle();
        pt.setRegion(region);
        pt.setTitleType(titleType);
        TransliteratedString ts = new TransliteratedString();
        ts.setScript(script);
        ts.setLanguage(language);
        ts.setText(text);
        pt.setText(ts);
        return pt;
    }

    public static PlatformTitle createLatinPlatformTitle(Language language, String text) {
        PlatformTitle pt = new PlatformTitle();
        TransliteratedString ts = new TransliteratedString();
        ts.setScript(StartHelper.getInstance(BaseListFinder.class).getScript(Script.LATIN));
        ts.setLanguage(language);
        ts.setText(text);
        pt.setText(ts);
        return pt;
    }


    public static GameTitle createGameTitle(Region region, TitleType titleType, Script script, Language language, String text) {
        GameTitle t = new GameTitle();
        t.setRegion(region);
        t.setTitleType(titleType);
        TransliteratedString ts = new TransliteratedString();
        ts.setScript(script);
        ts.setLanguage(language);
        ts.setText(text);
        t.setText(ts);
        return t;
    }

    public static GameTitle createLatinGameTitle(Language language, String text) {
        GameTitle t = new GameTitle();
        TransliteratedString ts = new TransliteratedString();
        ts.setScript(StartHelper.getInstance(BaseListFinder.class).getScript(Script.LATIN));
        ts.setLanguage(language);
        ts.setText(text);
        t.setText(ts);
        return t;
    }



}
