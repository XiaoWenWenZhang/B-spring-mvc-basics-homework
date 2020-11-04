package com.thoughtworks.capacity.gtb.mvc.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPo {
    private String id;
    @NotBlank(message = "用户名不为空")
    @Pattern(regexp = "^[0-9a-zA-Z_]{3,10}$", message = "用户名不合法")
    private String username;
    @NotBlank(message = "密码是不为空")
    @Length(min = 5, max = 12, message = "密码不合法")
    private String password;
    @NotBlank
    @Email(message = "邮箱地址不合法")
    private String email;
}
