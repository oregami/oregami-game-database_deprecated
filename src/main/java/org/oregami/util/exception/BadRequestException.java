package org.oregami.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by sebastian on 16.05.16.
 */
@ResponseStatus(value= HttpStatus.BAD_REQUEST)  // 400
public class BadRequestException extends RuntimeException {
}
