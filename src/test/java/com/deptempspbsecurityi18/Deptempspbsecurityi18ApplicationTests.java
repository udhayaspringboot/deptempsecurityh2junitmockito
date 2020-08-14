
package com.deptempspbsecurityi18;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
import com.deptempspbsecurityi18.dao.DeptRepo;
import com.deptempspbsecurityi18.dao.EmpRepo;
import com.deptempspbsecurityi18.model.Department;
import com.deptempspbsecurityi18.model.Employee;
import com.deptempspbsecurityi18.service.DeptEmpService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Deptempspbsecurityi18ApplicationTests {
	
	
	@Autowired
	private DeptEmpDao deptDao;
	
	@MockBean
	DeptRepo deptRepo;
	
	@MockBean 
	EmpRepo empRepo;
	
	@Test
	public void saveDeptTestDao()
	{
		Department dep2 = new Department(3, "support", "chhennai");
		
		
		
		when(deptRepo.save(dep2)).thenReturn(dep2);
		assertEquals(true,deptDao.createDept(dep2));
		
	}
	
	@Test
	public void getAllDeptTest()
	{
		when(deptRepo.findAll()).thenReturn(Stream.of(new Department(1,"training","pune"),
				new Department(2,"HR","palakkad")).collect(Collectors.toList()));
		Assert.assertEquals(2,deptDao.readAllDept().size());
	}
	
	@Test
	public void getEmpFromDeptTest()
	{
		int id = 1;
		Department dept =new Department();
		dept.setDeptId(id);
		when(empRepo.findByDepartment(id)).thenReturn(Stream.of(new Employee(1,"Udhay","29-05-1989",
				"udhaya2cse@gmail.com",9876542310L,98562.26f,"HCl",dept),new Employee(2,"Raj","19-09-1995",
						"raj@gmail.com",9986542310L,98562.26f,"Infosys",dept)).collect(Collectors.toList()));
		Assert.assertEquals(2,deptDao.readEmpFromDept(id).size());
	}
	@Test
	public void saveDeptTest()
	{
		Department dep2 = new Department(3, "support", "chhennai");
		
		when(deptRepo.save(dep2)).thenReturn(dep2);
		assertEquals(true, deptDao.createDept(dep2));
	}
	@Test
	public void deleteDeptTest()
	{
		
		Department dep = new Department(5,"sales","pune");
		
		//deptRepo.deleteByDeptId(dep.getDeptId()));
		deptDao.delteDept(dep.getDeptId());
		verify(deptRepo,times(1)).deleteByDeptId(dep.getDeptId());
		//verify(dempServ, times(1)).delteDept(dep.getDeptId());
	}
	@Test
	public void getAllEmpTest()
	{
		int deptId=1;
		Department dps = new Department();
		dps.setDeptId(deptId);
		when(empRepo.findByDepartment(deptId)).thenReturn(Stream.of(new 
				Employee(23, "manoj", "mano@gmail.com", "1993-02-26", 894315697, 
						46953.56f, "tcs", dps),new Employee(25, "rakesh", 
								"rake@gmail.com", "1991-03-26", 
								894315697, 32953.56f, "cts", dps)).collect(Collectors.toList()));
		Assert.assertEquals(2,deptDao.readEmpFromDept(deptId).size());
		

	}
	
	@Test
	public void updateDeptTest()
	{
		Department df =new Department(5,"support","chennai");
		
		df.setDeptLoc("Pune");
		when(deptRepo.save(df)).thenReturn(df);
		assertEquals(true, deptDao.updateDept(df));
		
	}
	
	@Test
	public void createEmpTest()
	{
		Department dep1 = new Department();
		dep1.setDeptId(2);
		Employee emp2 = new Employee(26, "kiraan", "ksh@gmail.com", "1996-02-26", 894315697, 26953.56f, "ibm", dep1);
		Employee emp4 = new Employee(29, "Kumar", "kum@gmail.com", "1990-08-26", 894315697, 36953.56f, "infomatica", dep1);

		when(empRepo.save(emp2)).thenReturn(emp2);
		when(empRepo.save(emp4)).thenReturn(emp4);
		assertEquals(true, deptDao.createEmp(emp4));
		assertEquals(true, deptDao.createEmp(emp2));
		
	}

	
	@Test
	public void updateEmpTest()
	{
		Department dep = new Department();
		dep.setDeptId(5);
		Employee emp2 = new Employee(26, "kiraan", "ksh@gmail.com", 
				"1996-02-26", 894315697, 26953.56f, "ibm", dep);
		
		
		emp2.setCompanyName("IBM");
		
		when(empRepo.save(emp2)).thenReturn(emp2);
		assertEquals(true,deptDao.updateEmp(emp2));
		
	}
	@Test
	public void DeleteEmpTest()
	{
		int deptId=2;
		int empId=1;
		
		deptDao.deleteEmpFromDept(deptId, empId);
		verify(empRepo,times(1)).deleteByEmpIdAndDepartment(empId, deptId);
	}
	
	/*
	 * @Autowired DeptEmpDao deptDao;
	 * 
	 * @Test public void createDept() { assertEquals(true,deptDao.createDept(new
	 * Department(112,"Training","Pune"))); }
	 */

	/*
	 * @Autowired DeptEmpDao empd;
	 * 
	 * Department dep = new Department(90, "training", "pune"); Department dep1 =
	 * new Department(91, "development", "palakkad"); Department dep2 = new
	 * Department(92, "support", "chhennai");
	 * 
	 * Department de1 = new Department(dep.getDeptId()); Department de2 = new
	 * Department(dep1.getDeptId()); Department de3 = new
	 * Department(dep2.getDeptId());
	 * 
	 * 
	 * Employee emp = new Employee(23, "manoj", "mano@gmail.com", "1993-02-26",
	 * 894315697, 46953.56f, "tcs", de1); Employee emp1 = new Employee(25, "rakesh",
	 * "rake@gmail.com", "1991-03-26", 894315697, 32953.56f, "cts", de2); Employee
	 * emp2 = new Employee(26, "kiraan", "ksh@gmail.com", "1996-02-26", 894315697,
	 * 26953.56f, "ibm", de1); Employee emp4 = new Employee(29, "Kumar",
	 * "kum@gmail.com", "1990-08-26", 894315697, 36953.56f, "infomatica", de3);
	 * 
	 * Connection con;
	 * 
	 * @Test
	 * 
	 * public void a_connectionCheck() {
	 * 
	 * try { Class.forName("com.mysql.cj.jdbc.Driver"); } catch
	 * (ClassNotFoundException e) {
	 * 
	 * System.err.println("Driver Error"); } try { con =
	 * DriverManager.getConnection("jdbc:mysql://localhost:3306/deptempspb", "root",
	 * "root"); System.out.println("Connection successfull");
	 * assertNotNull("successfull", con);
	 * 
	 * } catch (SQLException e) {
	 * 
	 * System.err.println("Connection invalid"); } }
	 * 
	 * @Test public void b_createDept() { // a_connectionCheck();
	 * 
	 * //assertEquals(true, empd.createDept(dep));
	 * 
	 * assertEquals(true, empd.createDept(dep2)); assertEquals(true,
	 * empd.createDept(dep1));
	 * 
	 * 
	 * try {
	 * 
	 * assertEquals(true, empd.createDept(dep));
	 * 
	 * System.out.println("val testing" + dep.getDeptName());
	 * System.out.println("Connection closed"); } catch (Exception e) {
	 * 
	 * System.err.println("Connection issue"); e.printStackTrace(); }
	 * 
	 * }
	 */ // create employee
	/*
	 * @Test public void c_createEmployee() {
	 * 
	 * 
	 * 
	 * a_connectionCheck();
	 * 
	 * assertNotNull("Creating employee", empd.createEmp(emp));
	 * assertNotNull("Creating employee", empd.createEmp(emp1));
	 * assertNotNull("Creating employee", empd.createEmp(emp2));
	 * assertNotNull("Creating employee", empd.createEmp(emp));
	 * assertNotNull("Creating employee", empd.createEmp(emp1));
	 * assertNotNull("Creating employee", empd.createEmp(emp2));
	 * 
	 * 
	 * } // Get All Department Values
	 * 
	 * @Test public void d_readDept() { a_connectionCheck();
	 * assertNotNull("Showing Department values", empd.readAllDept()); try {
	 * 
	 * // con.close();
	 * 
	 * System.out.println("Connection closed"); } catch (Exception e) {
	 * 
	 * System.err.println("Connection issue");
	 * 
	 * } }
	 */ // update employee in department

	/*
	 * @Test public void e_updateEmp() { a_connectionCheck();
	 * 
	 * assertNotNull("Employee Updated", empd.updateEmp(emp4));
	 * 
	 * try { System.out.println("Connection closed"); } catch (Exception e) {
	 * 
	 * System.err.println("Connection issue"); } } // update department
	 * 
	 * @Test public void f_updateDept() { a_connectionCheck();
	 * 
	 * assertNotNull("Department Updated", empd.updateDept(dep1)); }
	 * 
	 * // get employees from department
	 * 
	 * @Test
	 * 
	 * public void g_readEmp() {
	 * 
	 * a_connectionCheck();
	 * 
	 * assertNotNull("Showing Employee", empd.readEmpFromDept(dep2.getDeptId()));}
	 * 
	 * // delete employee in department
	 * 
	 * @Test
	 * 
	 * public void h_deleteEmp() {
	 * 
	 * a_connectionCheck();
	 * 
	 * assertNotNull("deleted Employee", empd.deleteEmpFromDept(dep.getDeptId(),
	 * emp.getEmpId())); try { // con.close();
	 * System.out.println("Connection closed"); } catch (Exception e) {
	 * 
	 * System.err.println("Connection issue"); } } // delete department
	 * 
	 * @Test public void i_deleteDept() { a_connectionCheck();
	 * assertNotNull("deleting Department", empd.delteDept(dep2.getDeptId()));
	 * 
	 * }
	 * 
	 * @Test public void j_readEmp() { a_connectionCheck();
	 * assertNotNull("reading employee value Department", empd.readEmpFromDept(1));
	 * 
	 * }
	 */}

// }
