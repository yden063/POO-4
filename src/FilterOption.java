public abstract class FilterOption extends AbstractFilter {
	
	protected AbstractFilter filter;

	public FilterOption(AbstractFilter filter) {
		super(filter.queue);
		this.filter = filter;
	}

}
