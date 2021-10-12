package uz.pdp.cellcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cellcompany.payload.BranchStatDto;
import uz.pdp.cellcompany.payload.Dashboard;
import uz.pdp.cellcompany.repository.PocketRepository;

import java.util.List;

@Service
public class DashboardService {


    @Autowired
    BranchService branchService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    TariffService tariffService;

    @Autowired
    PocketRepository pocketRepository;

    public Dashboard getStat() {
        List<BranchStatDto> soldSimCardByBranch = branchService.getAll();

        Dashboard dashboard = new Dashboard();
        dashboard.setSoldSimCards(soldSimCardByBranch);
        dashboard.setDailyStat(paymentService.getDailyStat());
        dashboard.setMonthlyStat(paymentService.getMonthlyStat());
        dashboard.setYearlyStat(paymentService.getYearlyStat());
        dashboard.setTariffStat(tariffService.getAll());
        dashboard.setPocketStat(pocketRepository.findAll());

        return new Dashboard();
    }

}
