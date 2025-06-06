package Taxi.Taxi_Booking.dao;

import Taxi.Taxi_Booking.model.ServiceForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceFormCrud extends JpaRepository<ServiceForm, Integer> {

}
