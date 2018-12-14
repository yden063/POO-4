package service;

import java.util.Map;

import filter.AbstractFilter;

public abstract class ServiceAbstract implements Service {

	protected Map<String, AbstractFilter> filter;

	protected ServiceAbstract(Map<String, AbstractFilter> filter) {
		this.filter = filter;
	}

	@Override
	public abstract String execute();

}
