package uz.pdp.cellcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.cellcompany.entity.*;
import uz.pdp.cellcompany.payload.ApiResponse;
import uz.pdp.cellcompany.payload.BuyTariffDto;
import uz.pdp.cellcompany.repository.RoleRepository;
import uz.pdp.cellcompany.repository.SimCardRepository;
import uz.pdp.cellcompany.repository.SimCardTariffRepository;
import uz.pdp.cellcompany.repository.TariffRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TariffService {

    @Autowired
    TariffRepository tariffRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    SimCardRepository simCardRepository;

    @Autowired
    SimCardTariffRepository simCardTariffRepository;

    @Autowired
    DetailService detailService;

    public ApiResponse addTariff(Tariff tariff) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Role tariffManager = roleRepository.findById(8L).get();

        if (principal.getRoles().contains(tariffManager)) {
            tariffRepository.save(tariff);
            return new ApiResponse("tariff added", true);
        }
        return new ApiResponse("you can't add tariff", true);
    }

    public ApiResponse updateTariff(Long id, Tariff tariff) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Role tariffManager = roleRepository.findById(8L).get();

        if (principal.getRoles().contains(tariffManager)) {
            Optional<Tariff> optionalTariff = tariffRepository.findById(id);
            if (optionalTariff.isPresent()) {
                tariff.setId(id);
                tariffRepository.save(tariff);
                return new ApiResponse("tariff updated", true);
            }
            return new ApiResponse("we couldn't find tariff", false);
        }
        return new ApiResponse("you can't update tariff", true);
    }

    public List<Tariff> getAll() {
        return tariffRepository.findAll();
    }

    public ApiResponse buyTariff(BuyTariffDto buyTariffDto) {
        Optional<SimCard> optionalSimCard = simCardRepository.findByCompanyCodeAndPhoneNumber(buyTariffDto.getCompanyCode(), buyTariffDto.getPhoneNumber());

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (optionalSimCard.isPresent()) {
            SimCard simCard = optionalSimCard.get();
            if (principal.getSimCard().contains(simCard)) {
                Optional<Tariff> optionalTariff = tariffRepository.findById(buyTariffDto.getTariffId());
                if (optionalTariff.isPresent()) {
                    Tariff tariff = optionalTariff.get();
                    SimCardTariff simCardTariff = new SimCardTariff();
                    simCardTariff.setTariff(tariff);
                    simCardTariff.setInnerMinute(tariff.getInnerMinute());
                    simCardTariff.setOuterMinute(tariff.getOuterMinute());
                    simCardTariff.setNumberOfSms(tariff.getNumberOfSms());
                    simCardTariff.setMb(tariff.getMb());
                    simCardTariff = simCardTariffRepository.save(simCardTariff);
                    simCard.setScTariff(simCardTariff);

                    Detail detail = new Detail();
                    detail.setBoughtTariff(tariff);
                    detailService.addDetail(detail);

                    return new ApiResponse("you bought tariff", true);
                }
                return new ApiResponse("we didn't find tariff", false);
            }
            return new ApiResponse("you don't own this sim card", false);
        }
        return new ApiResponse("sim card is not found", false);
    }

}
