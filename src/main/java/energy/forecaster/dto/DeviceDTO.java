package energy.forecaster.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceDTO {

    private Long id;

    @NotBlank
    private String name;

    private String type;
    private Long locationId; // connects device to a location
}
