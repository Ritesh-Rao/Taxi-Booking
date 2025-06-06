package Taxi.Taxi_Booking.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="service")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ServiceForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String image;
    private String title;
    private String description;

}
