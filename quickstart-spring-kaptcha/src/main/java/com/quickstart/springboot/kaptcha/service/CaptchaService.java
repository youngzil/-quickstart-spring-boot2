package com.quickstart.springboot.kaptcha.service;

import com.quickstart.springboot.kaptcha.utils.RedisUtils;
import com.quickstart.springboot.kaptcha.vo.CaptchaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CaptchaService {

    @Value("${server.session.timeout:300}")
    private Long timeout;

    @Autowired
    private RedisUtils redisUtils;


    private final String CAPTCHA_KEY = "captcha:verification:";

    public CaptchaVO cacheCaptcha(String captcha){
        //生成一个随机标识符
        String captchaKey = UUID.randomUUID().toString();

        //缓存验证码并设置过期时间
        redisUtils.set(CAPTCHA_KEY.concat(captchaKey),captcha,timeout);

        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCaptchaKey(captchaKey);
        captchaVO.setExpire(timeout);

        return captchaVO;
    }

}
