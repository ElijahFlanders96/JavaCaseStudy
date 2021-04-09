package com.pizza.shop.controllers;


import java.util.ArrayList;
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

import com.pizza.shop.constants.Constants;
import com.pizza.shop.entity.DriverVehicle;
import com.pizza.shop.entity.Employee;
import com.pizza.shop.entity.Machinery;
import com.pizza.shop.entity.Store;
import com.pizza.shop.exceptions.NoDuplicateException;
import com.pizza.shop.exceptions.NoZeroException;
import com.pizza.shop.service.DriverVehicleService;
import com.pizza.shop.service.EmployeeService;
import com.pizza.shop.service.MachineryService;
import com.pizza.shop.service.StoreService;

// Controller class. This class handles all requests from a user on the app
@Controller
public class HomeController extends Constants {
	
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
	
	// routes to the Welcome page when the app is opened in the browser
	@GetMapping("/")
	public String showWelcomePageInit() {
		return welcome;
	}
	
	// routes to the Welcome page
	@GetMapping("/welcome")
	public String showWelcomePage() {
		return welcome;
	}
	
	// routes to the Employees page
	@GetMapping("/employees")
	public String showEmployeesPage(Model model) {
		model.addAttribute("employee", new Employee());
		return employees;
	}
	
	// routes to the Equipment page
	@GetMapping("/equipment")
	public String showEquipmentPage(Model model) {
		model.addAttribute("machinery", new Machinery());
		return equipment;
	}
	
	// routes to the Vehicles page
	@GetMapping("/vehicles")
	public String showVehiclesPage(Model model) {
		model.addAttribute("driverVehicle", new DriverVehicle());
		return vehicles;
	}
	
	// routes to the Stores page
	@GetMapping("/stores")
	public String showStoresPage(Model model) {
		model.addAttribute("store", new Store());
		return stores;
	}
	
	// routes to the Login page
	@GetMapping("/login")
	public String showLoginPage(Model model) {
		model.addAttribute("employee", new Employee());
		return login;
	}
	
	// routes to the Logout page
	@GetMapping("/logout")
	public String showLogoutPage() {
		return logout;
	}
	
	
	
	
	// login functionality
	// this method allows general managers to login. General managers are the only employee type that can access the features of this app
	@PostMapping("/login")
	public String processLoginRequest(@ModelAttribute("employee") Employee emp, @RequestParam("eId") int eId, @RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		emp = empService.getEmpService(eId);
		if (eId==0) {
			model.addAttribute("nullID", "the ID field cannot be null");
			return login;
		} else if (emp!=null && emp.getPosition().equals("General Manager") && email.equals(emp.getEmail()) && password.equals(emp.getPassword())) {
			session.setAttribute("currentUser", emp);
			model.addAttribute("loginSuccessMessage", "Welcome,");
			return login;
		}
		model.addAttribute("loginFailedMessage", "Invalid Credentials");
		return login;
	}
	
	// logout functionality
	// this method allows a general manager to logout and end the session
	@PostMapping("/logout")
	public String processLogoutRequest(Model model, HttpSession session) {
		Object loggedIn = session.getAttribute("currentUser");
		if (loggedIn!=null) {
			session.removeAttribute("currentUser");
		} else {
			model.addAttribute("logoutError", "Nobody is logged in!");
			return logout;
		}
		return logoutComplete;
	}
	
	
	
	
	
	
	//EMP METHODS
	
	// this method allows a user to view all employees in the database
	@PostMapping("viewEmps")
	public String viewEmpsByID(@ModelAttribute("employee") Employee emp, Model model, HttpSession session) {
		Object loggedIn = session.getAttribute("currentUser");
		if (loggedIn==null) {
			model.addAttribute("viewEmpSessionError", "You must be logged in to view all employees in the database");
			return employees;
		} else {
			model.addAttribute("empList", empService.getAllEmpService());
		}
		return employees;
	}
	
