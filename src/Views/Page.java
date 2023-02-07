package Views;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import Views.interfaces.IComponent;
import Views.interfaces.IInputComponent;
import Views.interfaces.IOutputComponent;

public class Page implements IInputComponent, IOutputComponent {
     private String title;
     private List<String> choses;
     private Integer selection;
     private List<IComponent> components;
     private Map<String, String> args;

     public Page() {
          this.components = new ArrayList<>();
          this.choses = new ArrayList<>();
     }

     public Page(String title) {
          this();
          this.title = title;
     }

     public Map<String, String> getArgs() {
          return args;
     }

     public Page addComponents(IComponent component) {
          this.components.add(component);
          return this;
     }

     public Page addChoses(String chose) {
          this.choses.add(chose);
          return this;
     }

     public Integer getSelection() {
          return selection;
     }

     @Override
     public Page display() {
          if ((choses == null || choses.size() == 0)
                    && (title == null || title.length() == 0)
                    && (components == null || components.isEmpty()))
               return this;
          System.out.println("======================");
          if (title != null && title.length() > 0) {
               System.out.println(title.toUpperCase());
               System.out.println("----------------------");
          }
          body();
          if (choses != null && choses.size() > 0) {
               try {
                    for (int i = 0; i < choses.size(); i++) {
                         System.out.println((i + 1) + ". " + choses.get(i));
                    }
                    System.out.print("Plese enter the selection:");
                    selection = sc.nextInt();
                    sc.nextLine();
               } catch (InputMismatchException inputExceptions) {
                    System.err.println("Your input were wrong!");
               } catch (NoSuchElementException | IllegalStateException systemException) {
                    System.err.println("\nCan't input selection by " + systemException.getMessage());
               }
          }
          System.out.println("======================");
          return this;
     }

     protected void body() {
          if (components != null && components.size() > 0) {
               for (IComponent component : components) {
                    component.display();
                    if (IOutputComponent.class.isAssignableFrom(component.getClass())) {
                         IOutputComponent nComponent = (IOutputComponent) component;
                         args = nComponent.getArgs();
                    }
               }
               System.out.println("----------------------");
          }
     }

     public Page reset() {
          this.args.clear();
          this.choses.clear();
          this.components.clear();
          this.selection = null;
          this.title = null;
          return this;
     }
}
