package Models.DAOConcrete;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import Models.DataAccessObject.DAO;
import Models.TransferObjects.Animal;

public class AnimalDAO extends DAO<Animal> {
     public AnimalDAO() {
          fileName = "Animals.dat";
          load();
          if (t == null) {
               t = new ArrayList<>();
          }
     }

     @Override
     public List<Animal> getAll() {
          load();
          if (t == null || t.size() == 0) {
               throw new NullPointerException("Don't have any animal");
          }
          return t;
     }

     @Override
     public Optional<Animal> get(String id) {
          load();
          try {
               return t.stream()
                         .filter(animal -> animal.getID().equalsIgnoreCase(id))
                         .findFirst();
          } catch (NullPointerException e) {
               throw new NullPointerException("Don't have any animal");
          }
     }

     @Override
     public Stream<Animal> get(Predicate<Animal> p) {
          return t.stream().filter(p);
     }

     @Override
     public void create(Animal animal) {
          load();
          if (get(animal.getID()).isPresent()) {
               throw new RuntimeException("Existed!");
          }
          t.add(animal);
          save();
     }

     @Override
     public void update(Animal animal) {
          load();
          if (!get(animal.getID()).isPresent()) {
               throw new NullPointerException("Not existed!");
          }
          Animal current = get(animal.getID()).get();
          current.setName(animal.getName());
          current.setType(animal.getType());
          current.setProperties(animal.getProperties());
          current.setState(animal.getState());
          save();
     }

     @Override
     public void delete(Animal animal) {
          load();
          if (!t.remove(animal)) {
               throw new NullPointerException("Not existed!");
          }
          save();
     }
}
