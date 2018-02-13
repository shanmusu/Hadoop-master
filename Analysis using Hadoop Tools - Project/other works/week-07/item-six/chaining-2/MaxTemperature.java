import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.util.*;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
public class MaxTemperature {

  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      System.err.println("Usage: MaxTemperature <input path> <output path>");
      System.exit(-1);
    }
 Configuration chainning = new Configuration();
    Job job1 = Job.getInstance(chainning);
	job1.setMapperClass(MaxTemperatureMapper.class);
	job1.setReducerClass(MaxTemperatureReducer.class);
    job1.setJobName("Max temperature");
   	job1.setOutputKeyClass(Text.class);
   	job1.setOutputValueClass(IntWritable.class);
   	Path outputPath=new Path("Job1MRchain");
   	FileInputFormat.addInputPath(job1,new Path(args[0]));
   	FileOutputFormat.setOutputPath(job1,outputPath);
    outputPath.getFileSystem(chainning).delete(outputPath);
   	job1.waitForCompletion(true);

 Configuration chainning2=new Configuration();
   Job job2=Job.getInstance(chainning2);
   job2.setMapperClass(MaxTemperatureMapper1.class);
   job2.setReducerClass(MaxTemperatureReducer1.class);
   job2.setOutputKeyClass(Text.class);
   job2.setOutputValueClass(IntWritable.class);
   Path outputPath2=new Path(args[1]);
   FileInputFormat.addInputPath(job2, outputPath);
   FileOutputFormat.setOutputPath(job2, outputPath2);
   outputPath2.getFileSystem(chainning2).delete(outputPath2, true);
   System.exit(job2.waitForCompletion(true)?0:1);

  }
}