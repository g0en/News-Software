package com.tp1lab4.project.Repositories;

import com.tp1lab4.project.Models.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INoticiaRepository extends JpaRepository<Noticia, Integer> {
    List<Noticia> findTop20ByTituloNoticiaContaining(String tituloNoticia);
}
