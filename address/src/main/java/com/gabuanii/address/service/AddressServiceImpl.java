package com.gabuanii.address.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabuanii.address.entity.Address;
import com.gabuanii.address.execption.AddressDuplicateException;
import com.gabuanii.address.execption.AddressNotFoundExecption;
import com.gabuanii.address.repository.AddressRepository;
import com.gabuanii.address.request.CreateAddressRequest;
import com.gabuanii.address.request.UpdateAddressRequest;
import com.gabuanii.address.response.AddressResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@Slf4j
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ObjectMapper objectMapper;

    @Override
    public Optional<Address> getAddress(long id) {
        return addressRepository.findById(id);
    }

    @Override
    public Optional<Address> getAddressByCity(String city) {
        return addressRepository.findByCity(city);
    }

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public AddressResponse createAddress(CreateAddressRequest addressRequest) {
        Address address = objectMapper.convertValue(addressRequest, Address.class);

        if (addressRepository.existsByStreetAndCity(addressRequest.getStreet(), addressRequest.getCity()))
            throw new AddressDuplicateException(addressRequest.getStreet() + " and " + addressRequest.getCity() + " is already exist!");

        return new AddressResponse(addressRepository.save(address));
    }

    @Override
    public AddressResponse updateAddress(UpdateAddressRequest addressRequest) {
        Address updateAddress = addressRepository.findById(addressRequest.getAddressId())
                .orElseThrow(() -> new AddressNotFoundExecption("Address with id " + addressRequest.getAddressId() + " is not found"));

        updateAddress.setStreet(addressRequest.getStreet());
        updateAddress.setCity(addressRequest.getCity());
        addressRepository.save(updateAddress);

        return new AddressResponse(addressRepository.save(updateAddress));
    }

    @Override
    public void deleteAddress(long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundExecption("Address with id " + id + " is not found"));
        addressRepository.delete(address);
    }
}
