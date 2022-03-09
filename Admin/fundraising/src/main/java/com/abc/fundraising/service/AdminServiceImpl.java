package com.abc.fundraising.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.fundraising.entity.AdminEntity;
import com.abc.fundraising.exception.AdminNotFoundException;
import com.abc.fundraising.exception.PostNotFoundException;
import com.abc.fundraising.model.Admin;
import com.abc.fundraising.model.AdminDetails;
import com.abc.fundraising.model.Category;
import com.abc.fundraising.model.Donor;
import com.abc.fundraising.model.Post;
import com.abc.fundraising.model.User;
import com.abc.fundraising.repository.AdminRepository;
import com.abc.fundraising.util.EntityModelUtil;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private PostService postService;

	@Autowired
	private DonorService donarService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private EntityModelUtil entityModelUtil;
	
	
	/****************************************************************************************************************************
	 - Method Name      : saveAdmin
	 - Input Parameters : int adminId
	 - Return type      : Admin
	 - Author           : Deva Pavan Kumar
	 - Creation Date    : 07-02-2022
	 - Description      : Saving the admin information into  the database..
	  ****************************************************************************************************************************/
	@Override
	public Admin saveAdmin(Admin admin) {

		AdminEntity savedAdmin = adminRepository.save(entityModelUtil.adminModelToEntity(admin));

		return entityModelUtil.adminEntityToModel(savedAdmin);
	}
	
	/****************************************************************************************************************************
	 - Method Name      : getAllAdmins
	 - Input Parameters : void
	 - Return type      : Admin
	 - Author           : Deva Pavan Kumar
	 - Creation Date    : 07-02-2022
	 - Description      :Get all the admins information entered by adminId from  the database.
	  ****************************************************************************************************************************/ 
	@Override
	public List<Admin> getAllAdmins() {
		List<AdminEntity> admin = adminRepository.findAll();
		return EntityModelUtil.adminEntityToModelList(admin);
	}
	
	/****************************************************************************************************************************
	 - Method Name      : getAdminById
	 - Input Parameters : int adminId
	 - Return type      : AdminDetails
	 - Author           : Deva Pavan Kumar
	 - Creation Date    : 07-02-2022
	 - Description      :Get the admin information from  the database by entering adminId.
	  ****************************************************************************************************************************/ 
	@Override
	public AdminDetails getAdminById(int adminId) throws AdminNotFoundException {

		AdminDetails admindetails = new AdminDetails();

		Optional<AdminEntity> optionalPost = adminRepository.findById(adminId);

		if (optionalPost.isEmpty()) {
			throw new AdminNotFoundException("Sorry! Admin is not existing with id: " + adminId);
		}
		AdminEntity admin = optionalPost.get();
		admindetails.setAdmin(entityModelUtil.adminEntityToModel(admin));
		Donor donor = donarService.getDonorDetails(admin.getDonorId());
		Category category = categoryService.getCategoryDetails(admin.getCategoryId());
		Post post = postService.getPostDetails(admin.getPostId());
		User user = userService.getUserDetails(admin.getUserId());

		admindetails.setDonor(donor);
		admindetails.setCategory(category);
		admindetails.setPost(post);
		admindetails.setUser(user);

		return admindetails;

	}
	
	/****************************************************************************************************************************
	 - Method Name      : deleteAdmin
	 - Input Parameters : int adminId
	 - Return type      : void
	 - Author           : Deva Pavan Kumar
	 - Creation Date    : 07-02-2022
	 - Description      :Delete the admins information entered by admin from  the database.
	  ****************************************************************************************************************************/
	

	@Override
	public void deleteAdmin(int adminId) {

		Optional<AdminEntity> optionalPost = adminRepository.findById(adminId);

		if (optionalPost.isPresent()) {
			adminRepository.deleteById(adminId);
		} else {
			throw new AdminNotFoundException("Sorry! Admin is not existing with id: " + adminId);
		}
	}
	
	/****************************************************************************************************************************
	 - Method Name      : updateAdmin
	 - Input Parameters : Admin
	 - Return type      : Admin
	 - Author           : Deva Pavan Kumar
	 - Creation Date    : 07-02-2022
	 - Description      :Update the admin information entered by admin from  the database.
	  ****************************************************************************************************************************/
	
	

	@Override
	public Admin updateAdmin(Admin admin) {
		Optional<AdminEntity> optionalUserEntity = adminRepository.findById(admin.getAdminId());

		if (optionalUserEntity.isEmpty()) {
			throw new AdminNotFoundException("Sorry! Admin is not existing with id: " + admin.getAdminId());
		}

		AdminEntity updatedAdminEntity = adminRepository.save(entityModelUtil.adminModelToEntity(admin));
		System.out.println("Updated the admin details");

		return entityModelUtil.adminEntityToModel(updatedAdminEntity);
	}
	
	/****************************************************************************************************************************
	 - Method Name      : getUserDetails
	 - Input Parameters : int userId
	 - Return type      : User
	 - Author           : Deva Pavan Kumar
	 - Creation Date    : 07-02-2022
	 - Description      :Get the user information entered by admin from  the database.
	  ****************************************************************************************************************************/
	

	public User getUserDetails(int userId) {

		User user = userService.getUserDetails(userId);
		return user;

	}

	/****************************************************************************************************************************
	 - Method Name      : getDonorDetails
	 - Input Parameters : int donorId
	 - Return type      : Door
	 - Author           : Deva Pavan Kumar
	 - Creation Date    : 07-02-2022
	 - Description      :Get the donor information entered by admin from  the database.
	  ****************************************************************************************************************************/
	
	public Donor getDonorDetails(int donorId) {

		Donor donor = donarService.getDonorDetails(donorId);
		return donor;
	}
	
	/****************************************************************************************************************************
	 - Method Name      : getPostDetails
	 - Input Parameters : int postId
	 - Return type      : Post
	 - Author           : Deva Pavan Kumar
	 - Creation Date    : 07-02-2022
	 - Description      :Get the Post information entered by admin from  the database.
	  ****************************************************************************************************************************/

	public Post getPostDetails(int postId) {

		Post post = postService.getPostDetails(postId);
		return post;
	}
	
	/****************************************************************************************************************************
	 - Method Name      : getCategotyDetails
	 - Input Parameters : int categoryId
	 - Return type      : Category
	 - Author           : Deva Pavan Kumar
	 - Creation Date    : 07-02-2022
	 - Description      :Get the category information entered by admin from  the database.
	  ****************************************************************************************************************************/

	@Override
	public Category getCategotyDetails(int categoryId) {
		Category category = categoryService.getCategoryDetails(categoryId);
		return category;
	}
	
	/****************************************************************************************************************************
	 - Method Name      : getAdminDetails
	 - Input Parameters : int adminId
	 - Return type      : Admin
	 - Author           : Deva Pavan Kumar
	 - Creation Date    : 07-02-2022
	 - Description      :Get the admin information from  the database.
	  ****************************************************************************************************************************/

	@Override
	public Admin getAdminDetails(int adminId) {
		Optional<AdminEntity> optionalAdminEntity = adminRepository.findById(adminId);
		if(optionalAdminEntity.isEmpty()) {
			throw new PostNotFoundException("Sorry Admin is not found with id :" +adminId);
		}
		return entityModelUtil.adminEntityToModel(optionalAdminEntity.get());
	}

}