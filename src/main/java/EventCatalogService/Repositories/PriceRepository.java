package EventCatalogService.Repositories;

import EventCatalogService.DataBase.Event;
import EventCatalogService.DataBase.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price,Long> {
    //т.к базовые CRUD(Create,Read, Upadate,Delete) есть, оставляею пустым
}
