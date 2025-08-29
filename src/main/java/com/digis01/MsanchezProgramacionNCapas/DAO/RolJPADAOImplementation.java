
package com.digis01.MsanchezProgramacionNCapas.DAO;

import com.digis01.MsanchezProgramacionNCapas.JPA.Rol;
import com.digis01.MsanchezProgramacionNCapas.JPA.Usuario;
import com.digis01.MsanchezProgramacionNCapas.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RolJPADAOImplementation implements IRolJPADAO {
    
    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetAll() {
        Result result = new Result();

        try {
            TypedQuery<Rol> queryUsuario = entityManager.createQuery("FROM Rol", Rol.class);
            List<Rol> roles = queryUsuario.getResultList();

            result.objects = new ArrayList<>();

            for (Rol rol : roles) {
                result.objects.add(new com.digis01.MsanchezProgramacionNCapas.ML.Rol(rol));
            }

            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }
    
}
