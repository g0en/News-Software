package com.tp1lab4.project.Controllers;

import com.tp1lab4.project.Models.Noticia;
import com.tp1lab4.project.Services.INoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/noticia")
public class NoticiaRestController {
    @Autowired
    private INoticiaService service;

    @PostMapping
    public Noticia createNoticia(@RequestBody Noticia noticia){
        return this.service.createNoticia(noticia);
    }

    @PutMapping("{id}")
    public Noticia updateNoticia(@PathVariable Integer id, @RequestBody Noticia request){
        return this.service.updateNoticia(id, request);
    }
}
