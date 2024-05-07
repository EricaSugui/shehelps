package com.soulcode.demo.repositories;

import com.soulcode.demo.models.Persona;
import com.soulcode.demo.models.TypeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {


}