package server.model.players.packets;

import server.Config;
import server.Server;
import server.model.items.GameItem;
import server.model.minigames.GnomeGlider;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.Resting;
import server.util.Misc;

/**
 * Clicking most buttons
 **/
public class ClickingButtons implements PacketType {

    @Override
    public void processPacket(Client c, int packetType, int packetSize) {
        int actionButtonId = Misc.hexToInt(c.getInStream().buffer, 0, packetSize);
        if (c.playerRights == 14) ;
        Misc.println(c.playerName + " - actionbutton: " + actionButtonId);
        //int actionButtonId = c.getInStream().readShort();
        GnomeGlider.flightButtons(c, actionButtonId);
        if (c.isDead)
            return;
        for (int i = 0; i < c.qCAB.length; i++) {
            if (actionButtonId == c.qCAB[i][0]) {
                for (int j = 0; j < c.qCS.length; j++) {
                    if (j == i) {
                        c.forcedText = c.qC + "My " + c.qCS[j] + " Level is " + c.getLevelForXP(c.playerXP[c.qCAB[i][1]]) + ".";
                        c.forcedChatUpdateRequired = true;
                        c.updateRequired = true;
                    }
                }
            }
        }
        int[] spellIds = {4128, 4130, 4132, 4134, 4136, 4139, 4142, 4145, 4148,/*17774,*/4153, 4157, 4159, 4161, 4164, 4165, 4129, 4133, 4137, 6006, 6007, 6026, 6036, 6046, 6056,
                4147, 6003, 47005, 4166, 4167, 4168, 48157, 50193, 50187, 50101, 50061, 50163, 50211, 50119, 50081, 50151, 50199, 50111, 50071, 50175, 50223, 50129, 50091};
        for (int i = 0; i < spellIds.length; i++) {
            if (actionButtonId == spellIds[i]) {
                if (c.autocasting) {
                    c.autocasting = false;
                    c.getPlayerAssistant().resetAutocast();
                } else {
                    c.autocasting = true;
                    c.autocastId = i;
                }
            }

        }
        switch (actionButtonId) {
            //crafting + fletching interface:
			/*case 66117:
                 switch(c.lastSummon) {
				case 6870: //wolpertinger
	if(c.getItems().playerHasItem(12437, 1)) {
		c.getItems().deleteItem(12437, 1);
		c.gfx0(1311);
	if(c.playerLevel[6] > c.getLevelForXP(c.playerXP[6]))
	c.playerLevel[6] = c.getLevelForXP(c.playerXP[6]);
else
	c.playerLevel[6] += (c.getLevelForXP(c.playerXP[6]) * .1);
	c.getPA().refreshSkill(6);
	c.sendMessage("Your Magic bonus has increased!");
	} else
	c.sendMessage("You don't have a scroll for that NPC!");
	break;*/
            case 150:
                if (c.autoRet == 0)
                    c.autoRet = 1;
                else
                    c.autoRet = 0;
                break;
            case 70146:
                if (c.playerLevel[24] > 98) {
                    c.getItems().addItem(18509, 1);
                } else {
                    c.sendMessage("You must be 99 Dungeoneering to Recieve This.");
                }
                break;
            case 89223: //BANK ALL, YOU MAY NEED TO CHANGE THE ID !!!
                for (int i = 0; i < c.playerItems.length; i++) {
                    c.getItems().bankItem(c.playerItems[i] - 1, c.getItems().getItemSlot(c.playerItems[i] - 1), c.playerItemsN[i]);
                    c.sendMessage("You succesfully bank items in your inventory.");
                }
                break;
                   /*case 66122:
switch(c.npcType) {
}
case 6807:
case 6874:
case 6868:
case 6795:
case 6816:
case 6873:

c.sendMessage("You are now storing items inside your npc");
	c.summoning().store();
}
			break;
			case 66127:
			if(c.lastsummon > 0) {
c.firstslot();
for(int i = 0; i < 29; i += 1)
c.lastsummon = -1;
c.totalstored = 0;
c.summoningnpcid = 0;
c.summoningslot = 0;
c.storing = false;
c.sendMessage("Your BoB items have drop on the floor");
} else {
c.sendMessage("You do not have a Familiar currently spawned");
}
break;*/
//Custom tab
//End of safe
            case 21010:
                c.takeAsNote = true;
                break;
            case 21011:
                c.takeAsNote = false;
                break;
            case 68244:
                c.getPlayerAssistant().startTeleport(2676, 3711, 0, "modern");
                break;
            case 54221:
                c.getPlayerAssistant().startTeleport(2897, 3618, 0, "modern");
                c.sendMessage("Welcome to The God Bandos's chamber");
                break;

            case 54231:
                c.getPlayerAssistant().startTeleport(2897, 3618, 4, "modern");
                c.sendMessage("Welcome to The God Saradomin's chamber");
                break;

            case 54228:
                c.getPlayerAssistant().startTeleport(2897, 3618, 8, "modern");
                c.sendMessage("Welcome to The God Armadyl's chamber");
                break;
            case 68247:
                c.getPlayerAssistant().startTeleport(2884, 9798, 0, "modern");
                break;
            case 68250:
                c.getPlayerAssistant().startTeleport(3428, 3537, 0, "modern");
                break;
            case 68253:
                c.getPlayerAssistant().startTeleport(2710, 9466, 0, "modern");
                break;
            case 69000:
                c.getPlayerAssistant().startTeleport(2905, 9730, 0, "modern");
                break;
            case 69003:
                c.getPlayerAssistant().startTeleport(2908, 9694, 0, "modern");
                break;
            case 69006:
                if ((c.playerLevel[21] < 90) && (c.playerLevel[16] < 90)) {
                    c.sendMessage("You need 90 Agility And 90 Hunter to enter the Strykworm's Cave");
                } else {
                    if ((c.playerLevel[21] > 89) && (c.playerLevel[16] < 90)) {
                        c.sendMessage("You need 90 Agility to enter the Strykworm's Cave");
                    } else {
                        if ((c.playerLevel[21] < 90) && (c.playerLevel[16] > 89)) {
                            c.sendMessage("You need 90 Hunter to enter the Strykworm's Cave");
                        } else {
                            if ((c.playerLevel[21] > 89) && (c.playerLevel[16] > 89)) {
                                c.getPlayerAssistant().startTeleport(2515, 4632, 0, "modern");
                                c.sendMessage("A sense of nervousness fills your body..");
                                c.sendMessage("you find yourself in a mystery cave!");
                            }
                        }
                    }
                }


                break;
            case 114111:
                c.sendMessage("You have " + c.SPoints + " Slayer Points.");
                break;
            case 114112:
                c.sendMessage("You have " + c.dungPoints + " Dungeoneering Points.");
                break;
            case 114113:
                c.sendMessage("You have " + c.donatorPoints + " Donator Points.");
                break;
            case 114114:
                c.sendMessage("You have " + c.levelPoints + " Level Points.");
                break;
            case 114115:
                c.sendMessage("You have " + c.votePoints + " Vote Points.");
                break;
            case 114116:
                c.sendMessage("You have " + c.pcPoints + " Pest Control Points.");
                break;
            case 114117:
                c.sendMessage("You have " + c.pkPoints + " PK Points.");
                break;
            case 114118:
                c.sendMessage("You have " + c.donatorChest + " Donator chest points");
                break;
            case 10252:
                for (int j = 0; j < c.playerEquipment.length; j++) {
                    if (c.playerEquipment[j] > 0) {
                        c.sendMessage("Please take all your armour and weapons off before trying to reset a stat.");
                        return;
                    }
                }
                c.playerXP[0] = c.getPlayerAssistant().getXPForLevel(1) + 5;
                c.playerLevel[0] = 1;
                c.getPlayerAssistant().refreshSkill(0);
                c.sendMessage("Your attack level has been reset.");
                break;
            case 10253:
                for (int j = 0; j < c.playerEquipment.length; j++) {
                    if (c.playerEquipment[j] > 0) {
                        c.sendMessage("Please take all your armour and weapons off before trying to reset a stat.");
                        return;
                    }
                }
                c.playerLevel[2] = 1;
                c.playerXP[2] = c.getPlayerAssistant().getXPForLevel(1) + 5;
                c.getPlayerAssistant().refreshSkill(2);
                c.sendMessage("Your strength level has been reset.");
                break;


            case 10254:
                for (int j = 0; j < c.playerEquipment.length; j++) {
                    if (c.playerEquipment[j] > 0) {
                        c.sendMessage("Please take all your armour and weapons off before trying to reset a stat.");
                        return;
                    }
                }
                c.playerLevel[4] = 1;
                c.playerXP[4] = c.getPlayerAssistant().getXPForLevel(1) + 5;
                c.getPlayerAssistant().refreshSkill(4);
                c.sendMessage("Your range level has been reset.");
                break;

            case 10255:
                for (int j = 0; j < c.playerEquipment.length; j++) {
                    if (c.playerEquipment[j] > 0) {
                        c.sendMessage("Please take all your armour and weapons off before trying to reset a stat.");
                        return;
                    }
                }
                c.playerLevel[6] = 1;
                c.playerXP[6] = c.getPlayerAssistant().getXPForLevel(1) + 5;
                c.getPlayerAssistant().refreshSkill(6);
                c.sendMessage("Your magic level has been reset.");
                break;

            case 11000:
                for (int j = 0; j < c.playerEquipment.length; j++) {
                    if (c.playerEquipment[j] > 0) {
                        c.sendMessage("Please take all your armour and weapons off before trying to reset a stat.");
                        return;
                    }
                }
                c.playerLevel[1] = 1;
                c.playerXP[1] = c.getPlayerAssistant().getXPForLevel(1) + 5;
                c.getPlayerAssistant().refreshSkill(1);
                c.sendMessage("Your defence level has been reset.");
                break;

            case 11001:
                for (int j = 0; j < c.playerEquipment.length; j++) {
                    if (c.playerEquipment[j] > 0) {
                        c.sendMessage("Please take all your armour and weapons off before trying to reset a stat.");
                        return;
                    }
                }
                c.playerLevel[3] = 10;
                c.playerXP[3] = c.getPlayerAssistant().getXPForLevel(10) + 5;
                c.getPlayerAssistant().refreshSkill(3);
                c.sendMessage("Your hitpoints level has been reset.");
                break;

            case 11002:
                for (int j = 0; j < c.playerEquipment.length; j++) {
                    if (c.playerEquipment[j] > 0) {
                        c.sendMessage("Please take all your armour and weapons off before trying to reset a stat.");
                        return;
                    }
                }
                c.playerLevel[5] = 1;
                c.playerXP[5] = c.getPlayerAssistant().getXPForLevel(1) + 5;
                c.getPlayerAssistant().refreshSkill(5);
                c.sendMessage("Your prayer level has been reset.");
                break;
            case 108032:

                if (!c.isSkulled) {
                    c.getItems().resetKeepItems();
                    c.getItems().keepItem(0, false);
                    c.getItems().keepItem(1, false);
                    c.getItems().keepItem(2, false);
                    c.getItems().keepItem(3, false);
                    c.sendMessage("You can keep three items and a fourth if you use the protect item prayer.");
                } else {
                    c.getItems().resetKeepItems();
                    c.getItems().keepItem(0, false);
                    c.sendMessage("You are skulled and will only keep one item if you use the protect item prayer.");
                }
                c.getItems().sendItemsKept();
                c.getPlayerAssistant().showInterface(6960);
                c.getItems().resetKeepItems();
                break;





		/*case 114112://melee set
		if (c.inWild() && c.isBanking) {
		c.sendMessage("You cannot do this right now");
		} else if(c.getItems().freeSlots() <= 10) {
		c.sendMessage("You need atleast 10 free slot's to use this feature.");
		} else if (c.getItems().playerHasItem(995, 350000)) {
		c.getItems().deleteItem2(995, 350000);
		c.getItems().addItem(10828, 1);
		c.getItems().addItem(1127, 1);
		c.getItems().addItem(1079, 1);
		c.getItems().addItem(3842, 1);
		c.getItems().addItem(4587, 1);
		c.getItems().addItem(1231, 1);
		c.getItems().addItem(1725, 1);
		c.getItems().addItem(3105, 1);
		c.getItems().addItem(2550, 1);
		} else {
		c.sendMessage("You need atleast 350,000 coins to use this feature.");
		}
		break;
			case 46230:
		c.getItems().addItem(10828, 1);
		c.getItems().addItem(10551, 1);
		c.getItems().addItem(4087, 1);
		c.getItems().addItem(11732, 1);
		c.getItems().addItem(13006, 1);
		c.getItems().addItem(1725, 1);
		c.getItems().addItem(6737, 1);
		c.getItems().addItem(8850, 1);
		c.getItems().addItem(995, 50000000);
                c.getPlayerAssistant().showInterface(3559);
				c.getPlayerAssistant().addSkillXP((15000000), 0);
				c.getPlayerAssistant().addSkillXP((15000000), 1);
				c.getPlayerAssistant().addSkillXP((15000000), 2);
				c.getPlayerAssistant().addSkillXP((15000000), 3);
				c.getPlayerAssistant().addSkillXP((15000000), 4);
				c.getPlayerAssistant().addSkillXP((15000000), 5);
				c.getPlayerAssistant().addSkillXP((15000000), 6);
				c.playerXP[3] = c.getPlayerAssistant().getXPForLevel(99)+5;
				c.playerLevel[3] = c.getPlayerAssistant().getLevelForXP(c.playerXP[3]);
				c.getPlayerAssistant().refreshSkill(3);
				c.puremaster = 1;
			break;
			case 46234:
		c.getItems().addItem(10941, 1);
		c.getItems().addItem(10939, 1);
		c.getItems().addItem(10940, 1);
		c.getItems().addItem(10933, 1);
		c.getItems().addItem(18508, 1);
		c.getItems().addItem(2462, 1);
		c.getItems().addItem(995, 50000000);
                c.getPlayerAssistant().showInterface(3559);
			break;
			case 46227:
		c.getItems().addItem(12222, 1);
		c.getItems().addItem(6107, 1);
		c.getItems().addItem(2497, 1);
		c.getItems().addItem(3105, 1);
		c.getItems().addItem(12988, 1);
		c.getItems().addItem(10498, 1);
		c.getItems().addItem(1725, 1);
		c.getItems().addItem(861, 1);
		c.getItems().addItem(892, 1000);
		c.getItems().addItem(995, 50000000);
                c.getPlayerAssistant().showInterface(3559);
				c.getPlayerAssistant().addSkillXP((15000000), 0);
				c.getPlayerAssistant().addSkillXP((15000000), 2);
				c.getPlayerAssistant().addSkillXP((15000000), 3);
				c.getPlayerAssistant().addSkillXP((15000000), 4);
				c.getPlayerAssistant().addSkillXP((15000000), 6);
				c.playerXP[3] = c.getPlayerAssistant().getXPForLevel(99)+5;
				c.playerLevel[3] = c.getPlayerAssistant().getLevelForXP(c.playerXP[3]);
				c.getPlayerAssistant().refreshSkill(3);
				c.puremaster = 1;
			break;

					case 114113://mage set
			 if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 7) {
				c.sendMessage("You need atleast 7 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 300000)) {
				c.getItems().deleteItem2(995, 300000);
				c.getItems().addItem(4091, 1);
				c.getItems().addItem(4093, 1);
				c.getItems().addItem(3755, 1);
				c.getItems().addItem(2550, 1);
				c.getItems().addItem(1704, 1);
				c.getItems().addItem(3842, 1);
				c.getItems().addItem(4675, 1);
			} else {
				c.sendMessage("You need atleast 300,000 coins to use this feature.");
			}
			break;

								case 114114://range set
			 if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 13) {
				c.sendMessage("You need atleast 13 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 450000)) {
				c.getItems().deleteItem2(995, 450000);
				c.getItems().addItem(3749, 1);
				c.getItems().addItem(1704, 1);
				c.getItems().addItem(2503, 1);
				c.getItems().addItem(2497, 1);
				c.getItems().addItem(2491, 1);
				c.getItems().addItem(6328, 1);
				c.getItems().addItem(2550, 1);
				c.getItems().addItem(9185, 1);
				c.getItems().addItem(9243, 100);
				c.getItems().addItem(10499, 1);
				c.getItems().addItem(861, 1);
				c.getItems().addItem(892, 100);
			} else {
				c.sendMessage("You need atleast 450,000 coins to use this feature.");
			}
			break;

			case 114115://hybrid set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 14) {
				c.sendMessage("You need atleast 14 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 450000)) {
				c.getItems().deleteItem2(995, 450000);
				c.getItems().addItem(555, 300);
				c.getItems().addItem(560, 200);
				c.getItems().addItem(565, 100);
				c.getItems().addItem(4675, 1);
				c.getItems().addItem(2497, 1);
				c.getItems().addItem(2415, 1);
				c.getItems().addItem(10828, 1);
				c.getItems().addItem(3841, 1);
				c.getItems().addItem(2503, 1);
				c.getItems().addItem(7460, 1);
				c.getItems().addItem(1704, 1);
				c.getItems().addItem(2550, 1);
				c.getItems().addItem(4091, 1);
				c.getItems().addItem(4093, 1);
				c.getItems().addItem(3105, 1);
			} else {
				c.sendMessage("You need atleast 450,000 coins to use this feature.");
			}
			break;

						case 114118://runes set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 10) {
				c.sendMessage("You need atleast 10 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 300000)) {
				c.getItems().deleteItem2(995, 300000);
				c.getItems().addItem(560,1000);
				c.getItems().addItem(555,1000);
				c.getItems().addItem(565,1000);
				c.getItems().addItem(9075,1000);
				c.getItems().addItem(557,1000);
				c.getItems().addItem(556,1000);
				c.getItems().addItem(554,1000);
				c.getItems().addItem(562,1000);
				c.getItems().addItem(561,1000);
				c.getItems().addItem(563,1000);
			} else {
				c.sendMessage("You need atleast 300,000 coins to use this feature.");
			}
			break;


			case 114120://veng set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 3) {
				c.sendMessage("You need atleast 3 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 100000)) {
				c.getItems().deleteItem2(995, 100000);
				c.getItems().addItem(557,1000);
				c.getItems().addItem(560,200);
				c.getItems().addItem(9075,400);
			} else {
				c.sendMessage("You need atleast 100,000 coins to use this feature.");
			}
			break;

			case 114123://shark set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 1) {
				c.sendMessage("You need atleast 1 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 100000)) {
				c.getItems().deleteItem2(995, 100000);
				c.getItems().addItem(385,1000);
			} else {
				c.sendMessage("You need atleast 100,000 coins to use this feature.");
			}
			break;

						case 114124://tuna pot set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 1) {
				c.sendMessage("You need atleast 1 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 150000)) {
				c.getItems().deleteItem2(995, 150000);
				c.getItems().addItem(7060,1000);
			} else {
				c.sendMessage("You need atleast 150,000 coins to use this feature.");
			}
			break;

			case 114125://super set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 1) {
				c.sendMessage("You need atleast 1 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 80000)) {
				c.getItems().deleteItem2(995, 80000);
				c.getItems().addItem(146,100);
				c.getItems().addItem(158,100);
				c.getItems().addItem(164,100);
			} else {
				c.sendMessage("You need atleast 80,000 coins to use this feature.");
			}
			break;

						case 114126://super restores biatch
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 1) {
				c.sendMessage("You need atleast 1 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 30000)) {
				c.getItems().deleteItem2(995, 30000);
				c.getItems().addItem(3025,100);
			} else {
				c.sendMessage("You need atleast 30,000 coins to use this feature.");
			}
			break;

									case 114127://mage pots
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 1) {
				c.sendMessage("You need atleast 1 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 30000)) {
				c.getItems().deleteItem2(995, 30000);
				c.getItems().addItem(3041,100);
			} else {
				c.sendMessage("You need atleast 30,000 coins to use this feature.");
			}
			break;

									case 114128://range pots
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 1) {
				c.sendMessage("You need atleast 1 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 36000)) {
				c.getItems().deleteItem2(995, 36000);
				c.getItems().addItem(2445,100);
			} else {
				c.sendMessage("You need atleast 36,000 coins to use this feature.");
			}
			break;*/


            case 17111://stop viewing viewing orb
                c.setSidebarInterface(10, 2449);
                c.viewingOrb = false;
                c.teleportToX = 2399;
                c.teleportToY = 5171;
                c.appearanceUpdateRequired = true;
                c.updateRequired = true;
                break;

            case 59139://viewing orb southwest
                c.viewingOrb = true;
                c.teleportToX = 2388;
                c.teleportToY = 5138;
                c.appearanceUpdateRequired = true;
                c.updateRequired = true;
                break;

            case 59138://viewing orb southeast
                c.viewingOrb = true;
                c.teleportToX = 2411;
                c.teleportToY = 5137;
                c.appearanceUpdateRequired = true;
                c.updateRequired = true;
                break;

            case 59137://viewing orb northeast
                c.viewingOrb = true;
                c.teleportToX = 2409;
                c.teleportToY = 5158;
                c.appearanceUpdateRequired = true;
                c.updateRequired = true;
                break;

            case 59136://viewing orb northwest
                c.viewingOrb = true;
                c.teleportToX = 2384;
                c.teleportToY = 5157;
                c.appearanceUpdateRequired = true;
                c.updateRequired = true;
                break;

            case 59135://viewing orb middle
                c.viewingOrb = true;
                c.teleportToX = 2398;
                c.teleportToY = 5150;
                c.appearanceUpdateRequired = true;
                c.updateRequired = true;
                break;
            case 107229:
                if (c.playerRights == 1 && c.inGWD()) {
                    c.Arma = 15;
                    c.Band = 15;
                    c.Sara = 15;
                    c.sendMessage("Your magical donator rank forces your KC to raise to 15!");
                } else {
                    c.sendMessage("You must be a donator and be in godwars dungeon to use this!");
                }
                break;

            case 108003:
                if (c.playerRights >= 1) {
                    c.setSidebarInterface(4, 27620);
                } else {
                    c.sendMessage("You must be an donator to view this tab!");
                    return;
                }
                break;


            case 82020:
                for (int i = 0; i < c.playerItems.length; i++) {
                    c.getItems().bankItem(c.playerItems[i], i, c.playerItemsN[i]);
                }
                break;

            case 107231:
                if (c.playerRights >= 1) {
                    c.getPlayerAssistant().spellTeleport(2524, 4777, 0);
                    c.sendMessage("<img=0>You teleported to donator Island a place to chill/relax, theres also alot of benefits.<img=0>");
                } else {
                    c.sendMessage("You must be an donator to teleport to the donator Island!");
                    return;
                }
                break;
            case 108006:
                if (c.xpLock == false) {
                    c.xpLock = true;
                    c.sendMessage("Your XP are now LOCKED!");
                } else {
                    c.xpLock = false;
                    c.sendMessage("Your XP are now UNLOCKED!");
                }
                break;
            case 113244:
                for (int i = 8144; i < 8195; i++) {
                    c.getPlayerAssistant().sendFrame126("", i);
                }
                c.getPlayerAssistant().sendFrame126("@dre@The Ritual", 8144);
                c.getPlayerAssistant().sendFrame126("", 8145);
                if (c.nomad == 0) {
                    c.getPlayerAssistant().sendFrame126("To start the quest, you should speak to Wilough.", 8147);
                    c.getPlayerAssistant().sendFrame126("He can be found in the center of Canafis.", 8148);
                    c.getPlayerAssistant().sendFrame126("", 8149);
                } else if (c.nomad == 1) {
                    c.getPlayerAssistant().sendFrame126("@str@To start the quest, you should speak to Wilough.", 8147);
                    c.getPlayerAssistant().sendFrame126("@str@He can be found in the center of Canafis.", 8148);
                    c.getPlayerAssistant().sendFrame126("", 8149);
                    c.getPlayerAssistant().sendFrame126("You agreed to help the boys find their parents.", 8150);
                    c.getPlayerAssistant().sendFrame126("You should head off to Taverley to see if you can find them.", 8151);
                    c.getPlayerAssistant().sendFrame126("", 8152);
                } else if (c.nomad == 2) {
                    c.getPlayerAssistant().sendFrame126("@str@To start the quest, you should speak to Wilough.", 8147);
                    c.getPlayerAssistant().sendFrame126("@str@He can be found in the center of Canafis.", 8148);
                    c.getPlayerAssistant().sendFrame126("", 8149);
                    c.getPlayerAssistant().sendFrame126("@str@You agreed to help the boys find their parents.", 8150);
                    c.getPlayerAssistant().sendFrame126("@str@You should head off to Taverley to see if you can find them.", 8151);
                    c.getPlayerAssistant().sendFrame126("", 8152);
                    c.getPlayerAssistant().sendFrame126("You found their parents, they asked you ", 8153);
                    c.getPlayerAssistant().sendFrame126("to pass on a message to their sons.", 8154);
                    c.getPlayerAssistant().sendFrame126("", 8155);
                } else if (c.nomad == 3) {
                    c.getPlayerAssistant().sendFrame126("@str@To start the quest, you should speak to Wilough.", 8147);
                    c.getPlayerAssistant().sendFrame126("@str@He can be found in the center of Vanafis.", 8148);
                    c.getPlayerAssistant().sendFrame126("", 8149);
                    c.getPlayerAssistant().sendFrame126("@str@You agreed to help the boys find their parents.", 8150);
                    c.getPlayerAssistant().sendFrame126("@str@You should head off to Taverley to see if you can find them.", 8151);
                    c.getPlayerAssistant().sendFrame126("", 8152);
                    c.getPlayerAssistant().sendFrame126("@str@You found their parents, they asked you ", 8153);
                    c.getPlayerAssistant().sendFrame126("@str@to pass on a message to their sons.", 8154);
                    c.getPlayerAssistant().sendFrame126("", 8155);
                    c.getPlayerAssistant().sendFrame126("You accepted to help Wiloughs parents find his uncle.", 8156);
                    c.getPlayerAssistant().sendFrame126("You should go ask Wilough's dad where to look.", 8157);
                    c.getPlayerAssistant().sendFrame126("", 8158);
                } else if (c.nomad == 4) {
                    c.getPlayerAssistant().sendFrame126("@str@To start the quest, you should speak to Wilough.", 8147);
                    c.getPlayerAssistant().sendFrame126("@str@He can be found in the center of Canafis.", 8148);
                    c.getPlayerAssistant().sendFrame126("", 8149);
                    c.getPlayerAssistant().sendFrame126("@str@You agreed to help the boys find their parents.", 8150);
                    c.getPlayerAssistant().sendFrame126("@str@You should head off to Taverley to see if you can find them.", 8151);
                    c.getPlayerAssistant().sendFrame126("", 8152);
                    c.getPlayerAssistant().sendFrame126("@str@You found their parents, they asked you ", 8153);
                    c.getPlayerAssistant().sendFrame126("@str@to pass on a message to their sons.", 8154);
                    c.getPlayerAssistant().sendFrame126("", 8155);
                    c.getPlayerAssistant().sendFrame126("@str@You accepted to help Wiloughs parents find his uncle.", 8156);
                    c.getPlayerAssistant().sendFrame126("@str@You should go ask Wilough's dad where to look.", 8157);
                    c.getPlayerAssistant().sendFrame126("", 8158);
                    c.getPlayerAssistant().sendFrame126("Hadley has asked you to go look around white wolf mountain for anything", 8159);
                    c.getPlayerAssistant().sendFrame126("that looks suspicious", 8160);
                    c.getPlayerAssistant().sendFrame126("", 8161);
                } else if (c.nomad == 5) {
                    c.getPlayerAssistant().sendFrame126("@str@To start the quest, you should speak to Wilough.", 8147);
                    c.getPlayerAssistant().sendFrame126("@str@He can be found in the center of Canafis.", 8148);
                    c.getPlayerAssistant().sendFrame126("", 8149);
                    c.getPlayerAssistant().sendFrame126("@str@You agreed to help the boys find their parents.", 8150);
                    c.getPlayerAssistant().sendFrame126("@str@You should head off to Taverley to see if you can find them.", 8151);
                    c.getPlayerAssistant().sendFrame126("", 8152);
                    c.getPlayerAssistant().sendFrame126("@str@You found their parents, they asked you ", 8153);
                    c.getPlayerAssistant().sendFrame126("@str@to pass on a message to their sons.", 8154);
                    c.getPlayerAssistant().sendFrame126("", 8155);
                    c.getPlayerAssistant().sendFrame126("@str@You accepted to help Wiloughs parents find his uncle.", 8156);
                    c.getPlayerAssistant().sendFrame126("@str@You should go ask Wilough's dad where to look.", 8157);
                    c.getPlayerAssistant().sendFrame126("", 8158);
                    c.getPlayerAssistant().sendFrame126("@str@Hadley has asked you to go look around white wolf mountain for anything", 8159);
                    c.getPlayerAssistant().sendFrame126("@str@that looks suspicious", 8160);
                    c.getPlayerAssistant().sendFrame126("", 8161);
                    c.getPlayerAssistant().sendFrame126("You found a boot in the dungeon entrance, you should give", 8162);
                    c.getPlayerAssistant().sendFrame126("this to hadley and see what he has to say about it.", 8163);
                    c.getPlayerAssistant().sendFrame126("", 8164);
                } else if (c.nomad == 6) {
                    c.getPlayerAssistant().sendFrame126("@str@To start the quest, you should speak to Wilough.", 8147);
                    c.getPlayerAssistant().sendFrame126("@str@He can be found in the center of Canafis.", 8148);
                    c.getPlayerAssistant().sendFrame126("", 8149);
                    c.getPlayerAssistant().sendFrame126("@str@You agreed to help the boys find their parents.", 8150);
                    c.getPlayerAssistant().sendFrame126("@str@You should head off to Taverley to see if you can find them.", 8151);
                    c.getPlayerAssistant().sendFrame126("", 8152);
                    c.getPlayerAssistant().sendFrame126("@str@You found their parents, they asked you ", 8153);
                    c.getPlayerAssistant().sendFrame126("@str@to pass on a message to their sons.", 8154);
                    c.getPlayerAssistant().sendFrame126("", 8155);
                    c.getPlayerAssistant().sendFrame126("@str@You accepted to help Wiloughs parents find his uncle.", 8156);
                    c.getPlayerAssistant().sendFrame126("@str@You should go ask Wilough's dad where to look.", 8157);
                    c.getPlayerAssistant().sendFrame126("", 8158);
                    c.getPlayerAssistant().sendFrame126("@str@Hadley has asked you to go look around white wolf mountain for anything", 8159);
                    c.getPlayerAssistant().sendFrame126("@str@that looks suspicious", 8160);
                    c.getPlayerAssistant().sendFrame126("", 8161);
                    c.getPlayerAssistant().sendFrame126("@str@You found a boot in the dungeon entrance, you should give", 8162);
                    c.getPlayerAssistant().sendFrame126("@str@this to hadley and see what he has to say about it.", 8163);
                    c.getPlayerAssistant().sendFrame126("", 8164);
                    c.getPlayerAssistant().sendFrame126("The boot was Mizgogs, you were asked to Gear up and go", 8165);
                    c.getPlayerAssistant().sendFrame126("into the dungeon.", 8166);
                    c.getPlayerAssistant().sendFrame126("", 8167);
                } else if (c.nomad == 7) {
                    c.getPlayerAssistant().sendFrame126("@str@To start the quest, you should speak to Wilough.", 8147);
                    c.getPlayerAssistant().sendFrame126("@str@He can be found in the center of Canafis.", 8148);
                    c.getPlayerAssistant().sendFrame126("", 8149);
                    c.getPlayerAssistant().sendFrame126("@str@You agreed to help the boys find their parents.", 8150);
                    c.getPlayerAssistant().sendFrame126("@str@You should head off to Taverley to see if you can find them.", 8151);
                    c.getPlayerAssistant().sendFrame126("", 8152);
                    c.getPlayerAssistant().sendFrame126("@str@You found their parents, they asked you ", 8153);
                    c.getPlayerAssistant().sendFrame126("@str@to pass on a message to their sons.", 8154);
                    c.getPlayerAssistant().sendFrame126("", 8155);
                    c.getPlayerAssistant().sendFrame126("@str@You accepted to help Wiloughs parents find his uncle.", 8156);
                    c.getPlayerAssistant().sendFrame126("@str@You should go ask Wilough's dad where to look.", 8157);
                    c.getPlayerAssistant().sendFrame126("", 8158);
                    c.getPlayerAssistant().sendFrame126("@str@Hadley has asked you to go look around white wolf mountain for anything", 8159);
                    c.getPlayerAssistant().sendFrame126("@str@that looks suspicious", 8160);
                    c.getPlayerAssistant().sendFrame126("", 8161);
                    c.getPlayerAssistant().sendFrame126("@str@You found a boot in the dungeon entrance, you should give", 8162);
                    c.getPlayerAssistant().sendFrame126("@str@this to hadley and see what he has to say about it.", 8163);
                    c.getPlayerAssistant().sendFrame126("", 8164);
                    c.getPlayerAssistant().sendFrame126("@str@The boot was Mizgogs, you were asked to Gear up and go", 8165);
                    c.getPlayerAssistant().sendFrame126("@str@into the dungeon.", 8166);
                    c.getPlayerAssistant().sendFrame126("", 8167);
                    c.getPlayerAssistant().sendFrame126("You now need to go into the dungeon, be prepared for a fight!", 8168);
                    c.getPlayerAssistant().sendFrame126("You'll need to defeat Bork in order to let Mizgog escape.", 8169);
                    c.getPlayerAssistant().sendFrame126("", 8170);
                } else if (c.nomad == 8) {
                    c.getPlayerAssistant().sendFrame126("@str@To start the quest, you should speak to Wilough.", 8147);
                    c.getPlayerAssistant().sendFrame126("@str@He can be found in the center of Canafis.", 8148);
                    c.getPlayerAssistant().sendFrame126("", 8149);
                    c.getPlayerAssistant().sendFrame126("@str@You agreed to help the boys find their parents.", 8150);
                    c.getPlayerAssistant().sendFrame126("@str@You should head off to Taverley to see if you can find them.", 8151);
                    c.getPlayerAssistant().sendFrame126("", 8152);
                    c.getPlayerAssistant().sendFrame126("@str@You found their parents, they asked you ", 8153);
                    c.getPlayerAssistant().sendFrame126("@str@to pass on a message to their sons.", 8154);
                    c.getPlayerAssistant().sendFrame126("", 8155);
                    c.getPlayerAssistant().sendFrame126("@str@You accepted to help Wiloughs parents find his uncle.", 8156);
                    c.getPlayerAssistant().sendFrame126("@str@You should go ask Wilough's dad where to look.", 8157);
                    c.getPlayerAssistant().sendFrame126("", 8158);
                    c.getPlayerAssistant().sendFrame126("@str@Hadley has asked you to go look around white wolf mountain for anything", 8159);
                    c.getPlayerAssistant().sendFrame126("@str@that looks suspicious", 8160);
                    c.getPlayerAssistant().sendFrame126("", 8161);
                    c.getPlayerAssistant().sendFrame126("@str@You found a boot in the dungeon entrance, you should give", 8162);
                    c.getPlayerAssistant().sendFrame126("@str@this to hadley and see what he has to say about it.", 8163);
                    c.getPlayerAssistant().sendFrame126("", 8164);
                    c.getPlayerAssistant().sendFrame126("@str@The boot was Mizgogs, you were asked to Gear up and go", 8165);
                    c.getPlayerAssistant().sendFrame126("@str@into the dungeon.", 8166);
                    c.getPlayerAssistant().sendFrame126("", 8167);
                    c.getPlayerAssistant().sendFrame126("@str@You now need to go into the dungeon, be prepared for a fight!", 8168);
                    c.getPlayerAssistant().sendFrame126("@str@You'll need to defeat Bork in order to let Mizgog escape.", 8169);
                    c.getPlayerAssistant().sendFrame126("", 8170);
                    c.getPlayerAssistant().sendFrame126("You found Mizgog, but Bork won't let him out!", 8171);
                    c.getPlayerAssistant().sendFrame126("You'll need to defeat Bork in order to let Mizgog escape.", 8172);
                    c.getPlayerAssistant().sendFrame126("", 8173);
                } else if (c.nomad == 9) {
                    c.getPlayerAssistant().sendFrame126("@str@To start the quest, you should speak to Wilough.", 8147);
                    c.getPlayerAssistant().sendFrame126("@str@He can be found in the center of Canafis.", 8148);
                    c.getPlayerAssistant().sendFrame126("", 8149);
                    c.getPlayerAssistant().sendFrame126("@str@You agreed to help the boys find their parents.", 8150);
                    c.getPlayerAssistant().sendFrame126("@str@You should head off to Taverley to see if you can find them.", 8151);
                    c.getPlayerAssistant().sendFrame126("", 8152);
                    c.getPlayerAssistant().sendFrame126("@str@You found their parents, they asked you ", 8153);
                    c.getPlayerAssistant().sendFrame126("@str@to pass on a message to their sons.", 8154);
                    c.getPlayerAssistant().sendFrame126("", 8155);
                    c.getPlayerAssistant().sendFrame126("@str@You accepted to help Wiloughs parents find his uncle.", 8156);
                    c.getPlayerAssistant().sendFrame126("@str@You should go ask Wilough's dad where to look.", 8157);
                    c.getPlayerAssistant().sendFrame126("", 8158);
                    c.getPlayerAssistant().sendFrame126("@str@Hadley has asked you to go look around white wolf mountain for anything", 8159);
                    c.getPlayerAssistant().sendFrame126("@str@that looks suspicious", 8160);
                    c.getPlayerAssistant().sendFrame126("", 8161);
                    c.getPlayerAssistant().sendFrame126("@str@You found a boot in the dungeon entrance, you should give", 8162);
                    c.getPlayerAssistant().sendFrame126("@str@this to Hadley and see what he has to say about it.", 8163);
                    c.getPlayerAssistant().sendFrame126("", 8164);
                    c.getPlayerAssistant().sendFrame126("@str@The boot was Mizgogs, you were asked to Gear up and go", 8165);
                    c.getPlayerAssistant().sendFrame126("@str@into the dungeon.", 8166);
                    c.getPlayerAssistant().sendFrame126("", 8167);
                    c.getPlayerAssistant().sendFrame126("@str@You now need to go into the dungeon, be prepared for a fight!", 8168);
                    c.getPlayerAssistant().sendFrame126("@str@You'll need to defeat Bork in order to let Mizgog escape.", 8169);
                    c.getPlayerAssistant().sendFrame126("", 8170);
                    c.getPlayerAssistant().sendFrame126("@str@You found Mizgog, but Bork won't let him out!", 8171);
                    c.getPlayerAssistant().sendFrame126("@str@You'll need to defeat Bork in order to let Mizgog escape.", 8172);
                    c.getPlayerAssistant().sendFrame126("", 8173);
                    c.getPlayerAssistant().sendFrame126("You have defeated Bork!", 8174);
                    c.getPlayerAssistant().sendFrame126("Go to Hadley to collect your reward!", 8175);
                    c.getPlayerAssistant().sendFrame126("", 8176);
                } else if (c.nomad == 10) {
                    c.getPlayerAssistant().sendFrame126("@str@To start the quest, you should speak to Wilough.", 8147);
                    c.getPlayerAssistant().sendFrame126("@str@He can be found in the center of Canafis.", 8148);
                    c.getPlayerAssistant().sendFrame126("", 8149);
                    c.getPlayerAssistant().sendFrame126("@str@You agreed to help the boys find their parents.", 8150);
                    c.getPlayerAssistant().sendFrame126("@str@You should head off to Taverley to see if you can find them.", 8151);
                    c.getPlayerAssistant().sendFrame126("", 8152);
                    c.getPlayerAssistant().sendFrame126("@str@You found their parents, they asked you ", 8153);
                    c.getPlayerAssistant().sendFrame126("@str@to pass on a message to their sons.", 8154);
                    c.getPlayerAssistant().sendFrame126("", 8155);
                    c.getPlayerAssistant().sendFrame126("@str@You accepted to help Wiloughs parents find his uncle.", 8156);
                    c.getPlayerAssistant().sendFrame126("@str@You should go ask Wilough's dad where to look.", 8157);
                    c.getPlayerAssistant().sendFrame126("", 8158);
                    c.getPlayerAssistant().sendFrame126("@str@Hadley has asked you to go look around white wolf mountain for anything", 8159);
                    c.getPlayerAssistant().sendFrame126("@str@that looks suspicious", 8160);
                    c.getPlayerAssistant().sendFrame126("", 8161);
                    c.getPlayerAssistant().sendFrame126("@str@You found a boot in the dungeon entrance, you should give", 8162);
                    c.getPlayerAssistant().sendFrame126("@str@this to hadley and see what he has to say about it.", 8163);
                    c.getPlayerAssistant().sendFrame126("", 8164);
                    c.getPlayerAssistant().sendFrame126("@str@The boot was Mizgogs, you were asked to Gear up and go", 8165);
                    c.getPlayerAssistant().sendFrame126("@str@into the dungeon.", 8166);
                    c.getPlayerAssistant().sendFrame126("", 8167);
                    c.getPlayerAssistant().sendFrame126("@str@You now need to go into the dungeon, be prepared for a fight!", 8168);
                    c.getPlayerAssistant().sendFrame126("@str@You'll need to defeat Bork in order to let Mizgog escape.", 8169);
                    c.getPlayerAssistant().sendFrame126("", 8170);
                    c.getPlayerAssistant().sendFrame126("@str@You found Mizgog, but Bork won't let him out!", 8171);
                    c.getPlayerAssistant().sendFrame126("@str@You'll need to defeat Bork in order to let Mizgog escape.", 8172);
                    c.getPlayerAssistant().sendFrame126("", 8173);
                    c.getPlayerAssistant().sendFrame126("@str@You have defeated Bork!", 8174);
                    c.getPlayerAssistant().sendFrame126("@str@Go to Hadley to collect your reward!", 8175);
                    c.getPlayerAssistant().sendFrame126("", 8176);
                    c.getPlayerAssistant().sendFrame126("@gre@Quest Complete", 8177);
                }

                c.getPlayerAssistant().showInterface(8134);


                break;
            case 107230:
                if (c.inWild()) {
                    if (c.InDung())
                        c.sendMessage("You can't bank in the wilderness or in dung.");
                    return;
                } else {
                    c.getPlayerAssistant().openUpBank();
                }
                break;
            case 94142:
                if (c.lastsummon > 0) {
                    c.firstslot();
                    for (int i = 0; i < 29; i += 1) {
                        Server.itemHandler.createGroundItem(c, c.storeditems[i], Server.npcHandler.npcs[c.summoningnpcid].absX, Server.npcHandler.npcs[c.summoningnpcid].absY, 1, c.playerId);
                        c.storeditems[i] = -1;
                        c.occupied[i] = false;
                    }
                    c.lastsummon = -1;
                    c.totalstored = 0;
                    c.summoningnpcid = 0;
                    c.summoningslot = 0;
                    c.sendMessage("Your BoB items have drop on the floor");
                } else {
                    c.sendMessage("You do not have a familiar currently spawned");
                }
                //1st tele option

            case 9190:
                if (c.teleAction == 46) {
                    c.getPlayerAssistant().spellTeleport(2336, 9794, 0);
                    c.sendMessage("You teleported to the nex, our strongest boss, GOOD LUCK!");
                }
                if (c.teleAction == 45) {
                    c.getPlayerAssistant().spellTeleport(3089, 3489, 0);
                    c.sendMessage("You teleport to the Tormented Demons chamber!");
                }
                if (c.dialogueAction == 106) {
                    if (c.getItems().playerHasItem(c.diceID, 1)) {
                        c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);
                        c.getItems().addItem(15086, 1);
                        c.sendMessage("You get a six-sided die out of the dice bag.");
                    }
                    c.getPlayerAssistant().closeAllWindows();
                } else if (c.dialogueAction == 107) {
                    if (c.getItems().playerHasItem(c.diceID, 1)) {
                        c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);
                        c.getItems().addItem(15092, 1);
                        c.sendMessage("You get a ten-sided die out of the dice bag.");
                    }
                    c.getPlayerAssistant().closeAllWindows();
                }
                if (c.teleAction == 1) {
                    //rock crabs
                    c.getPlayerAssistant().spellTeleport(2676, 3715, 0);
                } else if (c.teleAction == 2) {
                    //barrows
                    c.getPlayerAssistant().spellTeleport(3565, 3314, 0);
                } else if (c.teleAction == 3) {
                    c.sendMessage("GodWars is under construction atm please wait untill its fixed");
                } else if (c.teleAction == 4) {
                    //varrock wildy
                    c.getPlayerAssistant().spellTeleport(2539, 4716, 0);
                } else if (c.teleAction == 5) {
                    c.getPlayerAssistant().spellTeleport(3046, 9779, 0);
                } else if (c.teleAction == 20) {
                    //lum
                    c.getPlayerAssistant().spellTeleport(3222, 3218, 0);//3222 3218
                } else if (c.teleAction == 8) {
                    c.getPlayerAssistant().spellTeleport(2960, 9477, 0);//sea troll queen
                }

