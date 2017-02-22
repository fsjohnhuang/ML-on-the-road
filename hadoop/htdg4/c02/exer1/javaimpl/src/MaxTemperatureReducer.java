import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by john on 17-2-16.
 */
public class MaxTemperatureReducer extends org.apache.hadoop.mapreduce.Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {
        int max = Integer.MIN_VALUE;
        for (IntWritable i : values) {
            max = Integer.max(i.get(), max);
        }

        context.write(key, new IntWritable(max));
    }
}
