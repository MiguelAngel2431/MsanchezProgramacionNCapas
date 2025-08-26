
package com.digis01.MsanchezProgramacionNCapas.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Estado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idestado")
    private int IdEstado;
    
    @Column(name = "nombre")
    private String Nombre;
    //private int IdPais;
    
    //public Pais Pais; //Propiedad de navegacion (no ocupa setter ni getter, porque es public)
    @ManyToOne
    @JoinColumn(name = "idpais")
    public Pais Pais;
    
    //Constructores
    public Estado() {}
    
    public Estado(int idEstado, String nombre) {
        this.IdEstado = idEstado;
        this.Nombre = nombre;
        //this.IdPais = IdPais;
    }

    public Pais getPais() {
        return Pais;
    }

    public void setPais(Pais Pais) {
        this.Pais = Pais;
    }
    
    
    
    //Getter y Setter de idEstado
    public void setIdEstado(int idEstado) {
        this.IdEstado = idEstado;
    }
    
    public int getIdEstado() {
        return this.IdEstado;
    }
    
    //Getter y Setter de nombre
    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }
    
    public String getNombre() {
        return this.Nombre;
    }
    
}
