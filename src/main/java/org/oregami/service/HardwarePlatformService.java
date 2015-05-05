package org.oregami.service;

import com.google.inject.Inject;
import org.oregami.data.GenericDAOUUID;
import org.oregami.data.HardwarePlatformDao;
import org.oregami.entities.HardwarePlatform;
import org.oregami.util.validation.HardwarePlatformValidator;
import org.oregami.util.validation.IEntityValidator;

public class HardwarePlatformService extends TopLevelEntityService<HardwarePlatform> {

	@Inject
	private HardwarePlatformDao dao;

    @Override
    public GenericDAOUUID<HardwarePlatform, String> getDao() {
        return dao;
    }

    @Override
    public IEntityValidator buildEntityValidator(HardwarePlatform entity) {
        return new HardwarePlatformValidator(entity);
    }
}
