package org.oregami.entities;

import org.hibernate.envers.Audited;
import org.oregami.entities.datalist.TitleType;

import javax.persistence.*;

/**
 * Created by sebastian on 20.05.15.
 */
@Entity
@Audited
public class PlatformTitle extends BaseEntityUUID {

    @ManyToOne
    private Region region;

    @ManyToOne
    private TitleType titleType;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch= FetchType.EAGER)
    TransliteratedString text;

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public TitleType getTitleType() {
        return titleType;
    }

    public void setTitleType(TitleType titleType) {
        this.titleType = titleType;
    }

    public TransliteratedString getText() {
        return text;
    }

    public void setText(TransliteratedString text) {
        this.text = text;
    }
}
