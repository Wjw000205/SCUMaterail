package com.kdde.basemodule.basemodule.dto;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class UserRegistDTO {

    private String username;
    private String password;
    private String passwordConfirm;
    private String email;
    private String name;

}
