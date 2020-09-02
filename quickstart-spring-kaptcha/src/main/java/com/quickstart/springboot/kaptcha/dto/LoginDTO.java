package com.quickstart.springboot.kaptcha.dto;


import lombok.Data;

@Data
public class LoginDTO {

    private String userName;

    private String pwd;

    private String captchaKey;

    private String captcha;

}
