package energy.forecaster.service;

import energy.forecaster.dto.ChangePasswordDTO;
import energy.forecaster.dto.UserLoginDTO;
import energy.forecaster.dto.UserRegistrationDTO;
import energy.forecaster.dto.UserResponseDTO;

public interface UserService {

    public UserResponseDTO registerUser(UserRegistrationDTO dto);
    public String login(UserLoginDTO dto);
    public void changePassword(ChangePasswordDTO dto);
    public UserResponseDTO getCurrentUser();
    public String refreshToken(String oldToken);


}
