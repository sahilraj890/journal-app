package net.sahil.journalApp.service;

import net.sahil.journalApp.config.RedisConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisConfig redisConfig;

    @Disabled
    @Test
    void testSendMail() {
        redisTemplate.opsForValue().set("email", "sahil.shivameps@gmail.com");
        Object email = redisTemplate.opsForValue().get("email");
        int a = 1;
    }
}
