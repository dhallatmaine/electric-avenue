package ea.views;

import ea.data.PowerPlant;

import java.util.Iterator;
import java.util.List;

public class PowerPlantsView {

  private DefaultView defaultView;

  public PowerPlantsView() {
    defaultView = new DefaultView();
  }

  public void displayCurrentMarketPlants(List<PowerPlant> currentMarketPlants) {
    Iterator itr = currentMarketPlants.iterator();
    defaultView.outln("------Current Market------");
    int count = 1;
    while (itr.hasNext()) {
      PowerPlant p = (PowerPlant) itr.next();
      defaultView.outln(count++ + ".) Card - value: " + p.getValue().toString());
    }
  }

  public void displayFutureMarketPlants(List<PowerPlant> futureMarketPlants) {
    Iterator itr = futureMarketPlants.iterator();
    defaultView.outln("------Future Market------");
    int count = 5;
    while (itr.hasNext()) {
      PowerPlant p = (PowerPlant) itr.next();
      defaultView.outln(count++ + ".) Card - value: " + p.getValue().toString());
    }
  }

  public void displayDeckPlants(List<PowerPlant> deckPlants) {
    Iterator itr = deckPlants.iterator();
    defaultView.outln("------Begin Deck------");
    while (itr.hasNext()) {
      PowerPlant p = (PowerPlant) itr.next();
      if (p.getValue() == 0) {
        defaultView.outln("STEP [3] CARD");
      } else {
        defaultView.outln("Card - value: " + p.getValue().toString());
      }
    }
    defaultView.outln("------End Deck-------");
  }

  public void displayPlayerPlants(List<PowerPlant> playerPlants) {
    Iterator itr = playerPlants.iterator();
    defaultView.outln("----- Your Plants -----");
    int count = 1;
    while (itr.hasNext()) {
      PowerPlant p = (PowerPlant) itr.next();
      defaultView.outln(count++ + ".) Card - value: " + p.getValue().toString());
    }
  }

}