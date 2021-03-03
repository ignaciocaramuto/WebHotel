package data;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Estadia;
import entities.Habitacion;
import entities.Tipo_Habitacion;

public class DataHabitacion {
	
	public void add(Habitacion hab) {
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"insert into habitacion(id_Tipo_Habitacion) values(?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			
			stmt.setInt(1, hab.getId_Tipo_Habitacion());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                hab.setId_Tipo_Habitacion(keyResultSet.getInt(1));
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

	public Habitacion buscar(Tipo_Habitacion thab, Estadia es) {
		Habitacion h = null;
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select nro_Habitacion from habitacion where id_Tipo_Habitacion=? and nro_Habitacion not in (select id_Estadia from estadia where (fecha_Ingreso >= ? and fecha_Egreso < ?) or (fecha_Egreso >= ? and fecha_Ingreso < ?)) LIMIT 1"
					);
			stmt.setInt(1, thab.getId_Tipo_Habitacion());
			stmt.setDate(2, (Date) es.getFechaIngreso());
			stmt.setDate(3, (Date) es.getFechaEgreso());
			stmt.setDate(4, (Date) es.getFechaIngreso());
			stmt.setDate(5, (Date) es.getFechaEgreso());
			keyResultSet = stmt.executeQuery();
			
			if(keyResultSet!=null && keyResultSet.next()) {
				h = new Habitacion();
				h.setNro_Habitacion(keyResultSet.getInt("nro_Habitacion"));
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
		return h;
	}
	
	
}