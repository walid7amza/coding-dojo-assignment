package com.leaseplan.codingdojoassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.leaseplan.codingdojoassignment.model.CityWeather;

@Repository
public interface CityWeatherRepository extends JpaRepository<CityWeather, Integer> {

}
