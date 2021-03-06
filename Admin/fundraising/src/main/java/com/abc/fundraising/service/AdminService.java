package com.abc.fundraising.service;

import java.util.List;


import com.abc.fundraising.model.Admin;
import com.abc.fundraising.model.AdminDetails;
import com.abc.fundraising.model.Category;
import com.abc.fundraising.model.Donor;
import com.abc.fundraising.model.Post;
import com.abc.fundraising.model.User;

public interface AdminService {
		
	public AdminDetails getAdminById(int adminId);
			
	public void deleteAdmin(int adminId);

	public Admin saveAdmin(Admin admin);
	
	public Admin updateAdmin(Admin admin);

	public List<Admin> getAllAdmins();
	
    public User getUserDetails(int userId);
	
	public Donor getDonorDetails(int donorId);
	
	public Post getPostDetails(int postId);
	
	public Category getCategotyDetails(int categoryId);
	
	public Admin getAdminDetails(int adminId);

}