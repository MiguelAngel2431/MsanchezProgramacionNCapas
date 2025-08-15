package com.digis01.MsanchezProgramacionNCapas.Controller;

import com.digis01.MsanchezProgramacionNCapas.DAO.ColoniaDAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.DAO.EstadoDAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.DAO.MunicipioDAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.DAO.PaisDAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.DAO.RolDAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.DAO.UsuarioDAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.ML.Colonia;
import com.digis01.MsanchezProgramacionNCapas.ML.Direccion;
import com.digis01.MsanchezProgramacionNCapas.ML.Result;
import com.digis01.MsanchezProgramacionNCapas.ML.Usuario;
import jakarta.validation.Valid;
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
            model.addAttribute("Usuario", new Usuario());

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
//Result result = rolDAOImplementation.GetAll();
        model.addAttribute("roles", rolDAOImplementation.GetAll().objects);

        model.addAttribute("paises", paisDAOImplementation.GetAll().objects);

        //Creamos un usario para que pueda escribir en el
        model.addAttribute("Usuario", new Usuario());

        //return "UsuarioForm";
        /*if (IdDireccion == null) { //Editar usuario

        } else if (IdDireccion == 0) { //Editar direccion

        }*/

        return "UsuarioForm";

    }
    
    
    //Proceso de agregado
    @PostMapping("add")
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
    }

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

}
