package com.tp1lab4.project.Services.Impl;

import com.tp1lab4.project.Models.Noticia;
import com.tp1lab4.project.Repositories.INoticiaRepository;
import com.tp1lab4.project.Services.INoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoticiaService implements INoticiaService {
    @Autowired
    private INoticiaRepository noticiaRepository;

    @Override
    public Noticia createNoticia(Noticia noticia) {
        return this.noticiaRepository.save(noticia);
    }

    @Override
    public Noticia getNoticiaById(Integer id) {
        return this.noticiaRepository.findById(id).get();
    }

    @Override
    public Noticia updateNoticia(Integer id, Noticia request) {
        Noticia noticia = this.noticiaRepository.findById(id).get();
        noticia.setTituloNoticia(request.getTituloNoticia());
        noticia.setResumenNoticia(request.getResumenNoticia());
        noticia.setImagenNoticia(request.getImagenNoticia());
        noticia.setContenidoHtml(request.getContenidoHtml());
        noticia.setPublicada(request.getPublicada());
        noticia.setFechaPublicacion(request.getFechaPublicacion());
        noticia.setEmpresa(request.getEmpresa());

        return this.noticiaRepository.save(noticia);
    }

    @Override
    public List<Noticia> getNoticiasByTituloNoticia(String tituloNoticia, int id) {
        var noticias = this.noticiaRepository.findTop20ByTituloNoticiaContaining(tituloNoticia);

        noticias = noticias.stream().filter(noticia -> noticia.getEmpresa().getId() == id)                .collect(Collectors.toList());

        return noticias;
    }

    @Override
    public boolean getAllNoticias(Integer id) {
        ArrayList<Noticia> noticias = (ArrayList<Noticia>) this.noticiaRepository.findAll();
        for(Noticia n : noticias){
            if(n.getEmpresa().getId() == id){
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Noticia> noticias(Integer id) {
        ArrayList<Noticia> noticias = (ArrayList<Noticia>) this.noticiaRepository.findAll();
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

    @Override
    public Boolean deleteNoticia(Integer id) {
        try{
            this.noticiaRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void deleteNoticiaByFk(Integer id) {
        ArrayList<Noticia> noticias = (ArrayList<Noticia>) this.noticiaRepository.findAll();
        try {
            for(Noticia n : noticias){
                if(n.getEmpresa().getId() == id){
                    this.noticiaRepository.deleteById(n.getId());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}