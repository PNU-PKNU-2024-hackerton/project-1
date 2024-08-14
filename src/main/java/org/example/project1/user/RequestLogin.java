package org.example.project1.user;

import lombok.Data;

@Data
public class RequestLogin {
    private String email;
    private String password;
}
