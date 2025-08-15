package energy.forecaster.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PredictionResponseDTO {


    private Double predictedValue;
    private String modelVersion;
    //private Double confidence;
    private LocalDateTime createdAt;


}
