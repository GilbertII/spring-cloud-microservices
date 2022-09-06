package com.gabuanii.student.feign;

import com.gabuanii.student.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "api-gateway/address-service")
public interface AddressFeignClient {

    @GetMapping("/api/address/getAddress/{id}")
    public ResponseEntity<AddressResponse> getAddress(@PathVariable long id);
}
