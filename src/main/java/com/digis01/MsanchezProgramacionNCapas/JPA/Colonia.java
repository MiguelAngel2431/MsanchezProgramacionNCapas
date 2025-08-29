
package com.digis01.MsanchezProgramacionNCapas.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Colonia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcolonia")
    private int IdColonia;
    
    @Column(name = "nombre")
    private String Nombre;
    
    @Column(name = "codigopostal")
    private String CodigoPostal;
    //private int IdMunicipio;
    
    @ManyToOne
    @JoinColumn(name = "idmunicipio")
    public Municipio Municipio;
    
    //public Municipio Municipio; //Propiedad de navegacion (no ocupa setter ni getter, porque es public)
    
    //Costructores
    public Colonia() {}
    
    public Colonia(com.digis01.MsanchezProgramacionNCapas.ML.Colonia coloniaML) {
        this.IdColonia = coloniaML.getIdColonia();
        this.Nombre = coloniaML.getNombre();
        this.CodigoPostal = coloniaML.getCodigoPostal();
        
        this.Municipio = new Municipio();
        this.Municipio.setIdMunicipio(coloniaML.Municipio.getIdMunicipio());
    }
    
    /*public Colonia (int idColonia, String nombre, String codigoPostal) {
        this.IdColonia = idColonia;
        this.Nombre = nombre;
        this.CodigoPostal=  codigoPostal;
        //this.IdMunicipio = idMunicipio;
    }*/

    public Municipio getMunicipio() {
        return Municipio;
    }

    public void setMunicipio(Municipio Municipio) {
        this.Municipio = Municipio;
    }
    
    
    //Getter y Setter de idColonia
    public void setIdColonia(int idColonia) {
        this.IdColonia = idColonia;
    }
    
    public int getIdColonia() {
        return this.IdColonia;
    }
    
    //Getter y Setter de nombre
    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }
    
    public String getNombre() {
        return this.Nombre;
    }
    
    //Getter y Setter de codigoPostal
    public void setCodigoPostal(String codigoPostal) {
        this.CodigoPostal = codigoPostal;
    }
    
    public String getCodigoPostal() {
        return this.CodigoPostal;
    }
}
