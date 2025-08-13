package energy.forecaster.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationDTO {

    private Long id;

    @NotBlank
    private String name;

    private String address;
    private String city;
    private String state;
    private String country;
    private Double latitude;
    private Double longitude;

}
