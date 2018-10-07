package ea.views;

import ea.data.ResourceEnum;
import ea.engine.GameState;
import ea.engine.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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
    List<Integer> coalItr = game.getResources().get(ResourceEnum.COAL);
    displayPrice(coalItr);

    defaultView.out("2.) Oil: ");
    List<Integer> oilItr = game.getResources().get(ResourceEnum.OIL);
    displayPrice(oilItr);

    defaultView.out("3.) Trash: ");
    List<Integer> trashItr = game.getResources().get(ResourceEnum.TRASH);
    displayPrice(trashItr);

    defaultView.out("4.) Uranium: ");
    List<Integer> uraniumItr = game.getResources().get(ResourceEnum.URANIUM);
    displayPrice(uraniumItr);
  }

  public void displayPrice(List<Integer> resources) {
    resources.forEach(System.out::print);
    System.out.println();
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