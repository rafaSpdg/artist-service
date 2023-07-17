package com.dolphinevents.artistservice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Artist {

    public Artist() {

    }

    public Artist(String name, String artistName, String city, String pastExperience, String profession, String weeklyAvailable,String email, String phone, String password) {
        this.name = name;
        this.artistName = artistName;
        this.city = city;
        this.pastExperience = pastExperience;
        this.profession = profession;
        this.weeklyAvailable = weeklyAvailable;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
 
    @JsonProperty("artist_name")
    private String artistName;

    private String city;

    @JsonProperty("past_experience")
    private String pastExperience;

    private String profession;

    @JsonProperty("weekly_available")
    private String weeklyAvailable; // fazer uma list com os dias da semana 

    
    private String email;

    
    private String phone;

    @JsonIgnore
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPastExperience() {
        return pastExperience;
    }

    public void setPastExperience(String pastExperience) {
        this.pastExperience = pastExperience;
    }

    public String getWeeklyAvailable() {
        return weeklyAvailable;
    }

    public void setWeeklyAvailable(String weeklyAvailable) {
        this.weeklyAvailable = weeklyAvailable;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Artist [id=" + id + ", name=" + name + ", artistName=" + artistName + ", city=" + city
                + ", pastExperience=" + pastExperience + ", profession=" + profession + ", weeklyAvailable="
                + weeklyAvailable + ", email=" + email + ", phone=" + phone + ", password=" + password + "]";
    }  
    
}
