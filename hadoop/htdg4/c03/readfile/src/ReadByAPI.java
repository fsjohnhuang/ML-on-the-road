import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.InputStream;
import java.net.URI;

/**
 * Created by john on 17-2-16.
 */
public class ReadByAPI {
    public void read() throws Exception{
        String dfs = "hdfs:///input/input_data.txt";
        URI uri = URI.create(dfs);
        Configuration conf = new Configuration();
        FileSystem fs = null;
        InputStream in = null;
        try{
            fs = FileSystem.get(uri, conf);
            in = fs.open(new Path(dfs));
            IOUtils.copyBytes(in, System.out, 4096, false);
        }
        finally {
            IOUtils.closeStream(in);
        }
    }
}
