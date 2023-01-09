package com.ericbarajas.bookclub.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.ericbarajas.bookclub.models.Book;
import com.ericbarajas.bookclub.models.LoginUser;
import com.ericbarajas.bookclub.models.User;
import com.ericbarajas.bookclub.services.BookService;
import com.ericbarajas.bookclub.services.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userServ;
	
	@Autowired
	BookService bookServ;
	
	@GetMapping("/")
	public String index(
		@ModelAttribute("newUser") User emptyUser,
		@ModelAttribute("newLogin") LoginUser emptyLoginUser
	) {
		return "/user/index.jsp";
	}
	
	
	
	// PROCESS REGISTER
	@PostMapping("/register")
	public String register(
		@Valid @ModelAttribute("newUser") User filledUser,
		BindingResult results,
		HttpSession session,
		Model model
	) {
		User createdUser = userServ.register(filledUser, results);
		if(results.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "/user/index.jsp";
		}
		// SAVE THE USERS ID IN SESSION
		session.setAttribute("user_id", createdUser.getId());
		return "redirect:/books";
	}
	
	// PROCESS LOGIN
	@PostMapping("/login")
	public String login(
		@Valid @ModelAttribute("newLogin") LoginUser filledLoginUser,
		BindingResult results,
		HttpSession session,
		Model model
	) {
		User loggedUser = userServ.login(filledLoginUser, results);
		if(results.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "/user/index.jsp";
		}
		session.setAttribute("user_id", loggedUser.getId());
		return "redirect:/books";
	}
	
//	@GetMapping("/homepage")
//	public String homepage(
//		HttpSession session,
//		Model model // VIEW MODEL
//	) {
//		Long userId = (Long) session.getAttribute("user_id");
//		if(userId== null) {
//			return "redirect:/";
//		}
//		// GRAB ONE USER FROM DB
//		model.addAttribute("oneUser", userServ.getOneUser(userId));
//		
//		return "dashboard.jsp";
//		
//	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("user_id", null);
		return "redirect:/";
	}
	
//----------------CREATE A BOOK------------------//
	@GetMapping("/books/new")
	public String create(
		@ModelAttribute("bookObj") Book emptyBookObj,
		HttpSession session
	) {
		Long userId = (Long) session.getAttribute("user_id");
		if(userId== null) {
			return "redirect:/";
		}
		return "/book/create.jsp";
	}
	
	@PostMapping("/books/new")
	public String createBook(
		@Valid @ModelAttribute("bookObj") Book filledBookObj,
		BindingResult results
	) {
		//VALIDATIONS FAIL
		if(results.hasErrors()) {
			return "/book/create.jsp"; 
		}
		// VALIDATIONS PASS
		bookServ.createBook(filledBookObj);
		return "redirect:/books";
	}
	
//----------------CREATE A BOOK------------------//
	
//----------------------READ--------------------//
	// READ ALL BOOKS
	// BOOKS ROUTES
	@GetMapping("/books")
	public String showAll(
			HttpSession session, Model model
	) {
		Long userId = (Long) session.getAttribute("user_id");
		if(userId== null) {
			return "redirect:/";
		}
		model.addAttribute("oneUser", userServ.getOneUser(userId));
		model.addAttribute("allBooks", bookServ.getAllBooks());
		return "/book/dashboard.jsp";
	}
	
	// READ ONE
	@GetMapping("/books/{id}")
	public String showOneBook(
		@PathVariable("id") Long Id,
		Model model,
		HttpSession session
	) {
		Long userId = (Long) session.getAttribute("user_id");
		if(userId== null) {
			return "redirect:/";
		}
		Book oneBook = bookServ.getOneBook(Id);
		model.addAttribute("oneBook", oneBook);
		return "/book/show.jsp";
	}
//---------------------READ----------------------//
	
//-----------------EDIT (UPDATE)------------------------//
	@GetMapping("/books/{id}/edit")  
	public String edit(
			@PathVariable("id") Long Id,
			Model model,
			HttpSession session
	) {
		Long userId = (Long) session.getAttribute("user_id");
		if(userId== null) {
			return "redirect:/";
		}
		model.addAttribute("bookObj", bookServ.getOneBook(Id));
		return "/book/edit.jsp";
	}
	
	@PutMapping("/books/{id}/edit")
	public String editBook(
		@Valid @ModelAttribute("bookObj") Book filledBookObj,
		BindingResult results
	) {
		//VALIDATIONS FAIL
		if(results.hasErrors()) {
			return "/book/edit.jsp"; 
		}
		// VALIDATIONS PASS
		bookServ.createBook(filledBookObj);
		return "redirect:/books";
	}
//-----------------EDIT (UPDATE)------------------------//
	
//--------------------DELETE------------------------//
	@GetMapping("/book/{id}/delete")
	 public String deleteBook(@PathVariable("id") Long bookId, HttpSession session
	){
		Long userId = (Long) session.getAttribute("user_id");
		if(userId== null) {
			return "redirect:/";
		}
		bookServ.deleteBook(bookId);
		return "redirect:/books";
		 
	 }
//--------------------DELETE------------------------//
	
}
