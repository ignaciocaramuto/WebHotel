package data;

import java.util.ArrayList;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Cliente;
import entities.Estadia;

public class DataEstadia {
	


	public void add(int i, Estadia es, Cliente c) {
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into estadia(fecha_Ingreso, fecha_Egreso, estado, id_Cliente, nro_Habitacion) values(?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setDate(1, (java.sql.Date) es.getFechaIngreso());
			stmt.setDate(2, (java.sql.Date) es.getFechaEgreso());
			stmt.setString(3, "Reservada");
			stmt.setInt(4, c.getIdCliente());
			stmt.setInt(5, i);
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                es.setIdEstadia(keyResultSet.getInt(1));
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


	public Estadia getByCliAndDate(Cliente cli,Date fecha) {
		Estadia es=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT * FROM hotel.estadia where id_Cliente=? and fecha_Ingreso<=? and fecha_Egreso>=? and estado='Reservada';"
					);
			stmt.setInt(1, cli.getIdCliente());
			java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
			stmt.setDate(2, sqlDate);
			stmt.setDate(3, sqlDate);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				es=new Estadia();
				es.setIdEstadia(rs.getInt("id_Estadia"));
				es.setEstado(rs.getString("estado"));
				es.setFechaEgreso(rs.getDate("fecha_Egreso"));
				es.setFechaIngreso(rs.getDate("fecha_Ingreso"));
				es.setId_cliente(rs.getInt("id_Cliente"));
				es.setNro_habitacion(rs.getInt("Nro_Habitacion"));
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
		
		return es;
	}
	
	public void update(Estadia es) {
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"UPDATE `hotel`.`estadia` SET `estado` = ? WHERE (`id_Estadia` = ?);"
					);
			stmt.setString(1, es.getEstado());

			stmt.setInt(2, es.getIdEstadia());
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
	
	public Estadia getByCli(Cliente c) {
		
		Estadia es=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT * FROM hotel.estadia where id_Cliente=? and estado='En Curso';"
					);
			stmt.setInt(1, c.getIdCliente());
			
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				es=new Estadia();
				es.setIdEstadia(rs.getInt("id_Estadia"));
				es.setEstado(rs.getString("estado"));
				es.setFechaEgreso(rs.getDate("fecha_Egreso"));
				es.setFechaIngreso(rs.getDate("fecha_Ingreso"));
				es.setId_cliente(rs.getInt("id_Cliente"));
				es.setNro_habitacion(rs.getInt("Nro_Habitacion"));
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
		
		return es;
	}
	
	
	public boolean addService(entities.Servicio c,Estadia es) {
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		boolean band=true;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"INSERT INTO `hotel`.`estadia_servicio` (`id_estadia`, `id_servicio`) VALUES (?, ?);",
							PreparedStatement.RETURN_GENERATED_KEYS
							);

			
			stmt.setInt(1, es.getIdEstadia());
			stmt.setInt(2, c.getIdServicio());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
              
            }

			
		}  
		catch(java.sql.SQLIntegrityConstraintViolationException ex) {
			
			band=false;
		}
		catch (SQLException e) {
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
	return band;	
	}
}