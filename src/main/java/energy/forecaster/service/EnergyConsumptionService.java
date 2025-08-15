package energy.forecaster.service;

import energy.forecaster.dto.EnergyConsumptionDTO;
import energy.forecaster.entity.EnergyConsumption;
import energy.forecaster.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface EnergyConsumptionService {

    EnergyConsumption addConsumption(EnergyConsumptionDTO dto, Long userId);
    List<EnergyConsumption> getUserConsumptions(Long userId);
    EnergyConsumption getConsumptionById(Long id, Long userId);
    EnergyConsumption updateConsumption( Long id, EnergyConsumptionDTO dto,Long userId);
    void deleteConsumption(Long id, Long userId);


}
