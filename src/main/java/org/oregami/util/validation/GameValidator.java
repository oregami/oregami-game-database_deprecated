package org.oregami.util.validation;

import org.oregami.entities.Game;
import org.oregami.service.FieldNames;
import org.oregami.service.ServiceError;
import org.oregami.service.ServiceErrorContext;
import org.oregami.service.ServiceErrorMessage;

import java.util.ArrayList;
import java.util.List;

public class GameValidator implements IEntityValidator {

    private final Game game;

    private final int nameMinLenght = 3;

    public GameValidator(Game game) {
        if (game == null) {
            throw new RuntimeException("org.oregami.GameValidator.NoEntityGiven");
        }
        this.game = game;
    }

    @Override
    public List<ServiceError> validateForCreation() {
        List<ServiceError> errors = new ArrayList<ServiceError>();

        errors.addAll(validateRequiredFields());

        return errors;

    }

    @Override
    public List<ServiceError> validateRequiredFields() {
        List<ServiceError> errorMessages = new ArrayList<ServiceError>();

        String id = game.getId();
        if (id==null) {
            id = game.getValidationId();
        }

        if (game.getGameEntryType()==null) {
            errorMessages.add(new ServiceError(new ServiceErrorContext(FieldNames.GAME_GAMEENTRYTYPE, id), ServiceErrorMessage.GAME_GAMEENTRYTYPE_EMPTY));
        }
        return errorMessages;
    }

    @Override
	public List<ServiceError> validateForUpdate() {
        List<ServiceError> errors = new ArrayList<ServiceError>();
        errors.addAll(validateRequiredFields());
        return errors;
	}


}
