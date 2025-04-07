package EMS.backend_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import EMS.backend_demo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
