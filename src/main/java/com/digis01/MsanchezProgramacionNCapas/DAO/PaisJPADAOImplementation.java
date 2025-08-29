
package com.digis01.MsanchezProgramacionNCapas.DAO;

import com.digis01.MsanchezProgramacionNCapas.JPA.Pais;
import com.digis01.MsanchezProgramacionNCapas.JPA.Rol;
import com.digis01.MsanchezProgramacionNCapas.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PaisJPADAOImplementation implements IPaisJPADAO {
    
    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetAll() {
        Result result = new Result();

        try {
            TypedQuery<Pais> queryUsuario = entityManager.createQuery("FROM Pais", Pais.class);
            List<Pais> paises = queryUsuario.getResultList();

            result.objects = new ArrayList<>();

            for (Pais pais : paises) {
                result.objects.add(new com.digis01.MsanchezProgramacionNCapas.ML.Pais(pais));
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
