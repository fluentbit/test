package bd;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class Atleta2 {
	
	//Atributos
	public int idAtleta;
	public String nombre;
	public String apellido1;
	public String apellido2;
	public String DNI;
	public Calendar fechaNacimiento;
	public String sexo;
	public String contraseña;
	//Constructores
	
	Atleta(int id,String n,String a1,String a2,String dni,
			Calendar fecha, String s, String c){
		idAtleta=id;
		 nombre=n;
		 apellido1=a1;
		 apellido2=a2;
		 DNI=dni;
		 fechaNacimiento=fecha;
		 sexo=s;
		 contraseña=c;
	}
	public Atleta(){
		this(-1, null, null, null, null, null, null, null);
		fechaNacimiento = Calendar.getInstance();
	}
	
	//Metodos
	
	public boolean AtletaValido(){
		if(nombre!=null && apellido1!=null && apellido2!=null && DNI!=null
				&& fechaNacimiento!=null && sexo!=null && contraseña!=null){
					return true;
				}
				else
					return false;
	}
	

	public boolean RegistrarAtleta() throws SQLException, ClassNotFoundException{
		if(!ExisteAtleta()){

				
			ConexionDB conexion = new ConexionDB();
			conexion.conectarDB();
			Date fecha= new Date(fechaNacimiento.getTime().getTime());
			SimpleDateFormat formatofecha = new SimpleDateFormat("dd-MM-yyyy");
			String fechasql = formatofecha.format(fecha);
			System.out.println(fecha.toString());
			System.out.println(fechasql);
			String sql = "INSERT INTO Atleta (Nombre, Apellido1, Apellido2, DNI, Fecha_nacimiento, Sexo, Contraseña) VALUES ";
			sql = sql + "'" + nombre + "','" + apellido1 + "','" + apellido2 + "','" + DNI + "','" + fechasql + "','" + sexo + "','" + contraseña + "'";
			System.out.println(sql);
			conexion.update(sql);
			conexion.desconectarDB();
			return true;
		}
		else
			return false;
}
	
public boolean ExisteAtleta() throws SQLException, ClassNotFoundException{
		if(AtletaValido()){

			ConexionDB conexion=new ConexionDB();
			conexion.conectarDB();
			String sql=("SELECT DNI FROM Atleta WHERE DNI='"+DNI+"'");
		    ResultSet resultado = conexion.query(sql);
		    if(resultado.next()){
		    	resultado.close();
		    	conexion.desconectarDB();
		    	return true;
		     }
		     else{
				resultado.close();
		    	conexion.desconectarDB();
				return false;
		     }
		}
		else
			return false;
}
public int getIdAtleta() {
	return idAtleta;
}
public void setIdAtleta(int idAtleta) {
	this.idAtleta = idAtleta;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getApellido1() {
	return apellido1;
}
public void setApellido1(String apellido1) {
	this.apellido1 = apellido1;
}
public String getApellido2() {
	return apellido2;
}
public void setApellido2(String apellido2) {
	this.apellido2 = apellido2;
}
public String getDNI() {
	return DNI;
}
public void setDNI(String dNI) {
	DNI = dNI;
}
public Calendar getFechaNacimiento() {
	return fechaNacimiento;
}
public void setFechaNacimiento(Calendar fechaNacimiento) {
	this.fechaNacimiento = fechaNacimiento;
}
public String getSexo() {
	return sexo;
}
public void setSexo(String sexo) {
	this.sexo = sexo;
}
public String getContraseña() {
	return contraseña;
}
public void setContraseña(String contraseña) {
	this.contraseña = contraseña;
}
public boolean testcontrasena(String contrasenaatestear) throws ClassNotFoundException {
	
	ConexionDB conexion = new ConexionDB();
	conexion.conectarDB();
	String sql = "SELECT Contraseña FROM Atleta WHERE DNI = '" + getDNI() + "'";
	ResultSet rs1 = conexion.query(sql);
	
	try {
		while (rs1.next()){
			contraseña = (rs1.getString("Contraseña"));
		}
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, "Error chequeando contraseña: " + e.getMessage());
		e.printStackTrace();
	}
	if (contrasenaatestear.equals(getContraseña())) return true;
	else return false;
	
}
	
}
