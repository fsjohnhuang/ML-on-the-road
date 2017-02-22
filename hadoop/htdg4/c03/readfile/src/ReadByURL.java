import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by john on 17-2-16.
 */
public class ReadByURL {
    public void read() throws Exception{
        InputStream input = null;
        try {
            URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
            input = new URL("hdfs:///input/input_data.txt").openStream();
            IOUtils.copyBytes(input, System.out, 4096, false);
        }
        finally {
            IOUtils.closeStream(input);
        }
    }
}
