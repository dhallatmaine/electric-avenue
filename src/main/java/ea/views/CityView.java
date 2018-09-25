package ea.views;

import ea.data.City;
import ea.data.Player;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class CityView {

  private DefaultView defaultView;
  private Scanner scan;
  private PlayerView playerView;

  public CityView() {
    defaultView = new DefaultView();
    playerView = new PlayerView();
    scan = new Scanner(System.in);
  }

  public void displayCities(List<City> cities) {
    Iterator citiesItr = cities.iterator();
    int count = 1;
    while (citiesItr.hasNext()) {
      City c = (City) citiesItr.next();

      defaultView.outln(count++ + ".) " + c.getName() + " Region: " + c.getRegion());
      String phase1 = "Empty";
      String phase2 = "Empty";
      String phase3 = "Empty";
      if (c.getPhase1() != null) {
        phase1 = c.getPhase1().getName();
      }
      if (c.getPhase2() != null) {
        phase2 = c.getPhase2().getName();
      }
      if (c.getPhase3() != null) {
        phase3 = c.getPhase3().getName();
      }
      defaultView.outln("\tPhase1: " + phase1);
      defaultView.outln("\tPhase2: " + phase2);
      defaultView.outln("\tPhase3: " + phase3);
    }
  }

  public boolean getConfirmDoneBuilding() {
    String again = "";

    while (true) {
      defaultView.outln("Would you like to build in another city? [n/y]");
      if (scan.hasNext()) {
        again = scan.next();
        if (again.equals("n")) {
          return false;
        } else if (again.equals("y")) {
          return true;
        }
      }
    }
  }


  // get resource type from user
  public int getCityToBuildInFromUser(List<City> cities, Player player) {
    int choice = 0;
    boolean invalidInput = true;
    while (invalidInput) {
      defaultView.outln(player.getName() + ", which city would you like to build in?");
      if (scan.hasNextInt()) {
        choice = scan.nextInt();

        if (choice == 0) {
          defaultView.outln(player.getName() + " has chosen to skip building this turn.");
        }

        if (choice <= cities.size() && choice >= 0) {
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