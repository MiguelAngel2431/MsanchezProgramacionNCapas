
package com.digis01.MsanchezProgramacionNCapas.DAO;


import com.digis01.MsanchezProgramacionNCapas.JPA.Estado;
import com.digis01.MsanchezProgramacionNCapas.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EstadoJPADAOImplementation implements IEstadoJPADAO {
    
    @Autowired
    private EntityManager entityManager;

    @Override
    public Result EstadoByIdPais(int IdPais) {
        Result result = new Result();

        try {
            TypedQuery<Estado> queryEstados = entityManager.createQuery("FROM Estado where Pais.IdPais = :IdPais ", Estado.class);
            List<Estado> estados = queryEstados.getResultList();

            result.objects = new ArrayList<>();

            for (Estado estado : estados) {
                result.objects.add(new com.digis01.MsanchezProgramacionNCapas.ML.Estado(estado));
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
