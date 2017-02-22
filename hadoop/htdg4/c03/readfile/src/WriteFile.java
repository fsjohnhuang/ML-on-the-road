import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import java.net.URI;

/**
 * Created by john on 17-2-17.
 */
public class WriteFile {
    public void write() throws Exception{
        String dfs = "hdfs:///ohinput/data.txt";
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI(dfs), conf);
        FSDataOutputStream out = null;
        try{
            out = fs.create(new Path(dfs), new Progressable() {
                @Override
                public void progress() {
                    System.out.print(".");
                }
            });
            IOUtils.copyBytes(, out, 4096, false);
        }
        finally {
            IOUtils.closeStream(out);
        }
    }
}