	// this method allows a user to add an employee to the database
	@PostMapping("/addEmp")
	public String addNewEmployee(@ModelAttribute("employee") Employee emp, Model model, BindingResult result, HttpSession session) {
		Object loggedIn = session.getAttribute("currentUser");
		int eId = emp.geteId();
		List<Employee> empList = empService.getAllEmpService();
		List<Integer> empIds = new ArrayList<Integer>();
		for (Employee e : empList) {
			empIds.add(e.geteId());
		}
		if (result.hasErrors()) {
			model.addAttribute("errorMessage", "There was an error with an input field, please try again");
			return employees;
		} else if (loggedIn==null) {
			model.addAttribute("addEmpSessionError", "You must be logged in to add an employee to the database");
			return employees;
		} else if (eId==0) {
			model.addAttribute("addEmpNoZero", "0 is not a vailid ID");
			try {
				throw new NoZeroException("0 is not a vailid ID");
			} catch (NoZeroException e) {
				e.printStackTrace();
			}
			return employees;
		} else if (empIds.contains(eId)) {
			model.addAttribute("addEmpNoDuplicate", "ID already exists under another employee");
			try {
				throw new NoDuplicateException("ID already exists under another employee");
			} catch (NoDuplicateException e) {
				e.printStackTrace();
			}
			return employees;
		} else {
			int sId = emp.getStoreId();
			Store store = storeService.getStoreService(sId);
			if (store==null) {
				model.addAttribute("addEmpStoreError", "Please ensure that the Store ID matches the ID of an existing store");
				return employees;
			} else {
				empService.addEmpService(emp);
				storeService.addEmpToStoreService(eId, sId);
				model.addAttribute("successMessage", "Employee added to the database successfully!");
				System.out.println("added to db successfully");
			}
		}
		return employees;
	}
	
	// this method allows a user to view a single employee's information
	@PostMapping("/getEmp")
	public String getEmployee(@ModelAttribute("employee") Employee emp, @RequestParam("eId") int eId, Model model, HttpSession session) {
		Object loggedIn = session.getAttribute("currentUser");
		emp = empService.getEmpService(eId);
		if (emp==null) {
			model.addAttribute("getEmpError", "Please enter the ID of an existing employee");
			return employees;
		} else if (loggedIn==null) {
			model.addAttribute("getEmpSessionError", "You must be logged in to view an employee in the database");
			return employees;
		} else {
			System.out.println(emp.getFirstName());
			model.addAttribute("eId", "ID: " + emp.geteId() + ", ");
			model.addAttribute("firstName", "Name: " + emp.getFirstName() + " ");
			model.addAttribute("lastName", emp.getLastName() + ", ");
			model.addAttribute("wage", "Wage: " + emp.getWage() + ", ");
			model.addAttribute("position", "Position: " + emp.getPosition() + ", ");
			model.addAttribute("email", "Email: " + emp.getEmail() + ", ");
			model.addAttribute("phoneNumber", "Phone Number: " + emp.getPhoneNumber() + ", ");
			model.addAttribute("storeId", "Store ID: " + emp.getStoreId());
		}
		return employees;
	}
	
	// this method allows a user to update an employee's information in the database
	@PostMapping("/updateEmp")
	public String updateEmployee(@ModelAttribute("employee") Employee emp, Model model, BindingResult result, HttpSession session) {
		Object loggedIn = session.getAttribute("currentUser");
		int eId = emp.geteId();
		Employee empOriginal = empService.getEmpService(eId);
		int sIdOriginal = empOriginal.getStoreId();
		if (result.hasErrors()) {
			model.addAttribute("updateEmpError", "There was an error with an input field, please try again");
			return employees;
		} else if (loggedIn==null) {
			model.addAttribute("updateEmpSessionError", "You must be logged in to update an employee in the database");
			return employees;
		} else if (eId==0) {
			model.addAttribute("updateEmpNoZero", "0 is not a vailid ID");
			try {
				throw new NoZeroException("0 is not a vailid ID");
			} catch (NoZeroException e) {
				e.printStackTrace();
			}
			return employees;
		} else {
			int sId = emp.getStoreId();
			Store store = storeService.getStoreService(sId);
			if (store==null) {
				model.addAttribute("updateEmpStoreError", "Please ensure that the Store ID matches the ID of an existing store");
				return employees;
			} else {
				storeService.removeEmpFromStoreService(eId, sIdOriginal);
				storeService.addEmpToStoreService(eId, sId);
				empService.updateEmpService(emp);
				model.addAttribute("updateEmpSuccess", "Employee updated successfully!");
			}
		}
		return employees;
	}
	
