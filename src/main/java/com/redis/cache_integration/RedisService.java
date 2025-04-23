package com.redis.cache_integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;


@Service
@Slf4j
public class RedisService {

  private static final String KEY_PREFIX = "CUSTOMER:";

  @Autowired
  RedisTemplate<String, Customer> redisTemplate;
  //RedisTemplate<String, Object> redisTemplate;


  Customer getOrCreateCustomer (Customer customer) {

    //ObjectMapper mapper = new ObjectMapper();
    String key = KEY_PREFIX + customer.id();
    //Object cachedObject = redisTemplate.opsForValue().get(key);
    Customer cachedCustomer = redisTemplate.opsForValue().get(key);
    if (cachedCustomer != null) {
      log.info("Customer already exist into Redis cache !", customer.id());
      //Customer cachedCustomer = mapper.convertValue(cachedObject, Customer.class);
      return cachedCustomer;
    }
    redisTemplate.opsForValue().set(KEY_PREFIX + customer.id(), customer);
    //redisTemplate.opsForValue().set(KEY_PREFIX + customer.id(), customer, Duration.ofMinutes(1));
    log.info("Customer saved into Redis cache !", customer.id());
    return customer;
  }

}
