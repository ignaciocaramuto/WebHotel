package data;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import entities.*;

public class DataTipoHabitacion {
	
	//metodo para cargar en la db un tipo de habitacion nuevo
	public void add(Tipo_Habitacion th) {
		
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"insert into tipo_habitacion(denominacion, descripcion, capacidad_Personas, precio_Por_Dia) values(?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			
			stmt.setString(1, th.getDenominacion());
			stmt.setString(2, th.getDescripcion());
			stmt.setInt(3, th.getCapacidad_Personas());
			stmt.setFloat(4, th.getPrecio_Por_Dia());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                th.setId_Tipo_Habitacion(keyResultSet.getInt(1));
            }
			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}

	}
	
	//metodo pra editar un tipo de habitacion 
	public void update(Tipo_Habitacion th) {
		
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"update tipo_habitacion set denominacion=?, descripcion=?, capacidad_Personas=?, precio_Por_Dia=? where id_Tipo_Habitacion=?"
					);
			stmt.setString(1, th.getDenominacion());
			stmt.setString(2, th.getDescripcion());
			stmt.setInt(3, th.getCapacidad_Personas());
			stmt.setFloat(4, th.getPrecio_Por_Dia());
			stmt.setInt(5, th.getId_Tipo_Habitacion());
			stmt.executeUpdate();
		
	}  catch (SQLException e) {
		System.out.print("Clase DataTipoHabitacion metodo update ");
        e.printStackTrace();
	} finally {
        try {
            if(keyResultSet!=null)keyResultSet.close();
            if(stmt!=null)stmt.close();
            DbConnector.getInstancia().releaseConn();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
	}
	}
	
	//metodo para obtener el id de tipo habitacion de una denominacion por obtenida por parametro
	public Tipo_Habitacion validate(Tipo_Habitacion th) {
		Tipo_Habitacion thab = null;
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select id_Tipo_Habitacion from tipo_habitacion where denominacion=?"
					);
			
			stmt.setString(1, th.getDenominacion());
			keyResultSet=stmt.executeQuery();
			if(keyResultSet!=null && keyResultSet.next()) {
				thab = new Tipo_Habitacion();
				thab.setId_Tipo_Habitacion(keyResultSet.getInt("id_Tipo_Habitacion"));
			}
		
	}  catch (SQLException e) {
        e.printStackTrace();
	} finally {
        try {
            if(keyResultSet!=null)keyResultSet.close();
            if(stmt!=null)stmt.close();
            DbConnector.getInstancia().releaseConn();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
	}
		return thab;
		
	}
	
	//metodo para eliminar un tipo de habitacion teniendo como parametro un id de tipo habitacion
	public void delete(Tipo_Habitacion th) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"delete from tipo_Habitacion where id_Tipo_Habitacion=?"
					);
			
			stmt.setInt(1, th.getId_Tipo_Habitacion());
			stmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
//obtener tipo por id	
public Tipo_Habitacion getById(int id) {
		Tipo_Habitacion th=null;
			PreparedStatement stmt=null;
			ResultSet rs=null;
			try {
				stmt=DbConnector.getInstancia().getConn().prepareStatement(
						"SELECT * FROM estadia e \r\n" + 
						"inner join habitacion h on e.nro_Habitacion=h.nro_Habitacion\r\n" + 
						"inner join tipo_habitacion th on th.id_Tipo_Habitacion=h.id_Tipo_Habitacion where h.nro_Habitacion=?;"
						);
				stmt.setInt(1, id);
				
				rs=stmt.executeQuery();
				if(rs!=null && rs.next()) {
					th=new Tipo_Habitacion();
					th.setCapacidad_Personas(rs.getInt("th.capacidad_Personas"));
					th.setDenominacion(rs.getString("th.denominacion"));
					th.setDescripcion(rs.getString("th.descripcion"));
					th.setId_Tipo_Habitacion(rs.getInt("th.id_Tipo_Habitacion"));
					th.setPrecio_Por_Dia(rs.getFloat("th.precio_Por_Dia"));
				
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					DbConnector.getInstancia().releaseConn();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return th;
		
	}

}	

