// cc MaxTemperatureReducer Reducer for maximum temperature example
// vv MaxTemperatureReducer
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.*;
import java.lang.Math.*;
import org.apache.hadoop.io.*;

public class MaxTemperaturestdReducer
  extends Reducer<Text, IntWritable, Text, DoubleWritable> {
  
  @Override
  public void reduce(Text key, Iterable<IntWritable> values,
      Context context)
      throws IOException, InterruptedException {
    
    int maxValue = Integer.MIN_VALUE;
    int minvalue = Integer.MAX_VALUE;

    ArrayList<Integer> arr = new ArrayList<Integer>();

    for (IntWritable value : values) {
      arr.add(value.get());
      maxValue = Math.max(maxValue, value.get());
      minvalue = Math.min(minvalue, value.get());
    }

    int avg = (maxValue+minvalue)/2;
    Double square=0.0 ;
    int size = arr.size();
    for ( int i=0;i<size ; i++){
     square =  square+Math.pow((arr.get(i)-avg),2);
     
}
 Double std = Math.sqrt(square/size);
    

    context.write(key, new DoubleWritable(std));
  }
}
// ^^ MaxTemperatureReducer
