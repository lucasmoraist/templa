package com.lucasmoraist.templa.application.gateway;

import com.lucasmoraist.templa.domain.dto.EnrollUserDTO;

public interface CacheGateway {

    void save(String key, Object value);
    EnrollUserDTO get(String key);
    void delete(String key);

}
