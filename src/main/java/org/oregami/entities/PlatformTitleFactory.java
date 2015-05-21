package org.oregami.entities;

import org.oregami.entities.datalist.Script;
import org.oregami.entities.datalist.TitleType;

/**
 * Created by sebastian on 20.05.15.
 */
public abstract class PlatformTitleFactory {

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

}
