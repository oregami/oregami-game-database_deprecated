package org.oregami.test;

import com.google.inject.Injector;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.oregami.data.PublicationFranchiseDao;
import org.oregami.entities.PublicationFranchise;
import org.oregami.service.ServiceError;
import org.oregami.util.validation.PublicationFranchiseValidator;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by sebastian on 21.10.14.
 */
public class PublicationFranchiseValidatorTest {

    private static Injector injector;

    EntityManager entityManager = null;

    private Logger logger = Logger.getLogger(this.getClass());

    private static PublicationFranchiseDao dao = Mockito.mock(PublicationFranchiseDao.class);


    @Test
    public void returnNoErrors() {

        PublicationFranchise publicationFranchise = new PublicationFranchise();
        publicationFranchise.setName("franchise name");
        PublicationFranchiseValidator validator = new PublicationFranchiseValidator(publicationFranchise);

        List<ServiceError> errors = validator.validateForCreation();
        System.out.println(errors);
        Assert.assertTrue(errors.isEmpty());

    }

}
