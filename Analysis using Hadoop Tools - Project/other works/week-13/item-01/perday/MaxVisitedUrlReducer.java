import java.io.IOException;
import java.util.*;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxVisitedUrlReducer
  extends Reducer<Text, Text, Text, Text> {
  @Override
  public void reduce(Text key, Iterable<Text> values,Context context) throws IOException, InterruptedException {
    HashMap<String,Integer> dict = new HashMap<String,Integer>();
    int start_value = 1;
    for (Text temp_url : values) {
      String url = temp_url.toString();
      if(url.length() != 1)
      {
        if(!dict.containsKey(url)){
          dict.put(url,start_value);
        }
        else{
          dict.put(url,dict.get(url)+1);            
        }
      }
    }  
    Set set = dict.entrySet();
    Iterator i = set.iterator();
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
