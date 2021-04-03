package com.pizza.shop.controllers;

import java.util.List;

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
	
	@GetMapping("/logout")
	public String showLogoutPage() {
		return "logout";
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
	
	@PostMapping("/logout")
	public String processLogoutRequest(Model model, HttpSession session) {
		Object loggedIn = session.getAttribute("currentUser");
		if (loggedIn!=null) {
			session.removeAttribute("currentUser");
		} else {
			model.addAttribute("logoutError", "Nobody is logged in!");
			return "logout";
		}
		return "login";
	}
	
	
	
	
	
	
	//EMP METHODS
	@PostMapping("/addEmp")
	public String addNewEmployee(@ModelAttribute("employee") Employee emp, Model model, BindingResult result, HttpSession session) {
		Object loggedIn = session.getAttribute("currentUser");
		if (result.hasErrors()) {
			model.addAttribute("errorMessage", "There was an error with an input field, please try again");
			return "employees";
		} else if (loggedIn==null) {
			model.addAttribute("addEmpSessionError", "You must be logged in to add an employee to the database");
			return "employees";
		} else {
			int sId = emp.getStoreId();
			Store store = storeService.getStoreService(sId);
			if (store==null) {
				model.addAttribute("addEmpStoreError", "Please ensure that the Store ID matches the ID of an existing store");
				return "employees";
			} else {
				int eId = emp.geteId();
				empService.addEmpService(emp);
				storeService.addEmpToStoreService(eId, sId);
				model.addAttribute("successMessage", "Employee added to the database successfully!");
				System.out.println("added to db successfully");
			}
		}
		return "employees";
	}
	
	@PostMapping("/getEmp")
	public String getEmployee(@ModelAttribute("employee") Employee emp, @RequestParam("eId") int eId, Model model, HttpSession session) {
		Object loggedIn = session.getAttribute("currentUser");
		emp = empService.getEmpService(eId);
		if (emp==null) {
			model.addAttribute("getEmpError", "Please enter the ID of an existing employee");
			return "employees";
		} else if (loggedIn==null) {
			model.addAttribute("getEmpSessionError", "You must be logged in to view an employee in the database");
			return "employees";
		} else {
			System.out.println(emp.getFirstName());
			model.addAttribute("eId", emp.geteId() + ", ");
			model.addAttribute("firstName", emp.getFirstName() + ", ");
			model.addAttribute("lastName", emp.getLastName() + ", ");
			model.addAttribute("wage", emp.getWage() + ", ");
			model.addAttribute("position", emp.getPosition() + ", ");
			model.addAttribute("email", emp.getEmail() + ", ");
			model.addAttribute("phoneNumber", emp.getPhoneNumber() + ", ");
			model.addAttribute("storeId", emp.getStoreId() + ", ");
		}
		return "employees";
	}
	
	@PostMapping("/updateEmp")
	public String updateEmployee(@ModelAttribute("employee") Employee emp, Model model, BindingResult result, HttpSession session) {
		Object loggedIn = session.getAttribute("currentUser");
		if (result.hasErrors()) {
			model.addAttribute("updateEmpError", "There was an error with an input field, please try again");
			return "employees";
		} else if (loggedIn==null) {
			model.addAttribute("updateEmpSessionError", "You must be logged in to update an employee in the database");
			return "employees";
		} else {
			empService.updateEmpService(emp);
			model.addAttribute("updateEmpSuccess", "Employee updated successfully!");
			System.out.println("updated successfully");
		}
		return "employees";
	}
	
	@PostMapping("/removeEmp")
	public String removeEmployee(@ModelAttribute("employee") Employee emp, @RequestParam("eId") int eId, Model model, HttpSession session) {
		Object loggedIn = session.getAttribute("currentUser");
		emp = empService.getEmpService(eId);
		if (empService.getEmpService(eId)==null) {
			model.addAttribute("removeEmpError", "Please enter the ID of an existing employee");
		} else if (loggedIn==null) {
			model.addAttribute("removeEmpSessionError", "You must be logged in to remove an employee from the database");
			return "employees";
		} else {
			int sId = emp.getStoreId();
			storeService.removeEmpFromStoreService(eId, sId);
			empService.removeEmpService(eId);
			model.addAttribute("removeEmpSuccess", "Employee removed from database sucessfully!");
		}
		return "employees";
	}
	
	
	
	
	
	
	
	//STORE METHODS
		@PostMapping("/addStore")
		public String addNewStore(@ModelAttribute("store") Store store, Model model, BindingResult result, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			if (result.hasErrors()) {
				model.addAttribute("addStoreError", "There was an error with an input field, please try again");
				return "stores";
			} else if (loggedIn==null) {
				model.addAttribute("addStoreSessionError", "You must be logged in to add a store to the database");
				return "stores";
			} else {
				storeService.addStoreService(store);
				model.addAttribute("addStoreSuccess", "Store added to the database successfully!");
				System.out.println("added to db successfully");
			}
			return "stores";
		}
		
		@PostMapping("/getStore")
		public String getStore(@ModelAttribute("store") Store store, @RequestParam("sId") int sId, Model model, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			store = storeService.getStoreService(sId);
			if (store==null) {
				model.addAttribute("getStoreError", "Please enter the ID of an existing store");
			} else if (loggedIn==null) {
				model.addAttribute("getStoreSessionError", "You must be logged in to view a store in the database");
				return "stores";
			} else {
				model.addAttribute("sId", store.getsId() + ", ");
				model.addAttribute("name", store.getName() + ", ");
				model.addAttribute("address", store.getAddress() + ", ");
				model.addAttribute("gmId", store.getGmId() + ", ");
			}
			return "stores";
		}
		
		@PostMapping("/updateStore")
		public String updateStore(@ModelAttribute("store") Store store, Model model, BindingResult result, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			if (result.hasErrors()) {
				model.addAttribute("updateStoreError", "There was an error with an input field, please try again");
				return "stores";
			} else if (loggedIn==null) {
				model.addAttribute("updateStoreSessionError", "You must be logged in to update a store in the database");
				return "stores";
			} else {
				storeService.updateStoreService(store);
				model.addAttribute("updateStoreSuccess", "Store updated successfully!");
				System.out.println("updated successfully");
			}
			return "stores";
		}
		
		@PostMapping("/removeStore")
		public String removeStore(@ModelAttribute("store") Store store, @RequestParam("sId") int sId, Model model, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			if (storeService.getStoreService(sId)==null) {
				model.addAttribute("removeStoreError", "Please enter the ID of an existing store");
			} else if (loggedIn==null) {
				model.addAttribute("removeStoreSessionError", "You must be logged in to remove a store from the database");
				return "stores";
			} else {
				storeService.removeStoreService(sId);
				model.addAttribute("removeStoreSuccess", "Store removed from database sucessfully!");
			}
			return "stores";
		}
		
		
		
		
		
		
		//VEHICLE METHODS
		@PostMapping("/addCar")
		public String addNewCar(@ModelAttribute("driverVehicle") DriverVehicle car, Model model, BindingResult result, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			int eId = car.getDriverId();
			Employee driver = empService.getEmpService(eId);
			if (result.hasErrors()) {
				model.addAttribute("addCarError", "There was an error with an input field, please try again");
				return "vehicles";
			} else if (loggedIn==null) {
				model.addAttribute("addCarSessionError", "You must be logged in to add a vehicle to the database");
				return "vehicles";
			} else if (driver==null) {
				model.addAttribute("addCarEmpError", "The Driver ID must correspond with an existing employee");
				return "vehicles";
			}
			carService.addCarService(car);
			model.addAttribute("addCarSuccess", "Vehicle added to the database successfully!");
			System.out.println("added to db successfully");
			return "vehicles";
		}
		
		@PostMapping("/getCar")
		public String getCar(@ModelAttribute("driverVehicle") DriverVehicle car, @RequestParam("dId") int dId, Model model, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			car = carService.getCarService(dId);
			if (car==null) {
				model.addAttribute("getCarError", "Please enter the ID of an existing vehicle");
			} else if (loggedIn==null) {
				model.addAttribute("getCarSessionError", "You must be logged in to view a vehicle in the database");
				return "vehicles";
			} else {
				model.addAttribute("dId", car.getdId() + ", ");
				model.addAttribute("model", car.getModel() + ", ");
				model.addAttribute("year", car.getYear() + ", ");
				model.addAttribute("color", car.getColor() + ", ");
				model.addAttribute("insuranceProvider", car.getInsuranceProvider() + ", ");
				model.addAttribute("driverId", car.getDriverId() + ", ");
			}
			return "vehicles";
		}
		
		@PostMapping("/updateCar")
		public String updateCar(@ModelAttribute("driverVehicle") DriverVehicle car, Model model, BindingResult result, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			if (result.hasErrors()) {
				model.addAttribute("updateCarError", "There was an error with an input field, please try again");
				return "vehicles";
			} else if (loggedIn==null) {
				model.addAttribute("updateCarSessionError", "You must be logged in to update a vehicle in the database");
				return "vehicles";
			} else {
				carService.updateCarService(car);
				model.addAttribute("updateCarSuccess", "Vehicle updated successfully!");
				System.out.println("updated successfully");
			}
			return "vehicles";
		}
		
		@PostMapping("/removeCar")
		public String removeCar(@ModelAttribute("driverVehicle") DriverVehicle car, @RequestParam("dId") int dId, Model model, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			if (carService.getCarService(dId)==null) {
				model.addAttribute("RemoveCarError", "Please enter ID of an existing vehicle");
			} else if (loggedIn==null) {
				model.addAttribute("removeCarSessionError", "You must be logged in to remove a vehicle in the database");
				return "vehicles";
			} else {
				carService.removeCarService(dId);
				model.addAttribute("removeCarSuccess", "Vehicle removed from database sucessfully!");
			}
			return "vehicles";
		}
		
		
		
		
		
		
		
		//EQUIPMENT METHODS
		@PostMapping("/addMac")
		public String addNewMac(@ModelAttribute("machinery") Machinery mac, Model model, BindingResult result, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			if (result.hasErrors()) {
				model.addAttribute("addMacError", "There was an error with an input field, please try again");
				return "equipment";
			} else if (loggedIn==null) {
				model.addAttribute("addMacSessionError", "You must be logged in to add equipment to the database");
				return "equipment";
			} else if (mac.getStatus() <1 || mac.getStatus() > 3) {
				model.addAttribute("addMacStatusError", "Please select a Status value between 1 and 3");
				return "equipment";
			} else {
				int sId = mac.getStoreId();
				Store store = storeService.getStoreService(sId);
				if (store==null) {
					model.addAttribute("addMacStoreError", "Please ensure that the Store ID matches the ID of an existing store");
					return "equipment";
				} else {
					int mId = mac.getmId();
					macService.addMacService(mac);
					storeService.addMacToStoreService(mId, sId);
					model.addAttribute("addMacSuccess", "Equipment added to the database successfully!");
					System.out.println("added to db successfully");
				}
			}
			return "equipment";
		}
		
		@PostMapping("/getMac")
		public String getMac(@ModelAttribute("machinery") Machinery mac, @RequestParam("mId") int mId, Model model, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			mac = macService.getMacService(mId);
			if (mac==null) {
				model.addAttribute("getMacError", "Please enter the ID of existing equipment");
			} else if (loggedIn==null) {
				model.addAttribute("getMacSessionError", "You must be logged in to view equipment in the database");
				return "equipment";
			} else {
				model.addAttribute("mId", mac.getmId() + ", ");
				model.addAttribute("name", mac.getName() + ", ");
				model.addAttribute("status", mac.getStatus() + ", ");
				model.addAttribute("replacementCost", mac.getReplacementCost() + ", ");
				model.addAttribute("storeId", mac.getStoreId() + ", ");
			}
			return "equipment";
		}
		
		@PostMapping("/updateMac")
		public String updateMac(@ModelAttribute("machinery") Machinery mac, Model model, BindingResult result, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			if (result.hasErrors()) {
				model.addAttribute("updateMacError", "There was an error with an input field, please try again");
				return "equipment";
			} else if (loggedIn==null) {
				model.addAttribute("updateMacSessionError", "You must be logged in to update equipment in the database");
				return "equipment";
			} else if (mac.getStatus() <1 || mac.getStatus() > 3) {
				model.addAttribute("updateMacStatusError", "Please select a Status value between 1 and 3");
				return "equipment";
			} else {
				macService.updateMacService(mac);
				model.addAttribute("updateMacSuccess", "Equipment updated successfully!");
				System.out.println("updated successfully");
			}
			return "equipment";
		}
		
		@PostMapping("/removeMac")
		public String removeMac(@ModelAttribute("machinery") Machinery mac, @RequestParam("mId") int mId, Model model, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			mac = macService.getMacService(mId);
			if (macService.getMacService(mId)==null) {
				model.addAttribute("removeMacError", "Please enter the ID of existing equipment");
			} else if (loggedIn==null) {
				model.addAttribute("removeMacSessionError", "You must be logged in to remove equipment from the database");
				return "equipment";
			} else {
				int sId = mac.getStoreId();
				storeService.removeMacFromStoreService(mId, sId);
				macService.removeMacService(mId);
				model.addAttribute("removeMacSuccess", "Equipment removed from database sucessfully!");
			}
			return "equipment";
		}
}
