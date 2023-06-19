package com.danilo.people.util;

import com.danilo.people.dto.request.PersonRequestDTO;
import com.danilo.people.dto.response.PersonResponseDTO;
import com.danilo.people.entity.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper {

    public Person toPerson(PersonRequestDTO personRequestDTO){
        return Person.builder()
                .name(personRequestDTO.getName())
                .cpf(personRequestDTO.getCpf())
                .age(personRequestDTO.getAge())
                .build();
    }

    public PersonResponseDTO toPersonDTO(Person person) {
        return new PersonResponseDTO(person);
    }

    public List<PersonResponseDTO> toListPersonDTO(List<Person> personList) {
        return personList.stream().map(PersonResponseDTO::new).collect(Collectors.toList());
    }

    public void updatePersonData(Person person, PersonRequestDTO personRequestDTO){
        person.setName(personRequestDTO.getName());
        person.setCpf(personRequestDTO.getCpf());
        person.setAge(personRequestDTO.getAge());
    }
}
