package org.oregami.test;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.oregami.entities.Game;
import org.oregami.entities.datalist.GameEntryType;
import org.oregami.service.FieldNames;
import org.oregami.service.ServiceError;
import org.oregami.service.ServiceErrorContext;
import org.oregami.service.ServiceErrorMessage;
import org.oregami.util.validation.GameValidator;

import java.util.List;

/**
 * Created by sebastian on 21.03.15.
 */
public class GameValidatorTest {

    ServiceError errorGameEntryTypeEmpty = new ServiceError(new ServiceErrorContext(FieldNames.GAME_GAMEENTRYTYPE), ServiceErrorMessage.GAME_GAMEENTRYTYPE_EMPTY);


    @Test
    public void returnNoErrors() {

        Game game = new Game();
        GameEntryType gameType = new GameEntryType(GameEntryType.GAME);
        game.setGameEntryType(gameType);
        GameValidator validator = new GameValidator(game);

        List<ServiceError> errors = validator.validateForCreation();
        Assert.assertTrue(errors.isEmpty());

    }

    @Test
    public void returnError() {

        Game game = new Game();
        GameValidator validator = new GameValidator(game);

        List<ServiceError> errors = validator.validateForCreation();
        Assert.assertFalse(errors.isEmpty());
        Assert.assertThat(errors, Matchers.contains(errorGameEntryTypeEmpty));

    }


}
