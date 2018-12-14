package service;

import java.util.Map;
import java.util.Map.Entry;

import filter.AbstractFilter;

/**
 * This service gives all the existing sensors with their informations.
 *
 */
public class Service2 extends ServiceAbstract {

	protected Service2(Map<String, AbstractFilter> filter) {
		super(filter);
	}

	@Override
	public String execute() {
		String response = "";

		for (Entry<String, AbstractFilter> element : this.filter.entrySet()) {
			response += element.getKey() + " " + element.getValue().getQueue();
			response += "\n";
		}

		return response;
	}

}
