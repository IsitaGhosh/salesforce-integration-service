package com.springrest.springrest.Services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	@Autowired
	private SalesforceDataService salesforceDataService;

	public Map getAccountList() {
		String query = "SELECT Id, Name FROM Account";
		return salesforceDataService.getSalesforceData(query);
	}

	public Map getContactList() {
		String query = "SELECT Id, Name FROM Account";
		return salesforceDataService.getSalesforceData(query);
	}

}
