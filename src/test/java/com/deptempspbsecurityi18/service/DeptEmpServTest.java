package com.deptempspbsecurityi18.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.deptempspbsecurityi18.dao.DeptEmpDao;
import com.deptempspbsecurityi18.model.Department;
import com.deptempspbsecurityi18.model.Employee;


@RunWith(SpringRunner.class)
@SpringBootTest

public class DeptEmpServTest {
	
	@Autowired
	private DeptEmpService depEmpSer;
	
	@MockBean
	
	DeptEmpDao dempServ;
	
	@Test
	public void getAllDeptTest()
	{
		when(dempServ.readAllDept()).thenReturn(Stream.of(new Department(1,"training","pune"),
				new Department(2,"HR","palakkad")).collect(Collectors.toList()));
		Assert.assertEquals(2,depEmpSer.readAllDeptServ().size());
	}
	
	@Test
	public void getEmpFromDeptTest()
	{
		int id = 1;
		Department dept =new Department();
		dept.setDeptId(id);
		when(dempServ.readEmpFromDept(id)).thenReturn(Stream.of(new Employee(1,"Udhay","29-05-1989",
				"udhaya2cse@gmail.com",9876542310L,98562.26f,"HCl",dept),new Employee(2,"Raj","19-09-1995",
						"raj@gmail.com",9986542310L,98562.26f,"Infosys",dept)).collect(Collectors.toList()));
		Assert.assertEquals(2,depEmpSer.readEmpFromDeptServ(id).size());
	}
	@Test
	public void saveDeptTest()
	{
		Department dep2 = new Department(3, "support", "chhennai");
		
		when(dempServ.createDept(dep2)).thenReturn(true);
		assertEquals(true, depEmpSer.createDeptServ(dep2));
	}
	@Test
	public void deleteDeptTest()
	{
		
		Department dep = new Department(5,"sales","pune");
		
		when(dempServ.delteDept(dep.getDeptId())).thenReturn(true);
		assertEquals(true, depEmpSer.delteDeptServ(dep.getDeptId()));
		//verify(dempServ, times(1)).delteDept(dep.getDeptId());
	}
	@Test
	public void getAllEmpTest()
	{
		int deptId=1;
		Department dps = new Department();
		dps.setDeptId(deptId);
		when(dempServ.readEmpFromDept(deptId)).thenReturn(Stream.of(new 
				Employee(23, "manoj", "mano@gmail.com", "1993-02-26", 894315697, 
						46953.56f, "tcs", dps),new Employee(25, "rakesh", 
								"rake@gmail.com", "1991-03-26", 
								894315697, 32953.56f, "cts", dps)).collect(Collectors.toList()));
		Assert.assertEquals(2,depEmpSer.readEmpFromDeptServ(deptId).size());
		

	}
	
	@Test
	public void updateDeptTest()
	{
		Department df =new Department(5,"support","chennai");
		
		df.setDeptLoc("Pune");
		when(dempServ.updateDept(df)).thenReturn(true);
		assertEquals(true, depEmpSer.updateDeptServ(df));
		
	}
	
	@Test
	public void createEmpTest()
	{
		Department dep1 = new Department();
		dep1.setDeptId(2);
		Employee emp2 = new Employee(26, "kiraan", "ksh@gmail.com", "1996-02-26", 894315697, 26953.56f, "ibm", dep1);
		Employee emp4 = new Employee(29, "Kumar", "kum@gmail.com", "1990-08-26", 894315697, 36953.56f, "infomatica", dep1);

		when(dempServ.createEmp(emp2)).thenReturn(true);
		assertEquals(true, depEmpSer.createEmpServ(emp4));
		assertEquals(true, depEmpSer.createEmpServ(emp2));
		
	}

	
	@Test
	public void updateEmpTest()
	{
		Department dep = new Department();
		dep.setDeptId(5);
		Employee emp2 = new Employee(26, "kiraan", "ksh@gmail.com", 
				"1996-02-26", 894315697, 26953.56f, "ibm", dep);
		
		
		emp2.setCompanyName("IBM");
		
		when(dempServ.updateEmp(emp2)).thenReturn(true);
		assertEquals(true,depEmpSer.updateEmpServ(emp2));
		
	}
	@Test
	public void DeleteEmpTest()
	{
		int deptId=2;int empId=1;
		
		when(dempServ.deleteEmpFromDept(deptId, empId)).thenReturn(true);
		assertEquals(true,dempServ.deleteEmpFromDept(deptId, empId));
	}
}
