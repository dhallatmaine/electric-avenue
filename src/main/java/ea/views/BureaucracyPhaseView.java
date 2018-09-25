package ea.views;

import ea.data.Player;
import ea.data.PowerPlant;
import ea.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

@Component
public class BureaucracyPhaseView {

  private final ResourceService resourceService;
  private DefaultView defaultView;
  private Scanner scan;

  @Autowired
  public BureaucracyPhaseView(ResourceService resourceService) {
    this.resourceService = resourceService;
    defaultView = new DefaultView();
    scan = new Scanner(System.in);
  }

  public int getResourceAmountFromUser(String resource, int size) {
    // get resource type from user
    Scanner scan = new Scanner(System.in);

    int choice = 0;
    boolean invalidInput = true;
    while (invalidInput) {
      defaultView.outln("How much " + resource + " would you like to turn in?");
      if (scan.hasNextInt()) {
        choice = scan.nextInt();

        if (choice <= size && choice >= 0) {
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

  public int getPoweredCitiesFromUser(Player player) {
    List<PowerPlant> plants = new LinkedList<PowerPlant>(player.getPowerPlants());
    int choice = 0;
    boolean invalidInput = true;
    while (invalidInput) {
      defaultView.outln("Which power plant would you like to power?");
      Iterator playerPlantsItr = plants.iterator();

      int count = 1;
      while (playerPlantsItr.hasNext()) {
        PowerPlant pp = (PowerPlant) playerPlantsItr.next();
        defaultView.outln(count++ + ".) Plant: [" + pp.getValue() + "] Resource: [" + resourceService.getResourceNameByConst(pp.getType()) + ": " + pp.getResources() + "] Cities: [" + pp.getPoweredCities() + "]");
      }
      if (scan.hasNext()) {
        choice = scan.nextInt();

        if (choice - 1 < player.getPowerPlants().size()) {
          invalidInput = false;
        } else {
          defaultView.outln("Invalid selection, try again.");
        }
      } else {
        defaultView.outln("Invalid selection, try again.");
      }
    }
    return 0;
  }

}