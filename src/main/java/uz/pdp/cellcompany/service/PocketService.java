package uz.pdp.cellcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.cellcompany.entity.*;
import uz.pdp.cellcompany.payload.ApiResponse;
import uz.pdp.cellcompany.payload.BuyPocket;
import uz.pdp.cellcompany.repository.PocketRepository;
import uz.pdp.cellcompany.repository.RoleRepository;
import uz.pdp.cellcompany.repository.SimCardPocketRepository;
import uz.pdp.cellcompany.repository.SimCardRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PocketService {

    @Autowired
    PocketRepository pocketRepository;

    @Autowired
    SimCardPocketRepository simCardPocketRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    SimCardRepository simCardRepository;

    @Autowired
    DetailService detailService;


    public ApiResponse addPocket(Pocket pocket) {

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Role pocketManager = roleRepository.findById(9L).get();

        if (principal.getRoles().contains(pocketManager)) {
            pocketRepository.save(pocket);
            return new ApiResponse("pocket saved", true);
        }
        return new ApiResponse("you can't add pocket", false);
    }

    public ApiResponse buyPocket(BuyPocket buyPocket) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long pocketId = buyPocket.getPocketId();
        String phoneNumber = buyPocket.getPhoneNumber();
        String companyCode = buyPocket.getCompanyCode();

        Optional<Pocket> optionalPocket = pocketRepository.findById(pocketId);

        Optional<SimCard> optionalSimCard = simCardRepository.findByCompanyCodeAndPhoneNumber(companyCode, phoneNumber);

        if (optionalPocket.isPresent()) {
            Pocket pocket = optionalPocket.get();
            if (optionalSimCard.isPresent()) {
                SimCard simCard = optionalSimCard.get();
                if (simCard.getUser().getId().equals(principal.getId())) {
                    SimCardPocket simCardPocket = simCardPocketRepository.findBySimCardId(simCard.getId());
                    simCardPocket.setPocket(pocket);

                    int internet = pocket.getInternet();
                    int minute = pocket.getMinute();
                    int sms = pocket.getSms();

                    boolean isAddable = pocket.isAddableToRemained();
                    int oldInternet = simCardPocket.getInternet();
                    int oldMinute = simCardPocket.getMinute();
                    int oldSms = simCardPocket.getSms();
                    if (isAddable) {
                        simCardPocket.setInternet(internet != 0 ? oldInternet + internet : oldInternet);
                        simCardPocket.setMinute(minute != 0 ? oldMinute + minute : oldMinute);
                        simCardPocket.setSms(sms != 0 ? oldSms + sms : oldSms);
                    } else {
                        simCardPocket.setInternet(internet != 0 ? internet : oldInternet);
                        simCardPocket.setMinute(minute != 0 ? minute : oldMinute);
                        simCardPocket.setSms(sms != 0 ? sms : oldSms);
                    }

                    simCardPocket.setLastRefreshedDate(Timestamp.valueOf(LocalDateTime.now()));
                    simCardPocketRepository.save(simCardPocket);

                    Detail detail = new Detail();
                    detail.setBoughtPocket(pocket);
                    detailService.addDetail(detail);

                    return new ApiResponse("pocket added", true);
                }
                return new ApiResponse("it is not your phone number", false);
            }
            return new ApiResponse("SimCard is not found", false);
        }
        return new ApiResponse("Pocket is not found", false);
    }

    public ApiResponse deletePocket(Long id) {
        Optional<Pocket> optionalPocket = pocketRepository.findById(id);
        if (optionalPocket.isPresent()) {
            pocketRepository.deleteById(id);
            return new ApiResponse("pocket deleted", true);
        }
        return new ApiResponse("pocket not found", false);
    }

    public ApiResponse updatePocket(Long id,Pocket pocket) {
        Optional<Pocket> optionalPocket = pocketRepository.findById(id);
        if (optionalPocket.isPresent()) {
            pocket.setId(id);
            pocketRepository.save(pocket);
            return new ApiResponse("pocket updated",true);
        }
        return new ApiResponse("pocket not found", false);
    }
}