	// this method allows a user to remove an employee from the database
	@PostMapping("/removeEmp")
	public String removeEmployee(@ModelAttribute("employee") Employee emp, @RequestParam("eId") int eId, Model model, HttpSession session) {
		Object loggedIn = session.getAttribute("currentUser");
		emp = empService.getEmpService(eId);
		if (empService.getEmpService(eId)==null) {
			model.addAttribute("removeEmpError", "Please enter the ID of an existing employee");
		} else if (loggedIn==null) {
			model.addAttribute("removeEmpSessionError", "You must be logged in to remove an employee from the database");
			return employees;
		} else {
			int sId = emp.getStoreId();
			storeService.removeEmpFromStoreService(eId, sId);
			empService.removeEmpService(eId);
			model.addAttribute("removeEmpSuccess", "Employee removed from database sucessfully!");
		}
		return employees;
	}
	
	
	
	
	
	
	
	//STORE METHODS
	
		// this method allows a user to view all stores in the database
		@PostMapping("viewStores")
		public String viewStoresByID(@ModelAttribute("store") Store store, Model model, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			if (loggedIn==null) {
				model.addAttribute("viewStoreSessionError", "You must be logged in to view all stores in the database");
				return stores;
			} else {
				model.addAttribute("storeList", storeService.getAllStoreService());
			}
			return stores;
		}
	
		// this method allows a user to add a store to the database
		@PostMapping("/addStore")
		public String addNewStore(@ModelAttribute("store") Store store, Model model, BindingResult result, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			int sId = store.getsId();
			List<Store> storeList = storeService.getAllStoreService();
			List<Integer> storeIds = new ArrayList<Integer>();
			for (Store s : storeList) {
				storeIds.add(s.getsId());
			}
			if (result.hasErrors()) {
				model.addAttribute("addStoreError", "There was an error with an input field, please try again");
				return stores;
			} else if (loggedIn==null) {
				model.addAttribute("addStoreSessionError", "You must be logged in to add a store to the database");
				return stores;
			} else if (sId==0) {
				model.addAttribute("addStoreNoZero", "0 is not a vailid ID");
				try {
					throw new NoZeroException("0 is not a vailid ID");
				} catch (NoZeroException e) {
					e.printStackTrace();
				}
				return stores;
			} else if (storeIds.contains(sId)) {
				model.addAttribute("addStoreNoDuplicate", "ID already exists under another employee");
				try {
					throw new NoDuplicateException("ID already exists under another employee");
				} catch (NoDuplicateException e) {
					e.printStackTrace();
				}
				return employees;
			} else {
				int eId = store.getGmId();
				Employee emp = empService.getEmpService(eId);
				if (emp==null || emp.getPosition() != "General Manager") {
					model.addAttribute("addStoreGMError", "");
				}
				storeService.addStoreService(store);
				model.addAttribute("addStoreSuccess", "Store added to the database successfully!");
				System.out.println("added to db successfully");
			}
			return stores;
		}
		
		// this method allows a user to view a single store's information
		@PostMapping("/getStore")
		public String getStore(@ModelAttribute("store") Store store, @RequestParam("sId") int sId, Model model, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			store = storeService.getStoreService(sId);
			if (store==null) {
				model.addAttribute("getStoreError", "Please enter the ID of an existing store");
			} else if (loggedIn==null) {
				model.addAttribute("getStoreSessionError", "You must be logged in to view a store in the database");
				return stores;
			} else {
				model.addAttribute("sId", "ID: " + store.getsId() + ", ");
				model.addAttribute("name", "Name: " + store.getName() + ", ");
				model.addAttribute("address", "Address: " + store.getAddress() + ", ");
				model.addAttribute("gmId", "GM ID: " + store.getGmId());
			}
			return stores;
		}
		
		// this method allows a user to update a store's information in the database
		@PostMapping("/updateStore")
		public String updateStore(@ModelAttribute("store") Store store, Model model, BindingResult result, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			int sId = store.getsId();
			if (result.hasErrors()) {
				model.addAttribute("updateStoreError", "There was an error with an input field, please try again");
				return stores;
			} else if (loggedIn==null) {
				model.addAttribute("updateStoreSessionError", "You must be logged in to update a store in the database");
				return stores;
			} else if (sId==0) {
				model.addAttribute("updateStoreNoZero", "0 is not a vailid ID");
				try {
					throw new NoZeroException("0 is not a vailid ID");
				} catch (NoZeroException e) {
					e.printStackTrace();
				}
				return stores;
			} else {
				storeService.updateStoreService(store);
				model.addAttribute("updateStoreSuccess", "Store updated successfully!");
				System.out.println("updated successfully");
			}
			return stores;
		}
		
