package server.model.players;

import server.model.players.packets.*;


public class PacketHandler {

    private static PacketType packetId[] = new PacketType[256];

    static {
        SilentPacket silentPacket = new SilentPacket();
        ClickObject clickObject = new ClickObject();
        ClickNPC cn = new ClickNPC();
        AttackPlayer ap = new AttackPlayer();
        PrivateMessaging pm = new PrivateMessaging();
        Walking w = new Walking();
        ChangeRegions cr = new ChangeRegions();

        packetId[3] = silentPacket;
        packetId[202] = silentPacket;
        packetId[77] = silentPacket;
        packetId[86] = silentPacket;
        packetId[78] = silentPacket;
        packetId[36] = silentPacket;
        packetId[226] = silentPacket;
        packetId[246] = silentPacket;
        packetId[148] = silentPacket;
        packetId[183] = silentPacket;
        packetId[230] = silentPacket;
        packetId[136] = silentPacket;
        packetId[189] = silentPacket;
        packetId[152] = silentPacket;
        packetId[200] = silentPacket;
        packetId[85] = silentPacket;
        packetId[165] = silentPacket;
        packetId[238] = silentPacket;
        packetId[150] = silentPacket;

        packetId[132] = clickObject;
        packetId[252] = clickObject;
        packetId[70] = clickObject;

        packetId[72] = cn;
        packetId[131] = cn;
        packetId[155] = cn;
        packetId[17] = cn;
        packetId[21] = cn;

        packetId[73] = ap;
        packetId[249] = ap;

        packetId[188] = pm;
        packetId[126] = pm;
        packetId[215] = pm;
        packetId[95] = pm;
        packetId[133] = pm;

        packetId[98] = w;
        packetId[164] = w;
        packetId[248] = w;

        packetId[121] = cr;
        packetId[210] = cr;

        packetId[218] = new Report();
        packetId[40] = new Dialogue();
        packetId[57] = new ItemOnNpc();
        packetId[16] = new ItemClick2();
        packetId[75] = new ItemClick3();
        packetId[122] = new ClickItem();
        packetId[241] = new ClickingInGame();
        packetId[4] = new Chat();
        packetId[236] = new PickupItem();
        packetId[87] = new DropItem();
        packetId[185] = new ClickingButtons();
        packetId[130] = new ClickingStuff();
        packetId[103] = new Commands();
        packetId[214] = new MoveItems();
        packetId[237] = new MagicOnItems();
        packetId[181] = new MagicOnFloorItems();
        packetId[202] = new IdleLogout();
        packetId[128] = new ChallengePlayer();
        packetId[39] = new Trade();
        packetId[139] = new FollowPlayer();
        packetId[41] = new WearItem();
        packetId[145] = new RemoveItem();
        packetId[117] = new Bank5();
        packetId[43] = new Bank10();
        packetId[129] = new BankAll();
        packetId[101] = new ChangeAppearance();
        packetId[135] = new BankX1();
        packetId[208] = new BankX2();
        packetId[53] = new ItemOnItem();
        packetId[192] = new ItemOnObject();
        packetId[25] = new ItemOnGroundItem();
        packetId[60] = new ClanChat();
    }


    public static void processPacket(Client c, int packetType, int packetSize) {
        if (packetType == -1) {
            return;
        }
        PacketType p = packetId[packetType];
        if (p != null) {
            try {
                p.processPacket(c, packetType, packetSize);
            } catch (Exception e) {
                e.printStackTrace();
                c.disconnected = true;
            }
        } else {
            System.out.println("Unhandled packet type: " + packetType + " - size: " + packetSize);
        }
    }
}
