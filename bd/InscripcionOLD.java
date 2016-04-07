package bd;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Inscripcion {
	//Atributos
	public int idInscripcion;
	public int idAtleta;
	public int idCompeticion;
	public int idCategoria;
	public String estado;
	public int cuota;
	public boolean pagado;
	public String fechapago;




	//Constructores 

	public int getIdInscripcion() {
		return idInscripcion;
	}
	public void setIdInscripcion(int idInscripcion) {
		this.idInscripcion = idInscripcion;
	}
	public int getIdAtleta() {
		return idAtleta;
	}
	public void setIdAtleta(int idAtleta) {
		this.idAtleta = idAtleta;
	}
	public int getIdCompeticion() {
		return idCompeticion;
	}
	public void setIdCompeticion(int idCompeticion) {
		this.idCompeticion = idCompeticion;
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getCuota() {
		return cuota;
	}
	public void setCuota(int cuota) {
		this.cuota = cuota;
	}
	public boolean isPagado() {
		return pagado;
	}
	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}
	public String getFechapago() {
		return fechapago;
	}
	public void setFechapago(String fechapago) {
		this.fechapago = fechapago;
	}
	public Inscripcion(int inscripcion, int atleta, int competicion,
			int categoria, String estado, int cuota, int pagado){
		this.idInscripcion=inscripcion;
		this.idAtleta=atleta;
		this.idCompeticion=competicion;
		this.idCategoria=categoria;
		this.estado=estado;
		this.cuota=cuota;
		this.pagado=false;
	}
	public Inscripcion(){
		this(-1,-1,-1,-1, null,-1,-1);
	}

	//Metodos


	public Inscripcion(int idInscripcion, int idAtleta, int idCompeticion, int idCategoria, String estado, int cuota,
			boolean pagado, String fechapago) {
		super();
		this.idInscripcion = idInscripcion;
		this.idAtleta = idAtleta;
		this.idCompeticion = idCompeticion;
		this.idCategoria = idCategoria;
		this.estado = estado;
		this.cuota = cuota;
		this.pagado = pagado;
		this.fechapago = fechapago;
	}
	public void AñadirInscripcion() throws SQLException{

			estado = "Pendiente";
			ConexionDB con = new ConexionDB();
			String sql = "INSERT INTO Inscripcion (Atleta, Competicion, Categoria, Estado, Pagado, Cuota) VALUES ";
			sql = sql + idAtleta + "," + idCompeticion + "," + idCategoria + "," + estado + "," + cuota;
			con.update(sql);
			con.desconectarDB();
	}


	public boolean ExisteInscripcion() throws SQLException{
		
		ConexionDB conexion = new ConexionDB();
		String sql=("SELECT * FROM Inscripcion WHERE (Atleta='"+idAtleta+"' AND Competicion='"
				+idCompeticion+"')");
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
	
	public void getInscripcion(String Id) throws SQLException{
		
		ConexionDB conexion = new ConexionDB();
		String sql="SELECT * FROM Inscripcion WHERE IdInscripcion = " + Id;
		ResultSet resultado = conexion.query(sql);
		conexion.desconectarDB();
	
		this.idInscripcion = resultado.getInt("Inscripcion");
		this.cuota = resultado.getInt("Cuota");
		resultado.close();
		return;
	}	


	public Inscripcion(int idA) throws SQLException{

		this();
		this.idAtleta=idA;
	}
	public void pagar() throws SQLException {
		this.setEstado ("Pagado");
		this.setPagado (true);
		ConexionDB con = new ConexionDB();
		String sql = "UPDATE Inscripcion SET Estado='Pagado', Pagado=TRUE WHERE IdInscripcion = " + getIdInscripcion();
		con.update(sql);
		con.desconectarDB();
		
	}
}