		// this method allows a user to remove a store from the database
		@PostMapping("/removeStore")
		public String removeStore(@ModelAttribute("store") Store store, @RequestParam("sId") int sId, Model model, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			if (storeService.getStoreService(sId)==null) {
				model.addAttribute("removeStoreError", "Please enter the ID of an existing store");
			} else if (loggedIn==null) {
				model.addAttribute("removeStoreSessionError", "You must be logged in to remove a store from the database");
				return stores;
			} else {
				storeService.removeStoreService(sId);
				model.addAttribute("removeStoreSuccess", "Store removed from database sucessfully!");
			}
			return stores;
		}
		
		// this method allows a user to view all employees associated with the same store
		@PostMapping("/getEmpList")
		public String getEmpList(@ModelAttribute("store") Store store, @RequestParam("sId") int sId, Model model, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			if (storeService.getStoreService(sId)==null) {
				model.addAttribute("empListStoreError", "Please enter the ID of an existing store");
			} else if (loggedIn==null) {
				model.addAttribute("empListSessionError", "You must be logged in to view all employees in a store");
				return stores;
			} else {
				model.addAttribute("empList", storeService.viewAllEmpService(sId));
			}
			return stores;
		}
		
		// this method allows a user to view all equipment associated with the same store
		@PostMapping("/getMacList")
		public String getMacList(@ModelAttribute("store") Store store, @RequestParam("sId") int sId, Model model, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			if (storeService.getStoreService(sId)==null) {
				model.addAttribute("macListStoreError", "Please enter the ID of an existing store");
			} else if (loggedIn==null) {
				model.addAttribute("macListSessionError", "You must be logged in to view all equipment in a store");
				return stores;
			} else {
				model.addAttribute("macList", storeService.viewAllMacService(sId));
			}
			return stores;
		}
		
		
		
		
		//VEHICLE METHODS
		
		// this method allows a user to view all vehicles in the database
		@PostMapping("viewCars")
		public String viewCarsByID(@ModelAttribute("driverVehicle") DriverVehicle car, Model model, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			if (loggedIn==null) {
				model.addAttribute("viewCarSessionError", "You must be logged in to view all vehicles in the database");
				return vehicles;
			} else {
				model.addAttribute("carList", carService.getAllCarService());
			}
			return vehicles;
		}
		
		// this method allows a user to add a vehicle to the database
		@PostMapping("/addCar")
		public String addNewCar(@ModelAttribute("driverVehicle") DriverVehicle car, Model model, BindingResult result, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			int dId = car.getdId();
			int eId = car.getDriverId();
			Employee driver = empService.getEmpService(eId);
			List<DriverVehicle> carList = carService.getAllCarService();
			List<Integer> carIds = new ArrayList<Integer>();
			for (DriverVehicle d : carList) {
				carIds.add(d.getdId());
			}
			if (result.hasErrors()) {
				model.addAttribute("addCarError", "There was an error with an input field, please try again");
				return vehicles;
			} else if (loggedIn==null) {
				model.addAttribute("addCarSessionError", "You must be logged in to add a vehicle to the database");
				return vehicles;
			} else if (driver==null) {
				model.addAttribute("addCarEmpError", "The Driver ID must correspond with an existing employee");
				return vehicles;
			} else if (dId==0) {
				model.addAttribute("addCarNoZero", "0 is not a vailid ID");
				try {
					throw new NoZeroException("0 is not a vailid ID");
				} catch (NoZeroException e) {
					e.printStackTrace();
				}
				return vehicles;
			} else if (carIds.contains(dId)) {
				model.addAttribute("addCarNoDuplicate", "ID already exists under another employee");
				try {
					throw new NoDuplicateException("ID already exists under another employee");
				} catch (NoDuplicateException e) {
					e.printStackTrace();
				}
				return employees;
			} else {
				carService.addCarService(car);
				model.addAttribute("addCarSuccess", "Vehicle added to the database successfully!");
			}
			return vehicles;
		}
		
