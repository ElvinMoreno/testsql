package co.empresa.test.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import co.empresa.test.modelo.Usuario;
import co.empresa.test.util.ConexionMySQL;

public class UsuarioDaoMySQL implements UsuarioDao{
	
	private ConexionMySQL conexion;
	
	private static final String INSERT_USUARIO_SQL = "INSERT INTO usuario (nombre, email, pais VALUES(?, ?, ?);"; 
	private static final String DELETE_USUARIO_SQL = "DELETE  FROM usuario WHERE id = ?;";
	private static final String UPDATE_USUARIO_SQL = "UPDATE usuario SET nombre = ?, email = ?, pais = ? WHERE id = ?;"; 
	private static final String SELECT_USUARIO_BY_ID = "SELECT * FROM usuario WHERE id = ?;"; 
	private static final String SELECT_ALL_USUARIO = "SELECT * FROM usuario;"; 

	
	public UsuarioDaoMySQL() {
		this.conexion = conexion.getConexion();		
	}
	
	public void insert(Usuario usuario) throws SQLException{
		try {
			PreparedStatement preparedstatement = conexion.setPreparedStatement(INSERT_USUARIO_SQL);
			preparedstatement.setString(1, usuario.getNombre());
			preparedstatement.setString(2, usuario.getEmail());
			preparedstatement.setString(3, usuario.getPais());
			conexion.execute();
		}catch(SQLException e) {						
			e.printStackTrace();
		}		
	}
	
	public void delete(int id) {
		try {
			PreparedStatement preparedstatement = conexion.setPreparedStatement(DELETE_USUARIO_SQL);
			preparedstatement.setInt(1, id);
			conexion.execute();
		}catch(SQLException e) {						
			e.printStackTrace();
		}			
	}
	
	public void update(Usuario usuario) throws SQLException{
		try {
			PreparedStatement preparedstatement = conexion.setPreparedStatement(UPDATE_USUARIO_SQL);
			preparedstatement.setString(1, usuario.getNombre());
			preparedstatement.setString(2, usuario.getEmail());
			preparedstatement.setString(3, usuario.getPais());
			preparedstatement.setInt(4, usuario.getId());
			conexion.execute();
		}catch(SQLException e) {						
			e.printStackTrace();
		}		
	}
	
	@Override
	public List < Usuario > selectAll(){
		List<Usuario> usuarios = new ArrayList<>();
		try {
			PreparedStatement preparedstatement = conexion.setPreparedStatement(SELECT_ALL_USUARIO);			
			
			ResultSet rs = conexion.query();
			
			while(rs.next()){
				int id = rs.getInt("id");
				String nombre  = rs.getString("nombre");
				String email  = rs.getString("email");
				String pais  = rs.getString("pais");
				usuarios.add(new Usuario(id, nombre, email, pais));				
			}
		}catch(SQLException e) {						
			e.printStackTrace();
		}	
		return usuarios;
	}
	
	@Override
	public Usuario selec(int id){
		Usuario usuario= null;
		try {
			PreparedStatement preparedstatement = conexion.setPreparedStatement(SELECT_USUARIO_BY_ID);	
			preparedstatement.setInt(1, id);
			
			ResultSet rs = conexion.query();
			
			while(rs.next()){
				String nombre  = rs.getString("nombre");
				String email  = rs.getString("email");
				String pais  = rs.getString("pais");
				usuario = new Usuario(id, nombre, email, pais);				
			}
		}catch(SQLException e) {						
			e.printStackTrace();
		}	
		return usuario;
	}


}
