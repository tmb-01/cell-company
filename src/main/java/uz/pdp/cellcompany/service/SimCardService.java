package uz.pdp.cellcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.cellcompany.entity.*;
import uz.pdp.cellcompany.payload.ApiResponse;
import uz.pdp.cellcompany.payload.RegisterNumberDto;
import uz.pdp.cellcompany.repository.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SimCardService {

    @Autowired
    SimCardRepository simCardRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    SimCardPocketRepository simCardPocketRepository;

    @Autowired
    SimCardTariffRepository simCardTariffRepository;

    public ApiResponse addSimCard(SimCard simCard) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Role numberManager = roleRepository.findById(2L).get();

        if (principal.getRoles().contains(numberManager)) {
            simCardRepository.save(simCard);
            return new ApiResponse("number saved", true);
        }
        return new ApiResponse("you can't add number", false);
    }

    public List<SimCard> getAll() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Role numberManager = roleRepository.findById(2L).get();

        if (principal.getRoles().contains(numberManager)) {
            return simCardRepository.findAll();
        }
        return new ArrayList<>();
    }

    public ApiResponse registerNumber(RegisterNumberDto registerNumberDto) {

        Role branchDirector = roleRepository.findById(5L).get();
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal.getRoles().contains(branchDirector)) {

            Optional<User> optionalUser = userRepository.findById(registerNumberDto.getUserId());
            Optional<SimCard> optionalSimCard = simCardRepository.findById(registerNumberDto.getPhoneNumberId());

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (optionalSimCard.isPresent()) {
                    SimCard simCard = optionalSimCard.get();

                    Optional<Branch> optionalBranch = branchRepository.findById(registerNumberDto.getBranchId());

                    if (optionalBranch.isPresent()) {

                        SimCardPocket saveScPocket = simCardPocketRepository.save(new SimCardPocket());
                        SimCardTariff saveScTariff = simCardTariffRepository.save(new SimCardTariff());

                        Branch branch = optionalBranch.get();
                        simCard.setBoughtFrom(branch);
                        simCard.setBoughtAt(Timestamp.valueOf(LocalDateTime.now()));
                        simCard.setUser(user);
                        simCard.setScPocket(saveScPocket);
                        simCard.setScTariff(saveScTariff);
                        simCardRepository.save(simCard);
                        return new ApiResponse("phone number registered", true);
                    }
                    return new ApiResponse("branch is not found", false);
                }
                return new ApiResponse("sim card is not found", false);
            }
            return new ApiResponse("user is not found", false);
        }
        return new ApiResponse("you can't register number", false);
    }
}
