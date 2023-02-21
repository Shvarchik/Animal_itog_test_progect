package Services;

import java.util.List;

public interface IRepository <T> {
  
        List <T> getAll();
        T getById(int id);
        void create(T item);
        void update(T item);  
        void delete (int item);  
}
