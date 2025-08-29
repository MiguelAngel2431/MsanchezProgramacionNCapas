
package com.digis01.MsanchezProgramacionNCapas.DAO;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MunicipioJPADAOImplementation {
    
    @Autowired
    private EntityManager entityManager;
}
