import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxVisitedUrlMapper
  extends Mapper<LongWritable, Text, Text, Text> {

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    String line = value.toString(); 
    if(!line.startsWith("#")){
      if(!line.contains("/index.")){
        if(line.contains(" 200 ")){
            String ss[]= line.split(" ");
            String month = ss[0].substring(0,7);
            String url = ss[4];
         context.write(new Text(month), new Text(url.toString()));
  
        }
      }
    }

  }
}

