package com.redis.cache_integration;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@Slf4j
public class RedisController {

  @Autowired
  RedisService redisService;


  @GetMapping
  public String ping () {

    return "Hello Mr. Singh, how are you?";
  }


  @PostMapping("/redis")
  public ResponseEntity<Customer> getOrCreateCustomer (@RequestBody Customer customer) {

    log.info("RedisController ::: ", customer.id());
    return ResponseEntity.ok(redisService.getOrCreateCustomer(customer));
  }


}
