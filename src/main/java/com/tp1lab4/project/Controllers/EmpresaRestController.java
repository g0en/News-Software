package com.tp1lab4.project.Controllers;

import com.tp1lab4.project.Models.Empresa;
import com.tp1lab4.project.Services.IEmpresaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/empresa")
public class EmpresaRestController {
    @Autowired
    private IEmpresaServices services;

    @GetMapping
    public ArrayList<Empresa> getEmrpesa(){
        return this.services.getEmpresa();
    }

    @GetMapping("/{id}")
    public Empresa getEmpresaById(@PathVariable Integer id){
        return this.services.getEmpresaById(id);
    }

    @PostMapping
    public Empresa createEmpresa(@RequestBody Empresa empresa){
        return this.services.createEmpresa(empresa);
    }

    @PutMapping("/{id}")
    public Empresa updateEmpresa(@PathVariable Integer id, @RequestBody Empresa request){
        return services.updateEmpresa(id,  request);
    }

    @DeleteMapping("/{id}")
    public String deleteEmpresa(@PathVariable Integer id){
        Boolean response = this.services.deleteEmpresa(id);
        if(response){
            return "Se elimino la empresa: " + id + ".";
        }else{
            return "Error al eliminar la emrpesa.";
        }
    }
}