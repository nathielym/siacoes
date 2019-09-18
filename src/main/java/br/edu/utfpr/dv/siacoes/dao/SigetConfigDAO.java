package br.edu.utfpr.dv.siacoes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import br.edu.utfpr.dv.siacoes.model.SigetConfig;
import br.edu.utfpr.dv.siacoes.model.SigetConfig.AttendanceFrequency;
import br.edu.utfpr.dv.siacoes.model.SigetConfig.SupervisorFilter;

public class SigetConfigDAO extends TemplateDAO<SigetConfig>{

	@Override
	protected String getStringSqlFind() {
		return "SELECT * FROM sigetconfig WHERE idDepartment = ?";
	}
	
	@Override
	protected void ormFind(ResultSet rs, Set<SigetConfig> objeto) throws SQLException {
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
		
		return "INSERT INTO sigetconfig(minimumScore, registerProposal, showgradestostudent, supervisorfilter, cosupervisorfilter, supervisorIndication, maxTutoredStage1, maxTutoredStage2, requestFinalDocumentStage1, repositoryLink, supervisorJuryRequest, supervisorAgreement, supervisorJuryAgreement, validateAttendances, attendanceFrequency, maxfilesize, minimumJuryMembers, minimumJurySubstitutes, jurytimestage1, jurytimestage2, supervisorAssignsGrades, idDepartment) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		 
	}
	
	@Override
	protected void ormSave(PreparedStatement stmt, SigetConfig objeto) throws SQLException{
		stmt.setDouble(1, objeto.getMinimumScore());
		stmt.setInt(2, (objeto.isRegisterProposal() ? 1 : 0));
		stmt.setInt(3, (objeto.isShowGradesToStudent() ? 1 : 0));
		stmt.setInt(4, objeto.getSupervisorFilter().getValue());
		stmt.setInt(5, objeto.getCosupervisorFilter().getValue());
		stmt.setInt(6, objeto.getSupervisorIndication());
		stmt.setInt(7, objeto.getMaxTutoredStage1());
		stmt.setInt(8, objeto.getMaxTutoredStage2());
		stmt.setInt(9, (objeto.isRequestFinalDocumentStage1() ? 1 : 0));
		stmt.setString(10, objeto.getRepositoryLink());
		stmt.setInt(11, (objeto.isSupervisorJuryRequest() ? 1 : 0));
		stmt.setInt(12, (objeto.isSupervisorAgreement() ? 1 : 0));
		stmt.setInt(13, (objeto.isSupervisorJuryAgreement() ? 1 : 0));
		stmt.setInt(14, (objeto.isValidateAttendances() ? 1 : 0));
		stmt.setInt(15, objeto.getAttendanceFrequency().getValue());
		stmt.setInt(16, objeto.getMaxFileSize());
		stmt.setInt(17, objeto.getMinimumJuryMembers());
		stmt.setInt(18, objeto.getMinimumJurySubstitutes());
		stmt.setInt(19, objeto.getJuryTimeStage1());
		stmt.setInt(20, objeto.getJuryTimeStage2());
		stmt.setInt(21, (objeto.isSupervisorAssignsGrades() ? 1 : 0));
		stmt.setInt(22, objeto.getDepartment().getIdDepartment());
		
	}
	
	@Override
	protected SigetConfig ormLoad(ResultSet rs) throws SQLException {
		SigetConfig config = new SigetConfig();
		
		config.getDepartment().setIdDepartment(rs.getInt("idDepartment"));
		config.setMinimumScore(rs.getDouble("minimumScore"));
		config.setRegisterProposal(rs.getInt("registerProposal") == 1);
		config.setShowGradesToStudent(rs.getInt("showgradestostudent") == 1);
		config.setSupervisorFilter(SupervisorFilter.valueOf(rs.getInt("supervisorFilter")));
		config.setCosupervisorFilter(SupervisorFilter.valueOf(rs.getInt("cosupervisorFilter")));
		config.setSupervisorIndication(rs.getInt("supervisorIndication"));
		config.setMaxTutoredStage1(rs.getInt("maxTutoredStage1"));
		config.setMaxTutoredStage2(rs.getInt("maxTutoredStage2"));
		config.setRequestFinalDocumentStage1(rs.getInt("requestFinalDocumentStage1") == 1);
		config.setRepositoryLink(rs.getString("repositoryLink"));
		config.setSupervisorJuryRequest(rs.getInt("supervisorJuryRequest") == 1);
		config.setSupervisorAgreement(rs.getInt("supervisorAgreement") == 1);
		config.setSupervisorJuryAgreement(rs.getInt("supervisorJuryAgreement") == 1);
		config.setValidateAttendances(rs.getInt("validateAttendances") == 1);
		config.setAttendanceFrequency(AttendanceFrequency.valueOf(rs.getInt("attendanceFrequency")));
		config.setMaxFileSize(rs.getInt("maxfilesize"));
		config.setMinimumJuryMembers(rs.getInt("minimumJuryMembers"));
		config.setMinimumJurySubstitutes(rs.getInt("minimumJurySubstitutes"));
		config.setJuryTimeStage1(rs.getInt("jurytimestage1"));
		config.setJuryTimeStage2(rs.getInt("jurytimestage2"));
		config.setSupervisorAssignsGrades(rs.getInt("supervisorAssignsGrades") == 1);
		
		return config;
	}
	
}
