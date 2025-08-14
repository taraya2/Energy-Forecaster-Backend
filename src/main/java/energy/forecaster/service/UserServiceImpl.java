package energy.forecaster.service;

import energy.forecaster.dto.ChangePasswordDTO;
import energy.forecaster.dto.UserLoginDTO;
import energy.forecaster.dto.UserRegistrationDTO;
import energy.forecaster.dto.UserResponseDTO;
import energy.forecaster.entity.User;
import energy.forecaster.repository.UserRepository;
import energy.forecaster.security.CustomUserDetails;
import energy.forecaster.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserRepository users;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtTokenProvider jwt;

    public UserResponseDTO registerUser(UserRegistrationDTO dto) {
        if (users.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        if (users.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Username already in use");
        }

        User u = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .passwordHash(encoder.encode(dto.getPassword()))
                .role("USER")
                .createdAt(LocalDateTime.now())
                .build();

        users.save(u);

        UserResponseDTO res = new UserResponseDTO();
        res.setId(u.getId());
        res.setUsername(u.getUsername());
        res.setEmail(u.getEmail());
        res.setRole(u.getRole());
        res.setCreatedAt(u.getCreatedAt().toString());
        return res;
    }

    public String login(UserLoginDTO dto) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );
        CustomUserDetails p = (CustomUserDetails) auth.getPrincipal();
        return jwt.generateToken(p.getUsername(), p.getAuthorities().stream()
                .findFirst().orElseThrow().getAuthority().replace("ROLE_", ""));
    }

    public String refreshToken(String oldToken) {
        if (!jwt.validateToken(oldToken)) {
            throw new IllegalArgumentException("Invalid token");
        }
        String email = jwt.getEmailFromToken(oldToken);
        String role = jwt.getRoleFromToken(oldToken);
        return jwt.generateToken(email, role);
    }

    public void changePassword(ChangePasswordDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof CustomUserDetails principal)) {
            throw new IllegalStateException("User not authenticated");
        }
        User u = users.findByEmail(principal.getUsername())
                .orElseThrow(() -> new IllegalStateException("User not found"));

        if (!encoder.matches(dto.getOldPassword(), u.getPasswordHash())) {
            throw new IllegalArgumentException("Old password is incorrect");
        }

        u.setPasswordHash(encoder.encode(dto.getNewPassword()));
        users.save(u);
    }

    public UserResponseDTO getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof CustomUserDetails principal)) {
            throw new IllegalStateException("User not authenticated");
        }
        User u = users.findByEmail(principal.getUsername())
                .orElseThrow(() -> new IllegalStateException("User not found"));

        UserResponseDTO res = new UserResponseDTO();
        res.setId(u.getId());
        res.setUsername(u.getUsername());
        res.setEmail(u.getEmail());
        res.setRole(u.getRole());
        res.setCreatedAt(u.getCreatedAt().toString());
        return res;
    }


}
