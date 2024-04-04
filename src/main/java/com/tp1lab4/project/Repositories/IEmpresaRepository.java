package com.tp1lab4.project.Repositories;

import com.tp1lab4.project.Models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpresaRepository extends JpaRepository<Empresa, Integer> {
}
