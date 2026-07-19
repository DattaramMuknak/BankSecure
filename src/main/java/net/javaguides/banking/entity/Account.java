package net.javaguides.banking.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name="accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="account_holder_name")
    private String accountHolderName;
    private double balance;
}