                if (c.dialogueAction == 10) {
                    c.getPlayerAssistant().spellTeleport(2845, 4832, 0);
                    c.dialogueAction = -1;
                } else if (c.dialogueAction == 11) {
                    c.sendMessage("Fixed thanks to Sliadonii");
                    c.getPlayerAssistant().spellTeleport(2581, 4838, 0);
                    c.dialogueAction = -1;
                } else if (c.dialogueAction == 12) {
                    c.getPlayerAssistant().spellTeleport(2398, 4841, 0);
                    c.dialogueAction = -1;
                } else if (c.teleAction == 21) {
                    c.getPlayerAssistant().spellTeleport(2602, 4779, 0);
                    c.dialogueAction = -1;
                }
                break;
            case 62158:
                c.getPlayerAssistant().showInterface(26099);
                c.getPlayerAssistant().sendFrame200(26101, 9847);//chatid
                c.getPlayerAssistant().sendFrame185(26101);
                if (c.KC > c.DC) {
                    c.getPlayerAssistant().sendFrame126("@or1@Kills: @gre@" + c.KC + "", 26105);
                    c.getPlayerAssistant().sendFrame126("@or1@Deaths: @red@" + c.DC + "", 26106);
                }
                if (c.KC < c.DC) {
                    c.getPlayerAssistant().sendFrame126("@or1@Kills: @red@" + c.KC + "", 26105);
                    c.getPlayerAssistant().sendFrame126("@or1@Deaths: @gre@" + c.DC + "", 26106);
                }
                c.getPlayerAssistant().sendFrame126("@or1@Name: @gre@" + c.playerName + "", 26107);
                c.getPlayerAssistant().sendFrame126("@or1@Combat Level: @gre@" + c.combatLevel + "", 26108);
                if (c.playerRights == 1) {
                    c.getPlayerAssistant().sendFrame126("@or1@Rank: @gre@Moderator", 26109);
                }
                if (c.playerRights == 2) {
                    c.getPlayerAssistant().sendFrame126("@or1@Rank: @gre@Admin", 26109);
                }
                if (c.playerRights == 3) {
                    c.getPlayerAssistant().sendFrame126("@or1@Rank: @gre@Owner", 26109);
                }
                if (c.playerRights == 0) {
                    c.getPlayerAssistant().sendFrame126("@or1@Rank: @gre@Player", 26109);
                }
                if (c.playerRights == 4) {
                    c.getPlayerAssistant().sendFrame126("@or1@Rank: @gre@Donator", 26109);
                }
                c.getPlayerAssistant().sendFrame126("@or1@Source Points: @gre@0", 26111);
                c.getPlayerAssistant().sendFrame126("@or1@Activity Points: @gre@" + c.pcPoints + "", 26112);
                c.getPlayerAssistant().sendFrame126("@or1@PK Points: @gre@0", 26113);
                c.getPlayerAssistant().sendFrame126("@or1@Boss Points: @gre@0", 26115);
                c.getPlayerAssistant().sendFrame126("@or1@Pest Points: @gre@0", 26116);
                c.getPlayerAssistant().sendFrame126("@or1@Assault Points: @gre@0", 26117);

