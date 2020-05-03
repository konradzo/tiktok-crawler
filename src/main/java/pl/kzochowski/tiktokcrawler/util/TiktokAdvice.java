package pl.kzochowski.tiktokcrawler.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.kzochowski.tiktokcrawler.service.ProfileService;

@ControllerAdvice
public class TiktokAdvice {

    @ResponseBody
    @ExceptionHandler(ProfileService.ProfilePageDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String profileNotFoundHandler(ProfileService.ProfilePageDoesNotExistException exception){
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ProfileService.ProfileJsonProcessingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String profileJsonProcessingHandler(ProfileService.ProfileJsonProcessingException exception){
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ProfileService.DatabaseDoesNotContainProfile.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String databaseNotContainsProfileHandler(ProfileService.DatabaseDoesNotContainProfile exception){
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ProfileService.ProfileAlreadyAddedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String profileAlreadyAddedHandler(ProfileService.ProfileAlreadyAddedException exception){
        return exception.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ProfileService.ProfileCreatingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String profileCreatingExceptionHandler(ProfileService.ProfileCreatingException exception){
        return exception.getMessage();
    }

}
