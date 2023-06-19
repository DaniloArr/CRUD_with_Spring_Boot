package com.danilo.people.service;

import com.danilo.people.dto.request.PersonRequestDTO;
import com.danilo.people.dto.response.PersonResponseDTO;

import java.util.List;

public interface PersonService {

    PersonResponseDTO findById(Long id);

    List<PersonResponseDTO> findAll();

    PersonResponseDTO register(PersonRequestDTO personDTO);

    PersonResponseDTO update(PersonRequestDTO personDTO, Long id);

    String delete(Long id);
}
