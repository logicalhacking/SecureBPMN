package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.DelegateExecution;

public class CreditWorthinessService implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		String country = (String) execution.getVariable("user_country");
		
		if (country != null && country.equals("Germany")) {
			// collect basic information
			String lastname = (String) execution.getVariable("user_lastname");
			String firstname = (String) execution.getVariable("user_firstname");
			Date dateOfBirth = (Date) execution.getVariable("user_dateOfBirth");
			String locationOfBirth = (String) execution.getVariable("user_locationOfBirth");
			
			// collect additional information
			List<String> addInfoList = new ArrayList<String>();
			
			addInfoList.add((String) execution.getVariable("user_address"));
			
			String birthname = (String) execution.getVariable("user_birthname");
			if (birthname != null && birthname.length() > 0) {
				addInfoList.add(birthname);
			}
			
			if ((Boolean) execution.getVariable("user_hasAccount")) {
				addInfoList.add(((Long) execution.getVariable("user_currentAccountBalance")).toString());
			}
			
			// Schufa request
			String result = executeSchufaRequest(lastname, firstname, dateOfBirth, locationOfBirth, (String[]) addInfoList.toArray());
			execution.createVariableLocal("externalWorthiness", "");
			execution.setVariable("externalWorthiness", result);
			
		} else {
			// unsupported
			notifyWrongCountry(country);
		}
		
	}
	
	private String executeSchufaRequest(String lastname, String firstname, Date dateOfBirth, String locationOfBirth, String[] addInfo) {
		// request Schufa information
		return "SchufaInfo";
	}
	
	private void notifyWrongCountry(String country) {
		// notify: wrong country provided
	}

}
