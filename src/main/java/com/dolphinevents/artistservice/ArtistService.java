package com.dolphinevents.artistservice;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ArtistService implements ArtistServiceInterface{
    
    private ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> findAllArtists() {
        return artistRepository.findAll();
    }

    public Artist findArtistById(Integer id) throws ArtistNotFoundException {

        Artist artist = artistRepository.findById(id).get() ;

        if(artist == null) {
            throw new ArtistNotFoundException("Could not find any Artist with id " + id);
        }

        return artist;
    }

    public void deleteById(Integer id) {
        artistRepository.deleteById(id);
    }

    public Artist saveArtist(Artist artist) {
        artistRepository.save(artist);

        return artist;
    }

}
