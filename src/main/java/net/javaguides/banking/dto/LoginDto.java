package net.javaguides.banking.dto;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    private String email;
    private String password;
}
