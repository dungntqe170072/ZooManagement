package Models.DataAccessObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class DAO<T> implements IDAO<T> {
     protected String fileName;
     protected List<T> t;

     public abstract List<T> getAll();

     public abstract Optional<T> get(String id);

     public abstract Stream<T> get(Predicate<T> p);

     public abstract void create(T t);

     public abstract void update(T t);

     public abstract void delete(T t);

     public void save() {
          if (fileName == null || fileName.equals("") || fileName.matches("\\.(dat|DAT)")) {
               throw new RuntimeException("File name is invalid!");
          }
          File file = new File("Database", fileName);
          try {
               if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.getAbsoluteFile().createNewFile();
               }
               try (FileOutputStream fStream = new FileOutputStream(file)) {
                    ObjectOutputStream oStream = new ObjectOutputStream(fStream);
                    oStream.writeObject(t);
               } catch (FileNotFoundException fileNotFoundException) {
                    throw new RuntimeException("File not found!");
               } catch (IOException ioException) {
                    throw new RuntimeException(ioException.getCause());
               } catch (NullPointerException nullException) {
                    throw new NullPointerException("File is empty!");
               }
          } catch (IOException e) {
               throw new RuntimeException("Can't create new file");
          }
     }

     public void load() {
          if (fileName == null || fileName.equals("") || fileName.matches("\\.(dat|DAT)")) {
               throw new RuntimeException("File name is invalid!");
          }
          File file = new File("Database", fileName);
          try {
               if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.getAbsoluteFile().createNewFile();
               }
               try (FileInputStream fStream = new FileInputStream(file)) {
                    if (fStream.available() == 0)
                         return;
                    ObjectInputStream oStream = new ObjectInputStream(fStream);
                    t = (List<T>) oStream.readObject();
               } catch (IOException ioException) {
                    throw new RuntimeException(ioException.getCause());
               } catch (ClassNotFoundException classNotFoundException) {
                    System.err.println(classNotFoundException.getLocalizedMessage());
               } catch (NullPointerException nullException) {
                    throw new NullPointerException("File is empty!");
               }
          } catch (FileNotFoundException fileNotFoundException) {
               throw new RuntimeException("File not found", fileNotFoundException.getCause());
          } catch (IOException ioException) {

          }
     }
}
