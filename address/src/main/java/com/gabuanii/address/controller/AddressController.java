package com.gabuanii.address.controller;

import com.gabuanii.address.entity.Address;
import com.gabuanii.address.execption.AddressNotFoundExecption;
import com.gabuanii.address.request.CreateAddressRequest;
import com.gabuanii.address.request.UpdateAddressRequest;
import com.gabuanii.address.response.AddressResponse;
import com.gabuanii.address.service.AddressService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Slf4j
@RestController
@RequestMapping("/api/address")
@RefreshScope
public class AddressController {

    private final AddressService addressService;

    @Value("${prop.type}")
    private String test;

    @GetMapping("/getAddress/{id}")
    public ResponseEntity<AddressResponse> getAddress(@PathVariable long id) {
        Address address = addressService.getAddress(id)
                .orElseThrow(() -> new AddressNotFoundExecption("Address not found!"));
        AddressResponse addressResponse = new AddressResponse(address);
        return ResponseEntity.ok(addressResponse);
    }

    @GetMapping("/getAllAddress")
    public ResponseEntity<List<AddressResponse>> getAllAddress() {
        List<Address> addressList = addressService.getAllAddress();
        return ResponseEntity.ok(addressList.stream()
                .map(address -> new AddressResponse((address)))
                .collect(Collectors.toList()));
    }

    @PostMapping("/create")
    public ResponseEntity<AddressResponse> createAddress(@RequestBody CreateAddressRequest addressRequest) {
        return new ResponseEntity<>(addressService.createAddress(addressRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable long id, @RequestBody UpdateAddressRequest addressRequest) {
        return ResponseEntity.ok(addressService.updateAddress(addressRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable long id) {
        addressService.getAddress(id)
                .orElseThrow(() -> new AddressNotFoundExecption("Address not found!"));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test")
    public String test() {
        return test;
    }

}
