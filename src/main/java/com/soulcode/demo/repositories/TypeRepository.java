package com.soulcode.demo.repositories;

import com.soulcode.demo.models.Persona;
import com.soulcode.demo.models.Sector;
import com.soulcode.demo.models.TypeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TypeRepository extends JpaRepository<Persona, Integer> {
    @Query("SELECT p FROM Persona p WHERE p.Email = :email")
    Persona findByEmail(String email);

    @Query("SELECT p FROM Persona p WHERE p.Nome = :nome AND p.Setor = :setor")
    Persona findByNomeAndSetor(String nome, Sector setor);

    @Query("SELECT p FROM Persona p WHERE p.Email = :email AND p.Tipo = :tipoUsuario")
    Persona findByEmailAndTipoUsuario(String email, TypeUser tipoUsuario);

}
