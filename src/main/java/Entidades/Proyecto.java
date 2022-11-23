/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author PABLO
 */
@Entity
public class Proyecto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codProyecto;
    @Basic
	private String nombre;
        private Double montoPres;

    @Temporal(TemporalType.DATE)    
	private Date fechaIni;
    @Temporal(TemporalType.DATE) 
	private Date fechaFin;
    @OneToMany
	@JoinColumn(name="id_proyecto")
	private List<Empleado> empleado ;
	
    private static Proyecto instancia;
    private Proyecto() {
    }
    public static Proyecto getInstance() {
		
		if(instancia == null) {
			instancia = new Proyecto();

		}
		return instancia;
	}

    public Proyecto(int codProyecto, String nombreProyecto, Date fechaIni, Date fechaFin, Double montoPres) {
        this.codProyecto = codProyecto;
        this.nombre = nombreProyecto;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.montoPres = montoPres;

    }

    public int getCodProyecto() {
        return codProyecto;
    }

    public void setCodProyecto(int codProyecto) {
        this.codProyecto = codProyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombreProyecto) {
        this.nombre = nombreProyecto;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Double getMontoPres() {
        return montoPres;
    }

    public void setMontoPres(Double montoPres) {
        this.montoPres = montoPres;
    }

    public List<Empleado> getEmpleado() {
        return empleado;
    }

    public void setEmpleado(List<Empleado> empleado) {
        this.empleado = empleado;
    }


        
        
}
