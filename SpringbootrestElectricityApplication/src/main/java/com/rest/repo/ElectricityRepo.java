package com.rest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.model.Electricity;
@Repository
public interface ElectricityRepo extends JpaRepository<Electricity, Integer> {

}
