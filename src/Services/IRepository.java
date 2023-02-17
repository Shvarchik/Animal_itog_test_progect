package Services;

import java.util.List;

public interface IRepository <T> {
    

        List <T> GetAll();

        T GetById(int id);

        void Create(T item);

        void Update(T item);  

        void Remove(int item);  

}
