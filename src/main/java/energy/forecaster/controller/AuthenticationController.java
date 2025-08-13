package energy.forecaster.controller;

import energy.forecaster.dto.UserLoginDTO;
import energy.forecaster.dto.UserRegistrationDTO;
import energy.forecaster.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    // Register New User
    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@RequestBody
                            UserRegistrationDTO registrationDTO) {
        return null;
    }

    // Authenticate User and return JWT
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO loginDTO) {
        //String token = userService.authenticateUser(loginDTO);
        //return ResponseEntity.ok(token);
        return null;
    }




//    POST /api/auth/register → Register new user
//    Body: UserRegistrationDTO
//    POST /api/auth/login → Authenticate user, return JWT
//    Body: UserLoginDTO
}
