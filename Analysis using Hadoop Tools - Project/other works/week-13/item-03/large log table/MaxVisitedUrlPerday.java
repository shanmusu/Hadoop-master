import java.io.IOException;
import java.util.*;
import com.cloudera.sqoop.lib.RecordParser.ParseError;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.*;

public class MaxVisitedUrlPerday extends Configured implements Tool {

  public static class MaxVisitedUrlPerdayMapper
      extends Mapper<LongWritable, Text, Text, Text> {
    public void map(LongWritable k, Text v, Context context)  throws IOException, InterruptedException {
      String year = "";
      String url = "";
      String response = "";
      large_log log = new large_log();
      try {
        log.parse(v); 
      } 
      catch (ParseError pe) {
        return;
      }
      if(null ==log.get_dates() || log.get_dates().toString().equals(" "))
        return;
      else{
        year = log.get_dates().toString();
        url = log.get_cs_uri_stem();
        response = log.get_sc_status(); 
      }
      if (response.equals("200")) 
      {
        context.write(new Text(year), new Text(url));
      }
    }
    
  }

  public static class MaxVisitedUrlPerdayReducer extends Reducer<Text, Text, Text,Text > {
    public void reduce(Text key, Iterable<Text> values, Context context)
        throws IOException, InterruptedException {
    
    HashMap<String,Integer> dict = new HashMap<String,Integer>();
    int start_value = 1;
    for (Text temp_url : values) {
      String url = temp_url.toString();
      if(url.length() > 1)
      {
        if(!dict.containsKey(url)){
          dict.put(url,start_value);
        }
        else{
          dict.put(url,dict.get(url)+1);            
        }
      }
    }  

    String final_url="";
    int visiter_count = 0;

    for (String url: dict.keySet()){
      int tmpCount = dict.get(url);
      if (tmpCount>visiter_count){
        final_url = url;
        visiter_count = tmpCount;
      }
    }
    context.write(new Text(final_url), new Text(key));
    }

  }

  public int run(String [] args) throws Exception {
    Job job = new Job(getConf());

    job.setJarByClass(MaxVisitedUrlPerday.class);
    job.setMapperClass(MaxVisitedUrlPerdayMapper.class);
    job.setReducerClass(MaxVisitedUrlPerdayReducer.class);

    FileInputFormat.addInputPath(job, new Path("large_log"));
    FileOutputFormat.setOutputPath(job, new Path("large_perday"));
    job.setMapOutputKeyClass(Text.class);   
    job.setMapOutputValueClass(Text.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    job.setNumReduceTasks(1);

    if (!job.waitForCompletion(true)) {
      return 1; // error.
    }
    return 0;
  }

  public static void main(String [] args) throws Exception {
    int ret = ToolRunner.run(new MaxVisitedUrlPerday(), args);
    System.exit(ret);
    
  }
}
