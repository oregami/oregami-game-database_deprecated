package org.oregami.resources;

import org.apache.log4j.Logger;
import org.oregami.data.GenericDAOUUIDImpl;
import org.oregami.data.RevisionInfo;
import org.oregami.entities.BaseEntityUUID;
import org.oregami.entities.user.User;
import org.oregami.service.ServiceCallContext;
import org.oregami.service.ServiceResult;
import org.oregami.service.TopLevelEntityService;
import org.oregami.util.exception.BadRequestException;
import org.oregami.util.exception.NotFoundException;

import javax.persistence.OptimisticLockException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by sebastian on 18.03.15.
 */
public abstract class ResourceHelper {

    public static ServiceResult<BaseEntityUUID> create(User user, BaseEntityUUID entity, TopLevelEntityService service, Class resourceClass) {
        try {
            ServiceCallContext context = new ServiceCallContext(user);
            ServiceResult<BaseEntityUUID> serviceResult = service.createNewEntity(entity, context);
            if (serviceResult.hasErrors()) {
                return serviceResult;
            }
            //BUG: https://java.net/jira/browse/JERSEY-2838
            //    and https://github.com/dropwizard/dropwizard/issues/878
            //return Response.created(new URI(serviceResult.getResult().getId())).build();
            //workaround:

//            final URI uri = UriComponentsBuilder.(resourceClass)
//                    .path("{id}")
//                    .build(serviceResult.getResult().getId());
            return serviceResult;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ServiceResult<BaseEntityUUID> update(User user, String id, BaseEntityUUID entity, TopLevelEntityService service) {
        if (entity.getId() == null) {
            throw new BadRequestException();
        }
        try {
            ServiceCallContext context = new ServiceCallContext(user);
            ServiceResult<BaseEntityUUID> serviceResult = service.updateEntity(entity, context);
            return serviceResult;
        } catch (OptimisticLockException e) {
            Logger.getLogger(ResourceHelper.class).warn("OptimisticLockException", e);
            throw new BadRequestException();
        }
    }


    public static BaseEntityUUID getRevision(String id, String revision, GenericDAOUUIDImpl dao) {
        BaseEntityUUID entity = dao.findRevision(id, Integer.parseInt(revision));
        if (entity == null) {
            throw new NotFoundException();
        } else {
            return entity;
        }
    }

    public static List<RevisionInfo> getRevisions(String id, GenericDAOUUIDImpl dao) {
        List<RevisionInfo> revisionList = dao.findRevisions(id);
        if (revisionList == null) {
            throw new NotFoundException();
        } else {
            return revisionList;
        }
    }

    public static BaseEntityUUID get(String id, GenericDAOUUIDImpl dao) {
        BaseEntityUUID entity = dao.findOne(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        return entity;

    }

    public void delete(User user, String id, TopLevelEntityService service) {
        try {
            service.deleteEntity(id);
        } catch (NoSuchElementException e) {
            throw new NotFoundException();
        }

    }
}
