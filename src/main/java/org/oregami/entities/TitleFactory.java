package org.oregami.entities;

import org.oregami.data.BaseListFinder;
import org.oregami.entities.datalist.Script;
import org.oregami.entities.datalist.TitleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Created by sebastian on 20.05.15.
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TitleFactory {

    @Autowired
    BaseListFinder baseListFinder;

    public PlatformTitle createPlatformTitle(Region region, TitleType titleType, Script script, Language language, String text) {
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

    public PlatformTitle createLatinPlatformTitle(Language language, String text) {
        PlatformTitle pt = new PlatformTitle();
        TransliteratedString ts = new TransliteratedString();
        ts.setScript(baseListFinder.getScript(Script.LATIN));
        ts.setLanguage(language);
        ts.setText(text);
        pt.setText(ts);
        return pt;
    }


    public GameTitle createGameTitle(Region region, TitleType titleType, Script script, Language language, String text) {
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

    public GameTitle createLatinGameTitle(Language language, String text) {
        GameTitle t = new GameTitle();
        TransliteratedString ts = new TransliteratedString();
        ts.setScript(baseListFinder.getScript(Script.LATIN));
        ts.setLanguage(language);
        ts.setText(text);
        t.setText(ts);
        return t;
    }



}
