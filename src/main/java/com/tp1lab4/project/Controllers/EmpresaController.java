package com.tp1lab4.project.Controllers;

import com.tp1lab4.project.Models.Empresa;
import com.tp1lab4.project.Models.Noticia;
import com.tp1lab4.project.Services.IEmpresaServices;
import com.tp1lab4.project.Services.INoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@Controller
public class EmpresaController {
    @Autowired
    private IEmpresaServices empresaService;
    @Autowired
    private INoticiaService noticiaService;

    @PostMapping("/crear-empresa")
    public String createEmpresa(@ModelAttribute("empresa") Empresa empresa){
        this.empresaService.createEmpresa(empresa);
        return "redirect:/";
    }


    @GetMapping("/home/{id}")
    public String homeView(Model model, Model noticia, @PathVariable Integer id){
        model.addAttribute("empresa", this.empresaService.getEmpresaById(id));
        if(this.noticiaService.getAllNoticias(id)){
            ArrayList<Noticia> noticias = this.noticiaService.noticias(id);
            noticia.addAttribute("noticia", noticias);
        }else{
            Noticia noticiaAux = new Noticia();
            noticiaAux.setTituloNoticia("No hay noticias");
            noticiaAux.setResumenNoticia("");
            noticiaAux.setImagenNoticia("page-3_img1_original.jpg");
            noticia.addAttribute("noticia", noticiaAux);
        }

        return "home";
    }

    @GetMapping("/")
    public String indexView(Model model){
        model.addAttribute("empresas", this.empresaService.getEmpresa());
        return "index";
    }

    @GetMapping("/crear-empresa/form")
    public String crearEmpresaView(Model model){
        Empresa empresa = new Empresa();
        model.addAttribute("empresa", empresa);
        return "crearEmpresa";
    }

    @GetMapping("/actualizar-empresa/form/{id}")
    public String actualizarEmpresaView(Model model, @PathVariable Integer id){
        model.addAttribute("empresa", this.empresaService.getEmpresaById(id));
        return "actualizarEmpresa";
    }

    @PostMapping("/actualizar-empresa/{id}")
    public String updateEmpresa(@ModelAttribute Empresa empresa, @PathVariable Integer id){
        this.empresaService.updateEmpresa(id, empresa);
        return "redirect:/";
    }

    @GetMapping("/eliminar-empresa/{id}")
    public String deleteEmpresa(@PathVariable Integer id){
        this.empresaService.deleteEmpresa(id);
        return "redirect:/";
    }

    @GetMapping("/tiny")
    public String tiny(){
        return "tiny";
    }
}
