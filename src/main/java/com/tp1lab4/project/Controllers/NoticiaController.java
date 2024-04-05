package com.tp1lab4.project.Controllers;

import com.tp1lab4.project.Models.Noticia;
import com.tp1lab4.project.Services.IEmpresaServices;
import com.tp1lab4.project.Services.INoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class NoticiaController {
    @Autowired
    private INoticiaService noticiaService;

    @Autowired
    private IEmpresaServices empresaService;

    @GetMapping("crear-noticia/form")
    public String crearNoticiaView(Model modelNoticia, Model modelEmpresa){
        Noticia noticia = new Noticia();
        modelNoticia.addAttribute("noticia", noticia);
        modelEmpresa.addAttribute("empresa",this.empresaService.getEmpresa());
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
            this.noticiaService.createNoticia(noticia);
        }catch (IOException e){
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/actualizar-noticia/form/{idEmpresa}/{id}")
    public String actualizarNoticiaView(@PathVariable("id") Integer id, @PathVariable("idEmpresa") Integer idEmpresa, Model noticiaModel, Model empresaModel, Model empModel){
        noticiaModel.addAttribute("noticia", this.noticiaService.getNoticiaById(id));
        empresaModel.addAttribute("empresa", this.empresaService.getEmpresa());
        empModel.addAttribute("emp", this.empresaService.getEmpresaById(idEmpresa));
        return "actualizarNoticia";
    }

    @PostMapping("/actualizar-noticia/{idEmpresa}/{id}")
    public String updateNoticia(@PathVariable("id") Integer id, @PathVariable("idEmpresa") Integer idEmrpesa, @ModelAttribute Noticia noticia, @RequestParam("imagen") MultipartFile imagen, @RequestParam("contenidoHtml") String contenidoHtml) throws IOException {
        Path directorioImagenes = Paths.get("src//main//resources//static//images");
        String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
        try{
            byte[] bytesImg = imagen.getBytes();
            Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
            Files.write(rutaCompleta, bytesImg);
            noticia.setImagenNoticia(imagen.getOriginalFilename());
            noticia.setContenidoHtml(contenidoHtml);
            this.noticiaService.updateNoticia(id, noticia);
        }catch (IOException e){
            e.printStackTrace();
        }
        return "redirect:/detalle/" + noticia.getEmpresa().getId() + "/" + id;
    }

    @GetMapping("/detalle/{id}/{idNoticia}")
    public String detalleView(Model model, Model noticia, @PathVariable("id") Integer id, @PathVariable("idNoticia") Integer idNoticia){
        model.addAttribute("empresa", this.empresaService.getEmpresaById(id));
        noticia.addAttribute("noticia", this.noticiaService.getNoticiaById(idNoticia));
        return "detalle";
    }

    @GetMapping("/buscador/{id}")
    public String buscadorView(Model model, @RequestParam String tituloNoticia, @PathVariable("id") Integer id){
        try {
            model.addAttribute("empresa", this.empresaService.getEmpresaById(id));
            model.addAttribute("noticias", this.noticiaService.getNoticiasByTituloNoticia(tituloNoticia,id));
            return "buscador";
        }catch (Exception e){
            throw e;
        }
    }

    @GetMapping("/eliminar-noticia/{idEmpresa}/{id}")
    public String deleteNoticia(@PathVariable("id") Integer id, @PathVariable("idEmpresa") Integer idEmpresa){
        this.noticiaService.deleteNoticia(id);
        return "redirect:/home/" + idEmpresa;
    }
}