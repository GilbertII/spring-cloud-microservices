package com.gabuanii.student.service;

import com.gabuanii.student.feign.AddressFeignClient;
import com.gabuanii.student.response.AddressResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CommonService {

    private int count = 1;
    @Autowired
    private AddressFeignClient addressFeignClient;

    @CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddress")
    public AddressResponse getAddress(long addressId) {
        log.info("count = " + count + " addressId = " + addressId);
        count++;
        return addressFeignClient
                .getAddress(addressId)
                .getBody();
    }

    public AddressResponse fallbackGetAddress(long addressId, Throwable throwable ) {
        log.error("Error: " + throwable);
        return new AddressResponse();
    }

}
