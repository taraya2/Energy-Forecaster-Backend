package energy.forecaster.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EnergyConsumptionDTO {

    private Long id;

    @NotNull
    private Double energyUsed;

    @NotNull
    private LocalDateTime timestamp;

    private Double temperature;
    private Double humidity;

    private Long deviceId;
}
