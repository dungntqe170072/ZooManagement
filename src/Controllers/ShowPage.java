package Controllers;

import Views.Confirm;
import Views.Form;
import Views.Messager;
import Views.Page;
import Views.Table;
import Views.interfaces.IComponent;

public class ShowPage extends PageController {
     public ShowPage() {
          super(new Page("Show Animal")
                    .addChoses("Show by type")
                    .addChoses("Show all")
                    .addChoses("Go back"));
     }

     @Override
     protected void excuted() {
          Page page;
          IComponent component;
          try {
               switch (selection) {
                    case 1:
                         page = new Page()
                                   .addComponents(new Table<>("Type", zoo.getTypes()))
                                   .addComponents(new Form()
                                             .addLabel(
                                                       "Type",
                                                       "(0 - " + (zoo.getTypes().size() - 1) + ")",
                                                       p -> !p.isEmpty()
                                                                 && p.matches("^\\d+$")
                                                                 && Integer.parseInt(p) < zoo.getTypes().size()));
                         component = new Table<>(
                                   "Animal",
                                   zoo.showByType(
                                             zoo.getTypes().get(
                                                       Integer.parseInt(
                                                                 page.display()
                                                                           .getArgs()
                                                                           .get("Type")))));
                         component.display();
                         if (new Confirm("Do you want to continue?", "Continue")
                                   .display()
                                   .getSelection() == 2)
                              selection = null;
                         break;

                    case 2:
                         page = new Page()
                                   .addComponents(new Table<>("Animal", zoo.showAll()));
                         page.display();
                         selection = null;
                         break;

                    case 3:
                         if (new Confirm("Do you want to go back?", "Go back")
                                   .display()
                                   .getSelection() == 1)
                              selection = -1;
                         break;

                    default:
                         component = new Messager("Error", "Don't have this slection!");
                         component.display();
                         break;
               }
          } catch (RuntimeException runtimeException) {
               component = new Messager("", runtimeException.getMessage());
               component.display();
          }
     }
}