		// this method allows a user to view a single vehicle's information
		@PostMapping("/getCar")
		public String getCar(@ModelAttribute("driverVehicle") DriverVehicle car, @RequestParam("dId") int dId, Model model, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			car = carService.getCarService(dId);
			if (car==null) {
				model.addAttribute("getCarError", "Please enter the ID of an existing vehicle");
			} else if (loggedIn==null) {
				model.addAttribute("getCarSessionError", "You must be logged in to view a vehicle in the database");
				return vehicles;
			} else {
				model.addAttribute("dId", "ID: " + car.getdId() + ", ");
				model.addAttribute("model", "Model: " + car.getModel() + ", ");
				model.addAttribute("year", "Year: " + car.getYear() + ", ");
				model.addAttribute("color", "Color: " + car.getColor() + ", ");
				model.addAttribute("insuranceProvider", "Insurance Provider: " + car.getInsuranceProvider() + ", ");
				model.addAttribute("driverId", "Driver ID: " + car.getDriverId());
			}
			return vehicles;
		}
		
		// this method allows a user to update a vehicle's information in the database
		@PostMapping("/updateCar")
		public String updateCar(@ModelAttribute("driverVehicle") DriverVehicle car, Model model, BindingResult result, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			int dId = car.getdId();
			int eId = car.getDriverId();
			Employee driver = empService.getEmpService(eId);
			if (result.hasErrors()) {
				model.addAttribute("updateCarError", "There was an error with an input field, please try again");
				return vehicles;
			} else if (loggedIn==null) {
				model.addAttribute("updateCarSessionError", "You must be logged in to update a vehicle in the database");
				return vehicles;
			} else if (driver==null) {
				model.addAttribute("updateCarEmpError", "The Driver ID must correspond with an existing employee");
				return vehicles;
			} else if (dId==0) {
				model.addAttribute("updateCarNoZero", "0 is not a vailid ID");
				try {
					throw new NoZeroException("0 is not a vailid ID");
				} catch (NoZeroException e) {
					e.printStackTrace();
				}
				return vehicles;
			} else {
				carService.updateCarService(car);
				model.addAttribute("updateCarSuccess", "Vehicle updated successfully!");
				System.out.println("updated successfully");
			}
			return vehicles;
		}
		
		// this method allows a user to remove a vehicle from the database
		@PostMapping("/removeCar")
		public String removeCar(@ModelAttribute("driverVehicle") DriverVehicle car, @RequestParam("dId") int dId, Model model, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			if (carService.getCarService(dId)==null) {
				model.addAttribute("removeCarError", "Please enter ID of an existing vehicle");
			} else if (loggedIn==null) {
				model.addAttribute("removeCarSessionError", "You must be logged in to remove a vehicle in the database");
				return vehicles;
			} else {
				carService.removeCarService(dId);
				model.addAttribute("removeCarSuccess", "Vehicle removed from database sucessfully!");
			}
			return vehicles;
		}
		
		
		
		
		
		
		
		//EQUIPMENT METHODS
		
		// this method allows a user to view all equipment in the database
		@PostMapping("viewMacs")
		public String viewMacsByID(@ModelAttribute("machinery") Machinery mac, Model model, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			if (loggedIn==null) {
				model.addAttribute("viewMacSessionError", "You must be logged in to view all equipment in the database");
				return equipment;
			} else {
				model.addAttribute("macList", macService.getAllMacService());
			}
			return equipment;
		}
		
		
		// this method allows a user to add equipment to the database
		@PostMapping("/addMac")
		public String addNewMac(@ModelAttribute("machinery") Machinery mac, Model model, BindingResult result, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			int mId = mac.getmId();
			List<Machinery> macList = macService.getAllMacService();
			List<Integer> macIds = new ArrayList<Integer>();
			for (Machinery m : macList) {
				macIds.add(m.getmId());
			}
			if (result.hasErrors()) {
				model.addAttribute("addMacError", "There was an error with an input field, please try again");
				return equipment;
			} else if (loggedIn==null) {
				model.addAttribute("addMacSessionError", "You must be logged in to add equipment to the database");
				return equipment;
			} else if (mac.getStatus() <1 || mac.getStatus() > 3) {
				model.addAttribute("addMacStatusError", "Please select a Status value between 1 and 3");
				return equipment;
			} else if (mId==0) {
				model.addAttribute("addMacNoZero", "0 is not a vailid ID");
				try {
					throw new NoZeroException("0 is not a vailid ID");
				} catch (NoZeroException e) {
					e.printStackTrace();
				}
				return equipment;
			} else if (macIds.contains(mId)) {
				model.addAttribute("addMacNoDuplicate", "ID already exists under another employee");
				try {
					throw new NoDuplicateException("ID already exists under another employee");
				} catch (NoDuplicateException e) {
					e.printStackTrace();
				}
				return employees;
			} else {
				int sId = mac.getStoreId();
				Store store = storeService.getStoreService(sId);
				if (store==null) {
					model.addAttribute("addMacStoreError", "Please ensure that the Store ID matches the ID of an existing store");
					return equipment;
				} else {
					macService.addMacService(mac);
					storeService.addMacToStoreService(mId, sId);
					model.addAttribute("addMacSuccess", "Equipment added to the database successfully!");
					System.out.println("added to db successfully");
				}
			}
			return equipment;
		}
		
