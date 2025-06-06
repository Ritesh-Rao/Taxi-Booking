package Taxi.Taxi_Booking.controller;

import Taxi.Taxi_Booking.model.BookingForm;
import Taxi.Taxi_Booking.model.ServiceForm;
import Taxi.Taxi_Booking.service.AdminCredentialsService;
import Taxi.Taxi_Booking.service.BookingFormService;
import Taxi.Taxi_Booking.service.ContactFormService;
import Taxi.Taxi_Booking.service.ServiceFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

	private ContactFormService contactFormService;
	private AdminCredentialsService adminCredentialsService;
	private BookingFormService bookingFormService;
	private ServiceFormService serviceFormService;

	@Autowired
	public void setServiceFormService(ServiceFormService serviceFormService) {
		this.serviceFormService = serviceFormService;
	}

	@Autowired
	public void setBookingFormService(BookingFormService bookingFormService) {
		this.bookingFormService = bookingFormService;
	}

	@Autowired
	public void setAdminCredentialsService(AdminCredentialsService adminCredentialsService) {
		this.adminCredentialsService = adminCredentialsService;
	}

	@Autowired
	public void setContactFormService(ContactFormService contactFormService) {
		this.contactFormService = contactFormService;
	}

	@GetMapping("dashboard")
	public String adminDashboard() {
		return "admin/dashboard";
	}

	@GetMapping("readAllContacts")
	public String readAllContacts(Model model) {

		model.addAttribute("allcontacts",contactFormService.readAllContactsService());
		return "admin/readallcontacts";
	}

	@GetMapping("deleteContact/{id}")
	public String deleteContact(@PathVariable int id, RedirectAttributes redirectAttributes) {

		contactFormService.deleteContactService(id);
		redirectAttributes.addFlashAttribute("message","CONTACT DELETED SUCCESSFULLY");
		return "redirect:/admin/readAllContacts";
	}

	@GetMapping("changeCredentials")
	public String changeCredentialsView() {
		return "admin/changecredentials";
	}

	@PostMapping("changeCredentials")
	public String changeCredentials(
			@RequestParam("oldusername") String oldusername,
			@RequestParam("oldpassword") String oldpassword,
			@RequestParam("newusername") String newusername,
			@RequestParam("newpassword") String newpassword,
			RedirectAttributes redirectAttributes
	) {

		String result = adminCredentialsService.checkAdminCredentials(oldusername, oldpassword);
		if(result.equals("SUCCESS")) {
			//PASSWORD UPDATE
			result=adminCredentialsService.updateAdminCredentials(newusername, newpassword, oldusername);
			redirectAttributes.addFlashAttribute("message",result);
		}
		else {
			redirectAttributes.addFlashAttribute("message",result);
		}
		return "redirect:/admin/dashboard";
	}

	@GetMapping("readAllBookings")
	public String readAllBookings(Model model) {

		List<BookingForm> allBookingsService = bookingFormService.readAllBookingsService();
//		System.out.println(allBookingsService);

		model.addAttribute("allBookings",allBookingsService);
		return "admin/readallbookings";
	}

	@GetMapping("deleteBooking/{id}")
	public String deleteBooking( @PathVariable int id,RedirectAttributes redirectAttributes) {

		bookingFormService.deleteBookingtService(id);
		redirectAttributes.addFlashAttribute("message","BOOKING DELETED SUCCESSFULLY");
		return "redirect:/admin/readAllBookings";
	}


	@GetMapping("addService")
	public String addServiceView() {
		return "admin/addservice";
	}

	@InitBinder
	public void stopBinding(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("image");
	}

	@PostMapping("addService")
	public String addService(@ModelAttribute ServiceForm serviceForm ,
							 @RequestParam("image") MultipartFile multipartFile, RedirectAttributes redirectAttributes  ) {

		String originalFilename = multipartFile.getOriginalFilename();
		serviceForm.setImage(originalFilename);

		try {

			ServiceForm service = serviceFormService.addService(serviceForm, multipartFile);
			if(service!=null) {
				redirectAttributes.addFlashAttribute("msg","Service Added Successfully");
			}else {
				redirectAttributes.addFlashAttribute("msg","Something went wrong");
			}

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg","Something went wrong");
		}


		return "redirect:/admin/addService";
	}
}