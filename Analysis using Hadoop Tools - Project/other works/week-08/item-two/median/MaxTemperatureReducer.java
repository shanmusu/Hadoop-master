// cc MaxTemperatureReducer Reducer for maximum temperature example
// vv MaxTemperatureReducer
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.*;

public class MaxTemperatureReducer
  extends Reducer<Text, IntWritable, Text, IntWritable > {
  
  @Override
  public void reduce(Text key, Iterable<IntWritable> values,
      Context context)
      throws IOException, InterruptedException {
    
    ArrayList<Integer> arr = new ArrayList<Integer>();

    for (IntWritable value : values) {
     arr.add(value.get());  
}

Collections.sort(arr);
int size = arr.size();
int med = 0;

if (size%2!=0){

med = arr.get((size/2));
}
else
{

med =(arr.get(size/2)+arr.get((size/2)+1))/2; 
}
    context.write(key, new IntWritable(med));
  }
}
// ^^ MaxTemperatureReducer
