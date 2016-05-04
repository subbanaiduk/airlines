https://pipiper.wordpress.com/2013/05/02/sorting-using-hadoop-totalorderpartitioner/
package wc;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.util.*;
 
//B. Find out top 5 country with Sum of bars and strips in a flag.

public class BarsStripes extends Configured implements Tool {
  public static void main(String args[]) throws Exception {
    int res = ToolRunner.run(new BarsStripes(), args);
    System.exit(res);
  }

  public int run(String[] args) throws Exception {
    Path inputPath = new Path(args[0]);
    Path outputPath = new Path(args[1]);

    Configuration conf = getConf();
    Job job = new Job(conf, this.getClass().toString());

    FileInputFormat.setInputPaths(job, inputPath);
    FileOutputFormat.setOutputPath(job, outputPath);

    job.setJobName("BarsStripes");
    job.setJarByClass(BarsStripes.class);
    job.setInputFormatClass(TextInputFormat.class);
    job.setOutputFormatClass(TextOutputFormat.class);
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(IntWritable.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);

    job.setMapperClass(Map.class);
    job.setCombinerClass(Reduce.class);
    job.setReducerClass(Reduce.class);

    return job.waitForCompletion(true) ? 0 : 1;
  }
  
  // B. Find out top 5 country with Sum of bars and strips in a flag. 
  //Afghanistan,5,1,648,16,10,2,0,3,5,1,1,0,1,1,1,0,green,0,0,0,0,1,0,0,1,0,0,black,green

  public class Map extends Mapper<IntWritable, Text, Text, IntWritable> {
	private Text Country = new Text();
	
	public void map(IntWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
	    String fields = line.split(",");
        int bars = Integer.parseInt(fields[7]);
		int stripes = Integer.parseInt(fields[8]);
		int sum = bars + stripes; 
        Country.set(fields[0]);
		context.write(new Text(Country), sum);	     	
	}
  
    public static class Reduce extends MapReduceBase implements Reducer<Text, IntWritable, IntWritable, Text> {

		public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
		
		int count = 0;
        ListIterator<IntWritable> listIter = values.listIterator(values.size()-1);
        while (listIter.hasPrevious()) {
            IntWritable it = listIter.previous();
			if(count++ > 5) 
				break;
			context.write(key, new IntWritable(value));
        }		
	}
}

