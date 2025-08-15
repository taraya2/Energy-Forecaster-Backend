package energy.forecaster.controller;

import energy.forecaster.dto.*;
import energy.forecaster.security.JwtTokenProvider;
import energy.forecaster.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    // Register New User
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerNewUser(@RequestBody
                            UserRegistrationDTO registrationDTO) {
        UserResponseDTO user = userService.registerUser(registrationDTO);;
        return ResponseEntity.ok(user);
    }

    // Authenticate User and return JWT
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO loginDTO) {
        String token = userService.login(loginDTO);
        return ResponseEntity.ok().body(token);
    }


    // get current user details
    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getProfile() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @PostMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordDTO dto) {
        userService.changePassword(dto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<String> refreshToken(@RequestBody RefreshTokenDTO dto) {
        return ResponseEntity.ok(userService.refreshToken(dto.getToken()));
    }



//    POST /api/auth/register → Register new user
//    Body: UserRegistrationDTO
//    POST /api/auth/login → Authenticate user, return JWT
//    Body: UserLoginDTO
}
