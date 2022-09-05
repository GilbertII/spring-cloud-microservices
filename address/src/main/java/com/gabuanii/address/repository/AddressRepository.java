package com.gabuanii.address.repository;

import com.gabuanii.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findById(long id);

    Optional<Address> findByCity(String city);

    Optional<Address> findFirstByCity(String city);

    boolean existsByStreetAndCity(String street, String city);
}
