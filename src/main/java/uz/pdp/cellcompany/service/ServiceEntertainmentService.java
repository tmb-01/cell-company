package uz.pdp.cellcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.cellcompany.entity.*;
import uz.pdp.cellcompany.payload.ApiResponse;
import uz.pdp.cellcompany.payload.BuyServiceDto;
import uz.pdp.cellcompany.repository.RoleRepository;
import uz.pdp.cellcompany.repository.ServiceEntertainmentRepository;
import uz.pdp.cellcompany.repository.SimCardRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceEntertainmentService {

    @Autowired
    ServiceEntertainmentRepository serviceEntertainmentRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    SimCardRepository simCardRepository;

    @Autowired
    DetailService detailService;

    public List<ServiceEntertainment> getStat() {
        return serviceEntertainmentRepository.findAllByMostUsed();
    }

    public ApiResponse add(ServiceEntertainment serviceEntertainment) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Role tariffManager = roleRepository.findById(8L).get();

        if (principal.getRoles().contains(tariffManager)) {
            serviceEntertainmentRepository.save(serviceEntertainment);
            return new ApiResponse("service saved", true);
        }
        return new ApiResponse("You can't add service", false);
    }

    public ApiResponse buyService(BuyServiceDto buyServiceDto) {

        Optional<SimCard> optionalSimCard = simCardRepository.findByCompanyCodeAndPhoneNumber(buyServiceDto.getCompanyCode(), buyServiceDto.getPhoneNumber());

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (optionalSimCard.isPresent()) {
            SimCard simCard = optionalSimCard.get();
            if (principal.getSimCard().contains(simCard)) {

                Optional<ServiceEntertainment> optionalServiceEntertainment = serviceEntertainmentRepository.findById(buyServiceDto.getServiceId());
                if (optionalServiceEntertainment.isPresent()) {
                    ServiceEntertainment serviceEntertainment = optionalServiceEntertainment.get();
                    List<ServiceEntertainment> serviceEntertainmentList = simCard.getServiceEntertainmentList();
                    serviceEntertainmentList.add(serviceEntertainment);
                    simCard.setServiceEntertainmentList(serviceEntertainmentList);
                    simCardRepository.save(simCard);

                    Detail detail = new Detail();
                    detail.setBoughtServiceEntertainment(serviceEntertainment);
                    detailService.addDetail(detail);
                    return new ApiResponse("you bought service", true);
                }
                return new ApiResponse("service not found", false);
            }
            return new ApiResponse("you don't own this sim card", false);
        }
        return new ApiResponse("we didn't find this sim card", false);
    }

    public ApiResponse update(Long id, ServiceEntertainment serviceEntertainment) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Role tariffManager = roleRepository.findById(8L).get();

        if (principal.getRoles().contains(tariffManager)) {
            Optional<ServiceEntertainment> optionalServiceEntertainment = serviceEntertainmentRepository.findById(id);
            if (optionalServiceEntertainment.isPresent()) {
                serviceEntertainmentRepository.save(serviceEntertainment);
                return new ApiResponse("service saved", true);
            }
            return new ApiResponse("we couldn't find this service", false);
        }
        return new ApiResponse("You can't add service", false);
    }

    public ApiResponse delete(Long id) {

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Role tariffManager = roleRepository.findById(8L).get();

        if (principal.getRoles().contains(tariffManager)) {
            serviceEntertainmentRepository.deleteById(id);
            return new ApiResponse("service deleted", true);
        }
        return new ApiResponse("you can't delete service", false);
    }
}
