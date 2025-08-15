package com.lucasmoraist.templa.application.gateway;

public interface CacheGateway {

    void save(String key, Object value);
    void delete(String key);

}