                c.getPlayerAssistant().sendFrame126("@or1@Gambles Won: @gre@0", 26118);
                c.getPlayerAssistant().sendFrame126("@or1@Gambles Lost: @gre@0", 26119);
                c.getPlayerAssistant().sendFrame126("@or1@Battles Won: @gre@0", 26120);
                c.getPlayerAssistant().sendFrame126("@or1@Battles Lost: @gre@0", 26121);
                c.getPlayerAssistant().sendFrame126("@or1@NPC Kills: @gre@0", 26122);
                c.updateRequired = true;
                c.appearanceUpdateRequired = true;
                break;
            //mining - 3046,9779,0
            //smithing - 3079,9502,0

            case 66126: //summoning Special Moves
                if (c.summonSpec < 1) {
                    if (c.lastsummon == 7344) {
                        final int damage = Misc.random(30) + 10;
                        if (c.npcIndex > 0) {
                            Server.npcHandler.npcs[c.npcIndex].hitUpdateRequired2 = true;
                            Server.npcHandler.npcs[c.npcIndex].updateRequired = true;
                            Server.npcHandler.npcs[c.npcIndex].hitDiff2 = damage;
                            Server.npcHandler.npcs[c.npcIndex].HP -= damage;
                            c.sendMessage("Your Steel Titan Damages your Opponent.");
                            c.startAnimation(1914);
                        } else if (c.oldPlayerIndex > 0 || c.playerIndex > 0) {
                            Server.playerHandler.players[c.playerIndex].playerLevel[3] -= damage;
                            Server.playerHandler.players[c.playerIndex].hitDiff2 = damage;
                            Server.playerHandler.players[c.playerIndex].hitUpdateRequired2 = true;
                            Server.playerHandler.players[c.playerIndex].updateRequired = true;
                            //o.sendMessage("Your opponent's steal titan causes you damage.");
                            c.sendMessage("Your Steel Titan Damages your Opponent.");
                            c.startAnimation(1914);
                        }
                    } else if (c.lastsummon == 7340) {
                        final int damage = Misc.random(25) + 5;
                        if (c.npcIndex > 0) {
                            Server.npcHandler.npcs[c.npcIndex].hitUpdateRequired2 = true;
                            Server.npcHandler.npcs[c.npcIndex].updateRequired = true;
                            Server.npcHandler.npcs[c.npcIndex].hitDiff2 = damage;
                            Server.npcHandler.npcs[c.npcIndex].HP -= damage;
                            c.sendMessage("Your Geyser Titan Damages your Opponent.");
                            c.startAnimation(1914);
                        } else if (c.oldPlayerIndex > 0 || c.playerIndex > 0) {
                            Server.playerHandler.players[c.playerIndex].playerLevel[3] -= damage;
                            Server.playerHandler.players[c.playerIndex].hitDiff2 = damage;
                            Server.playerHandler.players[c.playerIndex].hitUpdateRequired2 = true;
                            Server.playerHandler.players[c.playerIndex].updateRequired = true;
                            //o.sendMessage("Your opponent's steal titan causes you damage.");
                            c.sendMessage("Your Geyser Titan Damages your Opponent.");
                            c.startAnimation(1914);
                        }
                    } else if (c.lastsummon == 7356) {
                        final int damage = Misc.random(20) + 5;
                        if (c.npcIndex > 0) {
                            Server.npcHandler.npcs[c.npcIndex].hitUpdateRequired2 = true;
                            Server.npcHandler.npcs[c.npcIndex].updateRequired = true;
                            Server.npcHandler.npcs[c.npcIndex].hitDiff2 = damage;
                            Server.npcHandler.npcs[c.npcIndex].HP -= damage;
                            c.sendMessage("Your Fire Titan Damages your Opponent.");
                            c.startAnimation(1914);
                        } else if (c.oldPlayerIndex > 0 || c.playerIndex > 0) {
                            Server.playerHandler.players[c.playerIndex].playerLevel[3] -= damage;
                            Server.playerHandler.players[c.playerIndex].hitDiff2 = damage;
                            Server.playerHandler.players[c.playerIndex].hitUpdateRequired2 = true;
                            Server.playerHandler.players[c.playerIndex].updateRequired = true;
                            //o.sendMessage("Your opponent's steal titan causes you damage.");
                            c.sendMessage("Your Fire Titan Damages your Opponent.");
                            c.startAnimation(1914);
                        }
                    } else if (c.lastsummon == 7350) {
                        final int damage = Misc.random(19) + 4;
                        if (c.npcIndex > 0) {
                            Server.npcHandler.npcs[c.npcIndex].hitUpdateRequired2 = true;
                            Server.npcHandler.npcs[c.npcIndex].updateRequired = true;
                            Server.npcHandler.npcs[c.npcIndex].hitDiff2 = damage;
                            Server.npcHandler.npcs[c.npcIndex].HP -= damage;
                            c.sendMessage("Your Abyssal Titan Damages your Opponent.");
                            c.startAnimation(1914);
                        } else if (c.oldPlayerIndex > 0 || c.playerIndex > 0) {
                            Server.playerHandler.players[c.playerIndex].playerLevel[3] -= damage;
                            Server.playerHandler.players[c.playerIndex].hitDiff2 = damage;
                            Server.playerHandler.players[c.playerIndex].hitUpdateRequired2 = true;
                            Server.playerHandler.players[c.playerIndex].updateRequired = true;
                            //o.sendMessage("Your opponent's steal titan causes you damage.");
                            c.sendMessage("Your Abyssal Titan Damages your Opponent.");
                            c.startAnimation(1914);
                        }
                    } else if (c.lastsummon == 7358) {
                        final int damage = Misc.random(17) + 4;
                        if (c.npcIndex > 0) {
                            Server.npcHandler.npcs[c.npcIndex].hitUpdateRequired2 = true;
                            Server.npcHandler.npcs[c.npcIndex].updateRequired = true;
                            Server.npcHandler.npcs[c.npcIndex].hitDiff2 = damage;
                            Server.npcHandler.npcs[c.npcIndex].HP -= damage;
                            c.sendMessage("Your Moss Titan Damages your Opponent.");
                            c.startAnimation(1914);
                        } else if (c.oldPlayerIndex > 0 || c.playerIndex > 0) {
                            Server.playerHandler.players[c.playerIndex].playerLevel[3] -= damage;
                            Server.playerHandler.players[c.playerIndex].hitDiff2 = damage;
                            Server.playerHandler.players[c.playerIndex].hitUpdateRequired2 = true;
                            Server.playerHandler.players[c.playerIndex].updateRequired = true;
                            //o.sendMessage("Your opponent's steal titan causes you damage.");
                            c.sendMessage("Your Moss Titan Damages your Opponent.");
                            c.startAnimation(1914);
                        }
                    } else if (c.lastsummon == 6874) {
                        c.getItems().addItem(15272, 3);
                        c.sendMessage("Your Pak Yack's Special Supplys you with Food!");
                    } else if (c.lastsummon == 6823) {
                        c.playerLevel[3] += 25;
                        if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
                            c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
                        c.sendMessage("Your Unicorn's Special Heals you for 250 HP!");
                        c.getPlayerAssistant().refreshSkill(3);
                    } else if (c.lastsummon == 6814) {
                        c.playerLevel[3] += 13;
                        if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
                            c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
                        c.sendMessage("Your Bunyip's Special Heals you for 130 HP!");

                        c.getPlayerAssistant().refreshSkill(3);
                    } else if (c.lastsummon == 6870) {
                        c.playerLevel[3] += 15;
                        c.playerLevel[6] += 6;
                        c.sendMessage("Your Wolpertinger's Special Heals you for 150 HP!");
                        c.sendMessage("Your Wolpertinger's Increases and Restores your Magic!");
                        if (c.playerLevel[6] > c.getLevelForXP(c.playerXP[6]))
                            c.playerLevel[6] = c.getLevelForXP(c.playerXP[6]) + 6;
                        if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
                            c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
                        c.getPlayerAssistant().refreshSkill(3);
                        c.getPlayerAssistant().refreshSkill(6);
                    } else {
                        c.sendMessage("You have no familiar with special attack spawned!");
                    }
                    c.summonSpec = 240;
                } else {
                    c.sendMessage("You must wait at least 4 Minutes before using this again.");
                }
                break;


