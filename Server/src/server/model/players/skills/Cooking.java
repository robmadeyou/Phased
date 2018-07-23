/**
 * Cooking Class
 * Author Infexis
 **/
package server.model.players.skills;

import server.Config;
import server.model.items.Item;
import server.model.players.Client;
import server.util.Misc;

public class Cooking extends Skill implements Skill.ItemTraining, Skill.ObjectTraining {

    private int[][] cookingItems = {{317, 315, 7954, 1, 30}, {335, 333, 323, 20, 70}, {331, 329, 323, 30, 90}, {359, 361, 363, 35, 100}, {377, 379, 381, 40, 120}, {371, 373, 375, 50, 140}, {17797, 18159, 18179, 80, 0}, {7944, 7946, 7948, 62, 150}, {383, 385, 387, 80, 210}, {389, 391, 393, 91, 169}};

    public Cooking(Client c) {
        super(c);
    }

    private void sendStatementTwo(String s) { // 1 line click here to continue chat box interface
        getClient().getPlayerAssistant().sendFrame126(s, 357);
        getClient().getPlayerAssistant().sendFrame126("Click here to continue", 358);
        getClient().getPlayerAssistant().sendFrame164(356);
    }

    public void cookFish(int id, int slot) {
        for (int j = 0; j < 28; j++) {
            if (getClient().getItems().playerHasItem(id, 1)) {
                if (getClient().playerLevel[getClient().playerCooking] >= cookingItems[slot][3]) {
                    if (Misc.random(getClient().playerLevel[getClient().playerCooking] + 3 - cookingItems[slot][3]) == 1) {
                        getClient().sendMessage("You accidently burn the fish.");
                        getClient().getItems().deleteItem(id, getClient().getItems().getItemSlot(id), 1);
                        getClient().getItems().addItem(cookingItems[slot][2], 1);
                    } else {
                        getClient().startAnimation(883);
                        getClient().getItems().deleteItem(id, getClient().getItems().getItemSlot(id), 1);
                        getClient().getItems().addItem(cookingItems[slot][1], 1);
                        getClient().getPlayerAssistant().addSkillXP(cookingItems[slot][4] * Config.COOKING_EXPERIENCE, getClient().playerCooking);
                    }
                } else {
                    sendStatementTwo("You need a cooking level of " + cookingItems[slot][3] + " to cook this fish.");
                    break;
                }
            } else {
                break;
            }
        }
    }

    @Override
    public void useItemOnItem(Item usedItem, Item onItem) {

    }

    @Override
    public void useItem() {

    }

    @Override
    public void useItemOnObject(Item item, server.model.objects.Object object) {

    }

    public void itemOnObject(int id) {
        for (int j = 0; j < cookingItems.length; j++) {
            if (cookingItems[j][0] == id)
                cookFish(cookingItems[j][0], j);
        }
    }
}