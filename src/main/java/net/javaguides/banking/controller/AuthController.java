package net.javaguides.banking.controller;
import net.javaguides.banking.dto.*;
import net.javaguides.banking.entity.User;
import net.javaguides.banking.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins="*")
public class AuthController {
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    public AuthController(UserRepository userRepo, PasswordEncoder encoder){ this.userRepo=userRepo; this.encoder=encoder; }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterDto dto){
        if(userRepo.existsByEmail(dto.getEmail())) return ResponseEntity.badRequest().body("Email already exists!");
        User user = User.builder().name(dto.getName()).email(dto.getEmail()).password(encoder.encode(dto.getPassword())).build();
        userRepo.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto){
        var userOpt = userRepo.findByEmail(dto.getEmail());
        if(userOpt.isEmpty()) return ResponseEntity.status(401).body("User not found");
        User user = userOpt.get();
        if(!encoder.matches(dto.getPassword(), user.getPassword())) return ResponseEntity.status(401).body("Invalid password");
        LoginResponse resp = new LoginResponse(user.getId(), user.getName(), user.getEmail(), "Login successful");
        return ResponseEntity.ok(resp);
    }
}
