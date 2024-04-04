package com.tp1lab4.project.Services.Impl;

import com.tp1lab4.project.Models.Empresa;
import com.tp1lab4.project.Models.Noticia;
import com.tp1lab4.project.Repositories.INoticiaRepository;
import com.tp1lab4.project.Services.INoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NoticiaService implements INoticiaService {
    @Autowired
    private INoticiaRepository repository;

    @Override
    public Noticia createNoticia(Noticia noticia) {
        return this.repository.save(noticia);
    }

    @Override
    public Noticia getNoticiaById(Integer id) {
        return this.repository.findById(id).get();
    }

    @Override
    public Noticia updateNoticia(Integer id, Noticia request) {
        Noticia noticia = this.repository.findById(id).get();
        noticia.setTituloNoticia(request.getTituloNoticia());
        noticia.setResumenNoticia(request.getResumenNoticia());
        noticia.setImagenNoticia(request.getImagenNoticia());
        noticia.setContenidoHtml(request.getContenidoHtml());
        noticia.setPublicada(request.getPublicada());
        noticia.setFechaPublicacion(request.getFechaPublicacion());
        noticia.setEmpresa(request.getEmpresa());

        return this.repository.save(noticia);
    }

    @Override
    public boolean getAllNoticias(Integer id) {
        ArrayList<Noticia> noticias = (ArrayList<Noticia>) this.repository.findAll();
        for(Noticia n : noticias){
            if(n.getEmpresa().getId() == id){
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Noticia> noticias(Integer id) {
        ArrayList<Noticia> noticias = (ArrayList<Noticia>) this.repository.findAll();
        ArrayList<Noticia> noticiasReturn = new ArrayList<>();
        int i= 1;
        for(Noticia n : noticias){
            if(n.getEmpresa().getId() == id){
                noticiasReturn.add(n);
                if(i==5){
                    break;
                }
                i++;
            }
        }
        return noticiasReturn;
    }


}
