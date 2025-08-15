package energy.forecaster.repository;

import energy.forecaster.entity.EnergyConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnergyConsumptionRepository extends JpaRepository<EnergyConsumption, Long> {
    List<EnergyConsumption> findByUserId(Long userId);
    Optional<EnergyConsumption> findByIdAndUserId(Long id, Long userId);
}
