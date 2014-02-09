package org.oregami.entities;

import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

@Entity
public class PublicationIssue extends BaseEntity {

	private static final long serialVersionUID = -6611241084584031299L;

	@Column
	@Nullable
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate releaseDate;
	
	private int issueNameNumber;
	
	private int issueNameYear;
	
	private String issueNameSpecial;
	
	public PublicationIssue() {
		
	}
	
	public PublicationIssue(int year, int numberOrMonth) {
		this.issueNameYear = year;
		this.issueNameNumber = numberOrMonth;
	}
	
	
	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getIssueNameNumber() {
		return issueNameNumber;
	}

	public void setIssueNameNumber(int issueameNumber) {
		this.issueNameNumber = issueameNumber;
	}

	public int getIssueNameYear() {
		return issueNameYear;
	}

	public void setIssueNameYear(int issueNameYear) {
		this.issueNameYear = issueNameYear;
	}

	public String getIssueNameSpecial() {
		return issueNameSpecial;
	}

	public void setIssueNameSpecial(String issueNameSpecial) {
		this.issueNameSpecial = issueNameSpecial;
	}

	
	


}
