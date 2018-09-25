package ea.services.impl;

import ea.data.Player;
import ea.data.PowerPlant;
import ea.services.PayoutService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PayoutServiceImpl implements PayoutService {

  private List<Integer> payout;

  public PayoutServiceImpl() {
    payout = new ArrayList<Integer>();
    getPayout().add(10);
    getPayout().add(22);
    getPayout().add(33);
    getPayout().add(44);
    getPayout().add(54);
    getPayout().add(64);
    getPayout().add(73);
    getPayout().add(82);
    getPayout().add(90);
    getPayout().add(98);
    getPayout().add(105);
    getPayout().add(112);
    getPayout().add(118);
    getPayout().add(124);
    getPayout().add(129);
    getPayout().add(134);
    getPayout().add(138);
    getPayout().add(142);
    getPayout().add(145);
    getPayout().add(148);
    getPayout().add(150);
  }

  // TODO
  public Integer getPayoutAmount(Player player) {
    List<PowerPlant> powerPlants = player.getPowerPlants();
    Iterator itr = powerPlants.iterator();

    while (itr.hasNext()) {
      PowerPlant pp = (PowerPlant) itr.next();

    }

    return 0;
  }

  public List<Integer> getPayout() { return payout; }
  public void setPayout(List<Integer> payout) { this.payout = payout; }

}