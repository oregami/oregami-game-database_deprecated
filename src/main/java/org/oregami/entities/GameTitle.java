package org.oregami.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="GameTitle.GetAll", query = 
			"from GameTitle t")
})
public class GameTitle extends BaseEntity {

	private static final long serialVersionUID = -4693647736354542489L;
	
	private String nativeSpelling;
	
	private String standardTransliteration;
	
	@ManyToOne
	private Language language;

	public GameTitle() {
	}
	
	public GameTitle(String nativeSpelling) {
		this.setNativeSpelling(nativeSpelling);
	}
	
	public GameTitle(String nativeSpelling, Language language) {
		this.setNativeSpelling(nativeSpelling);
		this.setLanguage(language);
	}

	public String getNativeSpelling() {
		return nativeSpelling;
	}

	public void setNativeSpelling(String nativeSpelling) {
		this.nativeSpelling = nativeSpelling;
	}

	public String getStandardTransliteration() {
		return standardTransliteration;
	}

	public void setStandardTransliteration(String standardTransliteration) {
		this.standardTransliteration = standardTransliteration;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}
	
}
