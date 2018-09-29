package ea.views;

import ea.data.Resource;
import ea.engine.GameState;
import ea.engine.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Scanner;

@Component
public class ResourcesView {

  private final DefaultView defaultView;
  private final GameState gameState;

  @Autowired
  public ResourcesView(GameState gameState) {
    this.gameState = gameState;
    defaultView = new DefaultView();
  }

  public void displayResourceMarket(Integer gameId) {
    State game = gameState.getById(gameId);
    defaultView.outln("------ Resource Market ------");

    defaultView.out("1.) Coal: ");
    Iterator coalItr = game.getCoal().iterator();
    displayPrice(coalItr);

    defaultView.out("2.) Oil: ");
    Iterator oilItr = game.getOil().iterator();
    displayPrice(oilItr);

    defaultView.out("3.) Trash: ");
    Iterator trashItr = game.getTrash().iterator();
    displayPrice(trashItr);

    defaultView.out("4.) Uranium: ");
    Iterator uraniumItr = game.getUranium().iterator();
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