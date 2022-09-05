package com.gabuanii.address.response;

import com.gabuanii.address.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

    private Long addressId;
    private String street;
    private String city;

    public AddressResponse(Address address){
        this.addressId = address.getId();
        this.street = address.getStreet();
        this.city = address.getCity();
    }

}
