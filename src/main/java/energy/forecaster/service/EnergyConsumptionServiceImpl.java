package energy.forecaster.service;

import energy.forecaster.dto.EnergyConsumptionDTO;
import energy.forecaster.entity.EnergyConsumption;
import energy.forecaster.entity.User;
import energy.forecaster.repository.EnergyConsumptionRepository;
import energy.forecaster.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnergyConsumptionServiceImpl implements EnergyConsumptionService {

    private final EnergyConsumptionRepository energyRepo;
    private final UserRepository userRepo;

    @Override
    public EnergyConsumption addConsumption(EnergyConsumptionDTO dto, Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        EnergyConsumption consumption = EnergyConsumption.builder()
                .user(user)
                .timestamp(dto.getTimestamp())
                .energyUsed(dto.getEnergyUsed())
                .temperature(dto.getTemperature())
                .humidity(dto.getHumidity())
                .build();

        return energyRepo.save(consumption);
    }

    @Override
    public List<EnergyConsumption> getUserConsumptions(Long userId) {
        return energyRepo.findByUserId(userId);
    }

    @Override
    public EnergyConsumption getConsumptionById(Long id, Long userId) {
        return energyRepo.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Consumption not found or not yours"));
    }

    @Override
    public EnergyConsumption updateConsumption(Long id, EnergyConsumptionDTO dto, Long userId) {
        EnergyConsumption existing = getConsumptionById(id, userId);

        existing.setTimestamp(dto.getTimestamp());
        existing.setEnergyUsed(dto.getEnergyUsed());
        existing.setTemperature(dto.getTemperature());
        existing.setHumidity(dto.getHumidity());

        return energyRepo.save(existing);
    }

    @Override
    public void deleteConsumption(Long id, Long userId) {
        EnergyConsumption existing = getConsumptionById(id, userId);
        energyRepo.delete(existing);
    }
}
