package com.abc.fundraising.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.fundraising.model.Admin;
import com.abc.fundraising.model.AdminDetails;
import com.abc.fundraising.model.Category;
import com.abc.fundraising.model.Donor;
import com.abc.fundraising.model.Post;
import com.abc.fundraising.model.User;
import com.abc.fundraising.service.AdminService;
import com.abc.fundraising.service.CategoryService;
import com.abc.fundraising.service.DonorService;
import com.abc.fundraising.service.PostService;
import com.abc.fundraising.service.UserService;


@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private DonorService donorService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<Admin> CreateAdmin(@RequestBody Admin admin) {
		
		Admin newAdmin = adminService.saveAdmin(admin);
		return new ResponseEntity<>(newAdmin,HttpStatus.CREATED);
	}
	  
	@GetMapping("/get/{pid}")
	public ResponseEntity<?> fetchPostDetails(@PathVariable("pid") int adminId) {
		AdminDetails admin = adminService.getAdminById(adminId);
		return new ResponseEntity<>(admin, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{pid}")
	public ResponseEntity<?> deletePost(@PathVariable("pid") int adminId) {
		adminService.deleteAdmin(adminId);
		return new ResponseEntity<>("Post Deleted with id:" + adminId, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public List<Admin> fetchAllAdmins(){
	
	 List<Admin> admins = adminService.getAllAdmins();
	 
	 return admins;
	 
	}
		@PutMapping("/update")
		public ResponseEntity<Admin> modifyAdmin(@RequestBody Admin admin) {
		
			Admin updatedCustomer = adminService.updateAdmin(admin);
			return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
		}
		
		@GetMapping("/getbycategoryId/{categoryId}")
		public Category fetchCategoryByUserId(@PathVariable("categoryId") int categoryId){
			Category category = categoryService.getCategoryDetails(categoryId);
			return category;
		}
		
		@GetMapping("/getbyuserid/{userid}")
		public User fetchUser(@PathVariable("userid") int userId){
			User user = userService.getUserDetails(userId);
			return user;
		}
		
		@GetMapping("/getbydonorid/{donorid}")
		public Donor fetchDonor(@PathVariable("donorid") int donorId){
			Donor donor = donorService.getDonorDetails(donorId);
			return donor;
		}
		
		@GetMapping("/getbypostid/{postid}")
		public Post fetchPost(@PathVariable("postid") int postId){
			Post post = postService.getPostDetails(postId);
			return post;
		}

		@GetMapping("/getbyadminid/{aid}")
		public ResponseEntity<?> fetchAdmin(@PathVariable("aid") int adminId) {
			Admin admin = adminService.getAdminDetails(adminId);
			return new ResponseEntity<>(admin, HttpStatus.OK);
		}

	}