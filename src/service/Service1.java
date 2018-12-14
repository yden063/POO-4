package service;

import java.util.Map;

import filter.AbstractFilter;

/**
 * This service gives, according to a filter the state of a sensor.
 *
 */
public class Service1 extends ServiceAbstract {

	private String request;

	protected Service1(Map<String, AbstractFilter> filter, String request) {
		super(filter);
		this.request = request;
	}

	@Override
	public String execute() {
		String requestedSID = request.split(":")[2];
		AbstractFilter filter = this.filter.get(requestedSID);
		String response = "";

		if (filter != null)
			response = this.filter.get(requestedSID).getQueue().toString();
		else
			response = "This sensor doesn't exist!";

		return response;
	}

}