		// this method allows a user to view a single piece of equipment's information
		@PostMapping("/getMac")
		public String getMac(@ModelAttribute("machinery") Machinery mac, @RequestParam("mId") int mId, Model model, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			mac = macService.getMacService(mId);
			if (mac==null) {
				model.addAttribute("getMacError", "Please enter the ID of existing equipment");
			} else if (loggedIn==null) {
				model.addAttribute("getMacSessionError", "You must be logged in to view equipment in the database");
				return equipment;
			} else {
				model.addAttribute("mId", "ID: " + mac.getmId() + ", ");
				model.addAttribute("name", "Name: " + mac.getName() + ", ");
				model.addAttribute("status", "Status: " + mac.getStatus() + ", ");
				model.addAttribute("replacementCost", "Replacement Cost: " + mac.getReplacementCost() + ", ");
				model.addAttribute("storeId", "Store ID: " + mac.getStoreId());
			}
			return equipment;
		}
		
		// this method allows a user to update equipment information in the database
		@PostMapping("/updateMac")
		public String updateMac(@ModelAttribute("machinery") Machinery mac, Model model, BindingResult result, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			int mId = mac.getmId();
			Machinery macOriginal = macService.getMacService(mId);
			int sIdOriginal = macOriginal.getStoreId();
			if (result.hasErrors()) {
				model.addAttribute("updateMacError", "There was an error with an input field, please try again");
				return equipment;
			} else if (loggedIn==null) {
				model.addAttribute("updateMacSessionError", "You must be logged in to update equipment in the database");
				return equipment;
			} else if (mac.getStatus() <1 || mac.getStatus() > 3) {
				model.addAttribute("updateMacStatusError", "Please select a Status value between 1 and 3");
				return equipment;
			} else if (mId==0) {
				model.addAttribute("updateMacNoZero", "0 is not a vailid ID");
				try {
					throw new NoZeroException("0 is not a vailid ID");
				} catch (NoZeroException e) {
					e.printStackTrace();
				}
				return equipment;
			} else {
				int sId = mac.getStoreId();
				Store store = storeService.getStoreService(sId);
				if (store==null) {
					model.addAttribute("updateMacStoreError", "Please ensure that the Store ID matches the ID of an existing store");
					return equipment;
				} else {
					storeService.removeMacFromStoreService(mId, sIdOriginal);
					storeService.addMacToStoreService(mId, sId);
					macService.updateMacService(mac);
					model.addAttribute("updateMacSuccess", "Equipment updated successfully!");
				}
			}
			return equipment;
		}
		
		// this method allows a user to remove equipment from the database
		@PostMapping("/removeMac")
		public String removeMac(@ModelAttribute("machinery") Machinery mac, @RequestParam("mId") int mId, Model model, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			mac = macService.getMacService(mId);
			if (macService.getMacService(mId)==null) {
				model.addAttribute("removeMacError", "Please enter the ID of existing equipment");
			} else if (loggedIn==null) {
				model.addAttribute("removeMacSessionError", "You must be logged in to remove equipment from the database");
				return equipment;
			} else {
				int sId = mac.getStoreId();
				storeService.removeMacFromStoreService(mId, sId);
				macService.removeMacService(mId);
				model.addAttribute("removeMacSuccess", "Equipment removed from database sucessfully!");
			}
			return equipment;
		}
}
