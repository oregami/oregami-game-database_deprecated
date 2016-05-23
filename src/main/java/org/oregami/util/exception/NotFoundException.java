package org.oregami.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by sebastian on 16.05.16.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such entity")  // 404
public class NotFoundException extends RuntimeException {
}
