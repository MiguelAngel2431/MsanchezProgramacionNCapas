
package com.digis01.MsanchezProgramacionNCapas.JPA;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddireccion")
    private int IdDireccion;

    @Column(name = "calle")
    private String Calle;
    
    @Column(name = "numerointerior")
    private String NumeroInterior;
    
    @Column(name = "numeroexterior")
    private String NumeroExterior;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idusuario", nullable = false)
    public Usuario Usuario;
    
    @ManyToOne
    @JoinColumn(name = "idcolonia")
    public Colonia Colonia; //Propiedad de navegacion
    
    //Constructores
    public Direccion() {}
    
    public Direccion(com.digis01.MsanchezProgramacionNCapas.ML.Direccion direccionML) {
        this.Calle = direccionML.getCalle();
        this.NumeroInterior = direccionML.getNumeroInterior();
        this.NumeroExterior = direccionML.getNumeroExterior();
        
        this.Usuario = new Usuario();
        this.Usuario.setIdUsuario(direccionML.getIdUsuario());
        
        this.Colonia = new Colonia();
        this.Colonia.setIdColonia(direccionML.Colonia.getIdColonia());
        
         
        
    }
    
    /*public Direccion(int idDireccion, String calle, String numeroInterior, String numeroExterior, int idUsuario) {
        this.IdDireccion = idDireccion;
        this.Calle = calle;
        this.NumeroInterior = numeroInterior;
        this.NumeroExterior = numeroExterior;
        //this.IdColonia = idColonia;
        this.IdUsuario = idUsuario;
    }*/

    public Direccion(int IdDireccion) {
        this.IdDireccion = IdDireccion;
    }

    /*public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }*/
    
    
    
    //Getter y setter de idColonia
    public void setIdDireccion(int idDireccion) {
        this.IdDireccion = idDireccion;
    }
    
    public int getIdDireccion() {
        return this.IdDireccion;
    }
    
    //Getter y setter de calle
    public void setCalle(String calle) {
        this.Calle = calle;
    }
    
    public String getCalle() {
        return this.Calle;
    }
    
    //Getter y setter de numeroInterior
    public void setNumeroInterior(String numeroInterior) {
        this.NumeroInterior = numeroInterior;
    }
    
    public String getNumeroInterior() {
        return this.NumeroInterior;
    }
    
    //Getter y setter de numeroExterior
    public void setNumeroExterior(String numeroExterior) {
        this.NumeroExterior = numeroExterior;
    }
    
    public String getNumeroExterior() {
        return this.NumeroExterior;
    }
    
    //Getter y setter de Colonia
    public void setColonia(Colonia Colonia) {
        this.Colonia = Colonia;
    }
    
    public Colonia getColonia() {
        return Colonia;
    }
    
}
