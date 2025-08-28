package com.digis01.MsanchezProgramacionNCapas.DAO;

import com.digis01.MsanchezProgramacionNCapas.JPA.Colonia;
import com.digis01.MsanchezProgramacionNCapas.JPA.Direccion;
import com.digis01.MsanchezProgramacionNCapas.JPA.Usuario;
import com.digis01.MsanchezProgramacionNCapas.ML.Result;
import com.digis01.MsanchezProgramacionNCapas.ML.Rol;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioJPADAOImplementation implements IUsuarioJPADAO {

    @Autowired
    private EntityManager entityManager;

    @Autowired //Inyeccion de dependencias
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result GetAll() {

        Result result = new Result();

        try {
            TypedQuery<Usuario> queryUsuario = entityManager.createQuery("FROM Usuario", Usuario.class);
            List<Usuario> usuarios = queryUsuario.getResultList();

            result.objects = new ArrayList<>();

            for (Usuario usuario : usuarios) {
                result.objects.add(new com.digis01.MsanchezProgramacionNCapas.ML.Usuario(usuario));
            }

            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Transactional
    @Override
    public Result Add(com.digis01.MsanchezProgramacionNCapas.ML.Usuario usuarioML) {

        Result result = new Result();

        try {

            Usuario usuarioJPA = new Usuario(usuarioML);

            entityManager.persist(usuarioJPA);

            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Transactional
    @Override
    public Result AgregarDireccion(com.digis01.MsanchezProgramacionNCapas.ML.Direccion direccionML) {
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

    @Transactional
    @Override
    public Result EliminarUsuario(int IdUsuario) {
        Result result = new Result();

        try {

            Usuario usuarioJPA = entityManager.find(Usuario.class, IdUsuario);

            entityManager.remove(usuarioJPA);

            result.correct = true;

        } catch (Exception ex) {
            result.correct = true;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Transactional
    @Override
    public Result EliminarDireccion(int IdDireccion) {
        Result result = new Result();

        try {

            Direccion direccionJPA = entityManager.find(Direccion.class, IdDireccion);

            entityManager.remove(direccionJPA);

            result.correct = true;

        } catch (Exception ex) {
            result.correct = true;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }
    
    @Transactional
    @Override
    public Result EditarUsuario(com.digis01.MsanchezProgramacionNCapas.ML.Usuario usuarioML) {
        Result result = new Result();

        try {

//            Usuario usuarioJPA = new Usuario(usuarioML);

            //Usuario UsuarioJPADos = entityManager.find(Usuario.class, usuarioML.getIdUsuario());
            
            Usuario usuarioJPA = entityManager.find(Usuario.class, usuarioML.getIdUsuario());
            
            usuarioJPA.setNombre(usuarioML.getNombre());
            usuarioJPA.setUserName(usuarioML.getUserName());
            usuarioJPA.setApellidoPaterno(usuarioML.getApellidoPaterno());
            usuarioJPA.setApellidoMaterno(usuarioML.getApellidoMaterno());
            usuarioJPA.setEmail(usuarioML.getEmail());
            usuarioJPA.setPassword(usuarioML.getPassword());
            usuarioJPA.setSexo(usuarioML.getSexo());
            usuarioJPA.setTelefono(usuarioML.getTelefono());
            usuarioJPA.setCelular(usuarioML.getCelular());
            usuarioJPA.setCurp(usuarioML.getCurp());
            usuarioJPA.setImagen(usuarioML.getImagen());
            
            
            //entityManager.remove(usuarioJPA);

            Usuario usuarioJPADos  = entityManager.merge(usuarioJPA);

            result.correct = true;

            

        } catch (Exception ex) {
            result.correct = true;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result GetById(int idUsuario) {
        Result result = new Result();

        try {

            //TypedQuery<Usuario> queryUsuario = entityManager.createQuery("FROM Usuario", Usuario.class);
            //Usuario usuario = queryUsuario.getSingleResult();
            Usuario usuarioJPA = entityManager.find(Usuario.class, idUsuario);
            usuarioJPA.Direcciones = new ArrayList<>();

            usuarioJPA.Direcciones.add(new com.digis01.MsanchezProgramacionNCapas.JPA.Direccion(-1));

            result.object = usuarioJPA;

            // entityManager.find(Usuario.class,idUsuario);
            result.correct = true;

        } catch (Exception ex) {
            result.correct = true;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result EditarDireccion(com.digis01.MsanchezProgramacionNCapas.ML.Direccion direccionML) {
        Result result = new Result();

        try {

//            Usuario usuarioJPA = new Usuario(usuarioML);

            //Usuario UsuarioJPADos = entityManager.find(Usuario.class, usuarioML.getIdUsuario());
            
            Direccion direccionJPA = entityManager.find(Direccion.class, direccionML.getIdDireccion());
            
            direccionJPA.setCalle(direccionML.getCalle());
            direccionJPA.setNumeroInterior(direccionML.getNumeroInterior());
            direccionJPA.setNumeroExterior(direccionML.getNumeroExterior());
            direccionJPA.Colonia = new Colonia();
            direccionJPA.Colonia.setIdColonia(direccionML.Colonia.getIdColonia());
            
            
            //entityManager.remove(usuarioJPA);

            //Usuario usuarioJPADos  = entityManager.merge(usuarioJPA);

            result.correct = true;

            

        } catch (Exception ex) {
            result.correct = true;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

}