            case 154:
                if (System.currentTimeMillis() - c.logoutDelay < 8000) {
                    c.sendMessage("You cannot do skillcape emotes in combat!");
                    return;
                }
                if (System.currentTimeMillis() - c.lastEmote >= 7000) {
                    if (c.getPlayerAssistant().wearingCape(c.playerEquipment[c.playerCape])) {
                        c.stopMovement();
                        c.gfx0(c.getPlayerAssistant().skillcapeGfx(c.playerEquipment[c.playerCape]));
                        c.startAnimation(c.getPlayerAssistant().skillcapeEmote(c.playerEquipment[c.playerCape]));
                    } else if (c.playerEquipment[c.playerCape] == 18743) {
                        c.getPlayerAssistant().compemote(c);
                    } else if (c.playerEquipment[c.playerCape] == 18509) {
                        c.getPlayerAssistant().dungemote2(c);
                    } else if (c.playerEquipment[c.playerCape] == 19709) {
                        c.getPlayerAssistant().dungemote(c);
                    } else {
                        c.sendMessage("You must be wearing a Skillcape to do this emote.");
                    }
                    c.lastEmote = System.currentTimeMillis();
                }
                break;

            //2nd tele option
            case 9191:
                if (c.dialogueAction == 4441) {
                    c.sendMessage("You currently have " + c.levelPoints + " level points.");
                    c.getShops().openShop(59);
                }
                if (c.teleAction == 46) {
                    c.sendMessage("This boss wil come soon, we promise!");
                }

