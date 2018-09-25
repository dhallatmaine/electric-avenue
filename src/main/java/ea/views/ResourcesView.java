package ea.views;

import ea.data.Resource;
import ea.services.ResourceService;

import java.util.Iterator;
import java.util.Scanner;

public class ResourcesView {

  private ResourceService resourceService;
  private DefaultView defaultView;

  public ResourcesView(ResourceService resourceService) {
    this.resourceService = resourceService;
    defaultView = new DefaultView();
  }

  public void displayResourceMarket() {
    defaultView.outln("------ Resource Market ------");

    defaultView.out("1.) Coal: ");
    Iterator coalItr = resourceService.getCoal().iterator();
    displayPrice(coalItr);

    defaultView.out("2.) Oil: ");
    Iterator oilItr = resourceService.getOil().iterator();
    displayPrice(oilItr);

    defaultView.out("3.) Trash: ");
    Iterator trashItr = resourceService.getTrash().iterator();
    displayPrice(trashItr);

    defaultView.out("4.) Uranium: ");
    Iterator uraniumItr = resourceService.getUranium().iterator();
    displayPrice(uraniumItr);
  }

  public void displayPrice(Iterator itr) {
    while (itr.hasNext()) {
      Resource resource = (Resource) itr.next();
      defaultView.out(resource.getPrice() + " ");
    }
    defaultView.outln();
  }

  public int getResourceTypeFromUser() {
    // get resource type from user
    Scanner scan = new Scanner(System.in);

    int choice = 0;
    boolean invalidInput = true;
    while (invalidInput) {
      defaultView.outln("Which type of resource would you like to purchase?");
      if (scan.hasNextInt()) {
        choice = scan.nextInt();

        if (choice <= 4) {
          invalidInput = false;
        } else {
          defaultView.outln("Invalid selection, try again.");
        }
      } else {
        defaultView.outln("Invalid selection, try again.");
        scan.next();
      }
    }
    return choice;
  }

}