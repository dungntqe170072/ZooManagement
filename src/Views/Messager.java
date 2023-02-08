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
          if (type != null && !type.isEmpty())
               System.out.println(type.toUpperCase());
          if (message != null && !message.isEmpty())
               System.out.println(message);
          return this;
     }
}
