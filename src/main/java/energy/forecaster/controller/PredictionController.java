package energy.forecaster.controller;

import energy.forecaster.dto.PredictionResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/{userId}/predictions")
public class PredictionController {

    // get all predictions for user
    @GetMapping
    public List<PredictionResponseDTO> getAllPredictions(@PathVariable Long userId) {
        return null;
    }

    // get specific prediction
    @GetMapping("/{predictionId}")
    public List<PredictionResponseDTO> getPrediction(@PathVariable Long userId, @PathVariable Long predictionId) {
        return null;

    }
}
