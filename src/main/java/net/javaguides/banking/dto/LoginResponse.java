package net.javaguides.banking.dto;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private Long id;
    private String name;
    private String email;
    private String message;
}
