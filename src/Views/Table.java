package Views;

import java.util.List;

import Views.interfaces.IComponent;

public class Table<T> implements IComponent {
     String title;
     List<T> args;

     public Table() {
     }

     public Table(String title, List<T> args) {
          this.title = title;
          this.args = args;
     }

     @Override
     public Table<T> display() {
          if (title != null || title.length() > 0) {
               System.out.println(title.toUpperCase());
               System.out.println("----------------------");
          }
          if (args != null || args.size() > 0) {
               args.forEach(arg -> {
                    System.out.println("" + args.indexOf(arg) + ". " + arg);
               });
               System.out.println("----------------------");
          }
          return this;
     }
}
