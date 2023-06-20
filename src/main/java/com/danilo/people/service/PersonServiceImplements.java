package com.danilo.people.service;

import com.danilo.people.dto.request.PersonRequestDTO;
import com.danilo.people.dto.response.PersonResponseDTO;
import com.danilo.people.entity.Person;
import com.danilo.people.repository.PersonRepository;
import com.danilo.people.util.PersonMapper;
import com.danilo.people.util.Regex;
import lombok.RequiredArgsConstructor;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class PersonServiceImplements implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final Regex regexCpf;

    @Override
    public PersonResponseDTO findById(Long id) {
        return personMapper.toPersonDTO(loadPerson(id));
    }

    @Override
    public List<PersonResponseDTO> findAll() {
        return personMapper.toListPersonDTO(personRepository.findAll());
    }

    @Override
    public PersonResponseDTO register(PersonRequestDTO personDTO) {
        if (!regexCpf.isValidCPF(personDTO.getCpf())) {
            throw new IllegalArgumentException("Invalid CPF");
        }
        Person person = personMapper.toPerson(personDTO);
        return personMapper.toPersonDTO(personRepository.save(person));
    }

    @Override
    public PersonResponseDTO update(PersonRequestDTO personDTO, Long id) {
        if (!regexCpf.isValidCPF(personDTO.getCpf())) {
            throw new IllegalArgumentException("Invalid CPF");
        }
        Person person = loadPerson(id);
        personMapper.updatePersonData(person, personDTO);
        return personMapper.toPersonDTO(personRepository.save(person));
    }

    @Override
    public String delete(Long id) {
        personRepository.deleteById(id);
        return  "Person id:" + id + "deleted";
    }

    private Person loadPerson(Long id){
        return  personRepository.findById(id)
                    .orElseThrow(()-> new ResourceNotFoundException("Person not found"));
    }
}
