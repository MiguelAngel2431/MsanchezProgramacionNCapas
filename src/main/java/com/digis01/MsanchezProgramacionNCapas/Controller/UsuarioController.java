package com.digis01.MsanchezProgramacionNCapas.Controller;

import com.digis01.MsanchezProgramacionNCapas.DAO.ColoniaDAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.DAO.EstadoDAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.DAO.MunicipioDAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.DAO.PaisDAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.DAO.RolDAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.DAO.UsuarioDAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.ML.Colonia;
import com.digis01.MsanchezProgramacionNCapas.ML.Direccion;
import com.digis01.MsanchezProgramacionNCapas.ML.ErrorCM;
import com.digis01.MsanchezProgramacionNCapas.ML.Estado;
import com.digis01.MsanchezProgramacionNCapas.ML.Municipio;
import com.digis01.MsanchezProgramacionNCapas.ML.Pais;
import com.digis01.MsanchezProgramacionNCapas.ML.Result;
import com.digis01.MsanchezProgramacionNCapas.ML.Rol;
import com.digis01.MsanchezProgramacionNCapas.ML.Usuario;
import jakarta.validation.Valid;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired //Inyeccion de dependencias
    private UsuarioDAOImplementation usuarioDAOImplementation;

    @Autowired //Inyeccion de dependencias
    private RolDAOImplementation rolDAOImplementation;

    @Autowired //Inyeccion de dependencias
    private PaisDAOImplementation paisDAOImplementation;

    @Autowired //Inyeccion de dependencias
    private EstadoDAOImplementation estadoDAOImplementation;

    @Autowired //Inyeccion de dependencias
    private MunicipioDAOImplementation municipioDAOImplementation;

    @Autowired //Inyeccion de dependencias
    private ColoniaDAOImplementation coloniaDAOImplementation;

    //@GetMapping // verbo http GET, POST, PUT, DELETE, PATCH
    //Metodo para retornar todos los usarios a la vista
    @GetMapping
    public String Index(Model model) {
        Result result = usuarioDAOImplementation.GetAll();

        if (result.correct) {
            model.addAttribute("usuarios", result.objects);
        } else {
            model.addAttribute("usuarios", null);
        }

        return "UsuarioIndex"; //Retornamos hacia la vista UsuarioIndex.html

    }

    //Metodo para retornar los datos de un usuario especifico a la vista
    /*@GetMapping("usuarioDetail/{idUsuario}")
    public String UsuarioDetail(@PathVariable int idUsuario, Model model) {

        Result result = usuarioDAOImplementation.GetDetail(idUsuario);

        if (result.correct) {
            model.addAttribute("usuario", result.object);
        } else {
            model.addAttribute("usuario", null);
        }

        return "UsuarioDetail"; //Retornamos hacia la vista UsuarioDetail.html
    }*/

 /*@GetMapping("add")
    public String Add(Model model) {

        //Result result = rolDAOImplementation.GetAll();
        model.addAttribute("roles", rolDAOImplementation.GetAll().objects);

        model.addAttribute("paises", paisDAOImplementation.GetAll().objects);

        //Creamos un usario para que pueda escribir en el
        model.addAttribute("Usuario", new Usuario());

        return "UsuarioForm";
    }*/
    @GetMapping("/action/{IdUsuario}")
    public String Add(Model model, @PathVariable("IdUsuario") int idUsuario) {

        if (idUsuario == 0) { //Manda al formulario completo (en blanco)
            //Result result = rolDAOImplementation.GetAll();
            model.addAttribute("roles", rolDAOImplementation.GetAll().objects);

            model.addAttribute("paises", paisDAOImplementation.GetAll().objects);

            //Creamos un usario para que pueda escribir en el
            Usuario usuario = new Usuario();
            usuario.Direcciones = new ArrayList<>();
            usuario.Direcciones.add(new Direccion());
            model.addAttribute("Usuario", usuario);

            return "UsuarioForm";

        } else { //Manda a la vista del detalle del Usuario
            Result result = usuarioDAOImplementation.GetDetail(idUsuario);

            if (result.correct) {
                model.addAttribute("usuario", result.object);
            } else {
                model.addAttribute("usuario", null);
            }

            return "UsuarioDetail"; //Retornamos hacia la vista UsuarioDetail.html
        }

    }

    @GetMapping("formEditable")
    public String formEditable(
            @RequestParam int IdUsuario,
            @RequestParam(required = false) Integer IdDireccion,
            Model model) {

        //return "UsuarioForm";
        if (IdDireccion == null) { //Editar usuario

            Result result = usuarioDAOImplementation.GetById(IdUsuario);

            if (result.correct) {
                model.addAttribute("Usuario", result.object);
            } else {
                model.addAttribute("Usuario", null);
            }

            model.addAttribute("roles", rolDAOImplementation.GetAll().objects);

            model.addAttribute("paises", paisDAOImplementation.GetAll().objects);

            //model.addAttribute("Usuario", new Usuario());
        } else if (IdDireccion == 0) { //Agregar direccion

            Result result = usuarioDAOImplementation.GetId(IdUsuario);

            if (result.correct) {
                model.addAttribute("Usuario", result.object);
            } else {
                model.addAttribute("Usuario", null);
            }

            //Recuperamos el Id del usuario por medio del request param
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(IdUsuario);
            usuario.Direcciones = new ArrayList<>();
            usuario.Direcciones.add(new Direccion());

            model.addAttribute("Usuario", usuario);
            model.addAttribute("roles", rolDAOImplementation.GetAll().objects);

            model.addAttribute("paises", paisDAOImplementation.GetAll().objects);

            //model.addAttribute("Usuario", new Usuario());
        } else {// Editar direccion

            Result result = usuarioDAOImplementation.DireccionGetByIdDireccion(IdDireccion);

            if (result.correct) {

                Usuario usuario = new Usuario();
                usuario.setIdUsuario(IdUsuario);
                usuario.setDirecciones(new ArrayList<>());
                usuario.getDirecciones().add((Direccion) result.object);

                model.addAttribute("Usuario", usuario);
                model.addAttribute("paises", paisDAOImplementation.GetAll().objects);
//                estadoDAOImplementation.EstadoByIdPais(usuario.Direcciones.get(0).Colonia.Municipio.Estado.Pais.getIdPais())
            } else {
                model.addAttribute("Usuario", null);
            }

            model.addAttribute("roles", rolDAOImplementation.GetAll().objects);

            //model.addAttribute("paises", paisDAOImplementation.GetAll().objects);
            //Creamos un usario para que pueda escribir en el
            //model.addAttribute("Usuario", new Usuario());
        }

        return "UsuarioForm";

    }

    //Proceso de agregado
    @PostMapping("add")
    public String Add(@Valid
            @ModelAttribute("Usuario") Usuario usuario,
            BindingResult bindingResult,
            Model model,
            @RequestParam("imagenFile") MultipartFile imagen) {

        if (usuario.getIdUsuario() == 0) { //Agregar usuario
            //Si bindingResult tiene errores...
            if (bindingResult.hasErrors()) {
                model.addAttribute("Usuario", usuario);

                //Volver a pintar la lista de roles
                model.addAttribute("roles", rolDAOImplementation.GetAll().objects);

                return "UsuarioForm";
            } else {
                
                //Imagen
                if (imagen != null) {
                    String nombre = imagen.getOriginalFilename();
                    //archivo.jpg
                    //[archivo,jpg]
                    String extension = nombre.split("\\.")[1];
                    if (extension.equals("jpg")) {
                        try {
                            byte[] bytes = imagen.getBytes();
                            String base64Image = Base64.getEncoder().encodeToString(bytes);
                            usuario.setImagen(base64Image);
                        } catch (Exception ex) {
                            System.out.println("Error");
                        }

                    }
                }

                //Autoinferencia
                Result result = usuarioDAOImplementation.Add(usuario);

                return "redirect:/usuario";
            }
        }

        if (usuario.getIdUsuario() > 0) {
            if (usuario.Direcciones.get(0).getIdDireccion() == -1) { //Editar usuario
                //Si bindingResult tiene errores...
                //if (bindingResult.hasErrors()) {
                model.addAttribute("Usuario", usuario);

                //Volver a pintar la lista de roles
                model.addAttribute("roles", rolDAOImplementation.GetAll().objects);

                //return "UsuarioForm";
                //} else {
                
                //Imagen
                if (imagen != null) {
                    String nombre = imagen.getOriginalFilename();
                    //archivo.jpg
                    //[archivo,jpg]
                    String extension = nombre.split("\\.")[1];
                    if (extension.equals("jpg")) {
                        try {
                            byte[] bytes = imagen.getBytes();
                            String base64Image = Base64.getEncoder().encodeToString(bytes);
                            usuario.setImagen(base64Image);
                        } catch (Exception ex) {
                            System.out.println("Error");
                        }

                    }
                }
                
                //Autoinferencia
                Result result = usuarioDAOImplementation.EditarUsuario(usuario);

                return "redirect:/usuario";
                //}

            } else if (usuario.Direcciones.get(0).getIdDireccion() == 0) { //Agregar direccion
                //Si bindingResult tiene errores...
                //if (bindingResult.hasErrors()) {
                model.addAttribute("Usuario", usuario);

                //return "UsuarioForm";
                //} else {
                //Autoinferencia
                Result result = usuarioDAOImplementation.AgregarDireccion(usuario);

                return "redirect:/usuario";
                //}
            } else { //Editar direccion

                //Autoinferencia
                Result result = usuarioDAOImplementation.EditarDireccion(usuario);

                return "redirect:/usuario";

            }
        }

        //Si bindingResult tiene errores...
        if (bindingResult.hasErrors()) {
            model.addAttribute("Usuario", usuario);

            //Volver a pintar la lista de roles
            model.addAttribute("roles", rolDAOImplementation.GetAll().objects);

            return "UsuarioForm";
        } else {
            //Autoinferencia
            Result result = usuarioDAOImplementation.Add(usuario);

            return "redirect:/usuario";
        }
    }

    //Proceso de eliminado de direccion
    @GetMapping("/delete/{IdDireccion}")
    public String Delete(Model model, @PathVariable("IdDireccion") int idDireccion) {

        Result result = usuarioDAOImplementation.EliminarDireccion(idDireccion);

        return "redirect:/usuario";
    }

    //Proceso de eliminado de usuario
    @GetMapping("/EliminarUsuario/{IdUsuario}")
    public String EliminarUsuario(Model model, @PathVariable("IdUsuario") int idUsuario) {

        Result result = usuarioDAOImplementation.EliminarUsuario(idUsuario);

        return "redirect:/usuario";
    }

    //Proceso de agregado
    /*@PostMapping("add")
    public String Add(@Valid
            @ModelAttribute("Usuario") Usuario usuario,
            BindingResult bindingResult,
            Model model) {

        //Si bindingResult tiene errores...
        if (bindingResult.hasErrors()) {
            model.addAttribute("Usuario", usuario);

            //Volver a pintar la lista de roles
            model.addAttribute("roles", rolDAOImplementation.GetAll().objects);

            return "UsuarioForm";
        } else {
            //Autoinferencia
            Result result = usuarioDAOImplementation.Add(usuario);

            return "redirect:/usuario";
        }
    }*/
    //DropdownList Pais
    @GetMapping("getEstadosByIdPais/{IdPais}")
    @ResponseBody //Retorna un dato estructurado - JSON
    public Result EstadoByIdPais(@PathVariable int IdPais) {

        return estadoDAOImplementation.EstadoByIdPais(IdPais);
    }

    //DropdownList Estado
    @GetMapping("getMunicipiosByIdEstado/{IdEstado}")
    @ResponseBody //Retorna un dato estructurado - JSON
    public Result MunicipioByIdEstado(@PathVariable int IdEstado) {

        return municipioDAOImplementation.MunicipioByIdEstado(IdEstado);
    }

    //DropdownList Municipio
    @GetMapping("getColoniasByIdMunicipio/{IdMunicipio}")
    @ResponseBody //Retorna un dato estructurado - JSON
    public Result ColoniaByIdMunicipio(@PathVariable int IdMunicipio) {

        return coloniaDAOImplementation.ColoniaByIdMunicipio(IdMunicipio);
    }
    
    @GetMapping("cargamasiva")
    public String CargaMasiva() {
        return "CargaMasiva";
    }
    
    @PostMapping("cargamasiva")
    public String CargaMasiva(@RequestParam("archivo") MultipartFile file) {
        
        if (file.getOriginalFilename().split("\\.")[1].equals("txt")) {
            List<Usuario> usuarios = ProcesarTXT(file);
            List<ErrorCM> errores = ValidarDatos(usuarios);
            
            
        } else {
            //Excel
        }
        
        return "CargaMasiva";
        
    }
    
    private List<Usuario> ProcesarTXT(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            
            String linea = "";
            List<Usuario> usuarios = new ArrayList<>();
            
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
            
            //Mientras haya usuarios... 
            while ((linea = bufferedReader.readLine()) != null) {
                String[] campos = linea.split("\\|");
                Usuario usuario = new Usuario();
                usuario.setUserName(campos[0]);
                usuario.setNombre(campos[1]);
                usuario.setApellidoPaterno(campos[2]);
                usuario.setApellidoMaterno(campos[3]);
                usuario.setEmail(campos[4]);
                usuario.setPassword(campos[5]);
                usuario.setFechaNacimiento(simpleDateFormat.parse(campos[6]));
                usuario.setSexo(campos[7]);
                usuario.setTelefono(campos[8]);
                usuario.setCelular(campos[9]);
                usuario.setCurp(campos[10]);
                
                usuario.Rol = new Rol();
                usuario.Rol.setIdRol(Integer.parseInt(campos[11]));
                
                usuario.Direcciones = new ArrayList<>();
                Direccion direccion = new Direccion();
                
                direccion.setCalle(campos[12]);
                direccion.setNumeroInterior(campos[13]);
                direccion.setNumeroExterior(campos[14]);
                
                direccion.Colonia = new Colonia();
                
                usuarios.add(usuario);
                usuario.Direcciones.add(direccion);
                
            }
            
            return usuarios;
            
        } catch (Exception ex) {
            System.out.println("Error");
            return null;
        }
    }
    
    private List<ErrorCM> ValidarDatos(List<Usuario> usuarios) {
        List<ErrorCM> errores = new ArrayList<>();
        int linea = 1;
        for (Usuario usuario : usuarios) {
            if (usuario.getUserName() == null || usuario.getUserName() == "") {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getUserName(), "Campo obligatorio");
                errores.add(errorCM);
                
            }
            
            linea++;
            
        }
        
        return errores;
    }

}
