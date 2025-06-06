package Taxi.Taxi_Booking.service;

import Taxi.Taxi_Booking.model.ServiceForm;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ServiceFormService {
    public ServiceForm addService(ServiceForm serviceForm, MultipartFile multipartFile) throws Exception;

    public List<ServiceForm> readAllServices();
}
