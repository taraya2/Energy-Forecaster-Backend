package energy.forecaster.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegistrationDTO {

    @NotBlank
    private String Username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
