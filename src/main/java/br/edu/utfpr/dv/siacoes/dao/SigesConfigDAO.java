package br.edu.utfpr.dv.siacoes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import br.edu.utfpr.dv.siacoes.log.UpdateEvent;
import br.edu.utfpr.dv.siacoes.model.SigacConfig;
import br.edu.utfpr.dv.siacoes.model.SigesConfig;
import br.edu.utfpr.dv.siacoes.model.SigetConfig.SupervisorFilter;

public class SigesConfigDAO extends TemplateDAO<SigesConfig>{

	@Override
	protected String getStringSqlFind() {
		return "SELECT * FROM sigesconfig WHERE idDepartment = ?";
	}
	
	@Override
	protected void ormFind(ResultSet rs, Set<SigesConfig> objeto) throws SQLException {
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
		
		return "INSERT INTO sigesconfig(minimumScore, supervisorPonderosity, companySupervisorPonderosity, showgradestostudent, supervisorfilter, supervisorFillJuryForm, maxfilesize, jurytime, idDepartment) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		 
	}
	
	@Override
	protected void ormSave(PreparedStatement stmt, SigesConfig objeto) throws SQLException{
		stmt.setDouble(1, objeto.getMinimumScore());
		stmt.setDouble(2, objeto.getSupervisorPonderosity());
		stmt.setDouble(3, objeto.getCompanySupervisorPonderosity());
		stmt.setInt(4, objeto.isShowGradesToStudent() ? 1 : 0);
		stmt.setInt(5, objeto.getSupervisorFilter().getValue());
		stmt.setInt(6, objeto.isSupervisorFillJuryForm() ? 1 : 0);
		stmt.setInt(7, objeto.getMaxFileSize());
		stmt.setInt(8, objeto.getJuryTime());
		stmt.setInt(9, objeto.getDepartment().getIdDepartment());
	}
	
	@Override
	protected SigesConfig ormLoad(ResultSet rs) throws SQLException {
		SigesConfig config = new SigesConfig();
		
		config.getDepartment().setIdDepartment(rs.getInt("idDepartment"));
		config.setMinimumScore(rs.getDouble("minimumScore"));
		config.setSupervisorPonderosity(rs.getDouble("supervisorPonderosity"));
		config.setCompanySupervisorPonderosity(rs.getDouble("companySupervisorPonderosity"));
		config.setShowGradesToStudent(rs.getInt("showgradestostudent") == 1);
		config.setSupervisorFilter(SupervisorFilter.valueOf(rs.getInt("supervisorfilter")));
		config.setSupervisorFillJuryForm(rs.getInt("supervisorFillJuryForm") == 1);
		config.setMaxFileSize(rs.getInt("maxfilesize"));
		config.setJuryTime(rs.getInt("jurytime"));
		
		return config;
	}	
}
