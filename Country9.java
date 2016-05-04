
 //Find most common colour among flags from all countries.
 
 colors -- 11-17
 
 
   public static class Map extends Mapper<LongWritable, Text, Text, IntWritable> {
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    @Override
    public void map(LongWritable key, Text value,
                    Mapper.Context context) throws IOException, InterruptedException {
      String line = value.toString();
      int[] colors = new int[6];
	  String[] colorText = {"Red","Green","Blue","Gold","White","Black"};
      while (line != null) {
	    String fields = line.split(",");
		   switch()
		   for(int i=0;i<6;i++) {
              int color = Integer.parseInt(fields[i+11]);
			  word.set(colorText[i]);
			  switch(color) {
				  case 11:				     
				     context.write(word, one);
					 break;
				  case 12:
					 context.write(word, one);
					 break;
				  case 13:
					 context.write(word, one);
					 break;
				  case 14:
					 context.write(word, one);
					 break;
				  case 15:
					 context.write(word, one);
					 break;
				  case 16:				  
				     context.write(word, one);
					 break;
			  }   	          
		   }        
		}
      }
    }
  }
   
   
  public static class IntPairPartialSumCombiner
	extends Reducer<Text,IntPair,Text,IntPair> {
	private IntPair result = new IntPair();

	public void reduce(Text key, Iterable<IntPair> values,
			   Context context
			   ) throws IOException, InterruptedException {
	    int sum = 0;

	    for (IntPair val : values) {
		    sum += val.get();		
	    }	    
	    context.write(key, sum);
	}
    }


  public static class Reduce extends Reducer<IntWritable, IntWritable, Text, IntWritable> {
    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
      int sum = 0;
      for (IntWritable value : values) {
		sum += value.get();
      }
      context.write(key, sum));
    }
  }
  
  
  

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  