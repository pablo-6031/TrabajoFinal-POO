package Implementacion;

import java.text.Format;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Interfaces.EmpleadoDAO;
import Entidades.Empleado;
import recuParcialPOO.Libro;

public class ImpleEmpleadoDAO implements EmpleadoDAO {
	List<Empleado> listEmp = new ArrayList<Empleado>();
	public void agregarEmp(Empleado emp) {
		// TODO Auto-generated method stub
		try {
			emp.setNdeLegajo(Integer.parseInt(JOptionPane.showInputDialog("Num de Legajo: ")));
			emp.setApellidos(JOptionPane.showInputDialog("Ingrese el Apellido/s: "));
			emp.setNombres(JOptionPane.showInputDialog("Ingrese el Nombre/s: "));
			emp.setCuit(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el CUIT: ")));
			emp.setFechaNacimiento(Format.parse(JOptionPane.showInputDialog("Ingrese la Fecha de Nacimiento: "))));
			emp.setSueldo(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el Sueldo: ")));
		
			listEmp.add(new Empleado(emp.getNdeLegajo(),emp.getApellidos() , emp.getNombres(), emp.getCuit(), emp.getFechaNacimiento(), emp.getSueldo()));
			JOptionPane.showMessageDialog(null, "El Empleado " + emp.getApellidos() + " " + emp.getNombres() +" fue agregado.");
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,"Ingrese solo valores numericos enteros en el campo \nNum. de Legajo \nCUIT \ny Sueldo");
		}
	}

	public void eliminarEmp(int NumLeg) {
		// TODO Auto-generated method stub
		try {
			for (int i = 0; i < listEmp.size(); i++) {
				if (NumLeg == listEmp.get(i).getNdeLegajo()) {
					listEmp.remove(listEmp.get(i));
					JOptionPane.showMessageDialog(null, "El Empleado "+ listEmp.get(i).getApellidos() +" fue eliminado con exito");
				}else {
					JOptionPane.showMessageDialog(null, "El empleado no esta registrado o no existe");
				}
			}
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,"Ingrese solo valores numericos enteros en los campos \nNum. de Legajo");
		}
	}

	public void actualizarEmp() {
		// TODO Auto-generated method stub
		int modif = Integer.parseInt(JOptionPane.showInputDialog("Id: "));
		boolean bandera = false;
		try {
		for (Empleado e : listEmp) {
			if (modif == e.getNdeLegajo()) {
				bandera = true;
				JOptionPane.showMessageDialog(null, "LIBRO A MODIFICAR\n(No puede modificarse el Codigo)\n" + e.getNdeLegajo()+"\n"+e.getApellidos()+"\n"+e.getNombres()+"\n"+e.getCuit()+"\n"+e.getFechaNacimiento()+"\n"+e.getSueldo());
				e.setApellidos(JOptionPane.showInputDialog("Titulo del Libro: "));
				e.setNombres(JOptionPane.showInputDialog("Categoria: "));
				e.setCuit(Integer.parseInt(JOptionPane.showInputDialog("Ingrese nuevo CUIT: ")));
				c.setCantEjemplares(Integer.parseInt(JOptionPane.showInputDialog("Cant. de Ejemplares: ")));
			}
		}
		if(bandera == false) {
			JOptionPane.showMessageDialog(null, "Pelicula no encontrada");
		}
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,"Ingrese solo valores numericos enteros en los campos \nId y Año de estreno");
		}
	}

	public void listarEmp() {
		// TODO Auto-generated method stub
		for (Empleado e : listEmp) {
			JOptionPane.showMessageDialog(null,"|  "+ e.getNdeLegajo()+"  |  "+ e.getApellidos()+"  |  "+ e.getNombres()+"  |  "+ e.calcularEdad(e)+"  |");
			}
	}

	public void listEmpMayor() {
		// TODO Auto-generated method stub
		
	}

	public void listEmpPorSueldo() {
		// TODO Auto-generated method stub
		
	}

	public void buscar() {
		// TODO Auto-generated method stub
		
	}
	
	
}
