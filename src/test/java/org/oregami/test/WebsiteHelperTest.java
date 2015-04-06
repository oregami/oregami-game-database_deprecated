package org.oregami.test;

import com.google.inject.Injector;
import org.junit.*;
import org.oregami.data.WebsiteDao;
import org.oregami.entities.Website;
import org.oregami.util.StartHelper;
import org.oregami.util.WebsiteHelper;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Ignore
public class WebsiteHelperTest {

	private static Injector injector;

	private static EntityManager entityManager = null;

	public WebsiteHelperTest() {
	}

    @BeforeClass
    public static void initClass() {
        StartHelper.init(StartHelper.CONFIG_FILENAME_TEST);
        injector = StartHelper.getInjector();
        entityManager = injector.getInstance(EntityManager.class);
    }

	@Before
	public void startTx() {
		entityManager.getTransaction().begin();
	}

	@After
	public void rollbackTx() {
		entityManager.getTransaction().rollback();
	}

	@Test
	public void testCreateWebsite() throws IOException {
		Map<String, String> result = WebsiteHelper.instance().createWebsite("http://www.google.de", "1024*768");
		byte[] imageBytes = WebsiteHelper.instance().readFile(result.get("filename"));

		Website website = new Website();
		website.setImage(imageBytes);

		WebsiteDao websiteDao = injector.getInstance(WebsiteDao.class);
		String id1 = websiteDao.save(website);

		Assert.assertNotNull(id1);

		List<Website> findAll = websiteDao.findAll();
		Assert.assertTrue(findAll.size()>0);

		Website findOne = websiteDao.findOne(id1);
		Assert.assertNotNull(findOne);
		Assert.assertArrayEquals(imageBytes, findOne.getImage());

	}

}
