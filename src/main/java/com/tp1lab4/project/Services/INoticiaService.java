package com.tp1lab4.project.Services;

import com.tp1lab4.project.Models.Noticia;

import java.util.ArrayList;

public interface INoticiaService {
    Noticia createNoticia(Noticia noticia);
    Noticia getNoticiaById(Integer id);
    Noticia updateNoticia(Integer id, Noticia request);
    int getUltimaNoticia();
    boolean getAllNoticias(Integer id);
}
