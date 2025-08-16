
package com.digis01.MsanchezProgramacionNCapas.DAO;

import com.digis01.MsanchezProgramacionNCapas.ML.Colonia;
import com.digis01.MsanchezProgramacionNCapas.ML.Direccion;
import com.digis01.MsanchezProgramacionNCapas.ML.Result;
import com.digis01.MsanchezProgramacionNCapas.ML.Usuario;

//Firma de metodos
public interface IUsuarioDAO {
    Result GetAll(); // metodo abstracto, es decir, no lleva implementación
    Result GetById(int idUsuario); //Aqui es donde la direccion la pues en -1
    Result GetId(int idUsuario); //Aqui es donde la direccion la pues en -1
    Result GetDetail(int idUsuario); //metodo para obtener el detall 
    Result Add(Usuario usuario);
    Result DireccionGetByIdDireccion(int idDireccion);
    
    
}
