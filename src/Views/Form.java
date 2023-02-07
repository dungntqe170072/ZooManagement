package Views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import Views.interfaces.IInputComponent;
import Views.interfaces.IOutputComponent;

public class Form implements IInputComponent, IOutputComponent {
     private String title;
     private List<String> labels;
     private List<String> descritions;
     private List<String> args;
     private List<Predicate<String>> checkVaild;

     public Form() {
          labels = new ArrayList<>();
          args = new ArrayList<>();
          checkVaild = new ArrayList<>();
          descritions = new ArrayList<>();
     }

     public Form(String title) {
          this();
          this.title = title;
     }

     public Map<String, String> getArgs() {
          Map<String, String> newArgs = new HashMap<>();
          for (int i = 0; i < labels.size(); i++) {
               newArgs.put(labels.get(i), args.get(i));
          }
          return newArgs;
     }

     @Override
     public Form display() {
          if (title != null && title.length() > 0) {
               System.out.println(title.toUpperCase());
               System.out.println("----------------------");
          }
          try {
               for (int i = 0; i < labels.size(); i++) {
                    System.out.print(labels.get(i) + descritions.get(i) + ": ");
                    String temp = sc.nextLine();
                    if (checkVaild.size() > 0 && checkVaild.get(i) != null && !checkVaild.get(i).test(temp))
                         throw new RuntimeException(labels.get(i) + " is invalid");
                    args.add(i, temp);
               }
          } catch (NoSuchElementException | IllegalStateException exception) {
               System.err.println("Can't input label by " + exception.getMessage());
          } catch (NullPointerException nullException) {
               System.err.println("Can't input for it form");
          }

          return this;
     }

     public Form addLabel(String label, String description) {
          addLabel(label, description, null);
          return this;
     }

     public Form addLabel(String label, String description, Predicate<String> p) {
          this.labels.add(label);
          this.descritions.add(description);
          this.checkVaild.add(p);
          return this;
     }

}
