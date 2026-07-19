package net.javaguides.banking.dto;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    private String name;
    private String email;
    private String password;
}
