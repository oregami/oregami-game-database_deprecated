package org.oregami.test;

import org.junit.Assert;
import org.junit.Test;
import org.oregami.entities.Game;
import org.oregami.entities.ReleaseGroup;
import org.oregami.entities.TopLevelEntity;

/**
 * Created by sebastian on 11.03.15.
 */
public class AnnotationTest {

    @Test
    public void annotationIsPresent() {
        Game t = new Game();

        boolean annotationPresent = t.getClass().isAnnotationPresent(TopLevelEntity.class);
        Assert.assertTrue(annotationPresent);
        Assert.assertEquals(t.getClass().getAnnotation(TopLevelEntity.class).discriminator(), TopLevelEntity.Discriminator.GAME);
    }

    @Test
    public void annotationIsNotPresent() {
        ReleaseGroup s = new ReleaseGroup();
        boolean annotationPresent = s.getClass().isAnnotationPresent(TopLevelEntity.class);
        Assert.assertFalse(annotationPresent);
    }
}
