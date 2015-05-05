package org.oregami.service;

import com.google.inject.Inject;
import org.oregami.data.GenericDAOUUID;
import org.oregami.data.TransliteratedStringDao;
import org.oregami.entities.TransliteratedString;
import org.oregami.util.validation.IEntityValidator;
import org.oregami.util.validation.TransliteratedStringValidator;

public class TransliteratedStringService extends TopLevelEntityService<TransliteratedString> {

	@Inject
	private TransliteratedStringDao dao;

    @Override
    public GenericDAOUUID<TransliteratedString, String> getDao() {
        return dao;
    }

    @Override
    public IEntityValidator buildEntityValidator(TransliteratedString entity) {
        return new TransliteratedStringValidator(entity);
    }
}
