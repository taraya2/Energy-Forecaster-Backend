package energy.forecaster.repository;

import energy.forecaster.entity.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PredictionRepository extends JpaRepository<Prediction, Long> {
    List<Prediction> findByUserId(Long userId);
    Optional<Prediction> findByIdAndUserId(Long id, Long userId);
}
