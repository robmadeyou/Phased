package server.model.players.skills;


import server.Config;
import server.model.players.Client;

public class Prayer extends Skill {

    public int[][] bonesExp = {{526, 11}, {532, 29}, {534, 70}, {536, 175}, {6729, 300}, {18830, 760}};

    public Prayer(Client c) {
        super(c);
    }

    public void buryBone(int id, int slot) {
        Client c = getClient();

        if (System.currentTimeMillis() - c.buryDelay > 1500) {
            c.getItems().deleteItem(id, slot, 1);
            c.sendMessage("You bury the bones.");
            c.getPlayerAssistant().addSkillXP(getExp(id) * Config.PRAYER_EXPERIENCE, 5);
            c.buryDelay = System.currentTimeMillis();
            c.startAnimation(827);
            //handleZombie();
        }
    }

    public void bonesOnAltar(int id) {
        Client c = getClient();

        c.getItems().deleteItem(id, c.getItems().getItemSlot(id), 1);
        c.getPlayerAssistant().addSkillXP(getExp(id) * 2 * Config.PRAYER_EXPERIENCE, 5);
    }

    public boolean isBone(int id) {
        for (int j = 0; j < bonesExp.length; j++)
            if (bonesExp[j][0] == id)
                return true;
        return false;
    }

    public int getExp(int id) {
        for (int j = 0; j < bonesExp.length; j++) {
            if (bonesExp[j][0] == id)
                return bonesExp[j][1];
        }
        return 0;
    }
}