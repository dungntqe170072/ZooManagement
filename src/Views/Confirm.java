package Views;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import Views.interfaces.IInputComponent;

public class Confirm implements IInputComponent {
     private String ask;
     private int selection;
     private String confirm;

     public Confirm() {
     }

     public Confirm(String ask) {
          this.ask = ask;
     }

     public Confirm(String ask, String confirm) {
          this.ask = ask;
          this.confirm = confirm;
     }

     public int getSelection() {
          return selection;
     }

     @Override
     public Confirm display() {
          if (ask != null && ask.length() > 0) {
               try {
                    System.out.println(ask);
                    System.out.println("1. " +
                              ((confirm != null && confirm.length() != 0) ? this.confirm : "Confirm"));
                    System.out.println("2. Cancel");
                    System.out.print("Enter selection:");
                    selection = sc.nextInt();
                    sc.nextLine();
               } catch (InputMismatchException inputExceptions) {
                    System.err.println("Your input were wrong!");
               } catch (NoSuchElementException | IllegalStateException systemException) {
                    System.err.println("\nCan't input selection by " + systemException.getMessage());
               }
          }
          return this;
     }

}
