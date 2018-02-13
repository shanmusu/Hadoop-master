import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxTemperatureMapper1
  extends Mapper<LongWritable, Text , Text , IntWritable> {

  private static final int MISSING = 9999;

  @Override
  public void map( LongWritable key,Text value, Context context)
      throws IOException, InterruptedException {
     
      String line = value.toString();
      String keys = line.substring(1,13)+" " +line.substring(17,21);
      int values = Integer.parseInt(line.substring(22)); 
 // for(Text keys : key){
   
   //   keys = key.substring(1,13)+" "+key.substring(17,21);
//}
 context.write(new Text(keys) , new IntWritable(values));
    }
  }
