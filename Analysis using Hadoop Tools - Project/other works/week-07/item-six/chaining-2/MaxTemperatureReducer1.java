import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxTemperatureReducer1
  extends Reducer<Text, IntWritable, Text, IntWritable> {


  @Override
  public void reduce(Text key,Iterable<IntWritable> values,
      Context context)
      throws IOException, InterruptedException {
      int sum=0;
      int count=0;
   //   String keys = key.toString();
     // String cut =  keys.substring(1,12); 

      for (IntWritable value : values) {

      sum+=value.get(); 
      count++;    
}

int average= sum/count;
context.write(key,new IntWritable(average));
}
   
}

