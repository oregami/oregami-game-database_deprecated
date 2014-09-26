package org.oregami.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.oregami.data.RegionDao;
import org.oregami.dropwizard.OregamiApplication;
import org.oregami.entities.Region;

import javax.persistence.EntityManager;
import java.util.List;

public class RegionRevisionTest {

	private static Injector injector;
	
	static EntityManager entityManager = null;
	
	public RegionRevisionTest() {
	}
	
	@BeforeClass
	public static void init() {
        JpaPersistModule jpaPersistModule = new JpaPersistModule(OregamiApplication.JPA_UNIT);
        injector = Guice.createInjector(jpaPersistModule);
        injector.getInstance(PersistenceTest.class);
        PersistService persistService = injector.getInstance(PersistService.class);
        persistService.start();
        entityManager = injector.getInstance(EntityManager.class);
	}
	

	@Test
	public void testRegion() {
		
		entityManager.getTransaction().begin();

		RegionDao regionDao = injector.getInstance(RegionDao.class);
		
		Region germany = new Region(Region.GERMANY);
		String id1 = regionDao.save(germany);
		Assert.assertNotNull("ID expected", id1);
		
		List<Region> all = regionDao.findAll();
		Assert.assertTrue("1 Region expected", all.size()==1);
		
		Region europe = new Region(Region.EUROPE);
		String id2 = regionDao.save(europe);
		
		all = regionDao.findAll();
		Assert.assertTrue("2 Regions expected", all.size()==2);
		
		entityManager.getTransaction().commit();
		
		
		
		entityManager.getTransaction().begin();
		Region loadedGermany = regionDao.findByExactName(Region.GERMANY);
		Assert.assertNotNull(loadedGermany);
		Assert.assertEquals(loadedGermany.getId(), id1);
		Assert.assertEquals(loadedGermany, germany);
		
		Region loadedEurope = regionDao.findByExactName(Region.EUROPE);
		Assert.assertNotNull(loadedEurope);
		Assert.assertEquals(loadedEurope.getId(), id2);
		Assert.assertEquals(loadedEurope, europe);
		
		loadedEurope.setDescription("this is changed europe");
		regionDao.save(loadedEurope);
		
		all = regionDao.findAll();
		Assert.assertTrue("2 Regions expected", all.size()==2);
		entityManager.getTransaction().commit();
		
		
		entityManager.getTransaction().begin();
		Region loadedEurope2 = regionDao.findByExactName(Region.EUROPE);
		loadedEurope2.setDescription("this is twice changed europe");
		regionDao.save(loadedEurope);
		entityManager.getTransaction().commit();
		
		
		entityManager.getTransaction().begin();
		AuditReader auditReader = AuditReaderFactory.get(entityManager);
		List<Number> revisionNumbers = auditReader.getRevisions(Region.class, loadedEurope.getId());
//		System.out.println(revisionNumbers + " revisions found.");
		for (Number number : revisionNumbers) {
//			System.out.println("RevisionNumber: " + number);
			Region regionWithRevision = auditReader.find(Region.class, loadedEurope.getId(), number);
//			try {
//				System.out.println("Region with revision " + number + "\n"
//						+ new ObjectMapper().writeValueAsString(regionWithRevision));
//			} catch (JsonProcessingException e) {
//				Assert.fail(e.getMessage());
//			}
            Assert.assertNotNull(regionWithRevision);
		}
		entityManager.getTransaction().commit();



        entityManager.getTransaction().begin();
        regionDao.delete(loadedEurope);
        regionDao.delete(loadedGermany);
        List<Region> regionList = regionDao.findAll();
        Assert.assertEquals(0, regionList.size());
        entityManager.getTransaction().commit();
		
		
		
	}
	
}
