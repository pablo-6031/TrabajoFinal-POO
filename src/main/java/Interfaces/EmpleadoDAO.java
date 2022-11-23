package Interfaces;

import Entidades.Empleado;

public interface EmpleadoDAO {
	void agregarEmp(Empleado emp);
	void eliminarEmp(int NumLeg);
	void actualizarEmp();
	void listarEmp();
	void listEmpMayor();
	void listEmpPorSueldo();
	void buscar();
}
