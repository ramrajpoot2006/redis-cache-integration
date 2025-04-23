package com.redis.cache_integration;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;


import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
@EnableCaching // Enables Spring's annotation-driven cache management
public class RedisConfiguration {

  @Bean
  public RedisConnectionFactory redisConnectionFactory () {

    //return new LettuceConnectionFactory("localhost", 6379);
    return new LettuceConnectionFactory("127.0.0.1", 6379);
  }


  @Bean
  public RedisTemplate<String, Customer> redisTemplate (RedisConnectionFactory connectionFactory) {

    RedisTemplate<String, Customer> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory);

    // Use Jackson for value serialization
    Jackson2JsonRedisSerializer<Customer> serializer = new Jackson2JsonRedisSerializer<>(Customer.class);
    template.setValueSerializer(serializer);
    template.setKeySerializer(new StringRedisSerializer());

    template.afterPropertiesSet();
    return template;
  }


  /*
  @Bean
  public RedisTemplate<String, Object> redisTemplate (RedisConnectionFactory connectionFactory) {

    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory);
    template.setKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(new StringRedisSerializer());
    return template;
  }  */
}
