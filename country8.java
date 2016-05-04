//Find out largest speaking language among all countries.

5 -- population
6 -- language
0 -- country


public class Map extends Mapper<Text, Text, Text, IntWritable> {    
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String fields = line.split(",");
        int pop = Integer.parseInt(fields[4]);
		context.write(pop, new Text(fields[5]));	                  
    }
}

  public class Reduce extends Reducer<IntWritable,Text, Text, IntWritable> {
    List<Integer> lang = new List<Integer>();
    
    public void reduce( Iterable<IntWritable> values,Text key, Context context) throws IOException, InterruptedException{
		int count = 0;
        ListIterator<IntWritable> listIter = values.listIterator(values.size()-1);
        while (listIter.hasPrevious()) {
            IntWritable it = listIter.previous();
			if(count++ > 1) 
				break;
			context.write(key, new IntWritable(it.get());
        }		
    }
}
