package com.pizza.shop.Elijah.Flanders.controllers;


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

import com.pizza.shop.Elijah.Flanders.entity.DriverVehicle;
import com.pizza.shop.Elijah.Flanders.entity.Employee;
import com.pizza.shop.Elijah.Flanders.entity.Machinery;
import com.pizza.shop.Elijah.Flanders.entity.Store;
import com.pizza.shop.Elijah.Flanders.exceptions.NoDuplicateException;
import com.pizza.shop.Elijah.Flanders.exceptions.NoZeroException;
import com.pizza.shop.Elijah.Flanders.service.DriverVehicleService;
import com.pizza.shop.Elijah.Flanders.service.EmployeeService;
import com.pizza.shop.Elijah.Flanders.service.MachineryService;
import com.pizza.shop.Elijah.Flanders.service.StoreService;
import com.pizza.shop.Elijah.Flanders.utilizes.Utilizes;

@Controller
public class HomeController extends Utilizes {
	
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
		return welcome;
	}
	
	@GetMapping("/welcome")
	public String showWelcomePage() {
		return welcome;
	}
	
	@GetMapping("/employees")
	public String showEmployeesPage(Model model) {
		model.addAttribute("employee", new Employee());
		return employees;
	}
	
	@GetMapping("/equipment")
	public String showEquipmentPage(Model model) {
		model.addAttribute("machinery", new Machinery());
		return equipment;
	}
	
	@GetMapping("/vehicles")
	public String showVehiclesPage(Model model) {
		model.addAttribute("driverVehicle", new DriverVehicle());
		return vehicles;
	}
	
	@GetMapping("/stores")
	public String showStoresPage(Model model) {
		model.addAttribute("store", new Store());
		return stores;
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		return login;
	}
	
	@GetMapping("/logout")
	public String showLogoutPage() {
		return logout;
	}
	
	
	
	
	//login functionality
	@PostMapping("/login")
	public String processLoginRequest(@RequestParam("eId") int eId, @RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		Employee emp = empService.getEmpService(eId);
		if (emp!=null && emp.getPosition().equals("General Manager") && email.equals(emp.getEmail()) && password.equals(emp.getPassword())) {
			session.setAttribute("currentUser", emp);
			model.addAttribute("loginSuccessMessage", "Welcome,");
			return login;
		}
		model.addAttribute("loginFailedMessage", "Invalid Credentials");
		return login;
	}
	
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
			model.addAttribute("eId", emp.geteId() + ", ");
			model.addAttribute("firstName", emp.getFirstName() + ", ");
			model.addAttribute("lastName", emp.getLastName() + ", ");
			model.addAttribute("wage", emp.getWage() + ", ");
			model.addAttribute("position", emp.getPosition() + ", ");
			model.addAttribute("email", emp.getEmail() + ", ");
			model.addAttribute("phoneNumber", emp.getPhoneNumber() + ", ");
			model.addAttribute("storeId", emp.getStoreId() + ", ");
		}
		return employees;
	}
	
	@PostMapping("/updateEmp")
	public String updateEmployee(@ModelAttribute("employee") Employee emp, Model model, BindingResult result, HttpSession session) {
		Object loggedIn = session.getAttribute("currentUser");
		int eId = emp.geteId();
		List<Employee> empList = empService.getAllEmpService();
		List<Integer> empIds = new ArrayList<Integer>();
		for (Employee e : empList) {
			empIds.add(e.geteId());
		}
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
		} else if (empIds.contains(eId)) {
			model.addAttribute("updateEmpNoDuplicate", "ID already exists under another employee");
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
				model.addAttribute("updateEmpStoreError", "Please ensure that the Store ID matches the ID of an existing store");
				return employees;
			} else {
				storeService.removeEmpFromStoreService(eId, sId);
				empService.updateEmpService(emp);
				storeService.addEmpToStoreService(eId, sId);
				model.addAttribute("updateEmpSuccess", "Employee updated successfully!");
			}
		}
		return employees;
	}
	
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
				model.addAttribute("sId", store.getsId() + ", ");
				model.addAttribute("name", store.getName() + ", ");
				model.addAttribute("address", store.getAddress() + ", ");
				model.addAttribute("gmId", store.getGmId() + ", ");
			}
			return stores;
		}
		
		@PostMapping("/updateStore")
		public String updateStore(@ModelAttribute("store") Store store, Model model, BindingResult result, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			int sId = store.getsId();
			List<Store> storeList = storeService.getAllStoreService();
			List<Integer> storeIds = new ArrayList<Integer>();
			for (Store s : storeList) {
				storeIds.add(s.getsId());
			}
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
			} else if (storeIds.contains(sId)) {
				model.addAttribute("updateStoreNoDuplicate", "ID already exists under another employee");
				try {
					throw new NoDuplicateException("ID already exists under another employee");
				} catch (NoDuplicateException e) {
					e.printStackTrace();
				}
				return employees;
			} else {
				storeService.updateStoreService(store);
				model.addAttribute("updateStoreSuccess", "Store updated successfully!");
				System.out.println("updated successfully");
			}
			return stores;
		}
		
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
				model.addAttribute("dId", car.getdId() + ", ");
				model.addAttribute("model", car.getModel() + ", ");
				model.addAttribute("year", car.getYear() + ", ");
				model.addAttribute("color", car.getColor() + ", ");
				model.addAttribute("insuranceProvider", car.getInsuranceProvider() + ", ");
				model.addAttribute("driverId", car.getDriverId() + ", ");
			}
			return vehicles;
		}
		
		@PostMapping("/updateCar")
		public String updateCar(@ModelAttribute("driverVehicle") DriverVehicle car, Model model, BindingResult result, HttpSession session) {
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
			} else if (carIds.contains(dId)) {
				model.addAttribute("updateCarNoDuplicate", "ID already exists under another employee");
				try {
					throw new NoDuplicateException("ID already exists under another employee");
				} catch (NoDuplicateException e) {
					e.printStackTrace();
				}
				return employees;
			} else {
				carService.updateCarService(car);
				model.addAttribute("updateCarSuccess", "Vehicle updated successfully!");
				System.out.println("updated successfully");
			}
			return vehicles;
		}
		
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
				model.addAttribute("mId", mac.getmId() + ", ");
				model.addAttribute("name", mac.getName() + ", ");
				model.addAttribute("status", mac.getStatus() + ", ");
				model.addAttribute("replacementCost", mac.getReplacementCost() + ", ");
				model.addAttribute("storeId", mac.getStoreId() + ", ");
			}
			return equipment;
		}
		
		@PostMapping("/updateMac")
		public String updateMac(@ModelAttribute("machinery") Machinery mac, Model model, BindingResult result, HttpSession session) {
			Object loggedIn = session.getAttribute("currentUser");
			int mId = mac.getmId();
			List<Machinery> macList = macService.getAllMacService();
			List<Integer> macIds = new ArrayList<Integer>();
			for (Machinery m : macList) {
				macIds.add(m.getmId());
			}
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
			} else if (macIds.contains(mId)) {
				model.addAttribute("updateMacNoDuplicate", "ID already exists under another employee");
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
					model.addAttribute("updateMacStoreError", "Please ensure that the Store ID matches the ID of an existing store");
					return equipment;
				} else {
					storeService.removeMacFromStoreService(mId, sId);
					macService.updateMacService(mac);
					storeService.addMacToStoreService(mId, sId);
					model.addAttribute("updateMacSuccess", "Equipment updated successfully!");
				}
			}
			return equipment;
		}
		
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
