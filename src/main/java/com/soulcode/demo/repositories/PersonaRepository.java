package com.soulcode.demo.repositories;

import com.soulcode.demo.models.Persona;
import com.soulcode.demo.models.TypeUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
  List<Persona> findAll();

}