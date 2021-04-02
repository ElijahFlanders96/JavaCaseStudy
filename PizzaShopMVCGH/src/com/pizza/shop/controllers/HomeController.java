package com.pizza.shop.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pizza.shop.entity.DriverVehicle;
import com.pizza.shop.entity.Employee;
import com.pizza.shop.entity.Machinery;
import com.pizza.shop.entity.Store;
import com.pizza.shop.service.DriverVehicleService;
import com.pizza.shop.service.EmployeeService;
import com.pizza.shop.service.MachineryService;
import com.pizza.shop.service.StoreService;

@Controller
public class HomeController {
	
	private EmployeeService empService;
	private StoreService storeService;
	private DriverVehicleService carService;
	private MachineryService macService;
	
	@Autowired
	public HomeController(EmployeeService empService, StoreService storeService, DriverVehicleService carService, MachineryService macService) {
		this.empService = empService;
		this.storeService = storeService;
		this.carService = carService;
		this.macService = macService;
	}
	
	@GetMapping("/")
	public String showWelcomePageInit() {
		return "welcome";
	}
	
	@GetMapping("/welcome")
	public String showWelcomePage() {
		return "welcome";
	}
	
	@GetMapping("/employees")
	public String showEmployeesPage(Model model) {
		model.addAttribute("employee", new Employee());
		return "employees";
	}
	
	@GetMapping("/equipment")
	public String showEquipmentPage(Model model) {
		model.addAttribute("machinery", new Machinery());
		return "equipment";
	}
	
	@GetMapping("/vehicles")
	public String showVehiclesPage(Model model) {
		model.addAttribute("driverVehicle", new DriverVehicle());
		return "vehicles";
	}
	
	@GetMapping("/stores")
	public String showStoresPage(Model model) {
		model.addAttribute("store", new Store());
		return "stores";
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	
	//login functionality
	@PostMapping("/login")
	public String processLoginRequest(@RequestParam("eId") int eId, @RequestParam("email") String email, Model model, HttpSession session) {
		Employee emp = empService.getEmpService(eId);
		if (emp!=null && emp.getPosition().equals("General Manager") && email.equals(emp.getEmail())) {
			session.setAttribute("currentUser", emp);
			model.addAttribute("loginSuccessMessage", "Welcome,");
			return "login";
		}
		model.addAttribute("loginFailedMessage", "Invalid Credentials");
		return "login";
	}
	
	
	//EMP METHODS
	@PostMapping("/addEmp")
	public String addNewEmployee(@ModelAttribute("employee") Employee emp, Model model, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("errorMessage", "There was an error with an input field, please try again");
			return "employees";
		}
		empService.addEmpService(emp);
		model.addAttribute("successMessage", "Employee added to the database successfully!");
		System.out.println("added to db successfully");
		return "employees";
	}
	
	@PostMapping("/getEmp")
	public String getEmployee(@ModelAttribute("employee") Employee emp, @RequestParam("geteId") int eId, Model model) {
		emp = empService.getEmpService(eId);
		if (emp==null) {
			model.addAttribute("getEmpError", "Please enter the ID of an existing employee");
		}
		System.out.println(emp.getFirstName());
		model.addAttribute("eId", emp.geteId() + ", ");
		model.addAttribute("firstName", emp.getFirstName() + ", ");
		model.addAttribute("lastName", emp.getLastName() + ", ");
		model.addAttribute("wage", emp.getWage() + ", ");
		model.addAttribute("position", emp.getPosition() + ", ");
		model.addAttribute("email", emp.getEmail() + ", ");
		model.addAttribute("phoneNumber", emp.getPhoneNumber() + ", ");
		model.addAttribute("storeId", emp.getStoreId() + ", ");
		return "employees";
	}
	
