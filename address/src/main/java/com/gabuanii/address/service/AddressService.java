package com.gabuanii.address.service;

import com.gabuanii.address.entity.Address;
import com.gabuanii.address.request.CreateAddressRequest;
import com.gabuanii.address.request.UpdateAddressRequest;
import com.gabuanii.address.response.AddressResponse;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    Optional<Address> getAddress(long id);

    Optional<Address> getAddressByCity(String city);

    List<Address> getAllAddress();

    AddressResponse createAddress(CreateAddressRequest addressRequest);

    AddressResponse updateAddress(UpdateAddressRequest addressRequest);

    void deleteAddress(long id);
}
