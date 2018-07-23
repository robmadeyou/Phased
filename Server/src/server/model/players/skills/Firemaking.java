package server.model.players.skills;

import server.Config;
import server.model.objects.Objects;
import server.model.players.Client;

/**
 * Firemaking.java
 *
 * @author Sanity
 **/
public class Firemaking extends Skill {

    public long lastLight;
    public boolean resetAnim = true;
    private int[] logs = {1511, 1521, 1519, 1517, 1515, 1513};
    private int[] exp = {1, 3, 4, 5, 7, 8};
    private int[] level = {1, 15, 30, 45, 60, 75};
    private int DELAY = 1250;

    public Firemaking(Client c) {
        super(c);
    }

    public void checkLogType(int logType, int otherItem) {
        for (int j = 0; j < logs.length; j++) {
            if (logs[j] == logType || logs[j] == otherItem) {
                lightFire(j);
                return;
            }
        }
    }

    public void lightFire(int slot) {
        Client c = getClient();
        if (c.duelStatus >= 5) {
            c.sendMessage("Why am I trying to light a fire in the duel arena?");
            return;
        }
        if (c.playerLevel[c.playerFiremaking] >= level[slot]) {
            if (c.getItems().playerHasItem(590) && c.getItems().playerHasItem(logs[slot])) {
                if (System.currentTimeMillis() - lastLight > DELAY) {
                    c.startAnimation(733, 0);
                    c.getItems().deleteItem(logs[slot], c.getItems().getItemSlot(logs[slot]), 1);
                    c.getPlayerAssistant().addSkillXP(logs[slot] * Config.FIREMAKING_EXPERIENCE, c.playerFiremaking);
                    Objects fire = new Objects(2732, c.getX(), c.getY(), 0, -1, 10, 3);
                    Objects fire2 = new Objects(-1, c.getX(), c.getY(), 0, -1, 10, 60);
                    c.sendMessage("You light the fire.");
                    this.lastLight = System.currentTimeMillis();
                    //c.getPlayerAssistant().frame1();
                    resetAnim = true;
                }
            }
        }
    }

}