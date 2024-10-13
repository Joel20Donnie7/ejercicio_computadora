package com.gestioncomputador.repository;

import com.gestioncomputador.entity.Computadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputadoraRepository extends JpaRepository<Computadora,Integer> {
}