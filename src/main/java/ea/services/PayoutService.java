package ea.services;

import java.util.List;

public interface PayoutService {

  List<Integer> getPayout();
  void setPayout(List<Integer> payout);

}