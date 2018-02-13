// cc MaxTemperatureReducer Reducer for maximum temperature example
// vv MaxTemperatureReducer
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxTemperatureavgReducer
  extends Reducer<Text, IntWritable, Text, IntWritable> {
  
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

    int avg = (maxValue+minvalue)/2;

    context.write(key, new IntWritable(avg));
  }
}
// ^^ MaxTemperatureReducer
