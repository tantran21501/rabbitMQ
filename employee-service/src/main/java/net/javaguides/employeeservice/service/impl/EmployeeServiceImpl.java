package net.javaguides.employeeservice.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import net.javaguides.employeeservice.dto.APIResponseDto;
import net.javaguides.employeeservice.dto.DepartmentDto;
import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.dto.OrganizationDto;
import net.javaguides.employeeservice.entity.Employee;
import net.javaguides.employeeservice.mapper.EmployeeMapper;
import net.javaguides.employeeservice.repository.EmployeeRepository;
import net.javaguides.employeeservice.service.APIClient;
import net.javaguides.employeeservice.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private EmployeeRepository employeeRepository;

//    private RestTemplate restTemplate;
    private WebClient webClient;
    private APIClient apiClient;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee  = EmployeeMapper.getEmployee(employeeDto);
        Employee  saveEmployee = employeeRepository.save(employee);
        EmployeeDto saveEmployeeDto = EmployeeMapper.getEmployeeDto(saveEmployee);
        return saveEmployeeDto;
    }
//    @CircuitBreaker(name="${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Retry(name="${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
        LOGGER.info("inside getEmployeeById() method ");
        Employee employee = employeeRepository.findById(employeeId).get();
//        ResponseEntity<DepartmentDto> responseEntity =
//        restTemplate.getForEntity("http://localhost:8080/api/departments/"+ employee.getDepartmentCode(), DepartmentDto.class);
//        DepartmentDto departmentDto = responseEntity.getBody();
        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/departments/"+ employee.getDepartmentCode())
                .retrieve().bodyToMono(DepartmentDto.class)
                .block();
        OrganizationDto organizationDto = webClient.get()
                .uri("http://localhost:8083/api/organizations/"+ employee.getOrganizationCode())
                .retrieve().bodyToMono(OrganizationDto.class)
                .block();

//        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());
        EmployeeDto saveEmployeeDto = EmployeeMapper.getEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(saveEmployeeDto);
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setOrganization(organizationDto);
        return apiResponseDto;
    }
    public APIResponseDto getDefaultDepartment(Long employeeId, Exception e) {
        LOGGER.info("inside getDefaultDepartment() method ");

        Employee employee = employeeRepository.findById(employeeId).get();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentDescription("Research and Development Department");
        departmentDto.setDepartmentCode("R&D001");


        EmployeeDto saveEmployeeDto = EmployeeMapper.getEmployeeDto(employee);


        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(saveEmployeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }
}
