package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import Models.DAOConcrete.AnimalDAO;
import Models.DataAccessObject.IDAO;
import Models.TransferObjects.Animal;
import Models.TransferObjects.Property;
import Models.TransferObjects.Type;

public class Zoo {
     IDAO<Animal> animals;
     private List<Type> types;

     public List<Type> getTypes() {
          return types;
     }

     public Zoo() {
          animals = new AnimalDAO();
          types = new ArrayList<>();
          types.add(new Type("0-legged",
                    new Property("Leg", "No-leg"), new Property("move", "crawl")));
          types.add(new Type("bipedal and flightless",
                    new Property("Leg", "Two leg"), new Property("move", "run")));
          types.add(new Type("bipedal and flying",
                    new Property("Leg", "Two leg"), new Property("move", "fly")));
          types.add(new Type("4-legged",
                    new Property("Leg", "Four Leg"), new Property("move", "run")));
     }

     public List<Animal> showAll() {
          return animals.getAll();
     }

     public List<Animal> showByType(Type type) {
          return animals.get(p -> p.getType().getKey().equals(type.getKey()))
                    .collect(Collectors.toList());
     }

     public void add(String name, Type type, Double weight, String... properties) {
          String ID = ("" + name.charAt(0) + name.charAt(name.length() - 1)).toUpperCase();
          animals.create(new Animal(ID, name, type,
                    new Property("Weight", weight + "Kg"),
                    new Property("Others", properties)));
     }

     public List<Animal> searchByName(String name) {
          if (name == null || name.length() == 0)
               return animals.getAll();
          return animals.get(
                    p -> p.getName()
                              .toLowerCase()
                              .contains(
                                        name.toLowerCase()))
                    .collect(Collectors.toList());
     }

     public List<Animal> searchByWeight(Double lowerBound, Double upperBound) {
          if (lowerBound > upperBound)
               throw new RuntimeException("Lower must lower than upper");

          return animals.get((p) -> {
               for (Property property : p.getProperties()) {
                    if (property.getKey().equalsIgnoreCase("weight")) {
                         Double weight = Double.parseDouble(property.getValues()[0].split("[a-zA-Z]", 0)[0]);
                         return lowerBound <= weight && weight <= upperBound;
                    }
               }
               return false;
          }).collect(Collectors.toList());
     }

     public void update(String ID, String name, Type type, Double weight, Boolean state, String... others) {
          if (!animals.get(ID).isPresent())
               throw new RuntimeException("Not Existed!");
          Animal current = animals.get(ID).get();
          Animal nAnimal = new Animal();
          nAnimal.setID(ID);
          nAnimal.setName(name != null ? name : current.getName());
          nAnimal.setState(state != null ? state : current.getState());
          nAnimal.setType(type != null ? type : current.getType());
          nAnimal.setProperties();

          for (Property propertie : current.getProperties()) {
               switch (propertie.getKey().toLowerCase()) {
                    case "weight":
                         nAnimal.addProperty((weight != null)
                                   ? new Property("Weight", weight + "kg")
                                   : propertie);
                         break;

                    case "others":
                         nAnimal.addProperty((others != null && !propertie.equals(new Property("Others", others)))
                                   ? new Property("Others", others)
                                   : propertie);
                         break;
                    default:
                         break;
               }
          }

          animals.update(nAnimal);
     }

     public void delete(String ID) {
          if (!isExisted(ID))
               throw new RuntimeException("Not Existed");
          animals.delete(animals.get(ID).get());
     }

     public boolean isExisted(String ID) {
          return animals.get(ID).isPresent();
     }
}