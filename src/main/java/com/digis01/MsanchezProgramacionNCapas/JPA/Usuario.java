
package com.digis01.MsanchezProgramacionNCapas.JPA;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private int IdUsuario;
    
    @Column(name = "nombre")
    private String Nombre;
    
    @Column(name = "apellidopaterno")
    private String ApellidoPaterno;
    
    @Column(name = "apellidomaterno")
    private String ApellidoMaterno;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fechanacimiento")
    private Date FechaNacimiento;
 
    @Column(name = "username")
    private String UserName;
    
    @Column(name = "email")
    private String Email;
    
    @Column(name = "password")
    private String Password;
    
    @Column(name = "sexo")
    private String Sexo;
    
    @Column(name = "telefono")
    private String Telefono;
    
    @Column(name = "celular")
    private String Celular;
    
    @Column(name = "curp")
    private String Curp;
    
    /*@Column(name = "idrol")
    public int IdRol;*/

    @ManyToOne
    @JoinColumn(name = "idrol")
    public Rol Rol; //Propiedad de navegacion (no ocupa setter ni getter, porque es public)
    
    /*@OneToMany
    @JoinColumn (name = "iddireccion")*/
    @OneToMany(mappedBy = "usuarios", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Direccion> Direcciones;
    
    @Lob
    @Column(name = "imagen")
    private String Imagen;

    //Constructores
    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, String userName, String email, String password, String sexo, String telefono, String celular, String curp) {
        this.IdUsuario = idUsuario;
        this.Nombre = nombre;
        this.ApellidoPaterno = apellidoPaterno;
        this.ApellidoMaterno = apellidoMaterno;
        this.FechaNacimiento = fechaNacimiento;
        

        //Nuevos parametros
        this.UserName = userName;
        this.Email = email;
        this.Password = password;
        this.Sexo = sexo;
        this.Telefono = telefono;
        this.Celular = celular;
        this.Curp = curp;
        /*this.IdRol = IdRol;*/
        
    }

    public Usuario(String Nombre, String ApellidoPaterno, String ApellidoMaterno) {
        this.Nombre = Nombre;
        this.ApellidoPaterno = ApellidoPaterno;
        this.ApellidoMaterno = ApellidoMaterno;
        //this.IdRol = IdRol;
    }
    
    

    // Getter y Setter de idUsuario
    public void setIdUsuario(int idUsuario) {
        this.IdUsuario = idUsuario;
    }

    public int getIdUsuario() {
        return this.IdUsuario;
    }

    //Getter y Setter de nombre
    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getNombre() {
        return this.Nombre;
    }

    //Getter y Setter de apellidoPaterno
    public void setApellidoPaterno(String apellidoPaterno) {
        this.ApellidoPaterno = apellidoPaterno;
    }

    public String getApellidoPaterno() {
        return this.ApellidoPaterno;
    }

    //Getter y Setter de apellidoMaterno
    public void setApellidoMaterno(String apellidoMaterno) {
        this.ApellidoMaterno = apellidoMaterno;
    }

    public String getApellidoMaterno() {
        return this.ApellidoMaterno;
    }

    //Getter y Setter de apellidoMaterno
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.FechaNacimiento = fechaNacimiento;
    }

    public Date getFechaNacimiento() {
        return this.FechaNacimiento;
    }

    //Getter y Setter de userName
    public void setUserName(String userName) {
        this.UserName = userName;
    }

    public String getUserName() {
        return this.UserName;
    }

    //Getter y Setter de email
    public void setEmail(String email) {
        this.Email = email;
    }

    public String getEmail() {
        return this.Email;
    }

    //Getter y Setter de password
    public void setPassword(String password) {
        this.Password = password;
    }

    public String getPassword() {
        return this.Password;
    }

    //Getter y Setter de sexo
    public void setSexo(String sexo) {
        this.Sexo = sexo;
    }

    public String getSexo() {
        return this.Sexo;
    }

    //Getter y Setter de telefono
    public void setTelefono(String telefono) {
        this.Telefono = telefono;
    }

    public String getTelefono() {
        return this.Telefono;
    }

    //Getter y Setter de celular
    public void setCelular(String celular) {
        this.Celular = celular;
    }

    public String getCelular() {
        return this.Celular;
    }

    //Getter y Setter de curp
    public void setCurp(String curp) {
        this.Curp = curp;
    }

    public String getCurp() {
        return this.Curp;
    }
    /*
    //Getter y Stter de idRol
    public void setIdRol(int idRol) {
        this.IdRol = idRol;
    }

    public int getIdRol() {
        return this.IdRol;
    }
    */
    //Getter y Setter de Rol
    public void setRol(Rol Rol) {
        this.Rol = Rol;
    }

    public Rol getRol() {
        return Rol;
    }
    
    //Getter y Setter de la lista de Direcciones
    public List<Direccion> getDirecciones() {
        return Direcciones;
    }

    public void setDirecciones(List<Direccion> Direcciones) {
        this.Direcciones = Direcciones;
    }
    
    //Getter y Setter de imagen
    public void setImagen(String imagen) {
        this.Imagen = imagen;
    }
    
    public String getImagen() {
        return this.Imagen;
    }
    
}
