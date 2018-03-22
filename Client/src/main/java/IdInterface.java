import java.io.IOException;
import java.util.ArrayList;

public interface IdInterface {
    String get_ids(ArrayList<String> list) throws IOException;
    String saveId(ArrayList<String> list) throws IOException;
}
