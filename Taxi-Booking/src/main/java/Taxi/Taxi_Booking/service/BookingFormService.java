package Taxi.Taxi_Booking.service;

import Taxi.Taxi_Booking.model.BookingForm;

import java.util.List;

public interface BookingFormService {

    public BookingForm saveBookingFormService(BookingForm bookingForm);
    public List<BookingForm> readAllBookingsService();
    public void deleteBookingtService(int id);

}
