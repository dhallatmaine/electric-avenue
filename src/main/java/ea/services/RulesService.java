package ea.services;

import ea.rules.AmericaGermanyRules;
import ea.rules.BaseRules;
import org.springframework.stereotype.Component;

@Component
public class RulesService {

    public Integer getChosenRegions(Integer players) {
        switch (players) {
            case 2:
                return BaseRules.PLAYERS_2_CHOSEN_REGIONS;
            case 3:
                return BaseRules.PLAYERS_3_CHOSEN_REGIONS;
            case 4:
                return BaseRules.PLAYERS_4_CHOSEN_REGIONS;
            case 5:
                return BaseRules.PLAYERS_5_CHOSEN_REGIONS;
            case 6:
                return BaseRules.PLAYERS_6_CHOSEN_REGIONS;
            default:
                return 0;
        }
    }

    public Integer getRemovedCards(Integer players) {
        switch (players) {
            case 2:
                return BaseRules.PLAYERS_2_REMOVED_CARDS;
            case 3:
                return BaseRules.PLAYERS_3_REMOVED_CARDS;
            case 4:
                return BaseRules.PLAYERS_4_REMOVED_CARDS;
            case 5:
                return BaseRules.PLAYERS_5_REMOVED_CARDS;
            case 6:
                return BaseRules.PLAYERS_6_REMOVED_CARDS;
            default:
                return 0;
        }
    }

    public Integer getMaxPlants(Integer players) {
        switch (players) {
            case 2:
                return BaseRules.PLAYERS_2_MAX_PLANTS;
            case 3:
                return BaseRules.PLAYERS_3_MAX_PLANTS;
            case 4:
                return BaseRules.PLAYERS_4_MAX_PLANTS;
            case 5:
                return BaseRules.PLAYERS_5_MAX_PLANTS;
            case 6:
                return BaseRules.PLAYERS_6_MAX_PLANTS;
            default:
                return 0;
        }
    }

    public Integer getStep2Trigger(Integer players) {
        switch (players) {
            case 2:
                return BaseRules.PLAYERS_2_STEP_2_TRIGGER;
            case 3:
                return BaseRules.PLAYERS_3_STEP_2_TRIGGER;
            case 4:
                return BaseRules.PLAYERS_4_STEP_2_TRIGGER;
            case 5:
                return BaseRules.PLAYERS_5_STEP_2_TRIGGER;
            case 6:
                return BaseRules.PLAYERS_6_STEP_2_TRIGGER;
            default:
                return 0;
        }
    }

    public Integer getEndGameTrigger(Integer players) {
        switch (players) {
            case 2:
                return BaseRules.PLAYERS_2_END_GAME_TRIGGER;
            case 3:
                return BaseRules.PLAYERS_3_END_GAME_TRIGGER;
            case 4:
                return BaseRules.PLAYERS_4_END_GAME_TRIGGER;
            case 5:
                return BaseRules.PLAYERS_5_END_GAME_TRIGGER;
            case 6:
                return BaseRules.PLAYERS_6_END_GAME_TRIGGER;
            default:
                return 0;
        }
    }

    public Integer getStep1Coal(Integer players) {
        switch (players) {
            case 2:
                return AmericaGermanyRules.PLAYERS_2_STEP_1_COAL;
            case 3:
                return AmericaGermanyRules.PLAYERS_3_STEP_1_COAL;
            case 4:
                return AmericaGermanyRules.PLAYERS_4_STEP_1_COAL;
            case 5:
                return AmericaGermanyRules.PLAYERS_5_STEP_1_COAL;
            case 6:
                return AmericaGermanyRules.PLAYERS_6_STEP_1_COAL;
            default:
                return 0;
        }
    }

    public Integer getStep2Coal(Integer players) {
        switch (players) {
            case 2:
                return AmericaGermanyRules.PLAYERS_2_STEP_2_COAL;
            case 3:
                return AmericaGermanyRules.PLAYERS_3_STEP_2_COAL;
            case 4:
                return AmericaGermanyRules.PLAYERS_4_STEP_2_COAL;
            case 5:
                return AmericaGermanyRules.PLAYERS_5_STEP_2_COAL;
            case 6:
                return AmericaGermanyRules.PLAYERS_6_STEP_2_COAL;
            default:
                return 0;
        }
    }

    public Integer getStep3Coal(Integer players) {
        switch (players) {
            case 2:
                return AmericaGermanyRules.PLAYERS_2_STEP_3_COAL;
            case 3:
                return AmericaGermanyRules.PLAYERS_3_STEP_3_COAL;
            case 4:
                return AmericaGermanyRules.PLAYERS_4_STEP_3_COAL;
            case 5:
                return AmericaGermanyRules.PLAYERS_5_STEP_3_COAL;
            case 6:
                return AmericaGermanyRules.PLAYERS_6_STEP_3_COAL;
            default:
                return 0;
        }
    }

    public Integer getStep1Oil(Integer players) {
        switch (players) {
            case 2:
                return AmericaGermanyRules.PLAYERS_2_STEP_1_OIL;
            case 3:
                return AmericaGermanyRules.PLAYERS_3_STEP_1_OIL;
            case 4:
                return AmericaGermanyRules.PLAYERS_4_STEP_1_OIL;
            case 5:
                return AmericaGermanyRules.PLAYERS_5_STEP_1_OIL;
            case 6:
                return AmericaGermanyRules.PLAYERS_6_STEP_1_OIL;
            default:
                return 0;
        }
    }

