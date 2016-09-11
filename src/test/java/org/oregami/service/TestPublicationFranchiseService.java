package org.oregami.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.oregami.OregamiApplication;
import org.oregami.data.PublicationFranchiseDao;
import org.oregami.domain.model.publicationFranchise.Publication;
import org.oregami.domain.model.publicationFranchise.PublicationFranchise;
import org.oregami.domain.model.publicationFranchise.PublicationIssue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OregamiApplication.class)
@ActiveProfiles("hsqldb")
public class TestPublicationFranchiseService {

    private EntityManager entityManager;

    @Autowired
    private final EntityManagerFactory entityManagerFactory = null;

    @Autowired
    private PublicationFranchiseDao publicationFranchiseDao;

    @Autowired
    private PublicationFranchiseService publicationFranchiseService;

    public TestPublicationFranchiseService() {
    }

    @Before
    public void before() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Test
    @Ignore
    public void testBasicPersistence() {

//        entityManager.getTransaction().begin();

        PublicationFranchise pf = new PublicationFranchise();
        pf.setName("Power Play");

        entityManager.persist(pf);
        entityManager.flush();

        List<PublicationFranchise> list = publicationFranchiseDao.findAll();

        Assert.assertEquals(1, list.size());

        entityManager.getTransaction().rollback();
    }


    @Test
    public void testServiceUpdate() {

        List<PublicationFranchise> list = publicationFranchiseDao.findAll();
        int size = list.size();

        PublicationFranchise pf = new PublicationFranchise();
        pf.setName("Power Play");

//        entityManager.persist(pf);
//        entityManager.flush();
        publicationFranchiseDao.save(pf);

        list = publicationFranchiseDao.findAll();

        Assert.assertEquals(size+1, list.size());


        PublicationFranchise publicationFranchise = publicationFranchiseDao.findOne(pf.getId());
        publicationFranchise.setName("updated");
        ServiceResult<PublicationFranchise> serviceResult = publicationFranchiseService.updateEntity(publicationFranchise, null);
        Assert.assertTrue(serviceResult.getErrors().isEmpty());

        PublicationFranchise franchiseLoaded = publicationFranchiseDao.findOne(pf.getId());
        Assert.assertEquals(publicationFranchise.getName(), franchiseLoaded.getName());

        franchiseLoaded.setName("a");
        Publication publication = new Publication();
        publication.setName("pub name");
        PublicationIssue issue1 = new PublicationIssue();
        publication.getPublicationIssueList().add(issue1);
        franchiseLoaded.getPublicationList().add(publication);
        ServiceResult<PublicationFranchise> serviceResult2 = publicationFranchiseService.updateEntity(franchiseLoaded, null);
        System.out.println(serviceResult2);
        Assert.assertTrue(serviceResult2.hasErrors());
        Assert.assertEquals(3, serviceResult2.getErrors().size());

        //entityManager.getTransaction().rollback();
    }

}
