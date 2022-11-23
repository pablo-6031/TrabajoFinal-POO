/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Entidades.Empleado;
import Entidades.Proyecto;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PABLO
 */
public class Controlador {
    
     EmpleadoJpaController empJPA = new EmpleadoJpaController();

    
     public void crearUsuario (Empleado emp){
        try{
            empJPA.create(emp);
        }catch (Exception ex){
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE,null,ex);
        }
        
    }
    public void editarUsuario (Empleado emp){
        try{
            empJPA.edit(emp);
        }catch (Exception ex){
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    
    ProyectoJpaController proJPA = new ProyectoJpaController();

    
     public void crearProyecto(Proyecto pro){
        try{
            proJPA.create(pro);
        }catch (Exception ex){
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE,null,ex);
        }
        
    }
    public void editarProyecto(Proyecto pro){
        try{
            proJPA.edit(pro);
        }catch (Exception ex){
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
   

}
