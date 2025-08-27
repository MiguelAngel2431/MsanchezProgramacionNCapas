
package com.digis01.MsanchezProgramacionNCapas.DAO;

import com.digis01.MsanchezProgramacionNCapas.JPA.Usuario;
import com.digis01.MsanchezProgramacionNCapas.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.TypedQuery;
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

    @Override
    public Result Add(com.digis01.MsanchezProgramacionNCapas.ML.Usuario usuario) {
        
        Result result  = new Result();
        
        try {
            //List<Usuario> usuarios = entityManager.createNamedStoredProcedureQuery("Usuarios.Usuarios.addUserDrections").
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("UsuarioDireccionAdd");
            
            query.registerStoredProcedureParameter("pUserName", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("pNombre", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("pApellidoPaterno", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("pApellidoMaterno", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("pEmail", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("pPassword", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("pFechaNacimiento", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("pSexo", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("pTelefono", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("pCelular", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("pCurp", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("pIdRol", int.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("pImagen", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("pCalle", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("pNumeroInterior", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("pNumeroExterior", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("pIdColonia", int.class, ParameterMode.IN);
            
            query.setParameter("pUserName", usuario.getUserName());
            query.setParameter("pNombre", usuario.getNombre());
            query.setParameter("pApellidoPaterno", usuario.getApellidoPaterno());
            query.setParameter("pApellidoMaterno", usuario.getApellidoMaterno());
            query.setParameter("pEmail", usuario.getEmail());
            query.setParameter("pPassword", usuario.getPassword());
            

            query.setParameter("pFechaNacimiento",usuario.getFechaNacimiento());
            query.setParameter("pSexo", usuario.getSexo());
            query.setParameter("pTelefono", usuario.getTelefono());
            query.setParameter("pCelular", usuario.getCelular());
            query.setParameter("pCurp", usuario.getCurp());
            query.setParameter("pIdRol", usuario.Rol.getIdRol());
            query.setParameter("pImagen", usuario.getImagen());
            query.setParameter("pCalle", usuario.Direcciones.get(0).getCalle());
            query.setParameter("pNumeroInterior", usuario.Direcciones.get(0).getNumeroInterior());
            query.setParameter("pNumeroExterior", usuario.Direcciones.get(0).getNumeroExterior());
            query.setParameter("pIdColonia", usuario.Direcciones.get(0).Colonia.getIdColonia());
            
            query.execute();
            
            result.correct = true;
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }

    
    
}
