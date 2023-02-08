package Controllers;

import java.util.Map;

import Models.TransferObjects.Animal;
import Models.TransferObjects.Type;
import Views.*;
import Views.interfaces.IComponent;

public class HomePage extends PageController {
     public HomePage() {
          super(new Page()
                    .addChoses("Add new animal")
                    .addChoses("Update animal")
                    .addChoses("Delete animal")
                    .addChoses("Search animal")
                    .addChoses("Show list animal")
                    .addChoses("Exit"));
     }

     @Override
     protected void excuted() {
          Page page;
          IComponent component;
          PageController pageController;
          Map<String, String> args;
          try {
               switch (selection) {
                    case 1:
                         try {
                              page = new Page()
                                        .addComponents(new Table<Type>("Type", zoo.getTypes()))
                                        .addComponents(new Form("Add animal")
                                                  .addLabel("Name", "",
                                                            p -> !p.isEmpty())
                                                  .addLabel("Type", "(0 - " + (zoo.getTypes().size() - 1) + ")",
                                                            p -> p.matches("^\\d{1,4}$")
                                                                      && Integer.parseInt(p) >= 0
                                                                      && Integer.parseInt(p) < zoo.getTypes().size())
                                                  .addLabel("Weight", "(Kg)",
                                                            p -> p.matches("^(0|([1-9][0-9]*))(\\.[0-9]+)?$")
                                                                      && Double.parseDouble(p) > 0)
                                                  .addLabel("Others", "(Optional)"));
                              args = page.display().getArgs();
                              zoo.add(args.get("Name"),
                                        zoo.getTypes().get(Integer.parseInt(args.get("Type"))),
                                        Double.parseDouble(args.get("Weight")),
                                        (args.get("Others") != null && args.get("Others").length() > 0)
                                                  ? args.get("Others").split(",")
                                                  : null);
                              component = new Messager("Success", "Add success");
                              component.display();
                         } catch (RuntimeException runtime) {
                              component = new Messager("Error", runtime.getMessage());
                              component.display();
                         } finally {
                              if (new Confirm("Do you want continue?", "Continue").display().getSelection() == 2)
                                   selection = null;
                         }

                         break;

                    case 2:
                         try {
                              page = new Page()
                                        .addComponents(new Table<Animal>("Animal", zoo.showAll()))
                                        .addComponents(new Table<Type>("Type", zoo.getTypes()))
                                        .addComponents(new Form("Update animal")
                                                  .addLabel("ID", "", p -> zoo.isExisted(p))
                                                  .addLabel("Name", "")
                                                  .addLabel("Type", "(0 - " + (zoo.getTypes().size() - 1) + ")",
                                                            p -> p.isEmpty()
                                                                      || p.matches("^\\d{1,4}$"))
                                                  .addLabel("Weight", "(Kg)",
                                                            p -> p.isEmpty()
                                                                      || p.matches("^(0|([1-9][0-9]*))(\\.[0-9]+)?$"))
                                                  .addLabel("State", "(true or false)",
                                                            p -> p.isEmpty()
                                                                      || p.equalsIgnoreCase("true")
                                                                      || p.equalsIgnoreCase("false"))
                                                  .addLabel("Others", ""));
                              args = page.display().getArgs();
                              zoo.update(args.get("ID"),
                                        args.get("Name").length() > 0
                                                  ? args.get("Name")
                                                  : null,
                                        args.get("Type").length() > 0
                                                  ? zoo.getTypes().get(Integer.parseInt(args.get("Type")))
                                                  : null,
                                        args.get("Weight").length() > 0
                                                  ? Double.parseDouble(args.get("Weight"))
                                                  : null,
                                        args.get("State").length() > 0
                                                  ? args.get("State").equalsIgnoreCase("true")
                                                  : null,
                                        (args.get("Others") != null && args.get("Others").length() > 0)
                                                  ? args.get("Others").split(",")
                                                  : null);
                              component = new Messager("Success", "Update success");
                              component.display();
                         } catch (RuntimeException runtime) {
                              component = new Messager("Error", runtime.getMessage());
                              component.display();
                         } finally {
                              if (new Confirm("Do you want continue?", "Continue").display().getSelection() == 2)
                                   selection = null;
                         }
                         break;
                    case 3:
                         try {
                              page = new Page()
                                        .addComponents(new Table<Animal>("Animal", zoo.showAll()))
                                        .addComponents(new Form()
                                                  .addLabel("ID", "", p -> zoo.isExisted(p)));
                              page.display();
                              if (new Confirm(
                                        ("Do you want to delete " + page.getArgs().get("ID") + "?"), "Delete")
                                        .getSelection() == 1) {
                                   zoo.delete(page.getArgs().get("ID"));
                                   new Messager("Success", "Delete success").display();
                              }
                         } catch (RuntimeException runtime) {
                              component = new Messager("Error", runtime.getMessage());
                              component.display();
                         } finally {
                              if (new Confirm("Do you want continue?", "Continue").display().getSelection() == 2)
                                   selection = null;
                         }
                         break;
                    case 4:
                         pageController = new SearchPage();
                         pageController.run();
                         selection = null;
                         break;
                    case 5:
                         pageController = new ShowPage();
                         pageController.run();
                         selection = null;
                         break;
                    case 6:
                         if (new Confirm("Do you want to exit", "Exit").display().getSelection() == 1)
                              System.exit(0);
                         break;

                    default:
                         component = new Messager("Error", "Don't have this slection!");
                         component.display();
                         selection = null;
                         break;
               }
          } catch (RuntimeException runtime) {
               component = new Messager("Error", runtime.getMessage());
               component.display();
          }
     }
}
