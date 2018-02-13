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
      String log_line = value.toString();
      //average 
      if(!log_line.startsWith("#")){
        if(!log_line.contains("/index.")){
          String word_list[]= log_line.split(" ");
          String year = word_list[0];
          String url = word_list[4];
          String response = word_list[10];
          if(response.equals("200"))
            context.write(new Text(year), new Text(url.toString()));
        }
      }
    }
}