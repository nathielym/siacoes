package br.edu.utfpr.dv.siacoes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import br.edu.utfpr.dv.siacoes.log.UpdateEvent;
import lombok.var;

public abstract class TemplateDAO<T> {

	protected abstract String getStringSqlFind();
	protected abstract void ormFind(ResultSet result, Set<T> resultado) throws SQLException;
	
	public final Set<T> find(int idDepartment) throws SQLException {
		var resultado = new HashSet<T>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try{
			conn = ConnectionDAO.getInstance().getConnection();
			String sql = getStringSqlFind();
			stmt = conn.prepareStatement(sql);
		

			stmt.setInt(1, idDepartment);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				resultado.add(loadObject(rs));
			}else{
				return null;
			}
		}finally{
			if((stmt != null) && !stmt.isClosed())
				stmt.close();
			if((conn != null) && !conn.isClosed())
				conn.close();
		}
		
		return resultado;
	}
	
	protected abstract String getStringSqlSave();
	protected abstract void ormSave(PreparedStatement statement, T objeto) throws SQLException;
	
	public final void save(int idUser, T objeto) throws SQLException{
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try{
			conn = ConnectionDAO.getInstance().getConnection();
			String sql = getStringSqlSave();
			
			stmt = conn.prepareStatement(sql);
			ormSave(stmt, objeto);
			stmt.execute();
			
			new UpdateEvent(conn).registerUpdate(idUser, objeto);

		}finally{
			if((stmt != null) && !stmt.isClosed())
				stmt.close();
			if((conn != null) && !conn.isClosed())
				conn.close();
		}
		
	}
	
	protected abstract T ormLoad(ResultSet rs) throws SQLException;
	
	public final T loadObject(ResultSet rs) throws SQLException{
		
		return ormLoad(rs);
	}
	
}
