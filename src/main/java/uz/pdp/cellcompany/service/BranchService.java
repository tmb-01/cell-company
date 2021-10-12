package uz.pdp.cellcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.cellcompany.entity.Branch;
import uz.pdp.cellcompany.entity.Role;
import uz.pdp.cellcompany.entity.Turnstile;
import uz.pdp.cellcompany.entity.User;
import uz.pdp.cellcompany.payload.*;
import uz.pdp.cellcompany.repository.BranchRepository;
import uz.pdp.cellcompany.repository.RoleRepository;
import uz.pdp.cellcompany.repository.TurnstileRepository;
import uz.pdp.cellcompany.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BranchService {
    @Autowired
    BranchRepository branchRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TurnstileRepository turnstileRepository;

    public List<BranchStatDto> getAll() {
        return branchRepository.getSoldSimCardStat();
    }

    public ApiResponse add(Branch branch) {

        Role role = roleRepository.findById(1L).get(); // fillialal menejerining roli
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal.getRoles().contains(role)) {
            branchRepository.save(branch);
            return new ApiResponse("branch added", true);
        }
        return new ApiResponse(" you can't add branch", false);
    }

    public ApiResponse addEmployee(AddEmployeeDto addEmployeeDto) {
        UUID userId = addEmployeeDto.getUserId();
        Long branchId = addEmployeeDto.getBranchId();
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Role branchesManager = roleRepository.findById(1L).get(); // fillialal menejeri
        Role branchDirector = roleRepository.findById(5L).get();// fillial rahbari

        Optional<Branch> optionalBranch = branchRepository.findById(branchId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (optionalBranch.isPresent()) {
                Branch branch = optionalBranch.get();
                if (principal.getRoles().contains(branchesManager) || (principal.getRoles().contains(branchDirector) && branch.getManager().equals(principal))) {

                    List<User> branchUsers = branch.getUser();
                    branchUsers.add(user);
                    branch.setUser(branchUsers);
                    branchRepository.save(branch);

                    Turnstile turnstile = new Turnstile();
                    turnstile.setUser(user);
                    Turnstile save = turnstileRepository.save(turnstile);

                    return new ApiResponse("employee added and this is id of turnstile:" + save.getId(), true);
                }
                return new ApiResponse("you can't add employee", false);
            }
            return new ApiResponse("branch is not found", false);
        }
        return new ApiResponse("user is not found", false);
    }

    public ApiResponse deleteUser(DeleteEmployee deleteEmployee) {
        UUID userId = deleteEmployee.getUserId();
        Long branchId = deleteEmployee.getBranchId();
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Role branchesManager = roleRepository.findById(1L).get(); // fillialal menejeri
        Role branchDirector = roleRepository.findById(5L).get();// fillial rahbari

        Optional<Branch> optionalBranch = branchRepository.findById(branchId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (optionalBranch.isPresent()) {
                Branch branch = optionalBranch.get();
                if (principal.getRoles().contains(branchesManager) || (principal.getRoles().contains(branchDirector) && branch.getManager().equals(principal))) {

                    List<User> branchUsers = branch.getUser();
                    branchUsers.remove(user);
                    branch.setUser(branchUsers);
                    branchRepository.save(branch);

                    turnstileRepository.deleteById(user.getTurnstile().getId());

                    return new ApiResponse("employee deleted", true);
                }
                return new ApiResponse("you can't delete employee", false);
            }
            return new ApiResponse("branch is not found", false);
        }
        return new ApiResponse("user is not found", false);
    }
}