                if (c.teleAction == 45) {
                    c.getPlayerAssistant().spellTeleport(2717, 9802, 0);
                    c.sendMessage("You teleport to the King Black Fucking Dragon chamber!");
                }
                if (c.dialogueAction == 106) {
                    if (c.getItems().playerHasItem(c.diceID, 1)) {
                        c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);
                        c.getItems().addItem(15088, 1);
                        c.sendMessage("You get two six-sided dice out of the dice bag.");
                    }
                    c.getPlayerAssistant().closeAllWindows();
                } else if (c.dialogueAction == 107) {
                    if (c.getItems().playerHasItem(c.diceID, 1)) {
                        c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);
                        c.getItems().addItem(15094, 1);
                        c.sendMessage("You get a twelve-sided die out of the dice bag.");
                    }
                    c.getPlayerAssistant().closeAllWindows();
                }
                if (c.teleAction == 1) {
                    //tav dungeon
                    c.getPlayerAssistant().spellTeleport(2884, 9798, 0);
                } else if (c.teleAction == 2) {
                    //pest control
                    c.getPlayerAssistant().spellTeleport(2662, 2650, 0);
                } else if (c.teleAction == 3) {
                    //kbd
                    c.getPlayerAssistant().spellTeleport(3007, 3849, 0);
                } else if (c.teleAction == 4) {
                    //graveyard
                    c.getPlayerAssistant().spellTeleport(3243, 3517, 0);
                } else if (c.teleAction == 5) {
                    c.getPlayerAssistant().spellTeleport(3079, 9502, 0);

                } else if (c.teleAction == 8) {
                    c.getPlayerAssistant().spellTeleport(2984, 9630, 0);
                    c.sendMessage("Beware: Recommended team of 5 Players or More");

                } else if (c.teleAction == 20) {
                    c.getPlayerAssistant().spellTeleport(3210, 3424, 0);//3210 3424
                }
                if (c.dialogueAction == 10) {
                    c.getPlayerAssistant().spellTeleport(2790, 4850, 0);
                    c.dialogueAction = -1;
                } else if (c.dialogueAction == 11) {
                    c.getPlayerAssistant().spellTeleport(2527, 4833, 0);
                    c.dialogueAction = -1;
                }
                if (c.teleAction == 21) {
                    c.getPlayerAssistant().spellTeleport(2151, 5099, 0);
                    c.dialogueAction = -1;
                }
                break;
            //3rd tele option


            case 9192:
                if (c.teleAction == 46) {
                    c.getPlayerAssistant().spellTeleport(2916, 3628, 0);
                    c.sendMessage("The brutal avatar of destruction, yours to defeat.");
                }
                if (c.teleAction == 45) {
                    c.getPlayerAssistant().spellTeleport(1910, 4367, 0);
                    c.sendMessage("You teleport to the Dagganoth lair!");
                }
                if (c.dialogueAction == 106) {
                    if (c.getItems().playerHasItem(c.diceID, 1)) {
                        c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);
                        c.getItems().addItem(15100, 1);
                        c.sendMessage("You get a four-sided die out of the dice bag.");
                    }
                    c.getPlayerAssistant().closeAllWindows();
                } else if (c.dialogueAction == 107) {
                    if (c.getItems().playerHasItem(c.diceID, 1)) {
                        c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);
                        c.getItems().addItem(15096, 1);
                        c.sendMessage("You get a twenty-sided die out of the dice bag.");
                    }
                    c.getPlayerAssistant().closeAllWindows();
                }
                if (c.teleAction == 1) {
                    //slayer tower
                    c.getPlayerAssistant().spellTeleport(3428, 3537, 0);
                } else if (c.teleAction == 2) {
                    //tzhaar
                    c.getPlayerAssistant().spellTeleport(2438, 5168, 0);
                    c.sendMessage("To fight Jad, enter the cave.");
                } else if (c.teleAction == 3) {
                    //dag kings
                    c.getPlayerAssistant().spellTeleport(1910, 4367, 0);
                    c.sendMessage("Climb down the ladder to get into the lair.");
                } else if (c.teleAction == 4) {
                    //Lava Crossing
                    c.getPlayerAssistant().spellTeleport(3367, 3935, 0);

                } else if (c.teleAction == 5) {
                    c.getPlayerAssistant().spellTeleport(2597, 3408, 0);
                } else if (c.teleAction == 21) {
                    c.getPlayerAssistant().spellTeleport(3022, 9828, 0);
                } else if (c.teleAction == 20) {
                    c.getPlayerAssistant().spellTeleport(2757, 3477, 0);
                }

                if (c.dialogueAction == 10) {
                    c.getPlayerAssistant().spellTeleport(2713, 4836, 0);
                    c.dialogueAction = -1;
                } else if (c.dialogueAction == 11) {
                    c.getPlayerAssistant().spellTeleport(2162, 4833, 0);
                    c.dialogueAction = -1;
                } else if (c.dialogueAction == 12) {
                    c.getPlayerAssistant().spellTeleport(2207, 4836, 0);
                    c.dialogueAction = -1;
                }
                if (c.teleAction == 8) {
                    c.getPlayerAssistant().startTeleport(3258, 9517, 2, "ancient");
                    c.sendMessage("Approach Nomad with caution.");
                }
                break;

            case 9193:
                if (c.teleAction == 46) {
                    c.getPlayerAssistant().spellTeleport(2967, 9480, 0);
                    c.sendMessage("The Sea Troll Queen, yours to defeat!.");
                }
                if (c.teleAction == 45) {
                    c.getPlayerAssistant().spellTeleport(2984, 9630, 0);
                    c.sendMessage("You teleport to the Lahkrahnaz, 5 people recommended.");
                }
                if (c.dialogueAction == 106) {
                    if (c.getItems().playerHasItem(c.diceID, 1)) {
                        c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);
                        c.getItems().addItem(15090, 1);
                        c.sendMessage("You get an eight-sided die out of the dice bag.");
                    }
                    c.getPlayerAssistant().closeAllWindows();
                } else if (c.dialogueAction == 107) {
                    if (c.getItems().playerHasItem(c.diceID, 1)) {
                        c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);
                        c.getItems().addItem(15098, 1);
                        c.sendMessage("You get the percentile dice out of the dice bag.");
                    }
                    c.getPlayerAssistant().closeAllWindows();
                }
                if (c.teleAction == 1) {
                    //brimhaven dungeon
                    c.getPlayerAssistant().spellTeleport(2710, 9466, 0);
                    c.sendMessage("You teleported to brimhaven dungeon, be sure to bring antifire-shield.");
                } else if (c.teleAction == 2) {
                    //duel arena
                    c.getPlayerAssistant().spellTeleport(3366, 3266, 0);
                } else if (c.teleAction == 3) {
                    //chaos elemental
                    c.getPlayerAssistant().spellTeleport(2717, 9805, 0);
                } else if (c.teleAction == 21) {
                    if ((c.playerLevel[10] >= 90)) {
                        c.getPlayerAssistant().spellTeleport(2611, 3396, 0);
                        c.sendMessage("You teleport to the fishing guild ewww fishy!");
                    }
                } else if (c.teleAction == 21) {
                    if ((c.playerLevel[10] < 90)) {
                        c.sendMessage("You must be at least 90 Fishing to Enter!");
                    }
                } else if (c.teleAction == 4) {
                    //Fala
                    c.getPlayerAssistant().spellTeleport(3086, 3516, 0);

                } else if (c.teleAction == 5) {
                    c.getPlayerAssistant().spellTeleport(2724, 3484, 0);
                    c.sendMessage("For magic logs, try north of the duel arena.");
                }
                if (c.dialogueAction == 10) {
                    c.getPlayerAssistant().spellTeleport(2660, 4839, 0);
                    c.dialogueAction = -1;
                } else if (c.dialogueAction == 11) {
                    //c.getPA().spellTeleport(2527, 4833, 0); astrals here
                    c.getRunecrafting().craftRunes(2489);
                    c.dialogueAction = -1;
                } else if (c.dialogueAction == 12) {
                    //c.getPA().spellTeleport(2464, 4834, 0); bloods here
                    c.getRunecrafting().craftRunes(2489);
                    c.dialogueAction = -1;

                } else if (c.teleAction == 20) {
                    c.getPlayerAssistant().spellTeleport(2896, 3456, 0);
                }
                if (c.teleAction == 8) {
                    c.getPlayerAssistant().startTeleport(2465, 4770, 0, "ancient");
                    c.sendMessage("Beware of the Snakes!.");
                }
                break;

            case 9194:
                if (c.teleAction == 46) {
                    c.getPlayerAssistant().spellTeleport(3303, 9375, 0);
                    c.sendMessage("You teleported to the Corporal Beast, dont die.");
                }
                if (c.teleAction == 45) {
                    c.getPlayerAssistant().spellTeleport(3258, 9517, 2);
                    c.sendMessage("You teleport to Nomad!");
                }
                if (c.dialogueAction == 107) {
                    c.getDH().sendDialogues(106, 4289);
                    return;
                }
                if (c.dialogueAction == 106) {
                    c.getDH().sendDialogues(107, 4289);
                    return;
                }
                if (c.teleAction == 1) {
                    //island
                    c.getPlayerAssistant().spellTeleport(3117, 9847, 0);
                } else if (c.teleAction == 2) {
                    //last minigame spot
                    c.getPlayerAssistant().spellTeleport(2865, 3546, 0);
                    //c.getPA().closeAllWindows();
                } else if (c.teleAction == 3) {
                    c.getPlayerAssistant().spellTeleport(3302, 9372, 0);
                    c.sendMessage("Enter the gate to fight the mighty Corporeal Beast!");
                    c.sendMessage("Note: Magic protect, Ruby bolts (e) and Diamond bolts (e) are recommended!");
                    c.getPlayerAssistant().closeAllWindows();
                } else if (c.teleAction == 4) {
                    c.getPlayerAssistant().spellTeleport(2980, 3617, 0);
                } else if (c.teleAction == 5) {
                    c.getPlayerAssistant().spellTeleport(2812, 3463, 0);
                }
                if (c.dialogueAction == 10 || c.dialogueAction == 11) {
                    c.dialogueId++;
                    c.getDH().sendDialogues(c.dialogueId, 0);
                } else if (c.dialogueAction == 12) {
                    c.dialogueId = 17;
                    c.getDH().sendDialogues(c.dialogueId, 0);

                } else if (c.teleAction == 20) {
                    c.getPlayerAssistant().spellTeleport(3493, 3484, 0);

                } else if (c.teleAction == 8) {
                    c.getPlayerAssistant().startTeleport(2916, 3628, 12, "ancient");
                    c.sendMessage("The Brutal Avatar of Destruction, Good Luck!");
                }
                break;

            case 55096://This is the button id
                c.getPlayerAssistant().removeAllWindows();//Choosing No will remove all the windows
                c.droppedItem = -1;
                break;

            case 55095://This is the button id
                c.getPlayerAssistant().destroyItem(c.droppedItem);//Choosing Yes will delete the item and make it dissapear
                c.droppedItem = -1;
                break;

            case 71074:
                if (c.clanId >= 0 && Server.clanChat.clans[c.clanId].owner.equalsIgnoreCase(c.playerName)) {
                    if (c.CSLS == 0) {
                        if (System.currentTimeMillis() - c.lastEmote >= 1500) {
                            Server.clanChat.clans[c.clanId].CS = 1;
                            Server.clanChat.sendLootShareMessage(c.clanId, "LootShare has been toggled to " + (!Server.clanChat.clans[c.clanId].lootshare ? "ON" : "OFF") + " by the clan leader.");
                            Server.clanChat.clans[c.clanId].lootshare = !Server.clanChat.clans[c.clanId].lootshare;
                            c.CSLS = 1;
                            Server.clanChat.updateClanChat(c.clanId);
                            c.lastEmote = System.currentTimeMillis();
                            return;
                        }
                    }
                    if (c.CSLS == 1) {
                        if (System.currentTimeMillis() - c.lastEmote >= 1500) {
                            c.CSLS = 2;
                            Server.clanChat.clans[c.clanId].CS = 2;
                            Server.clanChat.updateClanChat(c.clanId);
                            Server.clanChat.sendLootShareMessage(c.clanId, "LootShare has been toggled to " + (!Server.clanChat.clans[c.clanId].lootshare ? "ON" : "OFF") + " by the clan leader.");
                            Server.clanChat.clans[c.clanId].lootshare = !Server.clanChat.clans[c.clanId].lootshare;
                            c.lastEmote = System.currentTimeMillis();
                            return;

                        }
                    }
                    if (c.CSLS == 2) {
                        if (System.currentTimeMillis() - c.lastEmote >= 1500) {
                            if (Server.clanChat.clans[c.clanId].playerz == 1) {
                                c.sendMessage("There must be atleast 2 members in the clan chat to toggle Coinshare ON.");
                                c.CSLS = 0;
                                Server.clanChat.clans[c.clanId].CS = 0;
                                Server.clanChat.updateClanChat(c.clanId);
                                c.lastEmote = System.currentTimeMillis();
                                return;
                            }
                            c.CSLS = 3;
                            Server.clanChat.clans[c.clanId].CS = 3;
                            Server.clanChat.updateClanChat(c.clanId);
                            Server.clanChat.sendCoinShareMessage(c.clanId, "CoinShare has been toggled to " + (!Server.clanChat.clans[c.clanId].coinshare ? "ON" : "OFF") + " by the clan leader.");
                            Server.clanChat.clans[c.clanId].coinshare = !Server.clanChat.clans[c.clanId].coinshare;
                            return;

                        }
                    }
                    if (c.CSLS == 3) {
                        if (System.currentTimeMillis() - c.lastEmote >= 1500) {
                            c.CSLS = 0;
                            Server.clanChat.clans[c.clanId].CS = 0;
                            Server.clanChat.updateClanChat(c.clanId);
                            Server.clanChat.sendCoinShareMessage(c.clanId, "CoinShare has been toggled to " + (!Server.clanChat.clans[c.clanId].coinshare ? "ON" : "OFF") + " by the clan leader.");
                            Server.clanChat.clans[c.clanId].coinshare = !Server.clanChat.clans[c.clanId].coinshare;
                            c.lastEmote = System.currentTimeMillis();
                            return;
                        }
                    }
                } else {
                    c.sendMessage("Only the owner of the clan has the power to do that.");
                }
                break;
            case 34185:
            case 34184:
            case 34183:
            case 34182:
            case 34189:
            case 34188:
            case 34187:
            case 34186:
            case 34193:
            case 34192:
            case 34191:
            case 34190:
                if (c.craftingLeather)
                    c.getCrafting().handleCraftingClick(actionButtonId);
                if (c.getFletching().fletching)
                    c.getFletching().handleFletchingClick(actionButtonId);
                break;


            case 15147:
                if (c.smeltInterface) {
                    c.smeltType = 2349;
                    c.smeltAmount = 1;
                    c.getSmithing().startSmelting(c.smeltType);
                }
                break;
            case 15146:
                if (c.smeltInterface) {
                    c.smeltType = 2349;
                    c.smeltAmount = 5;
                    c.getSmithing().startSmelting(c.smeltType);
                }
                break;
            case 10247:
                if (c.smeltInterface) {
                    c.smeltType = 2349;
                    c.smeltAmount = 10;
                    c.getSmithing().startSmelting(c.smeltType);
                }
                break;
            case 9110:
                if (c.smeltInterface) {
                    c.smeltType = 2349;
                    c.smeltAmount = 28;
                    c.getSmithing().startSmelting(c.smeltType);
                }
                break;

            case 15151:
                if (c.smeltInterface) {
                    c.smeltType = 2351;
                    c.smeltAmount = 1;
                    c.getSmithing().startSmelting(c.smeltType);
                }
                break;
            case 15150:
                if (c.smeltInterface) {
                    c.smeltType = 2351;
                    c.smeltAmount = 5;
                    c.getSmithing().startSmelting(c.smeltType);
                }
                break;
            case 15149:
                if (c.smeltInterface) {
                    c.smeltType = 2351;
                    c.smeltAmount = 10;
                    c.getSmithing().startSmelting(c.smeltType);
                }
                break;
            case 15148:
                if (c.smeltInterface) {
                    c.smeltType = 2351;
                    c.smeltAmount = 28;
                    c.getSmithing().startSmelting(c.smeltType);
                }
                break;


            case 15159:
                if (c.smeltInterface) {
                    c.smeltType = 2353;
                    c.smeltAmount = 1;
                    c.getSmithing().startSmelting(c.smeltType);
                }
                break;
            case 15158:
                if (c.smeltInterface) {
                    c.smeltType = 2353;
                    c.smeltAmount = 5;
                    c.getSmithing().startSmelting(c.smeltType);
                }
                break;
            case 15157:
                if (c.smeltInterface) {
                    c.smeltType = 2353;
                    c.smeltAmount = 10;
                    c.getSmithing().startSmelting(c.smeltType);
                }
                break;
            case 15156:
                if (c.smeltInterface) {
                    c.smeltType = 2353;
                    c.smeltAmount = 28;
                    c.getSmithing().startSmelting(c.smeltType);
                }
                break;

            case 29017:
                if (c.smeltInterface) {
                    c.smeltType = 2359;
                    c.smeltAmount = 1;
                    c.getSmithing().startSmelting(c.smeltType);
                }
                break;
            case 29016:
                if (c.smeltInterface) {
                    c.smeltType = 2359;
                    c.smeltAmount = 5;
                    c.getSmithing().startSmelting(c.smeltType);
                }
                break;
            case 24253:
                if (c.smeltInterface) {
                    c.smeltType = 2359;
                    c.smeltAmount = 10;
                    c.getSmithing().startSmelting(c.smeltType);
                }
                break;
            case 16062:
                if (c.smeltInterface) {
                    c.smeltType = 2359;
                    c.smeltAmount = 28;
                    c.getSmithing().startSmelting(c.smeltType);
                }
                break;

            case 29022:
                if (c.smeltInterface) {
                    c.smeltType = 2361;
                    c.smeltAmount = 1;
                    c.getSmithing().startSmelting(c.smeltType);
                }
                break;
            case 29020:
                if (c.smeltInterface) {
                    c.smeltType = 2361;
                    c.smeltAmount = 5;
                    c.getSmithing().startSmelting(c.smeltType);
                }
                break;
            case 29019:
                if (c.smeltInterface) {
                    c.smeltType = 2361;
                    c.smeltAmount = 10;
                    c.getSmithing().startSmelting(c.smeltType);
                }
                break;
            case 29018:
                if (c.smeltInterface) {
                    c.smeltType = 2361;
                    c.smeltAmount = 28;
                    c.getSmithing().startSmelting(c.smeltType);
                }
                break;
            case 29026:
                if (c.smeltInterface) {
                    c.smeltType = 2363;
                    c.smeltAmount = 1;
                    c.getSmithing().startSmelting(c.smeltType);
                }

                break;
            case 29025://smelt 5
                if (c.smeltInterface) {
                    c.smeltType = 2363;
                    c.smeltAmount = 5;
                    c.getSmithing().startSmelting(c.smeltType);
                }
                break;
            case 29024://smelt 10
                if (c.smeltInterface) {
                    c.smeltType = 2363;
                    c.smeltAmount = 10;
                    c.getSmithing().startSmelting(c.smeltType);
                }
                break;
            case 108005:
                c.getPlayerAssistant().showInterface(19148);
                break;

            case 59004:
                c.getPlayerAssistant().removeAllWindows();
                break;

            case 70212:
                if (c.clanId > -1)
                    Server.clanChat.leaveClan(c.playerId, c.clanId);
                else
                    c.sendMessage("You are not in a clan.");
                break;
            case 62137:
                if (c.clanId >= 0) {
                    c.sendMessage("You are already in a clan.");
                    break;
                }
                if (c.getOutStream() != null) {
                    c.getOutStream().createFrame(187);
                    c.flushOutStream();
                }
                break;

            case 9178:
                if (c.teleAction == 5792) {
                    c.getShops().openShop(5);
                    return;
                }
                int npcType = 6138;
                if (c.teleAction == 1399) {
                    c.getShops().openShop(8);
                    return;
                }
                if (c.teleAction == 1359) {
                    c.getShops().openShop(50);
                    c.sendMessage("Thank you for voting, it really helps us!");
                    return;
                }
                if (c.dialogueAction == 42) {

                    if (c.inWild())
                        return;
                    for (int j = 0; j < c.playerEquipment.length; j++) {
                        if (c.playerEquipment[j] > 0) {
                            c.getPlayerAssistant().closeAllWindows();
                            c.getDH().sendDialogues(420, npcType);
                            return;
                        }
                    }
                    try {
                        int skilld = 1;
                        int leveld = 1;
                        c.levelPoints = 0;
                        c.playerXP[skilld] = c.getPlayerAssistant().getXPForLevel(leveld) + 5;
                        c.playerLevel[skilld] = c.getPlayerAssistant().getLevelForXP(c.playerXP[skilld]);
                        c.getPlayerAssistant().refreshSkill(skilld);
                        //	c.getPA().closeAllWindows();
                        c.getDH().sendDialogues(230, npcType);
                    } catch (Exception e) {
                    }
                }
                if (c.usingGlory)
                    c.getPlayerAssistant().startTeleport(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0, "modern");
                if (c.dialogueAction == 2)
                    c.getPlayerAssistant().startTeleport(3428, 3538, 0, "modern");
                if (c.dialogueAction == 3)
                    c.getPlayerAssistant().startTeleport(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0, "modern");
                if (c.dialogueAction == 4)
                    c.getPlayerAssistant().startTeleport(3565, 3314, 0, "modern");
                if (c.dialogueAction == 20) {
                    c.getPlayerAssistant().startTeleport(2897, 3618, 4, "modern");
                }
                if (c.dialogueAction == 100) {
                    c.getDH().sendDialogues(25, 946);
                }

                break;


            case 9179:
                if (c.teleAction == 5792) {
                    c.getShops().openShop(20);
                    return;
                }
                if (c.teleAction == 1399) {
                    c.getShops().openShop(21);
                    return;
                }
                if (c.teleAction == 1359) {
                    c.getShops().openShop(49);
                    c.sendMessage("Donating really helps us!");
                    return;
                }
                npcType = 6138;
                if (c.dialogueAction == 42) { //prayer
                    if (c.inWild())
                        return;
                    for (int j = 0; j < c.playerEquipment.length; j++) {
                        if (c.playerEquipment[j] > 0) {
                            c.getPlayerAssistant().closeAllWindows();
                            c.getDH().sendDialogues(420, npcType);
                            return;
                        }
                    }
                    try {
                        int skillp = 5;
                        int levelp = 1;
                        c.playerXP[skillp] = c.getPlayerAssistant().getXPForLevel(levelp) + 5;
                        c.playerLevel[skillp] = c.getPlayerAssistant().getLevelForXP(c.playerXP[skillp]);
                        c.getPlayerAssistant().refreshSkill(skillp);
                        c.levelPoints = 0;
                        //c.getPA().closeAllWindows();
                        c.getDH().sendDialogues(260, npcType);
                    } catch (Exception e) {
                    }
                }
                if (c.usingGlory)
                    c.getPlayerAssistant().startTeleport(Config.AL_KHARID_X, Config.AL_KHARID_Y, 0, "modern");
                if (c.dialogueAction == 2)
                    c.getPlayerAssistant().startTeleport(2884, 3395, 0, "modern");
                if (c.dialogueAction == 3)
                    c.getPlayerAssistant().startTeleport(3243, 3513, 0, "modern");
                if (c.dialogueAction == 4)
                    c.getPlayerAssistant().startTeleport(2444, 5170, 0, "modern");
                if (c.dialogueAction == 20) {
                    c.getPlayerAssistant().startTeleport(2897, 3618, 12, "modern");
                }
                if (c.dialogueAction == 101) {
                    c.getDH().sendDialogues(21, 946);
                }
                if (c.dialogueAction == 100) {
                    c.getGamble().gambleBlackJack(c);
                }
                break;

            case 9180:
                if (c.teleAction == 5792) {
                    c.getShops().openShop(51);
                    return;
                }
                if (c.teleAction == 1399) {
                    c.getShops().openShop(12);
                    return;
                }
                if (c.teleAction == 1359) {
                    c.getShops().openShop(73);
                    return;
                }
                npcType = 6138;
                if (c.dialogueAction == 42) { //attack
                    if (c.inWild())
                        return;
                    for (int j = 0; j < c.playerEquipment.length; j++) {
                        if (c.playerEquipment[j] > 0) {
                            c.getPlayerAssistant().closeAllWindows();
                            c.getDH().sendDialogues(420, npcType);
                            return;
                        }
                    }
                    try {
                        int skill = 0;
                        int levela = 1;
                        c.levelPoints = 0;
                        c.playerXP[skill] = c.getPlayerAssistant().getXPForLevel(levela) + 5;
                        c.playerLevel[skill] = c.getPlayerAssistant().getLevelForXP(c.playerXP[skill]);
                        c.getPlayerAssistant().refreshSkill(skill);
                        //c.getPA().closeAllWindows();
                        c.getDH().sendDialogues(240, npcType);
                    } catch (Exception e) {
                    }
                }
                if (c.usingGlory)
                    c.getPlayerAssistant().startTeleport(Config.KARAMJA_X, Config.KARAMJA_Y, 0, "modern");
                if (c.dialogueAction == 2)
                    c.getPlayerAssistant().startTeleport(2471, 10137, 0, "modern");
                if (c.dialogueAction == 3)
                    c.getPlayerAssistant().startTeleport(3363, 3676, 0, "modern");
                if (c.dialogueAction == 4)
                    c.getPlayerAssistant().startTeleport(2659, 2676, 0, "modern");
                if (c.dialogueAction == 20) {
                    c.getPlayerAssistant().startTeleport(2897, 3618, 8, "modern");
                }
                if (c.dialogueAction == 101) {
                    c.getDH().sendDialogues(23, 946);
                }
                if (c.dialogueAction == 100) {
                    if (!c.getItems().playerHasItem(995, 1000000)) {
                        c.sendMessage("You need at least 1M coins to play this game!");
                        c.getPlayerAssistant().removeAllWindows();
                        break;
                    }
                    c.getGamble().playGame(c);
                }
                break;

            case 9181:
                npcType = 6138;
                if (c.teleAction == 1359) {
                    c.getShops().openShop(59);
                    return;
                }
                if (c.teleAction == 1399) {
                    c.getShops().openShop(13);
                    return;
                }
                if (c.dialogueAction == 42) { //allstats
                    if (c.inWild())
                        return;
                    for (int j = 0; j < c.playerEquipment.length; j++) {
                        if (c.playerEquipment[j] > 0) {
                            c.getPlayerAssistant().closeAllWindows();
                            c.getDH().sendDialogues(420, npcType);
                            return;
                        }
                    }
                    try {
                        int skill1 = 0;
                        int level = 1;
                        c.levelPoints = 0;
                        c.playerXP[skill1] = c.getPlayerAssistant().getXPForLevel(level) + 5;
                        c.playerLevel[skill1] = c.getPlayerAssistant().getLevelForXP(c.playerXP[skill1]);
                        c.getPlayerAssistant().refreshSkill(skill1);
                        int skill2 = 1;
                        //	int level = 1;
                        c.playerXP[skill2] = c.getPlayerAssistant().getXPForLevel(level) + 5;
                        c.playerLevel[skill2] = c.getPlayerAssistant().getLevelForXP(c.playerXP[skill2]);
                        c.getPlayerAssistant().refreshSkill(skill2);
                        int skill3 = 2;
                        //	int level = 1;
                        c.playerXP[skill3] = c.getPlayerAssistant().getXPForLevel(level) + 5;
                        c.playerLevel[skill3] = c.getPlayerAssistant().getLevelForXP(c.playerXP[skill3]);
                        c.getPlayerAssistant().refreshSkill(skill3);
                        int skill4 = 3;
                        level = 10;
                        c.playerXP[skill4] = c.getPlayerAssistant().getXPForLevel(level) + 5;
                        c.playerLevel[skill4] = c.getPlayerAssistant().getLevelForXP(c.playerXP[skill4]);
                        c.getPlayerAssistant().refreshSkill(skill4);
                        int skill5 = 4;
                        level = 1;
                        c.playerXP[skill5] = c.getPlayerAssistant().getXPForLevel(level) + 5;
                        c.playerLevel[skill5] = c.getPlayerAssistant().getLevelForXP(c.playerXP[skill5]);
                        c.getPlayerAssistant().refreshSkill(skill5);
                        int skill6 = 5;
                        //	int level = 1;
                        c.playerXP[skill6] = c.getPlayerAssistant().getXPForLevel(level) + 5;
                        c.playerLevel[skill6] = c.getPlayerAssistant().getLevelForXP(c.playerXP[skill6]);
                        c.getPlayerAssistant().refreshSkill(skill6);
                        int skill7 = 6;
                        //	int level = 1;
                        c.playerXP[skill7] = c.getPlayerAssistant().getXPForLevel(level) + 5;
                        c.playerLevel[skill7] = c.getPlayerAssistant().getLevelForXP(c.playerXP[skill7]);
                        c.getPlayerAssistant().refreshSkill(skill7);
                        //	c.getPA().closeAllWindows();
                        c.getDH().sendDialogues(250, npcType);
                    } catch (Exception e) {
                    }
                }
                if (c.usingGlory)
                    c.getPlayerAssistant().startTeleport(Config.MAGEBANK_X, Config.MAGEBANK_Y, 0, "modern");
                if (c.dialogueAction == 2)
                    c.getPlayerAssistant().startTeleport(2669, 3714, 0, "modern");
                if (c.dialogueAction == 3)
                    c.getPlayerAssistant().startTeleport(2540, 4716, 0, "modern");
                if (c.dialogueAction == 4) {
                    c.getPlayerAssistant().startTeleport(3366, 3266, 0, "modern");
                    c.sendMessage("Dueling is at your own risk. Refunds will not be given for items lost due to glitches.");
                }
                if (c.dialogueAction == 20) {
                    //c.getPA().startTeleport(3366, 3266, 0, "modern");
                    //c.killCount = 0;
                    c.sendMessage("This will be added shortly");
                } else if (c.dialogueAction == 10 || c.dialogueAction == 101) {
                    c.dialogueAction = 0;
                    c.getPlayerAssistant().removeAllWindows();
                } else {
                    c.getPlayerAssistant().removeAllWindows();
                }
                c.dialogueAction = 0;
                break;

            case 1093:
            case 1094:
            case 1097:
            case 15486:
                if (c.autocastId > 0) {
                    c.getPlayerAssistant().resetAutocast();
                } else {
                    if (c.playerMagicBook == 1) {
                        if (c.playerEquipment[c.playerWeapon] == 4675 || c.playerEquipment[c.playerWeapon] == 15486 || c.playerEquipment[c.playerWeapon] == 18355)
                            c.setSidebarInterface(0, 1689);
                        else
                            c.sendMessage("You can't autocast ancients without an ancient, chaotic staff or a SOL.");
                    } else if (c.playerMagicBook == 0) {
                        if (c.playerEquipment[c.playerWeapon] == 4170 || c.playerEquipment[c.playerWeapon] == 15486 || c.playerEquipment[c.playerWeapon] == 15040) {
                            c.setSidebarInterface(0, 12050);
                        } else {
                            c.setSidebarInterface(0, 1829);
                        }
                    }

                }
                break;


            case 9157:
                if (c.dialogueAction == 90) {
                    c.getDH().sendDialogues(366, 783);
                    c.nomad += 1;
                    c.getPlayerAssistant().sendFrame126("@yel@The Ritual", 29162);
                    return;
                } else if (c.dialogueAction == 91) {
                    c.getDH().sendDialogues(379, 302);
                    return;
                } else if (c.dialogueAction == 93) {
                    c.getDH().sendDialogues(406, 302);
                    return;
                } else if (c.dialogueAction == 92) {
                    c.getDH().sendDialogues(391, 783);
                    return;
                }
                if (c.dialogueAction == 3516) {
                    c.getItems().addItem(19139, 1);
                    c.getItems().addItem(19138, 1);
                    c.sendMessage("You've obtained the Completionist Cape!");

                }
                if (c.teleAction == 506) {
                    if (c.getItems().playerHasItem(995, 99000)) {
                        c.getItems().deleteItem(995, 99000);
                        c.sendMessage("You have bought a dungeoneering cape.");
                        c.getItems().addItem(15706, 1);
                    } else {
                        c.sendMessage("You do not have 99k.");
                    }
                }
                if (c.teleAction == 1397) {
                    if (c.getItems().playerHasItem(1917, 1)) {
                        c.getPlayerAssistant().addSkillXP((100000), 24);
                        c.getItems().deleteItem(1917, 1);
                        c.sendMessage("The gods reward you with 100k dungeoneering XP!");
                        c.gfx0(287);
                    } else {
                        c.sendMessage("You do not have a beer, kill guards to steal a beer!");
                    }
                }
                if (c.teleAction == 235) {
                    c.getPlayerAssistant().movePlayer(3281, 2785, 0);
                    c.sendMessage("Watch out, the soldiers will attack you!");
                    return;
                }
                if (c.teleAction == 1599) {
                    c.getPlayerAssistant().movePlayer(3233, 2902, 0);
                    c.getPlayerAssistant().closeAllWindows();
                    c.sendMessage("<img=13>[Shaloxis] By the power of Aggroth, i have teleported you.<img=13>");
                    return;
                }
                if (c.teleAction == 1335) {
                    c.sendMessage("You have launched the website.");
                    c.getPlayerAssistant().closeAllWindows();
                    c.getPlayerAssistant().sendFrame126("www.aggroth.com", 12000);
                    return;
                }
                if (c.teleAction == 1662) {
                    c.getShops().openShop(57);
                    return;
                }
                if (c.teleAction == 5791) {
                    c.getShops().openShop(3);
                    return;
                }
                if (c.dialogueAction == 1338) {
                    c.logout();
                }
                if (c.dialogueAction == 253) {
                    c.getPlayerAssistant().showInterface(14040);
                }
                if (c.dialogueAction == 4421) {
                    for (int i = 0; i < 21; i++) {
                        if (c.playerXP[i] <= c.getPlayerAssistant().getXPForLevel(10)) {
                            c.sendMessage("You need level 10 in ALL LEVELS to view the lowest shop");
                            return;
                        }
                    }
                    for (int i = 0; i < 21; i++) {
                        if (c.playerXP[i] >= c.getPlayerAssistant().getXPForLevel(10) && c.playerXP[i] <= c.getPlayerAssistant().getXPForLevel(20)) {
                            c.getShops().openShop(91);
                            return;
                        }
                    }
                    for (int i = 0; i < 21; i++) {
                        if (c.playerXP[i] >= c.getPlayerAssistant().getXPForLevel(20) && c.playerXP[i] <= c.getPlayerAssistant().getXPForLevel(30)) {
                            c.getShops().openShop(92);
                            return;
                        }
                    }
                    for (int i = 0; i < 21; i++) {
                        if (c.playerXP[i] >= c.getPlayerAssistant().getXPForLevel(30) && c.playerXP[i] <= c.getPlayerAssistant().getXPForLevel(40)) {
                            c.getShops().openShop(93);
                            return;
                        }
                    }
                    for (int i = 0; i < 21; i++) {
                        if (c.playerXP[i] >= c.getPlayerAssistant().getXPForLevel(40) && c.playerXP[i] <= c.getPlayerAssistant().getXPForLevel(50)) {
                            c.getShops().openShop(94);
                            return;
                        }
                    }
                    for (int i = 0; i < 21; i++) {
                        if (c.playerXP[i] >= c.getPlayerAssistant().getXPForLevel(50) && c.playerXP[i] <= c.getPlayerAssistant().getXPForLevel(60)) {
                            c.getShops().openShop(95);
                            return;
                        }
                    }
                    for (int i = 0; i < 21; i++) {
                        if (c.playerXP[i] >= c.getPlayerAssistant().getXPForLevel(60) && c.playerXP[i] <= c.getPlayerAssistant().getXPForLevel(70)) {
                            c.getShops().openShop(96);
                            return;
                        }
                    }
                    for (int i = 0; i < 21; i++) {
                        if (c.playerXP[i] >= c.getPlayerAssistant().getXPForLevel(70) && c.playerXP[i] <= c.getPlayerAssistant().getXPForLevel(80)) {
                            c.getShops().openShop(97);
                            return;
                        }
                    }
                    for (int i = 0; i < 21; i++) {
                        if (c.playerXP[i] >= c.getPlayerAssistant().getXPForLevel(80) && c.playerXP[i] <= c.getPlayerAssistant().getXPForLevel(90)) {
                            c.getShops().openShop(98);
                            return;
                        }
                    }
                    for (int i = 0; i < 21; i++) {
                        if (c.playerXP[i] >= c.getPlayerAssistant().getXPForLevel(90) && c.playerXP[i] <= c.getPlayerAssistant().getXPForLevel(99)) {
                            c.getShops().openShop(99);
                            return;
                        }
                    }
                    for (int i = 0; i < 21; i++) {
                        if (c.playerXP[i] >= c.getPlayerAssistant().getXPForLevel(99)) {
                            c.getShops().openShop(100);
                        }
                    }
                }
                if (c.dialogueAction == 1) {
                    int r = 4;
                    //int r = Misc.random(3);
                    switch (r) {
                        case 0:
                            c.getPlayerAssistant().movePlayer(3534, 9677, 0);
                            break;

                        case 1:
                            c.getPlayerAssistant().movePlayer(3534, 9712, 0);
                            break;

                        case 2:
                            c.getPlayerAssistant().movePlayer(3568, 9712, 0);
                            break;

                        case 3:
                            c.getPlayerAssistant().movePlayer(3568, 9677, 0);
                            break;
                        case 4:
                            c.getPlayerAssistant().movePlayer(3551, 9694, 0);
                            break;
                    }
                } else if (c.dialogueAction == 2) {
                    c.getPlayerAssistant().movePlayer(2507, 4717, 0);
                } else if (c.dialogueAction == 5) {
                    c.getSlayer().giveTask();
                } else if (c.dialogueAction == 6) {
                    c.getSlayer().giveTask2();
                } else if (c.dialogueAction == 7) {
                    c.getPlayerAssistant().startTeleport(3088, 3933, 0, "modern");
                    c.sendMessage("NOTE: You are now in the wilderness...");
                } else if (c.dialogueAction == 50) {
                    c.getPlayerAssistant().startTeleport(2661, 3307, 0, "modern");
                    c.sendMessage("This is PVP");
                } else if (c.dialogueAction == 51) {
                    c.getPlayerAssistant().startTeleport(3007, 3631, 0, "modern");
                } else if (c.dialogueAction == 8) {
                    c.getPlayerAssistant().resetBarrows();
                    c.sendMessage("Your barrows have been reset.");
                } else if (c.dialogueAction == 13) {
                    c.getPlayerAssistant().spellTeleport(1762, 5180, 0);
                    c.dialogueAction = -1;
                } else if (c.dialogueAction == 27) {
                    c.getPlayerAssistant().movePlayer(3210, 3424, 0);
                    c.monkeyk0ed = 0;
                    c.Jail = false;
                    c.forcedText = "I swear to god that i will never break the rules anymore!";
                    c.forcedChatUpdateRequired = true;
                    c.updateRequired = true;
                }
                c.dialogueAction = 0;
                c.getPlayerAssistant().removeAllWindows();
                break;
            case 9158:
                if (c.dialogueAction == 90) {
                    c.getDH().sendDialogues(367, 783);
                    return;
                } else if (c.dialogueAction == 91) {
                    c.getDH().sendDialogues(380, 817);
                    return;
                } else if (c.dialogueAction == 92) {
                    c.getDH().sendDialogues(392, 783);
                    return;
                } else if (c.dialogueAction == 93) {
                    c.getDH().sendDialogues(408, 302);
                    return;
                }
                if (c.teleAction == 1397) {
                    c.getDH().sendDialogues(1398, 1394);
                    return;
                }
                if (c.teleAction == 5791) {
                    c.getShops().openShop(2);
                    return;
                }
                if (c.dialogueAction == 50) {
                    c.getPlayerAssistant().startTeleport(2559, 3089, 0, "modern");
                    c.sendMessage("This is PVP!");
                } else if (c.dialogueAction == 51) {
                    c.getPlayerAssistant().startTeleport(3243, 3790, 0, "modern");

                } else if (c.dialogueAction == 13) {
                    c.getPlayerAssistant().spellTeleport(3202, 3859, 0);
                    c.dialogueAction = -1;
                } else if (c.dialogueAction == 34) {
                    c.getPlayerAssistant().removeAllWindows();
                    c.dialogueAction = -1;
                }

                if (c.dialogueAction == 8) {
                    c.getPlayerAssistant().fixAllBarrows();
                } else {
                    c.dialogueAction = 0;
                    c.getPlayerAssistant().removeAllWindows();
                }
                break;
            case 9159:
                if (c.teleAction == 5792) {
                    c.getShops().openShop(51);
                    return;
                }
                if (c.teleAction == 1359) {
                    c.getShops().openShop(73);
                    return;
                }
                if (c.dialogueAction == 51) {
                    c.getPlayerAssistant().startTeleport(3351, 3659, 0, "modern");
                }
                break;
            case 107243:
                c.setSidebarInterface(4, 1644);
                break;

            case 107215:
                c.setSidebarInterface(11, 904);
                break;

            /**Specials**/
            case 29188:
                c.specBarId = 7636; // the special attack text - sendframe126(S P E C I A L  A T T A C K, c.specBarId);
                c.usingSpecial = !c.usingSpecial;
                c.getItems().updateSpecialBar();
                break;

            case 29163:
                c.specBarId = 7611;
                c.usingSpecial = !c.usingSpecial;
                c.getItems().updateSpecialBar();
                break;

            case 33033:
                c.specBarId = 8505;
                c.usingSpecial = !c.usingSpecial;
                c.getItems().updateSpecialBar();
                break;

            case 29038:
                if (c.playerEquipment[c.playerWeapon] == 13902) {
                    c.specBarId = 7486;
                    c.usingSpecial = !c.usingSpecial;
                    c.getItems().updateSpecialBar();
                } else {
                    c.specBarId = 7486;
			/*if (c.specAmount >= 5) {
				c.attackTimer = 0;
				c.getCombat().attackPlayer(c.playerIndex);
				c.usingSpecial = true;
				c.specAmount -= 5;
			}*/
                    c.getCombat().handleGmaulPlayer();
                    c.getItems().updateSpecialBar();
                }
                break;

            case 29063:
                if (c.getCombat().checkSpecAmount(c.playerEquipment[c.playerWeapon])) {
                    c.gfx0(246);
                    c.forcedChat("Raarrrrrgggggghhhhhhh!");
                    c.startAnimation(1056);
                    c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]) + (c.getLevelForXP(c.playerXP[2]) * 15 / 100);
                    c.getPlayerAssistant().refreshSkill(2);
                    c.getItems().updateSpecialBar();
                } else {
                    c.sendMessage("You don't have the required special energy to use this attack.");
                }
                break;

            case 48023:
                c.specBarId = 12335;
                c.usingSpecial = !c.usingSpecial;
                c.getItems().updateSpecialBar();
                break;

            case 30108:
                c.specBarId = 7812;
                c.usingSpecial = !c.usingSpecial;
                c.getItems().updateSpecialBar();
                break;

            case 29138:
                if (c.playerEquipment[c.playerWeapon] == 15486) {
                    if (c.getCombat().checkSpecAmount(c.playerEquipment[c.playerWeapon])) {
                        c.gfx0(1958);
                        c.SolProtect = 120;
                        c.startAnimation(10518);
                        c.getItems().updateSpecialBar();
                        c.usingSpecial = !c.usingSpecial;
                        c.sendMessage("All damage will be split into half for 1 minute.");
                        c.forcedChat("I am Protected By the Light!");
                        c.getPlayerAssistant().sendFrame126("@bla@S P E C I A L  A T T A C K", 7562);
                    } else {
                        c.sendMessage("You don't have the required special energy to use this attack.");
                    }
                }
                c.specBarId = 7586;
                c.usingSpecial = !c.usingSpecial;
                c.getItems().updateSpecialBar();
                break;

            case 29113:
                c.specBarId = 7561;
                c.usingSpecial = !c.usingSpecial;
                c.getItems().updateSpecialBar();
                break;

            case 29238:
                c.specBarId = 7686;
                c.usingSpecial = !c.usingSpecial;
                c.getItems().updateSpecialBar();
                break;

            /**Dueling**/
            case 26065: // no forfeit
            case 26040:
                c.duelSlot = -1;
                c.getTradeAndDuel().selectRule(0);
                break;

            case 26066: // no movement
            case 26048:
                c.duelSlot = -1;
                c.getTradeAndDuel().selectRule(1);
                break;

            case 26069: // no range
            case 26042:
                c.duelSlot = -1;
                c.getTradeAndDuel().selectRule(2);
                break;

            case 26070: // no melee
            case 26043:
                c.duelSlot = -1;
                c.getTradeAndDuel().selectRule(3);
                break;

            case 26071: // no mage
            case 26041:
                c.duelSlot = -1;
                c.getTradeAndDuel().selectRule(4);
                break;

            case 26072: // no drinks
            case 26045:
                c.duelSlot = -1;
                c.getTradeAndDuel().selectRule(5);
                break;

            case 26073: // no food
            case 26046:
                c.duelSlot = -1;
                c.getTradeAndDuel().selectRule(6);
                break;

            case 26074: // no prayer
            case 26047:
                c.duelSlot = -1;
                c.getTradeAndDuel().selectRule(7);
                break;

            case 26076: // obsticals
            case 26075:
                c.duelSlot = -1;
                c.getTradeAndDuel().selectRule(8);
                break;

            case 2158: // fun weapons
            case 2157:
                c.duelSlot = -1;
                c.getTradeAndDuel().selectRule(9);
                break;

            case 30136: // sp attack
            case 30137:
                c.duelSlot = -1;
                c.getTradeAndDuel().selectRule(10);
                break;

            case 53245: //no helm
                c.duelSlot = 0;
                c.getTradeAndDuel().selectRule(11);
                break;

            case 53246: // no cape
                c.duelSlot = 1;
                c.getTradeAndDuel().selectRule(12);
                break;

            case 53247: // no ammy
                c.duelSlot = 2;
                c.getTradeAndDuel().selectRule(13);
                break;

            case 53249: // no weapon.
                c.duelSlot = 3;
                c.getTradeAndDuel().selectRule(14);
                break;

            case 53250: // no body
                c.duelSlot = 4;
                c.getTradeAndDuel().selectRule(15);
                break;

            case 53251: // no shield
                c.duelSlot = 5;
                c.getTradeAndDuel().selectRule(16);
                break;

            case 53252: // no legs
                c.duelSlot = 7;
                c.getTradeAndDuel().selectRule(17);
                break;

            case 53255: // no gloves
                c.duelSlot = 9;
                c.getTradeAndDuel().selectRule(18);
                break;

            case 53254: // no boots
                c.duelSlot = 10;
                c.getTradeAndDuel().selectRule(19);
                break;

            case 53253: // no rings
                c.duelSlot = 12;
                c.getTradeAndDuel().selectRule(20);
                break;

            case 53248: // no arrows
                c.duelSlot = 13;
                c.getTradeAndDuel().selectRule(21);
                break;

            /* Accepting Duel Interface Fixed by: Ardi
             * Remember to click thanks button & karma (reputation) for Ardi, if you're using this.
             */
            case 26018:
                if (c.duelStatus == 5) {
                    //c.sendMessage("This glitch has been fixed by Ardi, sorry sir.");
                    return;
                }
                if (c.inDuelArena()) {
                    Client o = (Client) Server.playerHandler.players[c.duelingWith];
                    if (o == null) {
                        c.getTradeAndDuel().declineDuel();
                        o.getTradeAndDuel().declineDuel();
                        return;
                    }


                    if (c.duelRule[2] && c.duelRule[3] && c.duelRule[4]) {
                        c.sendMessage("You won't be able to attack the player with the rules you have set.");
                        break;
                    }
                    c.duelStatus = 2;
                    if (c.duelStatus == 2) {
                        c.getPlayerAssistant().sendFrame126("Waiting for other player...", 6684);
                        o.getPlayerAssistant().sendFrame126("Other player has accepted.", 6684);
                    }
                    if (o.duelStatus == 2) {
                        o.getPlayerAssistant().sendFrame126("Waiting for other player...", 6684);
                        c.getPlayerAssistant().sendFrame126("Other player has accepted.", 6684);
                    }

                    if (c.duelStatus == 2 && o.duelStatus == 2) {
                        c.canOffer = false;
                        o.canOffer = false;
                        c.duelStatus = 3;
                        o.duelStatus = 3;
                        c.getTradeAndDuel().confirmDuel();
                        o.getTradeAndDuel().confirmDuel();
                    }
                } else {
                    Client o = (Client) Server.playerHandler.players[c.duelingWith];
                    c.getTradeAndDuel().declineDuel();
                    o.getTradeAndDuel().declineDuel();
                    c.sendMessage("You can't stake out of Duel Arena.");
                }
                break;

            /* Accepting Duel Interface Fixed by: Ardi
             * Remember to click thanks button & karma (reputation) for Ardi, if you're using this.
             */
            case 25120:
                if (c.duelStatus == 5) {
                    //c.sendMessage("This glitch has been fixed by Ardi, sorry sir.");
                    return;
                }
                if (c.inDuelArena()) {
                    if (c.duelStatus == 5) {
                        break;
                    }
                    Client o1 = (Client) Server.playerHandler.players[c.duelingWith];
                    if (o1 == null) {
                        c.getTradeAndDuel().declineDuel();
                        return;
                    }

                    c.duelStatus = 4;
                    if (o1.duelStatus == 4 && c.duelStatus == 4) {
                        c.getTradeAndDuel().startDuel();
                        o1.getTradeAndDuel().startDuel();
                        o1.duelCount = 4;
                        c.duelCount = 4;
                        c.duelDelay = System.currentTimeMillis();
                        o1.duelDelay = System.currentTimeMillis();
                    } else {
                        c.getPlayerAssistant().sendFrame126("Waiting for other player...", 6571);
                        o1.getPlayerAssistant().sendFrame126("Other player has accepted", 6571);
                    }
                } else {
                    Client o = (Client) Server.playerHandler.players[c.duelingWith];
                    c.getTradeAndDuel().declineDuel();
                    o.getTradeAndDuel().declineDuel();
                    c.sendMessage("You can't stake out of Duel Arena.");
                }
                break;


            case 4169: // god spell charge
                c.usingMagic = true;
                if (!c.getCombat().checkMagicReqs(48)) {
                    break;
                }

                if (System.currentTimeMillis() - c.godSpellDelay < Config.GOD_SPELL_CHARGE) {
                    c.sendMessage("You still feel the charge in your body!");
                    break;
                }
                c.godSpellDelay = System.currentTimeMillis();
                c.sendMessage("You feel charged with a magical power!");
                c.gfx100(c.MAGIC_SPELLS[48][3]);
                c.startAnimation(c.MAGIC_SPELLS[48][2]);
                c.usingMagic = false;
                break;


            case 28164: // item kept on death
                break;


            case 153:
                Resting.startResting(c);
                c.sendMessage("You rest.");
                break;
            case 152:
                Resting.stopResting(c);
                c.sendMessage("You stop resting.");
                break;
            case 102002:
                c.startAnimation(5713);
                c.sendMessage("You rest.");
                break;

            case 9154:
                c.logout();
                break;

            case 82016:
                c.takeAsNote = !c.takeAsNote;
                break;


            //home teleports

            case 117048:
            case 4171:
            case 50056:
                String type = c.playerMagicBook == 0 ? "modern" : "ancient";
                c.getPlayerAssistant().startTeleport(3094, 3469, 0, type);
                break;

            //case 4171:
			/*case 50056:
			String type = c.playerMagicBook == 0 ? "modern" : "ancient";
			c.getPA().startTeleport(3086, 3493, 0, type);
			break;*/

			/*case 50235:
			case 4140:
			case 117112:
			c.setSidebarInterface(6, 45300);
			break;*/


            case 4143:
            case 50245:
            case 117123:
                c.setSidebarInterface(6, 45200);
                break;

            case 50253:
            case 117131:
            case 4146:
                c.setSidebarInterface(6, 45500);
                break;


            case 51005:
            case 117154:
            case 4150:
                c.setSidebarInterface(6, 45600);
                break;

            case 50235:
            case 4140:
            case 117112:
                c.setSidebarInterface(6, 17650);

                //c.getDH().sendOption5("Rock Crabs", "Taverly Dungeon", "Slayer Tower", "Brimhaven Dungeon", "-More Options-");

                //c.teleAction = 1;
                break;
			/*
			case 4143:
			case 50245:
			case 117123:
			c.getDH().sendOption5("Barrows", "Pest Control", "TzHaar Cave", "Duel Arena", "Warrior Guild");
			c.teleAction = 2;
			break;

			case 50253:
			case 117131:
			case 4146:
			c.getDH().sendOption5("Godwars", "King Black Dragon (Wild)", "Dagannoth Kings", "Tormented Demons", "Corporeal Beast");
			c.teleAction = 3;
			break;


			case 51005:
			case 117154:
			case 4150:
			c.getDH().sendOption5("Mage Bank", "Varrock PK", "Lava Crossing (Multi)", "Edgeville", "Green Dragons");
			c.teleAction = 4;
			break;

			*/
            case 51013:
            case 6004:
            case 117162:
                c.getPlayerAssistant().startTeleport(2852, 3432, 0, "modern");
                //c.getDH().sendOption5("Mining", "Smithing", "Fishing/Cooking", "Woodcutting", "Farming");
                //c.teleAction = 5;
                break;


            case 117186:
                c.getDH().sendOption5("Sea Troll Queen", "Lakhrahnaz", "Nomad", "Giant sea Snake", "Avatar of Destruction");
                c.teleAction = 8;
                break;


            case 51023:
            case 6005:
                c.getDH().sendOption5("Lumbridge", "Varrock", "Camelot", "Taverley", "Canafis");
                c.teleAction = 20;
                break;


            case 51031:
            case 29031:
                c.getDH().sendOption5("Agility", "Hunter", "Mining", "Fishing Guild", "~Inactive~");
                c.teleAction = 21;
                break;

            case 72038:
            case 51039:
                c.getDH().sendOption5("Sea Troll Queen", "Lakhrahnaz", "Nomad", "Giant sea Snake", "Avatar of Destruction");
                c.teleAction = 8;
                break;

            case 9125: //Accurate
            case 6221: // range accurate
            case 22230: //kick (unarmed)
            case 48010: //flick (whip)
            case 21200: //spike (pickaxe)
            case 1080: //bash (staff)
            case 6168: //chop (axe)
            case 6236: //accurate (long bow)
            case 17102: //accurate (darts)
            case 8234: //stab (dagger)

            case 30088: //claws
            case 1177: //hammer
                c.fightMode = 0;
                if (c.autocasting)
                    c.getPlayerAssistant().resetAutocast();
                break;

            case 9126: //Defensive
            case 48008: //deflect (whip)
            case 22228: //punch (unarmed)
            case 21201: //block (pickaxe)
            case 1078: //focus - block (staff)
            case 6169: //block (axe)
            case 33019: //fend (hally)
            case 18078: //block (spear)
            case 8235: //block (dagger)
            case 1175: //accurate (darts)
            case 30089: //stab (dagger)
                c.fightMode = 1;
                if (c.autocasting)
                    c.getPlayerAssistant().resetAutocast();
                break;

            case 9127: // Controlled
            case 48009: //lash (whip)
            case 33018: //jab (hally)
            case 6234: //longrange (long bow)
            case 6219: //longrange
            case 18077: //lunge (spear)
            case 18080: //swipe (spear)
            case 18079: //pound (spear)
            case 17100: //longrange (darts)
                c.fightMode = 3;
                if (c.autocasting)
                    c.getPlayerAssistant().resetAutocast();
                break;

            case 9128: //Aggressive
            case 6220: // range rapid
            case 22229: //block (unarmed)
            case 21203: //impale (pickaxe)
            case 21202: //smash (pickaxe)
            case 1079: //pound (staff)
            case 6171: //hack (axe)
            case 6170: //smash (axe)
            case 33020: //swipe (hally)
            case 6235: //rapid (long bow)
            case 17101: //repid (darts)
            case 8237: //lunge (dagger)
            case 30091: //claws
            case 1176: //stat hammer
            case 8236: //slash (dagger)

            case 30090: //claws
                c.fightMode = 2;
                if (c.autocasting)
                    c.getPlayerAssistant().resetAutocast();
                break;

            /**Prayers**/
            case 87231: // thick skin
                if (c.trade11 > 1) {
                    for (int p = 0; p < c.PRAYER.length; p++) { // reset prayer glows
                        c.prayerActive[p] = false;
                        c.getPlayerAssistant().sendFrame36(c.PRAYER_GLOW[p], 0);
                    }
                    c.sendMessage("You must wait 15 minutes before using this!");
                    return;
                }
                c.getCurse().activateCurse(0);
                break;
            case 87233: // burst of str
                c.getCurse().activateCurse(1);
                break;
            case 87235: // charity of thought
                c.getCurse().activateCurse(2);
                break;
            case 87237: // range
                c.getCurse().activateCurse(3);
                break;
            case 87239: // mage
                c.getCurse().activateCurse(4);
                break;
            case 87241: // berserker
                if (c.altarPrayed == 0) {
                    return;
                }
                c.getCurse().activateCurse(5);
                break;
            case 87243: // super human
                c.getCurse().activateCurse(6);
                break;
            case 87245:    // improved reflexes
                c.getCurse().activateCurse(7);
                break;
            case 87247: //hawk eye
                c.getCurse().activateCurse(8);
                break;
            case 87249:
                c.getCurse().activateCurse(9);
                break;
            case 87251: // protect Item
                c.getCurse().activateCurse(10);
                break;
            case 87253: // 26 range
                c.getCurse().activateCurse(11);
                break;
            case 87255: // 27 mage
                c.getCurse().activateCurse(12);
                break;
            case 88001: // steel skin
                c.getCurse().activateCurse(13);
                break;
            case 88003: // ultimate str
                c.getCurse().activateCurse(14);
                break;
            case 88005: // incredible reflex
                c.getCurse().activateCurse(15);
                break;
            case 88007: // protect from magic
                c.getCurse().activateCurse(16);
                break;
            case 88009: // protect from range
                c.getCurse().activateCurse(17);
                break;
            case 88011: // protect from melee
                c.getCurse().activateCurse(18);
                break;
            case 88013: // 44 range
                c.getCurse().activateCurse(19);
                break;
            /**End of curse prayers**/


            /**Prayers**/
            case 97168: // thick skin
                c.getCombat().activatePrayer(0);
                break;
            case 97170: // burst of str
                c.getCombat().activatePrayer(1);
                break;
            case 97172: // charity of thought
                c.getCombat().activatePrayer(2);
                break;
            case 97174: // range
                c.getCombat().activatePrayer(3);
                break;
            case 97176: // mage
                c.getCombat().activatePrayer(4);
                break;
            case 97178: // rockskin
                c.getCombat().activatePrayer(5);
                break;
            case 97180: // super human
                c.getCombat().activatePrayer(6);
                break;
            case 97182:    // improved reflexes
                c.getCombat().activatePrayer(7);
                break;
            case 97184: //hawk eye
                c.getCombat().activatePrayer(8);
                break;
            case 97186:
                c.getCombat().activatePrayer(9);
                break;
            case 97188: // protect Item
			/*if(c.trade11 > 1) {
			for(int p = 0; p < c.PRAYER.length; p++) { // reset prayer glows
				c.prayerActive[p] = false;
				c.getPA().sendFrame36(c.PRAYER_GLOW[p], 0);
			}
			c.sendMessage("You must wait 15 minutes before using this!");
			return;
			}*/
                c.getCombat().activatePrayer(10);
                break;
            case 97190: // 26 range
                c.getCombat().activatePrayer(11);
                break;
            case 97192: // 27 mage
                c.getCombat().activatePrayer(12);
                break;
            case 97194: // steel skin
                c.getCombat().activatePrayer(13);
                break;
            case 97196: // ultimate str
                c.getCombat().activatePrayer(14);
                break;
            case 97198: // incredible reflex
                c.getCombat().activatePrayer(15);
                break;
            case 97200: // protect from magic
                c.getCombat().activatePrayer(16);
                break;
            case 97202: // protect from range
                c.getCombat().activatePrayer(17);
                break;
            case 97204: // protect from melee
                c.getCombat().activatePrayer(18);
                break;
            case 97206: // 44 range
                c.getCombat().activatePrayer(19);
                break;
            case 97208: // 45 mystic
                c.getCombat().activatePrayer(20);
                break;
            case 97210: // retrui
                c.getCombat().activatePrayer(21);
                break;
            case 97212: // redem
                c.getCombat().activatePrayer(22);
                break;
            case 97214: // smite
                c.getCombat().activatePrayer(23);
                break;
            case 97216: // chiv
                c.getCombat().activatePrayer(24);
                break;
            case 97218: // piety
                c.getCombat().activatePrayer(25);
                break;


            case 13092:
                if (System.currentTimeMillis() - c.lastButton < 400) {

                    c.lastButton = System.currentTimeMillis();

                    break;

                } else {

                    c.lastButton = System.currentTimeMillis();

                }
                Client ot = (Client) Server.playerHandler.players[c.tradeWith];
                if (ot == null) {
                    c.getTradeAndDuel().declineTrade();
                    c.sendMessage("Trade declined as the other player has disconnected.");
                    break;
                }
                c.getPlayerAssistant().sendFrame126("Waiting for other player...", 3431);
                ot.getPlayerAssistant().sendFrame126("Other player has accepted", 3431);
                c.goodTrade = true;
                ot.goodTrade = true;

                for (GameItem item : c.getTradeAndDuel().offeredItems) {
                    if (item.id > 0) {
                        if (ot.getItems().freeSlots() < c.getTradeAndDuel().offeredItems.size()) {
                            c.sendMessage(ot.playerName + " only has " + ot.getItems().freeSlots() + " free slots, please remove " + (c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots()) + " items.");
                            ot.sendMessage(c.playerName + " has to remove " + (c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots()) + " items or you could offer them " + (c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots()) + " items.");
                            c.goodTrade = false;
                            ot.goodTrade = false;
                            c.getPlayerAssistant().sendFrame126("Not enough inventory space...", 3431);
                            ot.getPlayerAssistant().sendFrame126("Not enough inventory space...", 3431);
                            break;
                        } else {
                            c.getPlayerAssistant().sendFrame126("Waiting for other player...", 3431);
                            ot.getPlayerAssistant().sendFrame126("Other player has accepted", 3431);
                            c.goodTrade = true;
                            ot.goodTrade = true;
                        }
                    }
                }
                if (c.inTrade && !c.tradeConfirmed && ot.goodTrade && c.goodTrade) {
                    c.tradeConfirmed = true;
                    if (ot.tradeConfirmed) {
                        c.getTradeAndDuel().confirmScreen();
                        ot.getTradeAndDuel().confirmScreen();
                        break;
                    }

                }


                break;

            case 13218:
                if (System.currentTimeMillis() - c.lastButton < 400) {

                    c.lastButton = System.currentTimeMillis();

                    break;

                } else {

                    c.lastButton = System.currentTimeMillis();

                }
                c.tradeAccepted = true;
                Client ot1 = (Client) Server.playerHandler.players[c.tradeWith];
                if (ot1 == null) {
                    c.getTradeAndDuel().declineTrade();
                    c.sendMessage("Trade declined as the other player has disconnected.");
                    break;
                }

                if (c.inTrade && c.tradeConfirmed && ot1.tradeConfirmed && !c.tradeConfirmed2) {
                    c.tradeConfirmed2 = true;
                    if (ot1.tradeConfirmed2) {
                        c.acceptedTrade = true;
                        ot1.acceptedTrade = true;
                        c.getTradeAndDuel().giveItems();
                        ot1.getTradeAndDuel().giveItems();
                        c.sendMessage("Trade accepted.");
                        c.SaveGame();
                        ot1.SaveGame();
                        ot1.sendMessage("Trade accepted.");
                        break;
                    }
                    ot1.getPlayerAssistant().sendFrame126("Other player has accepted.", 3535);
                    c.getPlayerAssistant().sendFrame126("Waiting for other player...", 3535);
                }

                break;
            /* Rules Interface Buttons */
            case 125011: //Click agree
                if (!c.ruleAgreeButton) {
                    c.ruleAgreeButton = true;
                    c.getPlayerAssistant().sendFrame36(701, 1);
                } else {
                    c.ruleAgreeButton = false;
                    c.getPlayerAssistant().sendFrame36(701, 0);
                }
                break;
            case 67100://Accept
                c.getPlayerAssistant().showInterface(3559);
                c.newPlayer = false;
                c.sendMessage("You need to click on you agree before you can continue on.");
                break;
            case 67103://Decline
                c.sendMessage("You have chosen to decline, Client will be disconnected from the server.");
                break;
            /* End Rules Interface Buttons */
            /* Player Options */
            case 74176:
                if (!c.mouseButton) {
                    c.mouseButton = true;
                    c.getPlayerAssistant().sendFrame36(500, 1);
                    c.getPlayerAssistant().sendFrame36(170, 1);
                } else if (c.mouseButton) {
                    c.mouseButton = false;
                    c.getPlayerAssistant().sendFrame36(500, 0);
                    c.getPlayerAssistant().sendFrame36(170, 0);
                }
                break;
            case 74184:
                if (!c.splitChat) {
                    c.splitChat = true;
                    c.getPlayerAssistant().sendFrame36(502, 1);
                    c.getPlayerAssistant().sendFrame36(287, 1);
                } else {
                    c.splitChat = false;
                    c.getPlayerAssistant().sendFrame36(502, 0);
                    c.getPlayerAssistant().sendFrame36(287, 0);
                }
                break;
            case 100231:
                if (!c.chatEffects) {
                    c.chatEffects = true;
                    c.getPlayerAssistant().sendFrame36(501, 1);
                    c.getPlayerAssistant().sendFrame36(171, 0);
                } else {
                    c.chatEffects = false;
                    c.getPlayerAssistant().sendFrame36(501, 0);
                    c.getPlayerAssistant().sendFrame36(171, 1);
                }
                break;
            case 100237:
                if (!c.acceptAid) {
                    c.acceptAid = true;
                    c.getPlayerAssistant().sendFrame36(503, 1);
                    c.getPlayerAssistant().sendFrame36(427, 1);
                } else {
                    c.acceptAid = false;
                    c.getPlayerAssistant().sendFrame36(503, 0);
                    c.getPlayerAssistant().sendFrame36(427, 0);
                }
                break;
            case 74201://brightness1
                c.getPlayerAssistant().sendFrame36(505, 1);
                c.getPlayerAssistant().sendFrame36(506, 0);
                c.getPlayerAssistant().sendFrame36(507, 0);
                c.getPlayerAssistant().sendFrame36(508, 0);
                c.getPlayerAssistant().sendFrame36(166, 1);
                break;
            case 74203://brightness2
                c.getPlayerAssistant().sendFrame36(505, 0);
                c.getPlayerAssistant().sendFrame36(506, 1);
                c.getPlayerAssistant().sendFrame36(507, 0);
                c.getPlayerAssistant().sendFrame36(508, 0);
                c.getPlayerAssistant().sendFrame36(166, 2);
                break;

            case 74204://brightness3
                c.getPlayerAssistant().sendFrame36(505, 0);
                c.getPlayerAssistant().sendFrame36(506, 0);
                c.getPlayerAssistant().sendFrame36(507, 1);
                c.getPlayerAssistant().sendFrame36(508, 0);
                c.getPlayerAssistant().sendFrame36(166, 3);
                break;

            case 74205://brightness4
                c.getPlayerAssistant().sendFrame36(505, 0);
                c.getPlayerAssistant().sendFrame36(506, 0);
                c.getPlayerAssistant().sendFrame36(507, 0);
                c.getPlayerAssistant().sendFrame36(508, 1);
                c.getPlayerAssistant().sendFrame36(166, 4);
                break;
            case 74206://area1
                c.getPlayerAssistant().sendFrame36(509, 1);
                c.getPlayerAssistant().sendFrame36(510, 0);
                c.getPlayerAssistant().sendFrame36(511, 0);
                c.getPlayerAssistant().sendFrame36(512, 0);
                break;
            case 74207://area2
                c.getPlayerAssistant().sendFrame36(509, 0);
                c.getPlayerAssistant().sendFrame36(510, 1);
                c.getPlayerAssistant().sendFrame36(511, 0);
                c.getPlayerAssistant().sendFrame36(512, 0);
                break;
            case 74208://area3
                c.getPlayerAssistant().sendFrame36(509, 0);
                c.getPlayerAssistant().sendFrame36(510, 0);
                c.getPlayerAssistant().sendFrame36(511, 1);
                c.getPlayerAssistant().sendFrame36(512, 0);
                break;
            case 74209://area4
                c.getPlayerAssistant().sendFrame36(509, 0);
                c.getPlayerAssistant().sendFrame36(510, 0);
                c.getPlayerAssistant().sendFrame36(511, 0);
                c.getPlayerAssistant().sendFrame36(512, 1);
                break;
            case 168:
                c.startAnimation(855);
                c.stopMovement();
                break;
            case 169:
                c.startAnimation(856);
                c.stopMovement();
                break;
            case 162:
                c.startAnimation(857);
                c.stopMovement();
                break;
            case 164:
                c.startAnimation(858);
                c.stopMovement();
                break;
            case 165:
                c.startAnimation(859);
                c.stopMovement();
                break;
            case 161:
                c.startAnimation(860);
                c.stopMovement();
                break;
            case 170:
                c.startAnimation(861);
                c.stopMovement();
                break;
            case 171:
                c.startAnimation(862);
                c.stopMovement();
                break;
            case 163:
                c.startAnimation(863);
                c.stopMovement();
                break;
            case 167:
                c.startAnimation(864);
                c.stopMovement();
                break;
            case 172:
                c.startAnimation(865);
                c.stopMovement();
                break;
            case 166:
                c.startAnimation(866);
                c.stopMovement();
                break;
            case 52050:
                c.startAnimation(2105);
                c.stopMovement();
                break;
            case 52051:
                c.startAnimation(2106);
                c.stopMovement();
                break;
            case 52052:
                c.startAnimation(2107);
                c.stopMovement();
                break;
            case 52053:
                c.startAnimation(2108);
                c.stopMovement();
                break;
            case 52054:
                c.startAnimation(2109);
                c.stopMovement();
                break;
            case 52055:
                c.startAnimation(2110);
                c.stopMovement();
                break;
            case 52056:
                c.startAnimation(2111);
                c.stopMovement();
                break;
            case 52057:
                c.startAnimation(2112);
                c.stopMovement();
                break;
            case 52058:
                c.startAnimation(2113);
                c.stopMovement();
                break;
            case 43092:
                c.startAnimation(0x558);
                c.stopMovement();
                c.gfx0(574);
                break;
            case 2155:
                c.startAnimation(11044);
                c.stopMovement();
                c.gfx0(1973);
                break;
            case 25103:
                c.startAnimation(10530);
                c.stopMovement();
                c.gfx0(1864);
                break;
            case 25106:
                c.startAnimation(8770);
                c.gfx0(1553);
                c.stopMovement();
                break;
            case 2154:
                c.startAnimation(7531);
                c.stopMovement();
                break;
            case 52071:
                c.startAnimation(0x84F);
                c.stopMovement();
                break;
            case 52072:
                c.startAnimation(0x850);
                c.stopMovement();
                break;
            case 73003:
                c.startAnimation(6111);
                c.stopMovement();
                break;
            case 73001:
                c.startAnimation(3544);
                c.stopMovement();
                break;
            case 73000:
                if (System.currentTimeMillis() - c.logoutDelay < 8000) {
                    c.sendMessage("You cannot do skillcape emotes in combat!");
                    return;
                }
                c.startAnimation(3543);
                c.stopMovement();
                break;
            case 72032:
                c.startAnimation(9990);
                c.stopMovement();
                c.gfx0(1734);
                break;
            case 72033:
                c.startAnimation(4278);
                c.stopMovement();
                break;
            case 59062:
                c.startAnimation(4280);
                c.stopMovement();
                break;
            case 72254:
                c.startAnimation(4275);
                c.stopMovement();
                break;
            case 73004:
                c.startAnimation(7272);
                c.stopMovement();
                c.gfx0(1244);
                break;
            case 72255:
                if (System.currentTimeMillis() - c.logoutDelay < 8000) {
                    c.sendMessage("You cannot do skillcape emotes in combat!");
                    c.stopMovement();
                    return;
                }
                c.startAnimation(2414);
                c.gfx0(1537);
                break;
            /* END OF EMOTES */
            case 28166:

                break;
            case 118098:
                c.getPlayerAssistant().castVeng();
                break;

            case 27209:
                c.forcedText = "[QC] My Slayer level is  " + c.getPlayerAssistant().getLevelForXP(c.playerXP[18]) + ".";
                c.sendMessage("I must slay another " + c.taskAmount + " " + Server.npcHandler.getNpcListName(c.slayerTask));
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                break;

            case 27211:
                c.forcedText = "[QC] My Hunter level is  " + c.getPlayerAssistant().getLevelForXP(c.playerXP[21]) + ".";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                break;
            case 27190:
                c.forcedText = "[QC] My Attack level is  " + c.getPlayerAssistant().getLevelForXP(c.playerXP[0]) + ".";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                break;
            case 27193:
                c.forcedText = "[QC] My Strength level is  " + c.getPlayerAssistant().getLevelForXP(c.playerXP[2]) + ".";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                break;
            case 27196:
                c.forcedText = "[QC] My Defence level is  " + c.getPlayerAssistant().getLevelForXP(c.playerXP[1]) + ".";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                break;
            case 27191:
                c.forcedText = "[QC] My Hitpoints level is  " + c.getPlayerAssistant().getLevelForXP(c.playerXP[3]) + ".";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                break;
            case 27199:
                c.forcedText = "[QC] My Range level is  " + c.getPlayerAssistant().getLevelForXP(c.playerXP[4]) + ".";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                break;
            case 27202:
                c.forcedText = "[QC] My Prayer level is  " + c.getPlayerAssistant().getLevelForXP(c.playerXP[5]) + ".";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                break;
            case 27205:
                c.forcedText = "[QC] My Magic level is  " + c.getPlayerAssistant().getLevelForXP(c.playerXP[6]) + ".";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                break;
            case 27201:
                c.forcedText = "[QC] My Cooking level is  " + c.getPlayerAssistant().getLevelForXP(c.playerXP[7]) + ".";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                break;
            case 27207:
                c.forcedText = "[QC] My Woodcutting level is  " + c.getPlayerAssistant().getLevelForXP(c.playerXP[8]) + ".";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                break;
            case 27206:
                c.forcedText = "[QC] My Fletching level is  " + c.getPlayerAssistant().getLevelForXP(c.playerXP[9]) + ".";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                break;
            case 27198:
                c.forcedText = "[QC] My Fishing level is  " + c.getPlayerAssistant().getLevelForXP(c.playerXP[10]) + ".";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                break;
            case 27204:
                c.forcedText = "[QC] My Firemaking level is  " + c.getPlayerAssistant().getLevelForXP(c.playerXP[11]) + ".";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                break;
            case 27203:
                c.forcedText = "[QC] My Crafting level is  " + c.getPlayerAssistant().getLevelForXP(c.playerXP[12]) + ".";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                break;
            case 27195:
                c.forcedText = "[QC] My Smithing level is  " + c.getPlayerAssistant().getLevelForXP(c.playerXP[13]) + ".";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                break;
            case 27192:
                c.forcedText = "[QC] My Mining level is  " + c.getPlayerAssistant().getLevelForXP(c.playerXP[14]) + ".";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                break;
            case 27197:
                c.forcedText = "[QC] My Herblore level is  " + c.getPlayerAssistant().getLevelForXP(c.playerXP[15]) + ".";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                break;
            case 27194:
                c.forcedText = "[QC] My Agility level is  " + c.getPlayerAssistant().getLevelForXP(c.playerXP[16]) + ".";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                break;
            case 27200:
                c.forcedText = "[QC] My Thieving level is  " + c.getPlayerAssistant().getLevelForXP(c.playerXP[17]) + ".";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                break;
            case 27210:
                c.forcedText = "[QC] My Farming level is  " + c.getPlayerAssistant().getLevelForXP(c.playerXP[19]) + ".";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                break;
            case 27208:
                c.forcedText = "[QC] My Runecrafting level is  " + c.getPlayerAssistant().getLevelForXP(c.playerXP[20]) + ".";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                break;
            case 27212:
                c.forcedText = "[QC] My summoning level is  " + c.getPlayerAssistant().getLevelForXP(c.playerXP[22]) + ".";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                break;
            case 27213:
                c.forcedText = "[QC] My PK'ing level is  " + c.getPlayerAssistant().getLevelForXP(c.playerXP[23]) + ".";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                break;
            case 27214:
                c.forcedText = "[QC] My Dungeoneering level is  " + c.getPlayerAssistant().getLevelForXP(c.playerXP[24]) + ".";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                break;
            case 77036:
                if (c.lastsummon > 0) {
                    c.firstslot();
                    for (int i = 0; i < 29; i += 1) {
                        Server.itemHandler.createGroundItem(c, c.storeditems[i], Server.npcHandler.npcs[c.summoningnpcid].absX, Server.npcHandler.npcs[c.summoningnpcid].absY, 1, c.playerId);
                        c.storeditems[i] = -1;
                        c.occupied[i] = false;
                    }
                    c.lastsummon = -1;
                    c.totalstored = 0;
                    c.summoningnpcid = 0;
                    c.summoningslot = 0;
                    c.sendMessage("Your BoB items have drop on the floor");
                } else {
                    c.sendMessage("You do not have a npc currently spawned");
                }
                /*
                 * Dungeoneering Start.
                 *
                 */
            case 70132:
                if (c.dungPoints >= 1000) {
                    c.dungPoints -= 1000;
                    c.sendMessage("You buy a Ring of Vigour!");
                    c.getItems().addItem(19669, 1);
                } else {
                    c.sendMessage("You don't have enough Dungeoneering points!");
                }
                break;
            case 70133:
                if (c.dungPoints >= 2300) {
                    c.dungPoints -= 2300;
                    c.getItems().addItem(18359, 1);
                } else {
                    c.sendMessage("You do not have enough dungeoneering points");
                }
                break;
            case 70148:
                if (c.dungPoints >= 2300) {
                    c.dungPoints -= 2300;
                    c.getItems().addItem(18363, 1);
                } else {
                    c.sendMessage("You do not have enough dungeoneering points");
                }
                break;
            case 70147:
                if (c.dungPoints >= 2300) {
                    c.dungPoints -= 2300;
                    c.getItems().addItem(18361, 1);
                } else {
                    c.sendMessage("You do not have enough dungeoneering points");
                }
                break;
            case 70138:
                if (c.dungPoints >= 50) {
                    c.dungPoints -= 50;
                    c.getItems().addItem(4447, 1);
                } else {
                    c.sendMessage("You do not have enough dungeoneering points");
                }
                break;
            case 70134:
                if (c.dungPoints >= 2500) {
                    c.dungPoints -= 2500;
                    c.getItems().addItem(13354, 1);
                } else {
                    c.sendMessage("You do not have enough dungeoneering points");
                }
                break;
            case 70135:
                if (c.dungPoints >= 2500) {
                    c.dungPoints -= 2500;
                    c.getItems().addItem(13352, 1);
                } else {
                    c.sendMessage("You do not have enough dungeoneering points");
                }
                break;
            case 70136:
                if (c.dungPoints >= 2500) {
                    c.dungPoints -= 2500;
                    c.getItems().addItem(13346, 1);
                } else {
                    c.sendMessage("You do not have enough dungeoneering points");
                }
                break;
            case 70137:
                if (c.dungPoints >= 2500) {
                    c.dungPoints -= 2500;
                    c.getItems().addItem(13348, 1);
                } else {
                    c.sendMessage("You do not have enough dungeoneering points");
                }
                break;
            case 70139:
                if (c.dungPoints >= 2500) {
                    c.dungPoints -= 2500;
                    c.getItems().addItem(13350, 1);
                } else {
                    c.sendMessage("You do not have enough dungeoneering points");
                }
                break;
            case 70140:
                if (c.dungPoints >= 2500) {
                    c.dungPoints -= 2500;
                    c.getItems().addItem(13355, 1);
                } else {
                    c.sendMessage("You do not have enough dungeoneering points");
                }
                break;
            case 70144:
                if (c.dungPoints >= 1200) {
                    c.dungPoints -= 1200;
                    c.getItems().addItem(4716, 1);
                    c.getItems().addItem(4718, 1);
                    c.getItems().addItem(4720, 1);
                    c.getItems().addItem(4722, 1);
                } else {
                    c.sendMessage("You do not have enough dungeoneering points");
                }
                break;
            case 70143:
                if (c.dungPoints >= 1000) {
                    c.dungPoints -= 1000;
                    c.getItems().addItem(4708, 1);
                    c.getItems().addItem(4710, 1);
                    c.getItems().addItem(4712, 1);
                    c.getItems().addItem(4714, 1);
                } else {
                    c.sendMessage("You do not have enough dungeoneering points");
                }
                break;
            case 70142:
                if (c.dungPoints >= 900) {
                    c.dungPoints -= 900;
                    c.getItems().addItem(4753, 1);
                    c.getItems().addItem(4755, 1);
                    c.getItems().addItem(4757, 1);
                    c.getItems().addItem(4759, 1);
                } else {
                    c.sendMessage("You do not have enough dungeoneering points");
                }
                break;
            case 70145:
                if (c.dungPoints >= 800) {
                    c.dungPoints -= 800;
                    c.getItems().addItem(4732, 1);
                    c.getItems().addItem(4734, 1);
                    c.getItems().addItem(4736, 1);
                    c.getItems().addItem(4738, 1);
                } else {
                    c.sendMessage("You do not have enough dungeoneering points");
                }
                break;
            case 70141:
                if (c.dungPoints >= 900) {
                    c.dungPoints -= 900;
                    c.getItems().addItem(4724, 1);
                    c.getItems().addItem(4726, 1);
                    c.getItems().addItem(4728, 1);
                    c.getItems().addItem(4730, 1);
                } else {
                    c.sendMessage("You do not have enough dungeoneering points");
                }
                break;
            case 66156:
                if (c.playerLevel[6] <= 9) {
                    c.sendMessage("You must be 10+ Magic To Choose Magic Class");
                } else {
                    if (c.dungRest > 1) {
                        c.sendMessage("You must wait 3 Minutes before using this again!");
                        return;
                    } else {
                        c.dungRest = 180; //180 = 3 Minutes
                        c.getItems().addItem(19893, 1);
                        c.getItems().addItem(19892, 1);
                        c.getItems().addItem(15786, 1);
                        c.getItems().addItem(15797, 1);
                        c.getItems().addItem(15837, 1);
                        c.getItems().addItem(15892, 1);
                        c.getItems().addItem(16185, 1);
                        c.getItems().addItem(16153, 1);
                        c.getItems().addItem(391, 3);
                        c.getItems().addItem(995, 2000000);
                        c.getItems().addItem(554, 50000);
                        c.getItems().addItem(555, 50000);
                        c.getItems().addItem(556, 50000);
                        c.getItems().addItem(557, 50000);
                        c.getItems().addItem(558, 50000);
                        c.getItems().addItem(559, 50000);
                        c.getItems().addItem(560, 50000);
                        c.getItems().addItem(561, 50000);
                        c.getItems().addItem(562, 50000);
                        c.getItems().addItem(563, 50000);
                        c.getItems().addItem(565, 50000);
                        c.getItems().addItem(564, 50000);
                        c.getItems().addItem(566, 50000);
                        c.playerMagicBook = 1;
                        c.setSidebarInterface(6, 12855);
                        c.getPlayerAssistant().closeAllWindows();
                        c.sendMessage("You have received Mage equipment and 2M.");
                    }
                }
                break;
            case 66157:
                if (c.dungRest > 1) {
                    c.sendMessage("You must wait 3 Minutes before using this again!");
                    return;
                } else {
                    c.dungRest = 180; //180 = 3 Minutes
                    c.getItems().addItem(15808, 1);
                    c.getItems().addItem(15914, 1);
                    c.getItems().addItem(15925, 1);
                    c.getItems().addItem(15936, 1);
                    c.getItems().addItem(16013, 1);
                    c.getItems().addItem(16035, 1);
                    c.getItems().addItem(16127, 1);
                    c.getItems().addItem(16262, 1);
                    c.getItems().addItem(19893, 1);
                    c.getItems().addItem(19892, 1);
                    c.getItems().addItem(391, 3);
                    c.getItems().addItem(995, 2000000);
                    c.getPlayerAssistant().closeAllWindows();
                    c.sendMessage("You have received Melee equipment and 2M.");
                }

                break;
            case 66158:
                if (c.playerLevel[4] <= 9) {
                    c.sendMessage("You must be 10+ Ranged To Choose Ranged Class");
                } else {
                    if (c.dungRest > 1) {
                        c.sendMessage("You must wait 3 Minutes before using this again!");
                        return;
                    } else {
                        c.dungRest = 180; //180 = 3 Minutes
                        c.getItems().addItem(16002, 1);
                        c.getItems().addItem(16046, 1);
                        c.getItems().addItem(16057, 1);
                        c.getItems().addItem(16068, 1);
                        c.getItems().addItem(16105, 1);
                        c.getItems().addItem(19893, 1);
                        c.getItems().addItem(19892, 1);
                        c.getItems().addItem(861, 1);
                        c.getItems().addItem(892, 10000);
                        c.getItems().addItem(397, 3);
                        c.getItems().addItem(995, 2000000);
                        c.getPlayerAssistant().closeAllWindows();
                        c.sendMessage("You have received Ranged equipment and 2M.");
                    }
                }
                break;
            //Dungeoneering finish
            case 177190:
                c.getPlayerAssistant().showInterface(14040);
                break;
            case 177206:
                c.getPlayerAssistant().spellTeleport(3007, 3849, 0);
                break;
            case 177209:
                c.getPlayerAssistant().spellTeleport(1910, 4367, 0);
                break;
            case 177212:
                c.getPlayerAssistant().spellTeleport(2717, 9805, 0);
                break;
            case 177221:
                c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151 : c.playerMagicBook == 1 ? 12855 : c.playerMagicBook == 2 ? 16640 : 12855);
                break;
            case 176177:
                c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151 : c.playerMagicBook == 1 ? 12855 : c.playerMagicBook == 2 ? 16640 : 12855);
                break;
            case 178065:
                c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151 : c.playerMagicBook == 1 ? 12855 : c.playerMagicBook == 2 ? 16640 : 12855);
                break;
            case 178034:
                c.getPlayerAssistant().spellTeleport(2539, 4716, 0);
                break;
            case 178050:
                c.getPlayerAssistant().spellTeleport(3243, 3517, 0);
                break;
            case 178053:
                c.getPlayerAssistant().spellTeleport(3367, 3935, 0);
                break;
            case 178056:
                c.getPlayerAssistant().spellTeleport(3086, 3516, 0);
                break;
            case 178059:
                c.getPlayerAssistant().spellTeleport(3344, 3667, 0);
                break;
            case 176162:
                c.getPlayerAssistant().spellTeleport(3565, 3314, 0);
                break;
            case 176168:
                c.getPlayerAssistant().spellTeleport(2438, 5172, 0);
                break;
            case 176146:
                c.getPlayerAssistant().spellTeleport(3366, 3266, 0);
                break;
            case 176165:
                c.getPlayerAssistant().spellTeleport(2662, 2650, 0);
                break;
            case 176171:
                c.getPlayerAssistant().spellTeleport(2865, 3546, 0);
                break;
            case 176246:
                c.getPlayerAssistant().spellTeleport(2676, 3715, 0);
                break;
            case 177006:
                c.getPlayerAssistant().spellTeleport(2884, 9798, 0);
                break;
            case 177009:
                c.getPlayerAssistant().spellTeleport(2710, 9466, 0);
                break;
            case 177012:
                c.getPlayerAssistant().spellTeleport(3428, 3527, 0);
                break;
            case 177015:
                c.getPlayerAssistant().spellTeleport(3117, 9847, 0);
                break;
            case 177021:
                c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151 : c.playerMagicBook == 1 ? 12855 : c.playerMagicBook == 2 ? 16640 : 12855);
                break;
            case 177215:
                c.getPlayerAssistant().spellTeleport(3303, 9375, 0);
                break;

            case 69009:
                if (c.playerMagicBook == 0) {
                    c.setSidebarInterface(6, 1151); //modern
                } else if (c.playerMagicBook == 1) {
                    c.setSidebarInterface(6, 12855); // ancient
                } else {
                    c.setSidebarInterface(6, 16640);
                }
                break;
            case 189118:
                if (c.Wheel == 0) {
                    c.sendMessage("You Need More Points!");
                } else {
                    c.getItems().addItem(c.getPlayerAssistant().Wheel(), 1);
                    c.Wheel = (c.Wheel - 1);
                    c.sendMessage("You Have Just Won A Radom Item From The Wheel!");
                    c.getPlayerAssistant().closeAllWindows();
                }
                break;
            case 24017:
                c.getPlayerAssistant().resetAutocast();
                //c.sendFrame246(329, 200, c.playerEquipment[c.playerWeapon]);
                c.getItems().sendWeapon(c.playerEquipment[c.playerWeapon], c.getItems().getItemName(c.playerEquipment[c.playerWeapon]));
                //c.setSidebarInterface(0, 328);
                //c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151 : c.playerMagicBook == 1 ? 12855 : 1151);
                break;
        }
        if (c.isAutoButton(actionButtonId))
            c.assignAutocast(actionButtonId);
    }

}
