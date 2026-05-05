package EventCatalogService.DataBase;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table
public class Price {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private int event_id;
    private String tier;
    private int price_amount;
    private String currency;
    private int available_quantity;
    private int total_quantity;
}
