package com.emt.libraryappemt.service;

import com.emt.libraryappemt.model.Country;
import com.emt.libraryappemt.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    Optional<Country> findById(Long id);
    List<Country> findAll();
    Optional<Country> save(CountryDto countryDto);
    void deleteById(Long id);
}
