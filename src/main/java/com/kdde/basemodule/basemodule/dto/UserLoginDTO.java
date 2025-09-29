package com.kdde.basemodule.basemodule.dto;

import lombok.*;

@Getter
@Data
public class UserLoginDTO {
    private String username;
    private String password;
    private String email;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}

