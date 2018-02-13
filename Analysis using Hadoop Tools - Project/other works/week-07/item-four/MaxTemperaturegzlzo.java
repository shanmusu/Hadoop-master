// cimport org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.compress.GzipCodec;
import com.hadoop.compression.lzo.LzopCodec
import org.apache.hadoop.io.compress.CompressionCodec;

public class MaxTemperaturelzo {

  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      System.err.println("Usage: MaxTemperature <input path> <output path>");
      System.exit(-1);
    }

    Configuration conf = new Configuration();
    

    Job job = new Job(conf);

    conf.setBoolean(Job.MAP_OUTPUT_COMPRESS, true);
    conf.setClass(Job.MAP_OUTPUT_COMPRESS_CODEC, LzopCodec.class,CompressionCodec.class);


    job.setJarByClass(MaxTemperaturelzo.class);
    job.setJobName("Max temperature");

    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));


    job.setMapperClass(MaxTemperatureMapper.class);
    job.setReducerClass(MaxTemperatureReducer.class);

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);

    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}