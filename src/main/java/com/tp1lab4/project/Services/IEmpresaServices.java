package com.tp1lab4.project.Services;

import com.tp1lab4.project.Models.Empresa;

import java.util.ArrayList;
import java.util.Optional;

public interface IEmpresaServices {
    ArrayList<Empresa> getEmpresa();
    Empresa getEmpresaById(Integer id);
    Empresa createEmpresa(Empresa empresa);
    Empresa updateEmpresa(Integer id, Empresa request);
    Boolean deleteEmpresa(Integer id);
}
