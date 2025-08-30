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
    Result GetAll(com.digis01.MsanchezProgramacionNCapas.ML.Usuario usuario);
    Result GetById(int idUsuario);
    Result GetDetail(int idUsuario);
    Result Add(com.digis01.MsanchezProgramacionNCapas.ML.Usuario usuario);
    Result EliminarUsuario(int IdUsuario);
    Result EditarUsuario(com.digis01.MsanchezProgramacionNCapas.ML.Usuario usuario);
    Result BajaLogica(int IdUsuario);
    
    //Result DireccionGetByIdDireccion(int idDireccion);
    //Result EditarDireccion(com.digis01.MsanchezProgramacionNCapas.ML.Direccion direccion);
    
}   
