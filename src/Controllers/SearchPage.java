package Controllers;

import Views.Confirm;
import Views.Form;
import Views.Messager;
import Views.Page;
import Views.Table;
import Views.interfaces.IComponent;

public class SearchPage extends PageController {

     public SearchPage() {
          super(new Page("Search Page")
                    .addChoses("Search by Name")
                    .addChoses("Search by Weight")
                    .addChoses("Go back"));
     }

     @Override
     protected void excuted() {
          Page page;
          IComponent component;
          try {
               switch (selection) {
                    case 1:
                         page = new Page("Search by Name")
                                   .addComponents(new Form()
                                             .addLabel("Name", "", p -> p.length() != 0));
                         page.display();
                         String name = page.getArgs().get("Name");
                         new Table<>("Animal", zoo.searchByName(name)).display();
                         break;

                    case 2:
                         page = new Page("Search by Weight")
                                   .addComponents(new Form()
                                             .addLabel("Lower", "(kg)",
                                                       p -> p.matches("^(0|([1-9][0-9]*))(\\.[0-9]+)?$"))
                                             .addLabel("Upper", "(kg)",
                                                       p -> p.matches("^(0|([1-9][0-9]*))(\\.[0-9]+)?$")));
                         page.display();
                         Double lower = Double.parseDouble(page.getArgs().get("Lower"));
                         Double upper = Double.parseDouble(page.getArgs().get("Upper"));
                         page.reset().addComponents(new Table<>("Animal", zoo.searchByWeight(lower, upper)));
                         page.display();
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
          } finally {
               selection = selection == -1 ? -1 : null;
          }
     }

}
