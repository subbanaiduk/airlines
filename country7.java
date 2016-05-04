//G. Find out least populated country in S.America landmass.

landmass -2
population - 5

public class Map extends Mapper<Text, Text, Text, IntWritable> {    
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String fields = line.split(",");
        
		int landmass = Integer.parseInt(fields[1]);
	    int pop = Integer.parseInt(fields[4]);
		if(landmass == 2) 
		     context.write(pop,new Text(fields[0]));
	    }              
    }
}

public class Reduce extends Reducer<IntWritable,Text, Text, IntWritable> {
        public void reduce(Iterable<IntWritable> values,Text key, Context context) throws IOException, InterruptedException{
	    
        int count = 0;
        ListIterator<IntWritable> listIter = values.listIterator();
		//listIter.get(1);
		
        while (listIter.hasPrevious()) {
            IntWritable it = listIter.previous();
			if(count++ > 1) 
				break;
			context.write(key, new IntWritable(value));
        }		
    }
}
