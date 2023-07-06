package com.dolphinevents.artistservice;

import java.util.List;

public interface ArtistServiceInterface {
    
    List<Artist> findAllArtists();

    Artist findArtistById(Integer id) throws ArtistNotFoundException; 

    void deleteById(Integer id);

    Artist saveArtist(Artist artist);
}
