package org.oregami.service;

import com.google.inject.Inject;
import org.oregami.data.GenericDAOUUID;
import org.oregami.data.ScriptDao;
import org.oregami.data.TransliteratedStringDao;
import org.oregami.entities.TransliteratedString;
import org.oregami.entities.datalist.Script;
import org.oregami.util.validation.IEntityValidator;
import org.oregami.util.validation.ScriptValidator;
import org.oregami.util.validation.TransliteratedStringValidator;

public class ScriptService extends TopLevelEntityService<Script> {

	@Inject
	private ScriptDao dao;

    @Override
    public GenericDAOUUID<Script, String> getDao() {
        return dao;
    }

    @Override
    public IEntityValidator buildEntityValidator(Script entity) {
        return new ScriptValidator(entity);
    }
}
