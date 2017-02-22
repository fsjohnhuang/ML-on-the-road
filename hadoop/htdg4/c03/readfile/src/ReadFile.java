/**
 * Created by john on 17-2-16.
 */
public class ReadFile {
    public static void main(String[] args) throws Exception {
        switch (args[0]){
            case "1":
                ReadByURL readByURL = new ReadByURL();
                readByURL.read();
                break;
            case "2":
                ReadByAPI readByAPI = new ReadByAPI();
                readByAPI.read();
                break;
            case "3":
                WriteFile writeFile = new WriteFile();
                writeFile.write();
                break;
        }
    }
}
