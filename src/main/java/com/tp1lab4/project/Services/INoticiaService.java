package com.tp1lab4.project.Services;

import com.tp1lab4.project.Models.Empresa;
import com.tp1lab4.project.Models.Noticia;

import java.util.ArrayList;
import java.util.List;

public interface INoticiaService {
    Noticia createNoticia(Noticia noticia);
    Noticia getNoticiaById(Integer id);
    Noticia updateNoticia(Integer id, Noticia request);
    List<Noticia> getNoticiasByTituloNoticia(String tituloNoticia, int id);
    boolean getAllNoticias(Integer id);
    ArrayList<Noticia> noticias(Integer id);
    Boolean deleteNoticia(Integer id);
    void deleteNoticiaByFk(Integer id);
}
