package org.oregami;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sebastian on 08.02.16.
 */
@Component
public class URLConfiguration {

	enum URLS {
		PUBLICATIONFRANCHISE("/publicationFranchise")
        ,GAMINGENVIRONMENTS("/gamingEnvironments")
        ,GAMES("/games")
		,HOME("/")
		;
		public final String value;
		URLS(String value) {
			this.value = value;
		}
	}

	public List<URLS> urls = new ArrayList<>();

	public List<URLS> getUrls() {
		if (urls.size() == 0) {
			synchronized (this) {
				if (urls.size() == 0) {
					urls.addAll(Arrays.asList(URLS.values()));
				}
			}
		}
		return urls;
	}

	public static String getBaseUrl() {
		return "http://dev.oregami.org/";
	}
}
