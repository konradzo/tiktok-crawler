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

}
