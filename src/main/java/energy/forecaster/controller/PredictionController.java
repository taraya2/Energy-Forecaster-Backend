package energy.forecaster.controller;

import energy.forecaster.dto.PredictionRequestDTO;
import energy.forecaster.dto.PredictionResponseDTO;
import energy.forecaster.entity.Prediction;
import energy.forecaster.security.CustomUserDetails;
import energy.forecaster.service.PredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predictions")
@RequiredArgsConstructor
public class PredictionController {

    private final PredictionService predictionService;

    @PostMapping
    public ResponseEntity<PredictionResponseDTO> makePrediction(
            @RequestBody PredictionRequestDTO dto,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(predictionService.makePrediction(dto, userDetails.getId()));
    }

    @GetMapping
    public ResponseEntity<List<Prediction>> getUserPredictions(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(predictionService.getUserPredictions(userDetails.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prediction> getPredictionById(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(predictionService.getPredictionById(id, userDetails.getId()));
    }
}
