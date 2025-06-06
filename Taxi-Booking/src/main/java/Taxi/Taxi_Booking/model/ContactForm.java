package Taxi.Taxi_Booking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "contactform")
public class ContactForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min =2, max = 30,message = "Invalid Name Size")
    @Column(length = 30)
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Size(min =5, max = 50,message = "Invalid Email Size")
    @Column(length = 50)
    private String email;

    @NotNull(message = "Phone No cant be Empty")
    @Min(value = 1000000000,message = "Phone No must be at least 10 digits")
    @Max(value = 9999999999L,message = "Phone No must be at least 10 digits")
    @Column(length = 10)
    private Long phone;

    @NotEmpty(message = "Message cannot be empty")
    @Size(min =3, max = 500,message = "Invalid Message Size")
    @Column(length = 500)
    private String message;
}
