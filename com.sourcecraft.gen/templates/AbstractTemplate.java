package templates;

import designer.AbstractDesigner;
import metrics.AbstractMetrics;
import metrics.SerializationWrapper;
import metrics.SimpleClassAndBugsMetrics;
import parser.AbstractParser;
import parser.ParseClasses;
import parser.SimpleClassParser;
import planner.AbstractPlanner;

public abstract class AbstractTemplate<T extends AbstractMetrics> {

	protected AbstractDesigner<T> designer;
	protected AbstractPlanner planner;
	protected AbstractMetrics metrics;
	protected SerializationWrapper<T> serializationWrapper;
	protected SimpleClassAndBugsMetrics parser;
	
	protected String metricsFilename;
	protected String sourceFilename;
	protected String bugsFilename;
	protected String cityFilename;
	protected String mapFilename;
	
	public abstract void run();
}
