package br.edu.utfpr.dv.siacoes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import br.edu.utfpr.dv.siacoes.model.SigacConfig;

public class SigacConfigDAO extends TemplateDAO<SigacConfig>{
	
	@Override
	protected String getStringSqlFind() {
		return "SELECT * FROM sigacconfig WHERE idDepartment = ?";
	}
	
	@Override
	protected void ormFind(ResultSet rs, Set<SigacConfig> objeto) throws SQLException {
		try {
			if(rs.next()){
				objeto.add(this.loadObject(rs));
			}
		}finally{
			if((rs != null) && !rs.isClosed())
				rs.close();
		}
	}
	
	@Override
	protected String getStringSqlSave() {
		
		return "INSERT INTO sigacconfig(minimumScore, maxfilesize, idDepartment) VALUES(?, ?, ?)";
		
		 
	}
	
	@Override
	protected void ormSave(PreparedStatement stmt, SigacConfig objeto) throws SQLException{
		stmt.setDouble(1, objeto.getMinimumScore());
		stmt.setInt(2, objeto.getMaxFileSize());
		stmt.setInt(3, objeto.getDepartment().getIdDepartment());
	}
	
	@Override
	protected SigacConfig ormLoad(ResultSet rs) throws SQLException {
		SigacConfig config = new SigacConfig();
		
		config.getDepartment().setIdDepartment(rs.getInt("idDepartment"));
		config.setMinimumScore(rs.getDouble("minimumScore"));
		config.setMaxFileSize(rs.getInt("maxfilesize"));
		
		return config;
	}
}
