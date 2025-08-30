
package com.digis01.MsanchezProgramacionNCapas.DAO;

import com.digis01.MsanchezProgramacionNCapas.JPA.Municipio;
import com.digis01.MsanchezProgramacionNCapas.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MunicipioJPADAOImplementation implements IMunicipioJPADAO {
    
    @Autowired
    private EntityManager entityManager;

    @Override
    public Result MunicipioByIdEstado(int IdEstado) {
        Result result = new Result();

        try {
            TypedQuery<Municipio> queryMunicipios = entityManager.createQuery("FROM Municipio where Estado.IdEstado = :IdEstado ", Municipio.class);
            queryMunicipios.setParameter("IdEstado", IdEstado);
            List<Municipio> municipios = queryMunicipios.getResultList();

            result.objects = new ArrayList<>();

            for (Municipio municipio : municipios) {
                result.objects.add(new com.digis01.MsanchezProgramacionNCapas.ML.Municipio(municipio));
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
