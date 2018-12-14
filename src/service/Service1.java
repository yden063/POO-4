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
		String response = this.filter.get(requestedSID).getQueue().toString();

		return response;
	}

}
