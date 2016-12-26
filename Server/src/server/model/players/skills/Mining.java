package server.model.players.skills;

import server.event.Event;
import server.event.EventContainer;
import server.event.EventManager;
import server.model.players.*;
import server.Config;
import server.util.Misc;

/**
* @Author Sanity
*/

public class Mining extends Skill {

	private final int VALID_PICK[] = {1265,1267,1269,1273,1271,1275};
	private final int[] PICK_REQS = {1,1,6,6,21,31,41,61};
	private final int[] RANDOM_GEMS = {1623,1621,1619,1617,1631};
	private int oreType;
	private int exp;
	private int levelReq;
	private int pickType;
	private final int EMOTE = 624;
	
	public Mining(Client c) {
		super(c);
	}
	
	public void startMining(int oreType, int levelReq, int exp) {
		Client c = getClient();

		c.turnPlayerTo(c.objectX, c.objectY);
		if (goodPick() > 0) {
			if (c.playerLevel[c.playerMining] >= levelReq) {
				this.oreType = oreType;
				this.exp = exp;
				this.levelReq = levelReq;
				this.pickType = goodPick();
				c.sendMessage("You swing your pick at the rock.");
				c.miningTimer = getMiningTimer(oreType);
				c.startAnimation(EMOTE);
			} else {
				resetMining();
				c.sendMessage("You need a mining level of " + levelReq + " to mine this rock.");
				c.startAnimation(65535);
			}		
		} else {
			resetMining();
			c.sendMessage("You need a pickaxe to mine this rock.");
			c.startAnimation(65535);
			c.getPlayerAssistant ().resetVariables();
		}
	}
	
	public void mineOre() {
		Client c = getClient();

		if (c.getItems().addItem(oreType,1)) {
			c.startAnimation(EMOTE);
			c.sendMessage("You manage to mine some ore.");
			c.getPlayerAssistant ().addSkillXP(exp * Config.MINING_EXPERIENCE, c.playerMining);
			c.getPlayerAssistant ().refreshSkill(c.playerMining);
			c.miningTimer = getMiningTimer(oreType);
			if (Misc.random(25) == 10) {
				c.getItems().addItem(RANDOM_GEMS[(int) (RANDOM_GEMS.length * Math.random())], 1);
				c.sendMessage("You find a gem!");
			}
		} else {
			c.getPlayerAssistant ().resetVariables();
			c.startAnimation(65535);
		}
	}
	
	public void resetMining() {
		this.oreType = -1;
		this.exp = -1;
		this.levelReq = -1;
		this.pickType = -1;
	}
	
	public int goodPick() {
		Client c = getClient();

		for (int j = VALID_PICK.length - 1; j >= 0; j--) {
			if (c.playerEquipment[c.playerWeapon] == VALID_PICK[j]) {
				if (c.playerLevel[c.playerMining] >= PICK_REQS[j])
					return VALID_PICK[j];
			}		
		}
		for (int i = 0; i < c.playerItems.length; i++) {
			for (int j = VALID_PICK.length - 1; j >= 0; j--) {
				if (c.playerItems[i] == VALID_PICK[j] + 1) {
					if (c.playerLevel[c.playerMining] >= PICK_REQS[j])
						return VALID_PICK[j];
				}
			}		
		}
		return - 1;
	}
	
	public int getMiningTimer(int ore) {
		int time = Misc.random(5);
		if (ore == 451) {
			time += 4;
		}
		return time;
	}

	/**
	 * Prospects the rock.
	 * @param c The client class.
	 * @param itemId The name of the item within the object.
	 */
	public void prospectRock(final Client c, final String itemName) {
		c.sendMessage("You examine the rock for ores...");
		EventManager.getSingleton().addEvent(new Event() {

			@Override
			public void execute(EventContainer container) {
				c.sendMessage("This rock contains "+itemName+".");
				container.stop();
			}

		}, 3000);
	}
}
