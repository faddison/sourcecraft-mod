package templates;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import core.CityEntity;
import mapper.MapGenerator;
import metrics.MetricsDeserializer;
import metrics.MetricsSerializer;
import metrics.SerializationWrapper;
import metrics.SimpleClassAndBugsMetrics;
import metrics.SimpleClassMetrics;
import designer.SimpleDesigner;
import parser.ParseClasses;
import parser.SimpleClassParser;
import planner.CityFileGenerator;
import planner.ImprovedGridPlanner;

public class NewSimpleTemplate extends AbstractTemplate<SimpleClassMetrics> {
	
	
	public NewSimpleTemplate()
	{
		super();
		designer = new SimpleDesigner();
		planner = new ImprovedGridPlanner();
		parser = new SimpleClassAndBugsMetrics();
		serializationWrapper = new SerializationWrapper<SimpleClassMetrics>();
		
		metricsFilename = "metrics/simpleclassmetrics-list.list";
		sourceFilename = "sources/SweetHomeStructure.xml";
		bugsFilename = "sources/SweetHomeBugResults.xml";
		cityFilename = "cities/box-new.txt";
		mapFilename = "maps/map-new.txt";
	}

	// suppress type check because we know what the types are
	// this needs to be resolved but for now its fine
	@Override
	public void run() 
	{
		try 
		{
			File file = new File(cityFilename);
			if (!file.exists())
				file.createNewFile();
			file = new File(mapFilename);
			if (!file.exists())
				file.createNewFile();

			ArrayList<SimpleClassMetrics> metricsList = new ArrayList<SimpleClassMetrics>();
			metricsList =  parser.startParsing(sourceFilename, bugsFilename);

			/* ***COME BACK TO THIS
			 * having problems with the serializable stuff below
			 * java.io.InvalidClassException: metrics.SimpleClassMetrics; local class incompatible: stream classdesc serialVersionUID = -4063490111111282074, local class serialVersionUID = -3790282589782583307
			 */
//			file = new File(metricsFilename);
//			if (file.exists())
//			{
//				metricsList = serializationWrapper.deserializer.deserialize(metricsFilename);
//			}
//			else
//			{
//				metricsList =  (ArrayList<SimpleClassMetrics>) parser.startParsing(sourceFilename, bugsFilename);
//				serializationWrapper.serializer.serialize(metricsList, metricsFilename);
//			}
				
			//
			//CityEntity cityEntity = designer.design(new ArrayList<SimpleClassMetrics>(metricsList.subList(100, 150)));
			CityEntity cityEntity = designer.design(metricsList);
			planner.plan(cityEntity, cityFilename);
			(new MapGenerator()).map(cityFilename, mapFilename);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}