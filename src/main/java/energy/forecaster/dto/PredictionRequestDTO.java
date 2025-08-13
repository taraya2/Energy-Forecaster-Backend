package energy.forecaster.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PredictionRequestDTO {

    @NotNull
    private Double temperature;
    @NotNull
    private Double humidity;
    @NotNull
    private Double pastEnergyUsage; // last known kWH

    private Long locationId;
    private Long deviceId;

}
