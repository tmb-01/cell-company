package uz.pdp.cellcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cellcompany.entity.Branch;
import uz.pdp.cellcompany.payload.AddEmployeeDto;
import uz.pdp.cellcompany.payload.ApiResponse;
import uz.pdp.cellcompany.payload.DeleteEmployee;
import uz.pdp.cellcompany.service.BranchService;

@RestController
@RequestMapping("api/v1/branch")
public class BranchController {

    @Autowired
    BranchService branchService;

    @GetMapping
    public ApiResponse getBranches(Branch branch) {
        return branchService.add(branch);
    }

    @PutMapping
    public ApiResponse addEmployee(@RequestBody AddEmployeeDto addEmployeeDto) {
        return branchService.addEmployee(addEmployeeDto);
    }

    @DeleteMapping
    public ApiResponse deleteEmployee(@RequestBody DeleteEmployee deleteEmployee) {
        return branchService.deleteUser(deleteEmployee);
    }

}
