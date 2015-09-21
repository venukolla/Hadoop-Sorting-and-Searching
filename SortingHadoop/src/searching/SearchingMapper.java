package searching;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class SearchingMapper extends Mapper<Object, Text, Text, Text>{
	
	private Text keyOutput = new Text();
	private String localLineName;
	private static int lineNumber = 0;
	
	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		localLineName = ((FileSplit) context.getInputSplit()).getPath()
				.toString();
	}
	
	@Override
	protected void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		StringTokenizer itr = new StringTokenizer(value.toString());
		lineNumber++;
		while (itr.hasMoreTokens()) {
			keyOutput.set(localLineName);
			context.write(keyOutput, new Text(lineNumber + " :: " + itr.nextToken()));
		}
	}
	

}
