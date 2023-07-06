package com.dolphinevents.artistservice;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class ArtistController {
    
    private ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/artists")
    public List<Artist> getAllArtists() {
        return artistService.findAllArtists();
    }

    @GetMapping("/filtering/artists")
    public MappingJacksonValue GetAllFilteringArtists() {
        List<Artist> artists = artistService.findAllArtists();

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(artists);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("city","name","artist_name","past_experience","profession","weekly_available","email","phone");

        FilterProvider filters = new SimpleFilterProvider().addFilter("ArtistFiltering", filter);

        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

    @GetMapping("/artists/{id}")
    public EntityModel<Artist> getOneArtistById(@PathVariable Integer id) throws ArtistNotFoundException{
        
        Artist artist = artistService.findArtistById(id);

        if(artist == null) {
            throw new ArtistNotFoundException("Id: " + id);
        }

        EntityModel<Artist> entityModel = EntityModel.of(artist);
        
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllArtists());

        entityModel.add(link.withRel("all-artists"));

        return entityModel;
    }

    @PostMapping("/artists")
    public ResponseEntity<Artist> createArtistPerfil(@Valid @RequestBody Artist artist) {
        Artist savedArtist = artistService.saveArtist(artist);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedArtist.getId()).toUri();
    
        return ResponseEntity.created(location).build();
    } 

    @DeleteMapping("/artists/{id}")
    public void removeArtist(@PathVariable Integer id) {
        artistService.deleteById(id);
    }

    @PostMapping("/artists/{id}")
    public ResponseEntity<Artist> updateArtist(@Valid @PathVariable Integer id, @RequestBody Artist artist) throws ArtistNotFoundException {
        Artist updateArtist = artistService.findArtistById(id);

        if(artist == null) {
            throw new ArtistNotFoundException("Artist with id " + id + " doesnt exists.");
        }

        updateArtist.setCity(updateArtist.getCity());
        updateArtist.setEmail(updateArtist.getEmail());
        updateArtist.setId(updateArtist.getId());
        updateArtist.setName(updateArtist.getName());
        updateArtist.setPassword(updateArtist.getPassword());
        updateArtist.setPastExperience(updateArtist.getPastExperience());
        updateArtist.setPhone(updateArtist.getPhone());
        updateArtist.setProfession(updateArtist.getProfession());
        updateArtist.setWeeklyAvailable(updateArtist.getWeeklyAvailable());

        return ResponseEntity.ok(updateArtist);

    }

}
