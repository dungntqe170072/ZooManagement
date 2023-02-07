package Controllers;

import java.util.List;

import Models.TransferObjects.Animal;
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
                         page = new Page();
                         zoo.getTypes().forEach(
                                   type -> {
                                        List<Animal> temp = zoo.showByType(type);
                                        if (temp.size() > 0)
                                             page.addComponents(new Table<Animal>(type.getKey(), zoo.showByType(type)));
                                   });
                         page.display();
                         break;

                    case 2:
                         page = new Page()
                                   .addComponents(new Table<>("Animal", zoo.showAll()));
                         page.display();
                         break;

                    case 3:
                         selection = -1;
                         break;

                    default:
                         break;
               }
          } catch (RuntimeException runtimeException) {
               component = new Messager("", runtimeException.getMessage());
               component.display();
          } finally {
               selection = selection == -1 ? -1 : null;
          }
     }
}
