package com.lucasmoraist.templa.infra.redis.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasmoraist.templa.application.gateway.CacheGateway;
import com.lucasmoraist.templa.domain.dto.EnrollUserDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Log4j2
@Service
public class RedisCache implements CacheGateway {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    public RedisCache(RedisTemplate<String, Object> redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void save(String key, Object value) {
        try {
            log.debug("Saving key: {} in cache with value: {}", key, value);
            redisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(value), 360, TimeUnit.MINUTES);
        } catch (JsonProcessingException ex) {
            log.error("Error serializing value for key: {}. Error: {}", key, ex.getMessage());
        }
    }

    @Override
    public EnrollUserDTO get(String key) {
        log.debug("Retrieving key: {} from cache", key);
        String value = (String) redisTemplate.opsForValue().get(key);

        try {
            return objectMapper.readValue(value, EnrollUserDTO.class);
        } catch (IOException ex) {
            log.error("Error deserializing value for key: {}. Error: {}", key, ex.getMessage());
            return null;
        }
    }


    @Override
    public void delete(String key) {
        log.debug("Deleting key: {} from cache", key);
        redisTemplate.delete(key);
    }

}
