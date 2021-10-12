package uz.pdp.cellcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cellcompany.entity.Detail;
import uz.pdp.cellcompany.repository.DetailRepository;

@Service
public class DetailService {

    @Autowired
    DetailRepository detailRepository;

    public void addDetail(Detail detail){
        detailRepository.save(detail);
    }

}
