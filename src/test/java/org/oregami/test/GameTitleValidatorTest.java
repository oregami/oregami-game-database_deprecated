package org.oregami.test;

import org.junit.Assert;
import org.junit.Test;
import org.oregami.entities.GameTitle;
import org.oregami.entities.TransliteratedString;
import org.oregami.service.FieldNames;
import org.oregami.service.ServiceError;
import org.oregami.service.ServiceErrorContext;
import org.oregami.service.ServiceErrorMessage;
import org.oregami.util.validation.GameTitleValidator;

import java.util.List;

/**
 * Created by sebastian on 21.03.15.
 */
public class GameTitleValidatorTest {

    ServiceError nativeSpellingEmpty = new ServiceError(new ServiceErrorContext(FieldNames.GAMETITLE_NATIVESPELLING), ServiceErrorMessage.FIELD_EMPTY);
    ServiceError nativeSpellingTooShort = new ServiceError(new ServiceErrorContext(FieldNames.GAMETITLE_NATIVESPELLING), ServiceErrorMessage.FIELD_TOO_SHORT);


    @Test
    public void returnNoErrors() {

        GameTitle gameTitle = new GameTitle();
        TransliteratedString transliteratedString = new TransliteratedString();
        transliteratedString.setText("Some GameTitle");
        gameTitle.setText(transliteratedString);
        GameTitleValidator validator = new GameTitleValidator(gameTitle);

        List<ServiceError> errors = validator.validateForCreation();
        Assert.assertTrue(errors.isEmpty());

    }

    /*
    @Test
    public void returnErrorNativeSpellingEmpty() {

        GameTitle gameTitle = new GameTitle();
        GameTitleValidator validator = new GameTitleValidator(gameTitle);

        List<ServiceError> errors = validator.validateForCreation();
        Assert.assertFalse(errors.isEmpty());
        Assert.assertThat(errors, Matchers.contains(nativeSpellingEmpty));

    }
*/
    /*
    @Test
    public void returnErrorNativeSpellingTooShort() {

        GameTitle gameTitle = new GameTitle();
        gameTitle.setNativeSpelling("XX");
        GameTitleValidator validator = new GameTitleValidator(gameTitle);

        List<ServiceError> errors = validator.validateForCreation();
        Assert.assertFalse(errors.isEmpty());
        Assert.assertThat(errors, Matchers.contains(nativeSpellingTooShort));

    }
*/
}
