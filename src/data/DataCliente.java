package data;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import entities.*;

public class DataCliente {

	public LinkedList<Cliente> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Cliente> clients= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select id_Cliente,tipo_doc,nro_doc,apellido,nombre,sexo,fecha_Nacimiento,email,telefono from cliente");
			//intencionalmente no se recupera la password
			if(rs!=null) {
				while(rs.next()) {
					Cliente c=new Cliente();
					c.setIdCliente(rs.getInt("id_Cliente"));
					c.setTipoDoc(rs.getString("tipo_doc"));
					c.setNumDoc(rs.getString("nro_doc"));
					c.setApellido(rs.getString("apellido"));
					c.setNombre(rs.getString("nombre"));
					c.setSexo(rs.getString("sexo"));
					c.setFechaNacimiento(rs.getDate("fecha_Nacimiento"));
					c.setMail(rs.getString("email"));
					c.setTelefono(rs.getString("telefono"));
					
					clients.add(c);
				}
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
		
		
		return clients;
	}
	
	
	public Cliente getByUser(Cliente cli) {
		Cliente c=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select id_Cliente,nombre,apellido,tipo_doc,nro_doc,email,telefono from cliente where email=? and password=?"
					);
			stmt.setString(1, cli.getMail());
			stmt.setString(2, cli.getContraseña());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				c=new Cliente();
				c.setIdCliente(rs.getInt("id_Cliente"));
				c.setNombre(rs.getString("nombre"));
				c.setApellido(rs.getString("apellido"));
				c.setTipoDoc(rs.getString("tipo_doc"));
				c.setNumDoc(rs.getString("nro_doc"));
				c.setMail(rs.getString("email"));
				c.setTelefono(rs.getString("telefono"));
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
		
		return c;
	}
	
	public void add(Cliente c) {
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into cliente(tipo_doc, nro_doc, nombre, apellido, email, password, telefono, sexo, fecha_Nacimiento, tipo_Tarjeta_Credito, nro_Tarjeta_Credito) values(?,?,?,?,?,?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, c.getTipoDoc());
			stmt.setString(2, c.getNumDoc());
			stmt.setString(3, c.getNombre());
			stmt.setString(4, c.getApellido());
			stmt.setString(5, c.getMail());
			stmt.setString(6, c.getContraseña());
			stmt.setString(7, c.getTelefono());
			stmt.setString(8, c.getSexo());
			stmt.setDate(9, c.getFechaNacimiento());
			stmt.setString(10, c.getTipoTarjetaCredito());
			stmt.setString(11, c.getNumTarjetaCredito());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                c.setIdCliente(keyResultSet.getInt(1));
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
	
	public void update(Cliente c) {
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"update cliente set tipo_doc=?, nro_doc=?, nombre=?, apellido=? email=? password=? telefono=? sexo=? fecha_Nacimiento=? tipo_Tarjeta_Credito=? nro_Tarjeta_Credito=? where id_Cliente=?"
					);
			stmt.setString(1, c.getTipoDoc());
			stmt.setString(2, c.getNumDoc());
			stmt.setString(3, c.getNombre());
			stmt.setString(4, c.getApellido());
			stmt.setString(5, c.getMail());
			stmt.setString(6, c.getContraseña());
			stmt.setString(7, c.getTelefono());
			stmt.setString(8, c.getSexo());
			stmt.setDate(9, c.getFechaNacimiento());
			stmt.setString(10, c.getTipoTarjetaCredito());
			stmt.setString(11, c.getNumTarjetaCredito());
			stmt.setInt(12, c.getIdCliente());
			stmt.executeUpdate();
		
	}  catch (SQLException e) {
		System.out.print("Clase DataCliente metodo update ");
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
	
	
	
	public void delete(Cliente c) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"delete from cliente where id_Cliente=?"
					);
			
			stmt.setInt(1, c.getIdCliente());
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
}
