package com.tp1lab4.project.Services.Impl;

import com.tp1lab4.project.Models.Empresa;
import com.tp1lab4.project.Repositories.IEmpresaRepository;
import com.tp1lab4.project.Repositories.INoticiaRepository;
import com.tp1lab4.project.Services.IEmpresaServices;
import com.tp1lab4.project.Services.INoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EmpresaServices implements IEmpresaServices {
    @Autowired
    private IEmpresaRepository empresaRepository;

    @Autowired
    private INoticiaService noticiaService;

    @Override
    public ArrayList<Empresa> getEmpresa() {
        return (ArrayList<Empresa>) this.empresaRepository.findAll();
    }

    @Override
    public Empresa getEmpresaById(Integer id) {
        return this.empresaRepository.findById(id).get();
    }

    @Override
    public Empresa createEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    @Override
    public Empresa updateEmpresa(Integer id, Empresa request) {
        Empresa empresa = this.empresaRepository.findById(id).get();

        empresa.setDenominacion(request.getDenominacion());
        empresa.setTelefono(request.getTelefono());
        empresa.setHorarioAtencion(request.getHorarioAtencion());
        empresa.setQuienesSomos(request.getQuienesSomos());
        empresa.setLatitud(request.getLatitud());
        empresa.setLongitud(request.getLongitud());
        empresa.setDomicilio(request.getDomicilio());
        empresa.setEmail(request.getEmail());

        return this.empresaRepository.save(empresa);
    }

    @Override
    public Boolean deleteEmpresa(Integer id) {
        try{
            this.noticiaService.deleteNoticiaByFk(id);
            this.empresaRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
