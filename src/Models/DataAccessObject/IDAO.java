package Models.DataAccessObject;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface IDAO<T> {
     public List<T> getAll();

     public Optional<T> get(String id);

     public Stream<T> get(Predicate<T> p);

     public void create(T t);

     public void update(T t);

     public void delete(T t);

     public void save();

     public void load();
}