    public Integer getStep2Oil(Integer players) {
        switch (players) {
            case 2:
                return AmericaGermanyRules.PLAYERS_2_STEP_2_OIL;
            case 3:
                return AmericaGermanyRules.PLAYERS_3_STEP_2_OIL;
            case 4:
                return AmericaGermanyRules.PLAYERS_4_STEP_2_OIL;
            case 5:
                return AmericaGermanyRules.PLAYERS_5_STEP_2_OIL;
            case 6:
                return AmericaGermanyRules.PLAYERS_6_STEP_2_OIL;
            default:
                return 0;
        }
    }

    public Integer getStep3Oil(Integer players) {
        switch (players) {
            case 2:
                return AmericaGermanyRules.PLAYERS_2_STEP_3_OIL;
            case 3:
                return AmericaGermanyRules.PLAYERS_3_STEP_3_OIL;
            case 4:
                return AmericaGermanyRules.PLAYERS_4_STEP_3_OIL;
            case 5:
                return AmericaGermanyRules.PLAYERS_5_STEP_3_OIL;
            case 6:
                return AmericaGermanyRules.PLAYERS_6_STEP_3_OIL;
            default:
                return 0;
        }
    }

    public Integer getStep1Trash(Integer players) {
        switch (players) {
            case 2:
                return AmericaGermanyRules.PLAYERS_2_STEP_1_TRASH;
            case 3:
                return AmericaGermanyRules.PLAYERS_3_STEP_1_TRASH;
            case 4:
                return AmericaGermanyRules.PLAYERS_4_STEP_1_TRASH;
            case 5:
                return AmericaGermanyRules.PLAYERS_5_STEP_1_TRASH;
            case 6:
                return AmericaGermanyRules.PLAYERS_6_STEP_1_TRASH;
            default:
                return 0;
        }
    }

    public Integer getStep2Trash(Integer players) {
        switch (players) {
            case 2:
                return AmericaGermanyRules.PLAYERS_2_STEP_2_TRASH;
            case 3:
                return AmericaGermanyRules.PLAYERS_3_STEP_2_TRASH;
            case 4:
                return AmericaGermanyRules.PLAYERS_4_STEP_2_TRASH;
            case 5:
                return AmericaGermanyRules.PLAYERS_5_STEP_2_TRASH;
            case 6:
                return AmericaGermanyRules.PLAYERS_6_STEP_2_TRASH;
            default:
                return 0;
        }
    }

    public Integer getStep3Trash(Integer players) {
        switch (players) {
            case 2:
                return AmericaGermanyRules.PLAYERS_2_STEP_3_TRASH;
            case 3:
                return AmericaGermanyRules.PLAYERS_3_STEP_3_TRASH;
            case 4:
                return AmericaGermanyRules.PLAYERS_4_STEP_3_TRASH;
            case 5:
                return AmericaGermanyRules.PLAYERS_5_STEP_3_TRASH;
            case 6:
                return AmericaGermanyRules.PLAYERS_6_STEP_3_TRASH;
            default:
                return 0;
        }
    }

    public Integer getStep1Uranium(Integer players) {
        switch (players) {
            case 2:
                return AmericaGermanyRules.PLAYERS_2_STEP_1_URANIUM;
            case 3:
                return AmericaGermanyRules.PLAYERS_3_STEP_1_URANIUM;
            case 4:
                return AmericaGermanyRules.PLAYERS_4_STEP_1_URANIUM;
            case 5:
                return AmericaGermanyRules.PLAYERS_5_STEP_1_URANIUM;
            case 6:
                return AmericaGermanyRules.PLAYERS_6_STEP_1_URANIUM;
            default:
                return 0;
        }
    }

    public Integer getStep2Uranium(Integer players) {
        switch (players) {
            case 2:
                return AmericaGermanyRules.PLAYERS_2_STEP_2_URANIUM;
            case 3:
                return AmericaGermanyRules.PLAYERS_3_STEP_2_URANIUM;
            case 4:
                return AmericaGermanyRules.PLAYERS_4_STEP_2_URANIUM;
            case 5:
                return AmericaGermanyRules.PLAYERS_5_STEP_2_URANIUM;
            case 6:
                return AmericaGermanyRules.PLAYERS_6_STEP_2_URANIUM;
            default:
                return 0;
        }
    }

    public Integer getStep3Uranium(Integer players) {
        switch (players) {
            case 2:
                return AmericaGermanyRules.PLAYERS_2_STEP_3_URANIUM;
            case 3:
                return AmericaGermanyRules.PLAYERS_3_STEP_3_URANIUM;
            case 4:
                return AmericaGermanyRules.PLAYERS_4_STEP_3_URANIUM;
            case 5:
                return AmericaGermanyRules.PLAYERS_5_STEP_3_URANIUM;
            case 6:
                return AmericaGermanyRules.PLAYERS_6_STEP_3_URANIUM;
            default:
                return 0;
        }
    }

}