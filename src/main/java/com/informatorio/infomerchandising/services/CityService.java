package com.informatorio.infomerchandising.services;

import com.informatorio.infomerchandising.dtos.CityRequest;
import com.informatorio.infomerchandising.entities.City;
import com.informatorio.infomerchandising.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Iterable<?> findAll() {
        return cityRepository.findAll();
    }

    public Iterable<?> findAllByNameContaining(String name) {
        return cityRepository.findByNameContaining(name);
    }

    public City save(CityRequest request) {
        var city = new City();
        city.setName(request.getName());

        return cityRepository.save(city);
    }

    public City findById(Long id) {
        return cityRepository.findById(id).orElse(null);
    }

    public City updateById(Long id, CityRequest request) {
        var data = findById(id);
        if (data != null) {
            if (request.getName() != null)
                data.setName(request.getName());

            cityRepository.save(data);
        }

        return data;
    }

    public City deleteById(Long id) {
        var data = findById(id);
        if (data != null)
            cityRepository.delete(data);
        return data;
    }
}
