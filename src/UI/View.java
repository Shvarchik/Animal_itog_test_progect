package UI;

import java.util.List;

public interface View <T> {
    
    String getName();
    String getBirthday();
    void print (String string);
    void printAll (List <T> list);

}
