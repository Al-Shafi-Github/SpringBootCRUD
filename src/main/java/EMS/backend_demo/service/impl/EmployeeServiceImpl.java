package EMS.backend_demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import EMS.backend_demo.dto.EmployeeDto;
import EMS.backend_demo.entity.Employee;
import EMS.backend_demo.exception.ResourceNotFoundException;
import EMS.backend_demo.mapper.EmployeeMapper;
import EMS.backend_demo.repository.EmployeeRepository;
import EMS.backend_demo.service.EmployeeService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee= employeeRepository.findById(employeeId)
                .orElseThrow(() -> 
                        new ResourceNotFoundException("Employee does not exist with given ID:" + employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
           List<Employee> employees = employeeRepository.findAll();
           return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
    Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException(("Employee is not Exists with the given id" + employeeId)));
    
    employee.setFirstName(updatedEmployee.getFirstName());
    employee.setLastName(updatedEmployee.getLastName());
    employee.setEmail(updatedEmployee.getEmail());
    Employee updateEmployeeObj =employeeRepository.save(employee);

    return EmployeeMapper.mapToEmployeeDto(updateEmployeeObj);
}

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException(("Employee is not Exists with the given id" + employeeId)));
         employeeRepository.deleteById(employeeId);
      
    }

}
