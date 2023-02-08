package Controllers;

import Models.Zoo;
import Views.Messager;
import Views.Page;

public abstract class PageController implements IControllers {
     protected Integer selection;
     protected Zoo zoo;
     private Page page;

     public PageController() {
          zoo = new Zoo();
     }

     public PageController(Page page) {
          this();
          this.page = page;
     }

     @Override
     public void run() {
          while (true) {
               try {
                    if (selection == null) {
                         page.display();
                         selection = page.getSelection();
                    }
                    excuted();
               } catch (RuntimeException runtime) {
                    new Messager("Error", runtime.getMessage()).display();
               }
               if (selection != null && selection == -1) {
                    break;
               }
          }
     }

     protected abstract void excuted();
}
