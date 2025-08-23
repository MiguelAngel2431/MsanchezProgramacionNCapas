package com.digis01.MsanchezProgramacionNCapas.DAO;

import com.digis01.MsanchezProgramacionNCapas.ML.Colonia;
import com.digis01.MsanchezProgramacionNCapas.ML.Direccion;
import com.digis01.MsanchezProgramacionNCapas.ML.Estado;
import com.digis01.MsanchezProgramacionNCapas.ML.Municipio;
import com.digis01.MsanchezProgramacionNCapas.ML.Pais;
import com.digis01.MsanchezProgramacionNCapas.ML.Result;
import com.digis01.MsanchezProgramacionNCapas.ML.Rol;
import com.digis01.MsanchezProgramacionNCapas.ML.Usuario;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.CallableStatementCallback;

@Repository // define que una clase tiene logica de base de datos 
public class UsuarioDAOImplementation implements IUsuarioDAO {

    @Autowired //Inyeccion de dependencias
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result GetAll(Usuario usuario) {
        Result result = new Result();

        try {

            jdbcTemplate.execute("{CALL UsuarioDireccionGetAll(?, ?, ?, ?)}", (CallableStatementCallback<Integer>) callableStatement -> {
                
                callableStatement.setString(1, usuario.getNombre());
                callableStatement.setString(2, usuario.getApellidoPaterno());
                callableStatement.setString(3, usuario.getApellidoMaterno());
                callableStatement.registerOutParameter(4, java.sql.Types.REF_CURSOR);

                callableStatement.execute();

                ResultSet resultSet = (ResultSet) callableStatement.getObject(4);

                result.objects = new ArrayList<>();

                while (resultSet.next()) {

                    int idUsuario = resultSet.getInt("IdUsuario");

                    //Esto es un usuario, obitiene el ultimo usuario
                    //ML.Usuario usuariotest = (ML.Usuario) (result.objects.get(result.objects.size() - 1));
                    //Que la lista no sea vacia, que el Id se repita
                    if (!result.objects.isEmpty() && idUsuario == ((Usuario) (result.objects.get(result.objects.size() - 1))).getIdUsuario()) {

                        //Solo vamos a agregar la direccion
                        Direccion direccion = new Direccion();

                        direccion.setIdDireccion(resultSet.getInt("IdDireccion"));
                        direccion.setCalle(resultSet.getString("Calle"));
                        direccion.setNumeroInterior(resultSet.getString("NumeroInterior"));
                        direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));
                        //direccion.setIdColonia(resultSet.getInt("IdColonia"));

                        direccion.Colonia = new Colonia();
                        direccion.Colonia.setIdColonia(resultSet.getInt("IdColonia"));
                        direccion.Colonia.setNombre(resultSet.getString("NombreColonia")); //Alias
                        direccion.Colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));
                        //direccion.Colonia.setIdMunicipio(resultSet.getInt("IdMunicipio"));

                        direccion.Colonia.Municipio = new Municipio();

                        direccion.Colonia.Municipio.setIdMunicipio(resultSet.getInt("IdMunicipio"));
                        direccion.Colonia.Municipio.setNombre(resultSet.getString("NombreMunicipio"));

                        direccion.Colonia.Municipio.Estado = new Estado();

                        direccion.Colonia.Municipio.Estado.setIdEstado(resultSet.getInt("IdEstado"));
                        direccion.Colonia.Municipio.Estado.setNombre(resultSet.getString("NombreEstado"));

                        direccion.Colonia.Municipio.Estado.Pais = new Pais();

                        direccion.Colonia.Municipio.Estado.Pais.setIdPais(resultSet.getInt("IdPais"));
                        direccion.Colonia.Municipio.Estado.Pais.setNombre(resultSet.getString("NombrePais"));

                        //Agregamos la direccion apuntando a la direccion de memoria del usuario
                        ((Usuario) (result.objects.get(result.objects.size() - 1))).Direcciones.add(direccion);

                    } else { //Que la lista sea vacia 

                        Usuario usuarioBD = new Usuario();

                        usuarioBD.setIdUsuario(resultSet.getInt("IdUsuario"));
                        usuarioBD.setUserName(resultSet.getString("UserName"));
                        usuarioBD.setNombre(resultSet.getString("Nombre"));
                        usuarioBD.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
                        usuarioBD.setApellidoMaterno(resultSet.getString("ApellidoMaterno"));
                        usuarioBD.setEmail(resultSet.getString("Email"));
                        usuarioBD.setPassword(resultSet.getString("Password"));
                        usuarioBD.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
                        usuarioBD.setSexo(resultSet.getString("Sexo"));
                        usuarioBD.setTelefono(resultSet.getString("Telefono"));
                        usuarioBD.setCelular(resultSet.getString("Celular"));
                        usuarioBD.setCurp(resultSet.getString("Curp"));
                        usuarioBD.setIdRol(resultSet.getInt("IdRol"));
                        //usuario.setImagen(resultSet.getString("Imagen"));

                        usuarioBD.Rol = new Rol();

                        //Aislamos los datos de la otra tabla (rol)
                        usuarioBD.Rol.setIdRol(resultSet.getInt("IdRol"));
                        usuarioBD.Rol.setNombre(resultSet.getString("NombreRol")); //Alias
                        usuarioBD.setImagen(resultSet.getString("Imagen"));

                        int idDireccion;

                        //Si el numero de direcciones es diferente de cero
                        if ((idDireccion = resultSet.getInt("IdDireccion")) != 0) {

                            usuarioBD.Direcciones = new ArrayList<>();

                            Direccion direccion = new Direccion();

                            direccion.setIdDireccion(resultSet.getInt("IdDireccion"));
                            direccion.setCalle(resultSet.getString("Calle"));
                            direccion.setNumeroInterior(resultSet.getString("NumeroInterior"));
                            direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));
                            //direccion.setIdColonia(resultSet.getInt("IdColonia"));

                            direccion.Colonia = new Colonia();
                            direccion.Colonia.setIdColonia(resultSet.getInt("IdColonia"));
                            direccion.Colonia.setNombre(resultSet.getString("NombreColonia")); //Alias
                            direccion.Colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));
                            //direccion.Colonia.setIdMunicipio(resultSet.getInt("IdMunicipio"));

                            direccion.Colonia.Municipio = new Municipio();

                            direccion.Colonia.Municipio.setIdMunicipio(resultSet.getInt("IdMunicipio"));
                            direccion.Colonia.Municipio.setNombre(resultSet.getString("NombreMunicipio"));

                            direccion.Colonia.Municipio.Estado = new Estado();

                            direccion.Colonia.Municipio.Estado.setIdEstado(resultSet.getInt("IdEstado"));
                            direccion.Colonia.Municipio.Estado.setNombre(resultSet.getString("NombreEstado"));

                            direccion.Colonia.Municipio.Estado.Pais = new Pais();

                            direccion.Colonia.Municipio.Estado.Pais.setIdPais(resultSet.getInt("IdPais"));
                            direccion.Colonia.Municipio.Estado.Pais.setNombre(resultSet.getString("NombrePais"));

                            usuarioBD.Direcciones.add(direccion);

                        }

                        result.objects.add(usuarioBD); //No afecta si la pongo mas arriba, porque el objeto alumno ya esta en la lista y puedo seguir modificando los parametros (paso por refencia)

                    }

                }

                result.correct = true;

                return 1;

            });

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result GetDetail(int idUsuario) {

        Result result = new Result();

        try {
            jdbcTemplate.execute("{CALL UsuarioDireccionGetById(?, ?)}", (CallableStatementCallback<Integer>) callableStatement -> {

                callableStatement.setInt(1, idUsuario);
                callableStatement.registerOutParameter(2, java.sql.Types.REF_CURSOR);

                callableStatement.execute();

                ResultSet resultSet = (ResultSet) callableStatement.getObject(2);

                //Ya no se pone un while porque solo es un usuario
                if (resultSet.next()) {

                    Usuario usuario = new Usuario();

                    usuario.setIdUsuario(resultSet.getInt("IdUsuario"));
                    usuario.setUserName(resultSet.getString("UserName"));
                    usuario.setNombre(resultSet.getString("Nombre"));
                    usuario.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
                    usuario.setApellidoMaterno(resultSet.getString("ApellidoMaterno"));
                    usuario.setEmail(resultSet.getString("Email"));
                    usuario.setPassword(resultSet.getString("Password"));
                    usuario.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
                    usuario.setSexo(resultSet.getString("Sexo"));
                    usuario.setTelefono(resultSet.getString("Telefono"));
                    usuario.setCelular(resultSet.getString("Celular"));
                    usuario.setCurp(resultSet.getString("Curp"));
                    usuario.setIdRol(resultSet.getInt("IdRol"));

                    usuario.Rol = new Rol();

                    //Aislamos los datos de la otra tabla (rol)
                    usuario.Rol.setIdRol(resultSet.getInt("IdRol"));
                    usuario.Rol.setNombre(resultSet.getString("NombreRol")); //Alias
                    usuario.setImagen(resultSet.getString("Imagen"));
                    int idDireccion;

                    //Si el numero de direcciones es diferente de cero
                    if ((idDireccion = resultSet.getInt("IdDireccion")) != 0) {

                        usuario.Direcciones = new ArrayList<>();

                        do {

                            Direccion direccion = new Direccion();

                            direccion.setIdDireccion(resultSet.getInt("IdDireccion"));
                            direccion.setCalle(resultSet.getString("Calle"));
                            direccion.setNumeroInterior(resultSet.getString("NumeroInterior"));
                            direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));
                            //direccion.setIdColonia(resultSet.getInt("IdColonia"));

                            direccion.Colonia = new Colonia();
                            direccion.Colonia.setIdColonia(resultSet.getInt("IdColonia"));
                            direccion.Colonia.setNombre(resultSet.getString("NombreColonia")); //Alias
                            direccion.Colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));
                            //direccion.Colonia.setIdMunicipio(resultSet.getInt("IdMunicipio"));

                            direccion.Colonia.Municipio = new Municipio();

                            direccion.Colonia.Municipio.setIdMunicipio(resultSet.getInt("IdMunicipio"));
                            direccion.Colonia.Municipio.setNombre(resultSet.getString("NombreMunicipio"));

                            direccion.Colonia.Municipio.Estado = new Estado();

                            direccion.Colonia.Municipio.Estado.setIdEstado(resultSet.getInt("IdEstado"));
                            direccion.Colonia.Municipio.Estado.setNombre(resultSet.getString("NombreEstado"));

                            direccion.Colonia.Municipio.Estado.Pais = new Pais();

                            direccion.Colonia.Municipio.Estado.Pais.setIdPais(resultSet.getInt("IdPais"));
                            direccion.Colonia.Municipio.Estado.Pais.setNombre(resultSet.getString("NombrePais"));

                            usuario.Direcciones.add(direccion);

                        } while (resultSet.next());

                    }

                    result.object = usuario;

                }

                result.correct = true;

                return 1;
            });

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result Add(Usuario usuario) {

        Result result = new Result();

        try {
            result.correct = jdbcTemplate.execute("CALL UsuarioDireccionAdd(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", (CallableStatementCallback<Boolean>) callableStatement -> {

                callableStatement.setString(1, usuario.getUserName());
                callableStatement.setString(2, usuario.getNombre());
                callableStatement.setString(3, usuario.getApellidoPaterno());
                callableStatement.setString(4, usuario.getApellidoMaterno());
                callableStatement.setString(5, usuario.getEmail());
                callableStatement.setString(6, usuario.getPassword());
                callableStatement.setDate(7, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
                callableStatement.setString(8, usuario.getSexo());
                callableStatement.setString(9, usuario.getTelefono());
                callableStatement.setString(10, usuario.getCelular());
                callableStatement.setString(11, usuario.getCurp());
                callableStatement.setInt(12, usuario.Rol.getIdRol());
                callableStatement.setString(13, usuario.getImagen());

                callableStatement.setString(14, usuario.Direcciones.get(0).getCalle());
                callableStatement.setString(15, usuario.Direcciones.get(0).getNumeroInterior());
                callableStatement.setString(16, usuario.Direcciones.get(0).getNumeroExterior());

                callableStatement.setInt(17, usuario.Direcciones.get(0).Colonia.getIdColonia());

                int isCorrect = callableStatement.executeUpdate();

                if (isCorrect == -1) {
                    return true;
                }

                return false;

            });

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result GetById(int idUsuario) {
        Result result = new Result();

        try {
            jdbcTemplate.execute("{CALL UsuarioGetById(?, ?)}", (CallableStatementCallback<Integer>) callableStatement -> {

                callableStatement.setInt(1, idUsuario);
                callableStatement.registerOutParameter(2, java.sql.Types.REF_CURSOR);

                callableStatement.execute();

                ResultSet resultSet = (ResultSet) callableStatement.getObject(2);

                //Ya no se pone un while porque solo es un usuario
                if (resultSet.next()) {

                    Usuario usuario = new Usuario();

                    usuario.setIdUsuario(resultSet.getInt("IdUsuario"));
                    usuario.setUserName(resultSet.getString("UserName"));
                    usuario.setNombre(resultSet.getString("Nombre"));
                    usuario.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
                    usuario.setApellidoMaterno(resultSet.getString("ApellidoMaterno"));
                    usuario.setEmail(resultSet.getString("Email"));
                    usuario.setPassword(resultSet.getString("Password"));
                    usuario.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
                    usuario.setSexo(resultSet.getString("Sexo"));
                    usuario.setTelefono(resultSet.getString("Telefono"));
                    usuario.setCelular(resultSet.getString("Celular"));
                    usuario.setCurp(resultSet.getString("Curp"));
                    usuario.setIdRol(resultSet.getInt("IdRol"));
                    //usuario.setImagen(resultSet.getString("Imagen"));

                    usuario.Direcciones = new ArrayList<>();

                    usuario.Direcciones.add(new Direccion(-1));

                    result.object = usuario;

                }

                result.correct = true;

                return 1;
            });

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result DireccionGetByIdDireccion(int idDireccion) {
        Result result = new Result();

        try {
            jdbcTemplate.execute("{CALL DireccionGetByIdDireccionJoin(?, ?)}", (CallableStatementCallback<Integer>) callableStatement -> {

                callableStatement.setInt(1, idDireccion);
                callableStatement.registerOutParameter(2, java.sql.Types.REF_CURSOR);

                callableStatement.execute();

                ResultSet resultSet = (ResultSet) callableStatement.getObject(2); //Casteo

                result.objects = new ArrayList<>(); //Se pone afuera del while porque si no, va a estar reescribiendo la lista

                while (resultSet.next()) {

                    Direccion direccion = new Direccion();

                    direccion.setIdUsuario(resultSet.getInt("IdUsuario"));
                    direccion.setIdDireccion(resultSet.getInt("IdDireccion"));
                    direccion.setCalle(resultSet.getString("Calle"));
                    direccion.setNumeroInterior(resultSet.getString("NumeroInterior"));
                    direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));

                    direccion.Colonia = new Colonia();
                    direccion.Colonia.setIdColonia(resultSet.getInt("IdColonia"));
                    direccion.Colonia.setNombre(resultSet.getString("NombreColonia")); //Alias
                    direccion.Colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));
                    //direccion.Colonia.setIdMunicipio(resultSet.getInt("IdMunicipio"));

                    direccion.Colonia.Municipio = new Municipio();

                    direccion.Colonia.Municipio.setIdMunicipio(resultSet.getInt("IdMunicipio"));
                    direccion.Colonia.Municipio.setNombre(resultSet.getString("NombreMunicipio"));

                    direccion.Colonia.Municipio.Estado = new Estado();

                    direccion.Colonia.Municipio.Estado.setIdEstado(resultSet.getInt("IdEstado"));
                    direccion.Colonia.Municipio.Estado.setNombre(resultSet.getString("NombreEstado"));

                    direccion.Colonia.Municipio.Estado.Pais = new Pais();

                    direccion.Colonia.Municipio.Estado.Pais.setIdPais(resultSet.getInt("IdPais"));
                    direccion.Colonia.Municipio.Estado.Pais.setNombre(resultSet.getString("NombrePais"));

                    //Recuperar los dropdowns en cascada

                    /* direccion.Colonia.Municipio = new Municipio();
                
                direccion.Colonia.Municipio.setIdMunicipio(resultSet.getInt("IdMunicipio"));
                
                direccion.Colonia.Municipio.Estado = new Estado();
                
                direccion.Colonia.Municipio.Estado.setIdEstado(resultSet.getInt("IdEstado"));
                
                direccion.Colonia.Municipio.Estado.Pais = new Pais();
                
                direccion.Colonia.Municipio.Estado.Pais.setIdPais(resultSet.getInt("IdPais"));*/
                    result.object = direccion;
                }

                result.correct = true;

                return 1;
            });

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result GetId(int idUsuario) {
        Result result = new Result();

        try {
            jdbcTemplate.execute("{CALL UsuarioGetById(?, ?)}", (CallableStatementCallback<Integer>) callableStatement -> {

                callableStatement.setInt(1, idUsuario);
                callableStatement.registerOutParameter(2, java.sql.Types.REF_CURSOR);

                callableStatement.execute();

                ResultSet resultSet = (ResultSet) callableStatement.getObject(2);

                //Ya no se pone un while porque solo es un usuario
                if (resultSet.next()) {

                    Usuario usuario = new Usuario();

                    usuario.setIdUsuario(resultSet.getInt("IdUsuario"));
                    /*usuario.setUserName(resultSet.getString("UserName"));
                    usuario.setNombre(resultSet.getString("Nombre"));
                    usuario.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
                    usuario.setApellidoMaterno(resultSet.getString("ApellidoMaterno"));
                    usuario.setEmail(resultSet.getString("Email"));
                    usuario.setPassword(resultSet.getString("Password"));
                    usuario.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
                    usuario.setSexo(resultSet.getString("Sexo"));
                    usuario.setTelefono(resultSet.getString("Telefono"));
                    usuario.setCelular(resultSet.getString("Celular"));
                    usuario.setCurp(resultSet.getString("Curp"));
                    usuario.setIdRol(resultSet.getInt("IdRol"));*/

                    usuario.Direcciones = new ArrayList<>();

                    usuario.Direcciones.add(new Direccion(0));

                    result.object = usuario;

                }

                result.correct = true;

                return 1;
            });

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result EditarDireccion(Usuario usuario) {
        Result result = new Result();

        try {
            jdbcTemplate.execute("{CALL DireccionUpdate(?, ?, ?, ?, ?, ?)}", (CallableStatementCallback<Integer>) callableStatement -> {

                callableStatement.setString(2, usuario.Direcciones.get(0).getCalle());
                callableStatement.setString(3, usuario.Direcciones.get(0).getNumeroInterior());
                callableStatement.setString(4, usuario.Direcciones.get(0).getNumeroExterior());
                callableStatement.setInt(5, usuario.Direcciones.get(0).Colonia.getIdColonia());

                callableStatement.setInt(6, usuario.getIdUsuario());
                
                callableStatement.setInt(1, usuario.Direcciones.get(0).getIdDireccion());

                callableStatement.execute();

                int status = callableStatement.getUpdateCount();

                if (status == -1) {
                    result.correct = true;
                }

                return 1;
            });

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result EditarUsuario(Usuario usuario) {
        Result result = new Result();

        try {
            jdbcTemplate.execute("{CALL UsuarioUpdate(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}", (CallableStatementCallback<Integer>) callableStatement -> {

                callableStatement.setString(2, usuario.getUserName());
                callableStatement.setString(3, usuario.getNombre());
                callableStatement.setString(4, usuario.getApellidoPaterno());
                callableStatement.setString(5, usuario.getApellidoMaterno());
                callableStatement.setString(6, usuario.getEmail());
               // callableStatement.setString(7, usuario.getPassword());
                callableStatement.setDate(7, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
                callableStatement.setString(8, usuario.getSexo());
                callableStatement.setString(9, usuario.getTelefono());
                callableStatement.setString(10, usuario.getCelular());
                callableStatement.setString(11, usuario.getCurp());
                callableStatement.setInt(12, usuario.Rol.getIdRol());
                callableStatement.setString(13, usuario.getImagen());
                
                callableStatement.setInt(1, usuario.getIdUsuario());

                callableStatement.execute();

                int status = callableStatement.getUpdateCount();

                if (status == -1) {
                    result.correct = true;
                }

                return 1;
            });

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result AgregarDireccion(Usuario usuario) {
        Result result = new Result();

        try {
            result.correct = jdbcTemplate.execute("CALL DireccionAdd(?, ?, ?, ?, ?)", (CallableStatementCallback<Boolean>) callableStatement -> {

                callableStatement.setString(1, usuario.Direcciones.get(0).getCalle());
                callableStatement.setString(2, usuario.Direcciones.get(0).getNumeroInterior());
                callableStatement.setString(3, usuario.Direcciones.get(0).getNumeroExterior());
                callableStatement.setInt(4, usuario.Direcciones.get(0).Colonia.getIdColonia());
                callableStatement.setInt(5, usuario.getIdUsuario());

                int isCorrect = callableStatement.executeUpdate();

                if (isCorrect == -1) {
                    return true;
                }

                return false;

            });

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result EliminarDireccion(int IdDireccion) {
        Result result = new Result();

        try {
            result.correct = jdbcTemplate.execute("CALL DireccionDelete(?)", (CallableStatementCallback<Boolean>) callableStatement -> {

                //callableStatement.setInt(1, usuario.Direcciones.get(0).getIdDireccion());
                callableStatement.setInt(1, IdDireccion);
                
                int isCorrect = callableStatement.executeUpdate();

                if (isCorrect == -1) {
                    return true;
                }

                return false;

            });

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result EliminarUsuario(int IdUsuario) {
        Result result = new Result();

        try {
            result.correct = jdbcTemplate.execute("CALL UsuarioDelete(?)", (CallableStatementCallback<Boolean>) callableStatement -> {

                //callableStatement.setInt(1, usuario.Direcciones.get(0).getIdDireccion());
                callableStatement.setInt(1, IdUsuario);
                
                int isCorrect = callableStatement.executeUpdate();

                if (isCorrect == -1) {
                    return true;
                }

                return false;

            });

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

}
