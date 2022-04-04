package com.ss.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.model.Customer;
import com.ss.model.Purchase;
import com.ss.service.CartService;
import com.ss.service.CustomerService;
import com.ss.service.PurchaseService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private CartService cartService;

	@ExceptionHandler(SQLException.class)
	public String handleSqlException(SQLException e, HttpSession session) {
		session.setAttribute("action", "User can't be deleted until their orders are deleted");
		return "redirect:/manageCustomer";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(Customer customer, Model model, HttpSession session) {
		List<String> cEmails = customerService.customerEmails();
		boolean notExist = true;
		for(String e : cEmails) {
			if(customer.getEmail().equals(e))
				notExist=false;
		}
		if(notExist) {
			if (validate(customer.getEmail())) {
				customerService.saveCustomer(customer);
				model.addAttribute("action", "Added successfully, login to shop");
				session.setAttribute("customerLogin", customer.getEmail());
				session.setAttribute("custName", customer.getName());
				cartService.cartDeleteAll();
				return "redirect:/";
			} else {
				model.addAttribute("action", "Email pattern doesn't match");
				return "new_customer";
			}
		}else {
			session.setAttribute("action", "Entered Email Already Exist please Login");
			return "redirect:/";
		}
		
	}

	@PostMapping("/verifyCustLogin")
	public String verifyLogin(@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password, HttpSession session, Model model) {
		if (!email.isEmpty() || !password.isEmpty()) {
			if (customerService.loginVerify(email, password)) {
				session.setAttribute("customerLogin", email);
				Customer customer = customerService.getCustomer(email);
				session.setAttribute("custName", customer.getName());
				cartService.cartDeleteAll();
				return "redirect:/";
			} else {
				model.addAttribute("action", "email or password wrong");
				return "customer_login";
			}
		} else {
			model.addAttribute("action", "Fields must not be empty");
			return "customer_login";
		}

	}

	@GetMapping("/customerLogout")
	public String customerLogout(HttpSession session) {
		cartService.cartDeleteAll();
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/manageCustomer")
	public String manageCustomer(Model model,HttpSession session) {
		model.addAttribute("action", session.getAttribute("action"));
		session.setAttribute("action", null);
		model.addAttribute("customers", customerService.getAllCustomers());
		return "manageCustomer";
	}

	@GetMapping("/deleteCustomer/{email}")
	public String deleteCustomer(@PathVariable(name = "email") String email, Model model) {
		customerService.deleteCustomer(email);
		model.addAttribute("action", "Customer Deleted Sucessfully");
		return "redirect:/manageCustomer";
	}

	@GetMapping("/customerOrders/{email}")
	public String customerOrders(@PathVariable(name = "email") String email, Model model,HttpSession session) {
		List<Purchase> sPurchase = purchaseService.getByEmail(email);
		if(!sPurchase.isEmpty()) {
		model.addAttribute("sPurchase", sPurchase);
		return "customerPurchase";
		}else {
			session.setAttribute("action", "No Active Orders/Purchases by Customer");
			return "redirect:/manageCustomer";
		}
	}
	
	@PostMapping("/searchCustomer")
	public String searchCustomer(@RequestParam("keyword") String keyword,Model model) {
		List<Customer> sCustomer = customerService.searchCustomer(keyword);
		if(sCustomer.isEmpty()) {
			model.addAttribute("action", "No Customer found");
			model.addAttribute("customers", customerService.getAllCustomers());
			return "manageCustomer";
		}else {
			model.addAttribute("searchHeading","Entered Catogery");
			model.addAttribute("sCustomer", sCustomer);
			return "searchCustomer";	
		}
		
	}

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public static boolean validate(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

}
