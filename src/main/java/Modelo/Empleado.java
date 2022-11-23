package Modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.swing.JOptionPane;

@Entity
public class Empleado implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int legajo;
    @Basic
	private String nombres;
	private String apellidos;
	private String cuit;
        private Double sueldo;
        
    @Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
    
    @ManyToOne
	@JoinColumn(name="id_proyecto",nullable=false)
	private Proyecto proyecto;
    
	private static Empleado instancia;
	//Constructor vacio
	private Empleado() {
		
	}
        public static Empleado getInstance() {
		
		if(instancia == null) {
			instancia = new Empleado();

		}
		return instancia;
	}
	//Constructor con parametros
        
	private Empleado(int ndeLegajo, String nombres, String apellidos, String cuit, Date fechaNacimiento, Double sueldo) {
		super();
		this.legajo = ndeLegajo;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.cuit = cuit;
		this.fechaNacimiento = fechaNacimiento;
		this.sueldo = sueldo;
	}

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
        
        
	//Getters and Setters
	public int getNdeLegajo() {
		return legajo;
	}
	public void setNdeLegajo(int ndeLegajo) {
		this.legajo = ndeLegajo;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
        
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento ) {
		this.fechaNacimiento = fechaNacimiento;
	}
        
        
	public Double getSueldo() {
		return sueldo;
	}
	public void setSueldo(Double sueldo) {
		this.sueldo = sueldo;
	}

	
}
