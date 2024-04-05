package com.tp1lab4.project.Controllers;

import com.tp1lab4.project.Models.Noticia;
import com.tp1lab4.project.Services.IEmpresaServices;
import com.tp1lab4.project.Services.INoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Controller
public class NoticiaController {
    @Autowired
    private INoticiaService service;

    @Autowired
    private IEmpresaServices services;

    @GetMapping("crear-noticia/form")
    public String crearNoticiaView(Model modelNoticia, Model modelEmpresa){
        Noticia noticia = new Noticia();
        modelNoticia.addAttribute("noticia", noticia);
        modelEmpresa.addAttribute("empresa",this.services.getEmpresa());
        return "crearNoticia";
    }

    @PostMapping("/crear-noticia")
    public String createNoticia(@ModelAttribute Noticia noticia, @RequestParam("imagen") MultipartFile imagen, @RequestParam("contenidoHtml") String contenidoHtml) throws IOException {
        Path directorioImagenes = Paths.get("src//main//resources//static//images");
        String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
        try{
            byte[] bytesImg = imagen.getBytes();
            Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
            Files.write(rutaCompleta, bytesImg);
            noticia.setImagenNoticia(imagen.getOriginalFilename());
            noticia.setContenidoHtml(contenidoHtml);
            this.service.createNoticia(noticia);
        }catch (IOException e){
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/actualizar-noticia/form/{id}")
    public String actualizarNoticiaView(@PathVariable Integer id, Model noticiaModel, Model empresaModel){
        noticiaModel.addAttribute("noticia", this.service.getNoticiaById(id));
        empresaModel.addAttribute("empresa", this.services.getEmpresa());
        return "actualizarNoticia";
    }

    @GetMapping("/detalle/{id}/{idNoticia}")
    public String detalleView(Model model, Model noticia, @PathVariable("id") Integer id, @PathVariable("idNoticia") Integer idNoticia){
        model.addAttribute("empresa", this.services.getEmpresaById(id));
        noticia.addAttribute("noticia", this.service.getNoticiaById(idNoticia));
        return "detalle";
    }

    @GetMapping("/buscador/{id}")
    public String buscadorView(Model model, @PathVariable Integer id){
        model.addAttribute("empresa", this.services.getEmpresaById(id));
        return "buscador";
    }

    @GetMapping("/eliminar-noticia/{id}")
    public String deleteNoticia(@PathVariable Integer id){
        this.service.deleteNoticia(id);
        return "redirect:/";
    }
}
