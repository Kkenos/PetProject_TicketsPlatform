package EventCatalogService.Service;

import EventCatalogService.DataBase.Venue;
import EventCatalogService.Repositories.VenueRepository;

import java.util.List;

public class VenueService {
    private VenueRepository venueRepository;
    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }
    public void CreateVenue(Venue venue) {
        if(!venue.getCity().matches("^[a-zA-Zа-яА-ЯёЁ\\\\s-]+$")){
            throw new IllegalArgumentException("Содержит недопустимые символы!");
        }
        if(venue.getCity().length() < 3){
            throw new IllegalArgumentException("Название города не может быть меньше 3 букв!");
        }
        if(venue.getCapacity()<0){
            throw new IllegalArgumentException("Вместимость не может быть меньше или равно 0!");
        }
        else{
            venue.setStatus("ACTIVE");
            venueRepository.save(venue);
        }
    }
    public void DeleteVenue(long venueId) {
        Venue currentPlace = venueRepository.findById(venueId).orElseThrow(() -> new IllegalArgumentException("такого мета нет!"));
        currentPlace.setStatus("DELETED");
        venueRepository.save(currentPlace);
    }
    public  void UpdateVenue(Venue venue) {
        Venue currentPlace = venueRepository.findById(venue.getVenueId()).orElseThrow(() -> new IllegalArgumentException("такого мета нет!"));
        if(currentPlace.getCapacity()>0){
            currentPlace.setCapacity(currentPlace.getCapacity()-1);
        }
        if(currentPlace.getName() != null){
            currentPlace.setName(currentPlace.getName());
        }
    }
    public Venue GetVenuesByEventId(long eventId) {
       return venueRepository.findById(eventId).orElseThrow(() -> new IllegalArgumentException("Нет события"));
    }
}
