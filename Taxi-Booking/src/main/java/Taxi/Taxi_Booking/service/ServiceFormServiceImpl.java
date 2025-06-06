package Taxi.Taxi_Booking.service;

import Taxi.Taxi_Booking.dao.ServiceFormCrud;
import Taxi.Taxi_Booking.model.ServiceForm;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.List;

@Service
public class ServiceFormServiceImpl implements ServiceFormService {

    private ServiceFormCrud serviceFormCrud;

    @Autowired
    public void setServiceFormCrud(ServiceFormCrud serviceFormCrud) {
        this.serviceFormCrud = serviceFormCrud;
    }



    @Transactional(rollbackOn = Exception.class)
    @Override
    public ServiceForm addService(ServiceForm serviceForm, MultipartFile multipartFile) throws Exception {

        ServiceForm save =null;
        try {

            save = serviceFormCrud.save(serviceForm);
            if(save!=null) {

                String path="C:\\Users\\Dell\\Downloads\\Taxi-Booking\\Taxi-Booking\\src\\main\\resources\\static\\myserviceimg\\"+multipartFile.getOriginalFilename();
                byte[] bytes = multipartFile.getBytes();

                FileOutputStream fos = new FileOutputStream(path);
                fos.write(bytes);
            }

        } catch (Exception e) {
            save=null;
            throw e;
        }
        return save;
    }

    @Override
    public List<ServiceForm> readAllServices() {

        return serviceFormCrud.findAll();
    }
}