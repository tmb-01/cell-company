package uz.pdp.cellcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cellcompany.payload.Dashboard;
import uz.pdp.cellcompany.service.DashboardService;

@RestController
@RequestMapping("api/v1/dashboard")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    public Dashboard getStat(){
        return dashboardService.getStat();
    }

}
