package sorting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SortingReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	
	private List<Integer> unsortedList = new ArrayList<Integer>();
	private Text text = new Text();
	
	@Override
	protected void reduce(Text arg0, Iterable<IntWritable> values,
			Context context)
					throws IOException, InterruptedException {
		
		
		for(IntWritable intvalues : values){
			unsortedList.add(intvalues.get());
		}
	}
	@Override
	protected void cleanup(Context context)
			throws IOException, InterruptedException {
		
		Collections.sort(unsortedList);
		
		for(Integer aaa: unsortedList){
			context.write(new Text(""), new IntWritable(aaa));
		}
	}

}