	@PostMapping("/updateEmp")
	public String updateEmployee(@ModelAttribute("employee") Employee emp, Model model, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("updateEmpError", "There was an error with an input field, please try again");
			return "employees";
		}
		empService.updateEmpService(emp);
		model.addAttribute("updateEmpSuccess", "Employee updated successfully!");
		System.out.println("updated successfully");
		return "employees";
	}
	
	@PostMapping("/removeEmp")
	public String removeEmployee(@ModelAttribute("employee") Employee emp, @RequestParam("eId") int eId, Model model) {
		empService.removeEmpService(eId);
		model.addAttribute("removeEmpSuccess", "Employee removed from database sucessfully!");
		return "employees";
	}
	
	
	
	
	
	//STORE METHODS
		@PostMapping("/addStore")
		public String addNewStore(@ModelAttribute("store") Store store, Model model, BindingResult result) {
			if (result.hasErrors()) {
				model.addAttribute("addStoreError", "There was an error with an input field, please try again");
				return "stores";
			}
			storeService.addStoreService(store);
			model.addAttribute("addStoreSuccess", "Store added to the database successfully!");
			System.out.println("added to db successfully");
			return "stores";
		}
		
		@PostMapping("/getStore")
		public String getStore(@ModelAttribute("store") Store store, @RequestParam("getsId") int sId, Model model) {
			store = storeService.getStoreService(sId);
			if (store==null) {
				model.addAttribute("getStoreError", "Please enter the ID of an existing store");
			}
			model.addAttribute("sId", store.getsId() + ", ");
			model.addAttribute("name", store.getName() + ", ");
			model.addAttribute("address", store.getAddress() + ", ");
			model.addAttribute("gmId", store.getGmId() + ", ");
			return "stores";
		}
		
		@PostMapping("/updateStore")
		public String updateStore(@ModelAttribute("store") Store store, Model model, BindingResult result) {
			if (result.hasErrors()) {
				model.addAttribute("updateStoreError", "There was an error with an input field, please try again");
				return "stores";
			}
			storeService.updateStoreService(store);
			model.addAttribute("updateStoreSuccess", "Store updated successfully!");
			System.out.println("updated successfully");
			return "stores";
		}
		
		@PostMapping("/removeStore")
		public String removeStore(@ModelAttribute("store") Store store, @RequestParam("sId") int sId, Model model) {
			storeService.removeStoreService(sId);
			model.addAttribute("removeStoreSuccess", "Store removed from database sucessfully!");
			return "stores";
		}
		
		
		
		
		
		//VEHICLE METHODS
		@PostMapping("/addCar")
		public String addNewCar(@ModelAttribute("driverVehicle") DriverVehicle car, Model model, BindingResult result) {
			if (result.hasErrors()) {
				model.addAttribute("addCarError", "There was an error with an input field, please try again");
				return "vehicles";
			}
			carService.addCarService(car);
			model.addAttribute("addCarSuccess", "Vehicle added to the database successfully!");
			System.out.println("added to db successfully");
			return "vehicles";
		}
		
		@PostMapping("/getCar")
		public String getCar(@ModelAttribute("driverVehicle") DriverVehicle car, @RequestParam("getdId") int dId, Model model) {
			car = carService.getCarService(dId);
			if (car==null) {
				model.addAttribute("getCarError", "Please enter the ID of an existing vehicle");
			}
			model.addAttribute("dId", car.getdId() + ", ");
			model.addAttribute("model", car.getModel() + ", ");
			model.addAttribute("year", car.getYear() + ", ");
			model.addAttribute("color", car.getColor() + ", ");
			model.addAttribute("insuranceProvider", car.getInsuranceProvider() + ", ");
			model.addAttribute("driverId", car.getDriverId() + ", ");
			return "vehicles";
		}
		
		@PostMapping("/updateCar")
		public String updateCar(@ModelAttribute("driverVehicle") DriverVehicle car, Model model, BindingResult result) {
			if (result.hasErrors()) {
				model.addAttribute("updateCarError", "There was an error with an input field, please try again");
				return "vehicles";
			}
			carService.updateCarService(car);
			model.addAttribute("updateCarSuccess", "Vehicle updated successfully!");
			System.out.println("updated successfully");
			return "vehicles";
		}
		
		@PostMapping("/removeCar")
		public String removeCar(@ModelAttribute("driverVehicle") DriverVehicle car, @RequestParam("dId") int dId, Model model) {
			carService.removeCarService(dId);
			model.addAttribute("removeCarSuccess", "Vehicle removed from database sucessfully!");
			return "vehicles";
		}
		
		
		
		//EQUIPMENT METHODS
		@PostMapping("/addMac")
		public String addNewMac(@ModelAttribute("machinery") Machinery mac, Model model, BindingResult result) {
			if (result.hasErrors()) {
				model.addAttribute("addMacError", "There was an error with an input field, please try again");
				return "equipment";
			}
			macService.addMacService(mac);
			model.addAttribute("addMacSuccess", "Equipment added to the database successfully!");
			System.out.println("added to db successfully");
			return "equipment";
		}
		
		@PostMapping("/getMac")
		public String getMac(@ModelAttribute("machinery") Machinery mac, @RequestParam("getmId") int mId, Model model) {
			mac = macService.getMacService(mId);
			if (mac==null) {
				model.addAttribute("getMacError", "Please enter the ID of existing equipment");
			}
			model.addAttribute("mId", mac.getmId() + ", ");
			model.addAttribute("name", mac.getName() + ", ");
			model.addAttribute("status", mac.getStatus() + ", ");
			model.addAttribute("replacementCost", mac.getReplacementCost() + ", ");
			model.addAttribute("storeId", mac.getStoreId() + ", ");
			return "equipment";
		}
		
		@PostMapping("/updateMac")
		public String updateMac(@ModelAttribute("machinery") Machinery mac, Model model, BindingResult result) {
			if (result.hasErrors()) {
				model.addAttribute("updateMacError", "There was an error with an input field, please try again");
				return "equipment";
			}
			macService.updateMacService(mac);
			model.addAttribute("updateMacSuccess", "Equipment updated successfully!");
			System.out.println("updated successfully");
			return "equipment";
		}
		
		@PostMapping("/removeMac")
		public String removeMac(@ModelAttribute("machinery") Machinery mac, @RequestParam("mId") int mId, Model model) {
			macService.removeMacService(mId);
			model.addAttribute("removeMacSuccess", "Equipment removed from database sucessfully!");
			return "equipment";
		}
}
