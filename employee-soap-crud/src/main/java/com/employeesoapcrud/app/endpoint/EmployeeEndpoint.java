package com.employeesoapcrud.app.endpoint;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.employeesoapcrud.app.entity.Employee;
import com.employeesoapcrud.app.interfaces.AddEmployeeRequest;
import com.employeesoapcrud.app.interfaces.AddEmployeeResponse;
import com.employeesoapcrud.app.interfaces.DeleteEmployeeRequest;
import com.employeesoapcrud.app.interfaces.DeleteEmployeeResponse;
import com.employeesoapcrud.app.interfaces.EmployeeInfo;
import com.employeesoapcrud.app.interfaces.GetAllEmployeeRequest;
import com.employeesoapcrud.app.interfaces.GetAllEmployeeResponse;
import com.employeesoapcrud.app.interfaces.GetEmployeeByIdRequest;
import com.employeesoapcrud.app.interfaces.GetEmployeeResponse;
import com.employeesoapcrud.app.interfaces.ServiceStatus;
import com.employeesoapcrud.app.interfaces.UpdateEmployeeRequest;
import com.employeesoapcrud.app.interfaces.UpdateEmployeeResponse;
import com.employeesoapcrud.app.service.EmployeeService;

@Endpoint
public class EmployeeEndpoint {

	private static final String NAMESPACE_URI = "http://interfaces.app.employeesoapcrud.com";

	@Autowired
	private EmployeeService employeeService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEmployeeRequest")
	@ResponsePayload
	public AddEmployeeResponse addEmployee(@RequestPayload AddEmployeeRequest request) {
		AddEmployeeResponse response = new AddEmployeeResponse();
		ServiceStatus serviceStatus = new ServiceStatus();

		Employee employee = new Employee();
		BeanUtils.copyProperties(request.getEmployeeInfo(), employee);
		employeeService.AddEmployee(employee);
		serviceStatus.setStatus("SUCCESS");
 	    serviceStatus.setMessage("Employee Details Added Successfully");
		response.setServiceStatus(serviceStatus);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeByIdRequest")
	@ResponsePayload
	public GetEmployeeResponse getEmployee(@RequestPayload GetEmployeeByIdRequest request) {
		GetEmployeeResponse response = new GetEmployeeResponse();
		EmployeeInfo employeeInfo = new EmployeeInfo();
		BeanUtils.copyProperties(employeeService.getEmployeeById(request.getEmployeeId()), employeeInfo);
		response.setEmployeeInfo(employeeInfo);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEmployeeRequest")
	@ResponsePayload
	public UpdateEmployeeResponse updateEmployee(@RequestPayload UpdateEmployeeRequest request) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(request.getEmployeeInfo(), employee);
		employeeService.updateEmployee(employee);
		ServiceStatus serviceStatus = new ServiceStatus();
		serviceStatus.setStatus("SUCCESS");
		serviceStatus.setMessage("Employee Details Updated Successfully");
		UpdateEmployeeResponse response = new UpdateEmployeeResponse();
		response.setServiceStatus(serviceStatus);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEmployeeRequest")
	@ResponsePayload
	public DeleteEmployeeResponse deleteEmployee(@RequestPayload DeleteEmployeeRequest request) {
		employeeService.deleteEmployee(request.getEmployeeId());
		ServiceStatus serviceStatus = new ServiceStatus();
		serviceStatus.setStatus("SUCCESS");
		serviceStatus.setMessage("Employee Details Deleted Successfully");
		DeleteEmployeeResponse response = new DeleteEmployeeResponse();
		response.setServiceStatus(serviceStatus);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllEmployeeRequest")
	@ResponsePayload
	public GetAllEmployeeResponse getAllEmployees(@RequestPayload GetAllEmployeeRequest request) {
		GetAllEmployeeResponse response = new GetAllEmployeeResponse();
		List<EmployeeInfo> employeeInfoList = new ArrayList<>();
		List<Employee> employeeList=employeeService.getAllEmployees();
		
		for (Employee entity : employeeList) {
			EmployeeInfo employeeInfo = new EmployeeInfo();
			BeanUtils.copyProperties(entity, employeeInfo);
			employeeInfoList.add(employeeInfo);
		}
		response.getEmployeeInfo().addAll(employeeInfoList);
		return response;
	}
}

