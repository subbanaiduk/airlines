// F. Find out largest county in terms of area in NE zone.
3- zone
4- area

public class Map extends Mapper<IntWritable, Text, IntWritable, IntWritable> {
    //private final static IntWritable one = new IntWritable(1);
    private IntWritable Zone = new IntWritable();

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String fields = line.split(",");
		int zone = Integer.parseInt(fields[2]);
	    int area = Integer.parseInt(fields[3]);
		if(zone == 1) 
			word.set(fields[0]);
		    context.write(area, word);
	    }              
    }
}

public class Reduce extends Reducer<IntWritable, Text, Text, IntWritable> {
        public void reduce(Iterable<IntWritable> values, Text key, Context context) throws IOException, InterruptedException{
			int count = 0;
        ListIterator<IntWritable> listIter = values.listIterator(values.size()-1);
        while (listIter.hasPrevious()) {
            IntWritable it = listIter.previous();
			if(count++ > 1) 
				break;
			context.write(key, new IntWritable(value));
        }
    }
}
