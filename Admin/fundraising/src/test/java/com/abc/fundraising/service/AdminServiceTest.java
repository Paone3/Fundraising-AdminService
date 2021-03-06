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
import com.abc.fundraising.util.EntityModelUtil;

@SpringBootTest(classes=AdminServiceTest.class)
public class AdminServiceTest
{
	@InjectMocks
	private AdminService adminService = new AdminServiceImpl();
	
	@Mock
	private AdminRepository adminRepository;
	
	@Mock
	private EntityModelUtil entityModelUtil;
	
	
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
	public void testGetUserDetails() {

	AdminEntity admin = new AdminEntity();

	admin.setAdminId(123);
	admin.setAdminName("PavanKumar");
	admin.setUserId(55);
	admin.setPostId(46);
	admin.setCategoryId(2);
	admin.setDonorId(75);
	
	
	Admin admin1 = new Admin();
	
	admin1.setAdminId(123);
	admin1.setAdminName("PavanKumar");
	admin1.setUserId(55);
	admin1.setPostId(46);
	admin1.setCategoryId(2);
	admin1.setDonorId(75);

		when(entityModelUtil.adminModelToEntity(admin1)).thenReturn(admin);
		when(entityModelUtil.adminEntityToModel(admin)).thenReturn(admin1);

		Optional<AdminEntity> optionalAdmin = Optional.of(admin);
		int adminId = 1;

		when(entityModelUtil.adminModelToEntity(admin1)).thenReturn(admin);
		when(entityModelUtil.adminEntityToModel(admin)).thenReturn(admin1);

		when(adminRepository.findById(1)).thenReturn(optionalAdmin);

	Admin existingAdmin = adminService.getAdminDetails(adminId);

		assertEquals(admin.getAdminId(),existingAdmin.getAdminId());

	}	
	@Test
	public void testSaveAdmin() {



	AdminEntity admin = new AdminEntity();

	admin.setAdminId(123);
	admin.setAdminName("PavanKumar");
	admin.setUserId(55);
	admin.setPostId(46);
	admin.setCategoryId(2);
	admin.setDonorId(75);
	
	
	Admin admin1 = new Admin();
	
	admin1.setAdminId(123);
	admin1.setAdminName("PavanKumar");
	admin1.setUserId(55);
	admin1.setPostId(46);
	admin1.setCategoryId(2);
	admin1.setDonorId(75);



	when(entityModelUtil.adminModelToEntity(admin1)).thenReturn(admin);
	when(entityModelUtil.adminEntityToModel(admin)).thenReturn(admin1);
	when(adminRepository.save(admin)).thenReturn(admin);



	assertEquals(admin1.getAdminName(), admin.getAdminName());

	}
	

}