/**
 * Cooking Class
 * Author Infexis
 * 
**/
package server.model.players.skills;

import server.model.items.Item;
import server.model.players.Client;
import server.util.Misc;
import server.Config;

public class Cooking extends Skill implements Skill.ItemTraining, Skill.ObjectTraining {

	public Cooking(Client c) {
		super(c);
	}

	private void sendStatementTwo(String s) { // 1 line click here to continue chat box interface
		getClient().getPlayerAssistant ().sendFrame126(s, 357);
		getClient().getPlayerAssistant ().sendFrame126("Click here to continue", 358);
		getClient().getPlayerAssistant ().sendFrame164(356);
	}

	public void cookFish(int id, int slot) {
		for (int j = 0; j < 28; j++) {
			if (getClient().getItems ().playerHasItem(id,1)) {
				if (getClient().playerLevel[getClient().playerCooking] >= cookingItems[slot][3]) {
					if (Misc.random(getClient().playerLevel[getClient().playerCooking] + 3 - cookingItems[slot][3]) == 1) {
						getClient().sendMessage ( "You accidently burn the fish." );
						getClient().getItems ().deleteItem(id, getClient().getItems ().getItemSlot(id), 1);
						getClient().getItems ().addItem(cookingItems[slot][2], 1);
					} else {
					getClient().startAnimation ( 883 );
						getClient().getItems ().deleteItem(id, getClient().getItems ().getItemSlot(id), 1);
						getClient().getItems ().addItem(cookingItems[slot][1], 1);
						getClient().getPlayerAssistant ().addSkillXP(cookingItems[slot][4] * Config.COOKING_EXPERIENCE, getClient().playerCooking);
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
	public void useItemOnItem (Item usedItem, Item onItem)
	{

	}

	@Override
	public void useItem ()
	{

	}

	@Override
	public void useItemOnObject (Item item, server.model.objects.Object object)
	{

	}
}