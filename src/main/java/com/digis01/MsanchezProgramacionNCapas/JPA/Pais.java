
package com.digis01.MsanchezProgramacionNCapas.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pais {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpais")
    private int IdPais;
    
    @Column(name = "nombre")
    private String Nombre;
    
    //Constructores
    public Pais() {}
    
    /*public Pais(int idPais, String nombre) {
        this.IdPais = idPais;
        this.Nombre = nombre;
    }*/
    
    //Getter y Setter de idPais
    public void setIdPais(int idPais) {
        this.IdPais = idPais;
    }
    
    public int getIdPais() {
        return this.IdPais;
    }
    
    //Getter y Setter de nombre
    public void setNombre(String nombre) {
        this.Nombre =nombre;
    }
    
    public String getNombre() {
        return this.Nombre;
    }
}
