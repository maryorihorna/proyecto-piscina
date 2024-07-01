// package com.proyecto.piscina.web.app.services;

// // import java.util.List;
// // import java.util.Optional;

// // import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// // import com.proyecto.piscina.web.app.entities.Persona;
// // import com.proyecto.piscina.web.app.respository.PersonaRepository;

// @Service
// public class PersonaService {

    // private final PersonaRepository personaRepository;

    // @Autowired
    // public PersonaService(PersonaRepository personaRepository) {
    //     this.personaRepository = personaRepository;
    // }

    // public Persona savePersona(Persona persona) {
    //     return personaRepository.save(persona);
    // }
  
    // public Persona updatePersona(long id, Persona persona) {
    //     Persona personaactual = personaRepository.findById(id).orElseThrow(() -> new IllegalStateException("El cliente con id " + id + " no existe"));
    //     personaactual.setNombre(persona.getNombre());
    //     personaactual.setApellido(persona.getApellido());
    //     personaactual.setTelefono(persona.getTelefono());
    //     personaactual.setEmail(persona.getEmail());
    //     return personaRepository.save(personaactual);
    // }

    // public Persona getPersona(Long id) {
    //     return personaRepository.findById(id).orElse(null);
    // }

    // public List<Persona> getAllPersonas() {
    //     return personaRepository.findAll();
    // }

    // public void deletePersona(Long id) {
    //    boolean existe = personaRepository.existsById(id);
    //     if (!existe) {
    //         throw new IllegalStateException("La persona con id " + id + " no existe");
    //     }
    //     personaRepository.deleteById(id);
    // }

    // public Optional<Persona> getPersona(long id) {
    //     return personaRepository.findById(id);
    // }
// }
