package searching;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SearchingReducer extends Reducer<Text, Text, Text, IntWritable>{

	private static String inputSearchWord = "";
	
	@Override
	protected void setup(Context context)
			throws IOException, InterruptedException {
		
		inputSearchWord = context.getConfiguration().get(SearchingConfig.KEY_WORD);
	}
	
	public void reduce(Text keyIn, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		for (Text val : values) {
			String text = val.toString();
			if (text.contains(inputSearchWord)) {
				String[] parts = text.split(" :: ");
				context.write(keyIn, new IntWritable(Integer.parseInt(parts[0])));
			}
		}
	}
	
}
