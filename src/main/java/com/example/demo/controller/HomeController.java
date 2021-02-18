package com.example.demo.controller;

import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.service.UsernameExistsException;

@Controller
@RequestMapping("/")
public class HomeController {

	private UserRepository userRepository;

	private UserService userService;

	@Autowired
	public HomeController(UserRepository userRepository, UserService userServise) {
		super();
		this.userRepository = userRepository;
		this.userService = userServise;
	}

	@GetMapping("/")
	public String home(Model model, Principal principal) {
		if (principal != null) {
			// юзернейм (уникальный)
			String username = principal.getName();
			User user = userRepository.findByUsername(username);
			model.addAttribute("message", "Welcome, " + user.getName());
		} else {
			model.addAttribute("message", "Welcome to my app!");
		}
		return "home";
	}

	@GetMapping("/login")
	public String signIn() {
		return "login";
	}

	@GetMapping("/signup")
	public String signUp() {
		return "signup";
	}

	@PostMapping("/signup")
	public String registerNewUser(@ModelAttribute("user") User user, HttpServletRequest request) {
		String password = user.getPassword();
		try {
				request.login(user.getUsername(), password);
			userService.registerNewUser(user);
		} catch (UsernameExistsException e) {
			return "redirect:/signup";
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/";
	}
}
