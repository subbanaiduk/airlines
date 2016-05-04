//Count number of countries based on zone.



public class Map extends Mapper<IntWritable, Text, IntWritable, IntWritable> {
    private final static IntWritable one = new IntWritable(1);
    private IntWritable Zone = new IntWritable();

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
      
	    String fields = line.split(",");
        int zone = Integer.parseInt(fields[2]);
		switch(zone) {
			case 1:
			    word.set("NorthEast");
				break;
			case 2:
				word.set("SouthEast");
				break;				
			case 3:
				word.set("SouthWest");
			    break;
			case 4:
				word.set("NorthWest");
			    break;
		}
		context.write(word, one);	                  
    }
}

 public static class Reduce extends Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
      int sum = 0;
      for (IntWritable value : values) {
        sum += value.get();
      }
      context.write(key, new IntWritable(sum));
    }
  }

public class Reduce extends Reducer<IntWritable, IntWritable, Text, IntWritable> {
    private IntVariable result = new IntVariable();
    IntWritable NE, SE, SW, NW;
    int d1,d2,d3,d4;
    int count;
    Text NorthEast, SouthEast, SouthWest, NorthWest;

    public void reduce(IntWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException{
        for (IntWritable val:values){   
            if(val.get() == 1) {
			   NE.set(d1);
			   d1++;
			} else if(val.get() == 2) {
			   SE.set(d2);
			   d2++;
			} else if(val.get() == 3) {
			   SW.set(d3);
			   d3++;
			} else if(val.get() == 4) {
			   NW.set(d4);
			   d4++;
			}			
        }       
        context.write(new Text("NorthEast"), NE);
        context.write(new Text("SouthEast"), SE);
        context.write(new Text("SouthWest"), SW);
        context.write(new Text("NorthWest"), NW); 
    }
}
