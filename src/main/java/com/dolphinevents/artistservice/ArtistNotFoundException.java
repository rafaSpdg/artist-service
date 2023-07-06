package com.dolphinevents.artistservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Artist does not exist.")
public class ArtistNotFoundException extends Throwable{
    
    public ArtistNotFoundException(String message) {
        super(message);
    }
}
