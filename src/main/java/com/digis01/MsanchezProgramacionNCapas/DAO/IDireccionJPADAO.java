
package com.digis01.MsanchezProgramacionNCapas.DAO;

import com.digis01.MsanchezProgramacionNCapas.ML.Result;

public interface IDireccionJPADAO {
    Result GetById(int idDireccion);
    Result Update(com.digis01.MsanchezProgramacionNCapas.ML.Usuario usuarioML);
    Result Add(com.digis01.MsanchezProgramacionNCapas.ML.Usuario direccion);
}
