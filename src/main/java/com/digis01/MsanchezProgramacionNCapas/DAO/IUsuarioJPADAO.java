/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digis01.MsanchezProgramacionNCapas.DAO;

import com.digis01.MsanchezProgramacionNCapas.ML.Result;

/**
 *
 * @author Alien 5
 */
public interface IUsuarioJPADAO {
    Result GetAll();
    Result GetById(int idUsuario);
    Result Add(com.digis01.MsanchezProgramacionNCapas.ML.Usuario usuario);
    Result EliminarUsuario(int IdUsuario);
    Result EditarUsuario(com.digis01.MsanchezProgramacionNCapas.ML.Usuario usuario);
    
    Result AgregarDireccion(com.digis01.MsanchezProgramacionNCapas.ML.Direccion direccion);
    Result EliminarDireccion(int IdDireccion);
    Result EditarDireccion(com.digis01.MsanchezProgramacionNCapas.ML.Direccion direccion);
    
}   
