package Views;

import Views.interfaces.IComponent;

public class Messager implements IComponent {
     String type;
     String message;

     public Messager() {
     }

     public Messager(String type, String message) {
          this.type = type;
          this.message = message;
     }

     @Override
     public Messager display() {
          if (type != null || type.length() > 0 || message != null || message.length() > 0) {
               System.out.println(type + " " + message);
          }
          return this;
     }
}
