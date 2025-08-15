package energy.forecaster.service;

import energy.forecaster.dto.PredictionRequestDTO;
import energy.forecaster.dto.PredictionResponseDTO;
import energy.forecaster.entity.Prediction;

import java.util.List;

public interface PredictionService {
    PredictionResponseDTO makePrediction(PredictionRequestDTO dto, Long userId);
    List<Prediction> getUserPredictions(Long userId);
    Prediction getPredictionById(Long id, Long userId);
}
