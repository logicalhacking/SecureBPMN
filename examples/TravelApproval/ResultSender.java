package com.mycompany.bpmn;

import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.DelegateExecution;

import com.mycompany.backend.DBConnection;
import com.mycompany.backend.DBManager;
import com.mycompany.backend.DBConstants;

public class ResultSender implements JavaDelegate {
	
	public void execute(DelegateExecution execution) throws Exception {
		
		DBConnection connection = DBManager.getDBConnection();
		
		String userID = (String) execution.getVariable("user_id");
		String userName = (String) execution.getVariable("user_name");
		Double userWage = (Double) execution.getVariable("user_wage");
		Double travelBudget = (Double) execution.getVariable("travel_budget");
		Integer travelDuration = (Integer) execution.getVariable("travel_duration");
		String approvingManagerID1 = (String) execution.getVariable("manager_id_first");
		String approvingManagerID2 = (String) execution.getVariable("manager_id_second");
		
		connection.save(DBConstants.TRAVEL_TABLE, userID, userName, userWage, travelBudget, travelDuration, approvingManagerID1, approvingManagerID2);
	}

}
