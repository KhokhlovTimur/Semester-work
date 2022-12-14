package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String login;
    private String password;
    //noAuth, auth, admin
    private String role;
    private int balance;
    private String email;
}
