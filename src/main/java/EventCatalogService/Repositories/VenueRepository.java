package EventCatalogService.Repositories;

import EventCatalogService.DataBase.Event;
import EventCatalogService.DataBase.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VenueRepository extends JpaRepository<Venue,Long> {
    List<Event> findByVenueStatus(String venueStatus);
}
