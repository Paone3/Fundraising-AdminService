package com.abc.fundraising.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.Conversions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.fundraising.entity.AdminEntity;
import com.abc.fundraising.exception.AdminNotFoundException;
import com.abc.fundraising.model.Admin;
import com.abc.fundraising.model.AdminDetails;
import com.abc.fundraising.repository.AdminRepository;

@SpringBootTest
public class AdminServiceTest
{
	@InjectMocks
	private AdminService adminService = new AdminServiceImpl();
	
	@Mock
	private AdminRepository adminRepository;
	
	
	@Test
	public void testGetAdminByIdNotFound() {
		
	    int adminId = 200;	    
	        
	    when(adminRepository.findById(adminId)).thenThrow(AdminNotFoundException.class);
		    
	    assertThrows(AdminNotFoundException.class,()->adminService.getAdminById(adminId));
	}
	

	
	
	@Test
    void testDeleteAdmin() {

		int adminId = 1;
		AdminEntity admin = new AdminEntity();
		
		admin.setAdminId(1);
		admin.setAdminName("Pavan");
		admin.setCategoryId(123);
		admin.setDonorId(167);
		admin.setPostId(7865);
		admin.setUserId(745);

		Optional<AdminEntity> optional = Optional.of(admin);
		when(adminRepository.findById(1)).thenReturn(optional);
		adminService.deleteAdmin(admin.getAdminId());
		verify(adminRepository, times(1)).deleteById(adminId);
	}
	
	@Test
	void testGetAdminDetails() {

		AdminEntity admin = new AdminEntity();
		admin.setAdminId(5);
		admin.setAdminName("Rohit");
		admin.setCategoryId(654);
		admin.setDonorId(737);
		admin.setPostId(929);
		admin.setUserId(77);

		Optional<AdminEntity> optionalAdmin = Optional.of(admin);
		int adminId = 1;

		when(adminRepository.findById(1)).thenReturn(optionalAdmin);

		Admin existingAdmin = adminService.getAdminDetails(adminId);

		assertEquals(admin.getAdminId(), existingAdmin.getAdminId());
		assertEquals(admin.getAdminName(),existingAdmin.getAdminName());
		assertEquals(admin.getCategoryId(), existingAdmin.getCategoryId());
		assertEquals(admin.getDonorId(), existingAdmin.getDonorId());
		assertEquals(admin.getPostId(), existingAdmin.getPostId());
		assertEquals(admin.getUserId(),existingAdmin.getUserId());

	}
	

}