package org.oregami.service;

import com.google.inject.Inject;
import org.oregami.data.GameDao;
import org.oregami.data.GenericDAOUUID;
import org.oregami.data.ReleaseGroupReasonDao;
import org.oregami.entities.Game;
import org.oregami.entities.datalist.ReleaseGroupReason;
import org.oregami.util.validation.GameValidator;
import org.oregami.util.validation.IEntityValidator;
import org.oregami.util.validation.NullValidator;

public class ReleaseGroupReasonService extends TopLevelEntityService<ReleaseGroupReason> {

	@Inject
	private ReleaseGroupReasonDao dao;

    @Override
    public GenericDAOUUID<ReleaseGroupReason, String> getDao() {
        return dao;
    }

    @Override
    public IEntityValidator buildEntityValidator(ReleaseGroupReason entity) {
        return new NullValidator(entity);
    }
}
