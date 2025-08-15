package energy.forecaster.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import energy.forecaster.dto.PredictionRequestDTO;
import energy.forecaster.dto.PredictionResponseDTO;
import energy.forecaster.entity.Prediction;
import energy.forecaster.entity.User;
import energy.forecaster.repository.PredictionRepository;
import energy.forecaster.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PredictionServiceImpl implements PredictionService {

    private final PredictionRepository predictionRepo;
    private final UserRepository userRepo;
    private final ObjectMapper objectMapper;

    private final MLModelClient mlModelClient;

    @Override
    public PredictionResponseDTO makePrediction(PredictionRequestDTO dto, Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        double predictedValue = mlModelClient.getPredictionFromPython(dto);

        String inputData;
        try {
            inputData = objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing prediction input data", e);
        }

        Prediction prediction = Prediction.builder()
                .user(user)
                .inputData(inputData)
                .predictedValue(predictedValue)
                .modelVersion("v1.0") // could be dynamic
                .createdAt(LocalDateTime.now())
                .build();

        predictionRepo.save(prediction);

        return new PredictionResponseDTO(
                prediction.getPredictedValue(),
                prediction.getModelVersion(),
                prediction.getCreatedAt()
        );
    }

    @Override
    public List<Prediction> getUserPredictions(Long userId) {
        return predictionRepo.findByUserId(userId);
    }

    @Override
    public Prediction getPredictionById(Long id, Long userId) {
        return predictionRepo.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Prediction not found or not yours"));
    }
}
