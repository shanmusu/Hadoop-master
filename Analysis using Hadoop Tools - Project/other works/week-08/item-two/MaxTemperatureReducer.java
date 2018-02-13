// cc MaxTemperatureReducer Reducer for maximum temperature example
// vv MaxTemperatureReducer
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxTemperatureReducer
  extends Reducer<Text, IntWritable, Text, Text> {
  
  @Override
  public void reduce(Text key, Iterable<IntWritable> values,
      Context context)
      throws IOException, InterruptedException {
    
    int maxValue = Integer.MIN_VALUE;
    int minvalue = Integer.MAX_VALUE;

    for (IntWritable value : values) {
      maxValue = Math.max(maxValue, value.get());
      minvalue = Math.min(minvalue, value.get());
    }

    String val = Integer.toString(maxValue)+" " +Integer.toString(minvalue);

    context.write(key, new Text(val));
  }
}
// ^^ MaxTemperatureReducer
