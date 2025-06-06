package Taxi.Taxi_Booking.service;

import Taxi.Taxi_Booking.dao.BookingFormCrud;
import Taxi.Taxi_Booking.model.BookingForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingFormServiceImpl implements BookingFormService {

    private BookingFormCrud bookingFormCrud;

    @Autowired
    public void setBookingFormCrud(BookingFormCrud bookingFormCrud) {
        this.bookingFormCrud = bookingFormCrud;
    }

    @Override
    public BookingForm saveBookingFormService(BookingForm bookingForm) {
        // TODO Auto-generated method stub

        return bookingFormCrud.save(bookingForm);
    }

    @Override
    public List<BookingForm> readAllBookingsService() {
        // TODO Auto-generated method stub
        return bookingFormCrud.findAll();
    }

    @Override
    public void deleteBookingtService(int id) {

        bookingFormCrud.deleteById(id);
    }
}
