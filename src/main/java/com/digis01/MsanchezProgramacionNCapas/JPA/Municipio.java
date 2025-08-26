
package com.digis01.MsanchezProgramacionNCapas.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Municipio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmunicipio")
    private int IdMunicipio;
    
    @Column(name = "nombre")
    private String Nombre;
    //private int idEstado;
    
    //public Estado Estado; //Propiedad de navegacion (no ocupa setter ni getter, porque es public)
    
    @ManyToOne
    @JoinColumn(name = "idestado")
    public Estado Estado;

    
    //Constructores
    public Municipio() {}
    
    public Municipio(int idMunicipio, String nombre) {
        this.IdMunicipio = idMunicipio;
        this.Nombre = nombre;
        //this.idEstado = idEstado;
    }

    public Estado getEstado() {
        return Estado;
    }

    public void setEstado(Estado Estado) {
        this.Estado = Estado;
    }
    
    
    
    //Getter y Setter de idMunicipio
    public void setIdMunicipio(int idMunicipio) {
        this.IdMunicipio = idMunicipio;
    }
    
    public int getIdMunicipio() {
        return this.IdMunicipio;
    }
    
    //Getter y Setter de nombre
    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }
    
    public String getNombre() {
        return this.Nombre;
    }
    
}
