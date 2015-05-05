package org.oregami.entities;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.oregami.entities.datalist.Script;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by sebastian on 25.04.15.
 */
@Entity
@Audited
@TopLevelEntity(discriminator = TopLevelEntity.Discriminator.TRANSLITERATEDSTRING)
@NamedQueries({
        @NamedQuery(name="TransliteratedString.GetAll", query =
                "from TransliteratedString t")
})
public class TransliteratedString  extends BaseEntityUUID {

    private String text;

    @ManyToOne
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Language language;

    @ManyToOne
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Script script;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Script getScript() {
        return script;
    }

    public void setScript(Script script) {
        this.script = script;
    }
}
