package pl.kzochowski.tiktokcrawler.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.kzochowski.tiktokcrawler.service.ProfileService.*;


@ControllerAdvice
public class TiktokAdvice {

    @ResponseBody
    @ExceptionHandler(ProfilePageDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String profileNotFoundHandler(ProfilePageDoesNotExistException exception){
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ProfileJsonProcessingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String profileJsonProcessingHandler(ProfileJsonProcessingException exception){
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(DatabaseDoesNotContainProfile.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String databaseNotContainsProfileHandler(DatabaseDoesNotContainProfile exception){
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ProfileAlreadyAddedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String profileAlreadyAddedHandler(ProfileAlreadyAddedException exception){
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ProfileCreatingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String profileCreatingExceptionHandler(ProfileCreatingException exception){
        return exception.getMessage();
    }

}
