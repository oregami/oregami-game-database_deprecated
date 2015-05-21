package org.oregami.dropwizard;

import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.oregami.rest.RestTestHelper;
import org.oregami.util.StartHelper;

/**
 * Created by sebastian on 06.04.15.
 */
public class OregamiAppRule extends DropwizardAppRule {
    public OregamiAppRule(Class applicationClass, String configPath, ConfigOverride... configOverrides) {
        super(applicationClass, configPath, configOverrides);
        StartHelper.init(configPath);
        RestTestHelper.initRestAssured();
    }





}
