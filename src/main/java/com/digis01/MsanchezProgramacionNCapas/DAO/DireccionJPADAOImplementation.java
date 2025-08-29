/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.MsanchezProgramacionNCapas.DAO;

import com.digis01.MsanchezProgramacionNCapas.JPA.Direccion;
import com.digis01.MsanchezProgramacionNCapas.ML.Result;
import com.digis01.MsanchezProgramacionNCapas.JPA.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DireccionJPADAOImplementation implements IDireccionJPADAO {
    
    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetById(int idDireccion) {
        
        Result result = new Result();
        
        try {
            
            Direccion direccionJPA = entityManager.find(Direccion.class, idDireccion);
            //usuarioJPA.Direcciones = new ArrayList<>();

            //usuarioJPA.Direcciones.add(new com.digis01.MsanchezProgramacionNCapas.JPA.Direccion(-1));

            result.object = direccionJPA;

            // entityManager.find(Usuario.class,idUsuario);
            result.correct = true;
            
        } catch (Exception ex) {
            result.correct =  false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }
    
    @Transactional
    @Override
    public Result Update(com.digis01.MsanchezProgramacionNCapas.ML.Usuario usuarioML) {
        Result result = new Result();
        
        try {
            
            Direccion direccionJPA = new Direccion(usuarioML);
            entityManager.merge(direccionJPA);

            result.correct = true;
            
            
        } catch (Exception ex) {
            result.correct =  false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }
    @Transactional
    @Override
    public Result Add(com.digis01.MsanchezProgramacionNCapas.ML.Usuario direccionML) {
        Result result = new Result();

        try {
            //Direccion direccionJPA = entityManager.find(Direccion.class, "IdUsuario");

//            usuarioML.Rol = new Rol();
//            usuarioML.Rol.setIdRol(0);
            Direccion direccionJPA = new Direccion(direccionML);

            entityManager.persist(direccionJPA);

            result.correct = true;

        } catch (Exception ex) {
            result.correct = true;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }


    
}
