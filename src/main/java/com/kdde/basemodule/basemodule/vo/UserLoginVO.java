package com.kdde.basemodule.basemodule.vo;

import lombok.*;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVO {
   private String username;
   private String name;
   private Integer satus;
   private Integer role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSatus() {
        return satus;
    }

    public void setSatus(Integer satus) {
        this.satus = satus;
    }

}
