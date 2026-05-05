package EventCatalogService.Repositories;

import EventCatalogService.DataBase.Event;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;


public interface EventRepository extends JpaRepository<Event,Long> {

    List<Event> findAllByEventStatus(String status);
    Page<Event> findAllByEventDateBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
    Page<Event> findByEventName(String eventName, Pageable pageable);
}
