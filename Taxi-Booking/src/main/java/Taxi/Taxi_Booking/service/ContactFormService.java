package Taxi.Taxi_Booking.service;

import Taxi.Taxi_Booking.model.ContactForm;

import java.util.List;

public interface ContactFormService {

    public ContactForm saveContactFormService(ContactForm contactForm);
    public List<ContactForm> readAllContactsService();
    public void deleteContactService(int id);
}
