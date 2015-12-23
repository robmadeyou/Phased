import sign.signlink;

public final class EntityDef
{

    public static int NPCAMOUNT = 11599;
    public int anInt55;
    public static int anInt56;
    public int anInt57;
    public int anInt58;
    public int anInt59;
    public static Stream stream;
    public int combatLevel;
    public final int anInt64 = 1834;
    public String name;
    public String itemActions[];
    public int anInt67;
    public byte aByte68;
    public int anIntArray70[];
    public static int streamIndices[];
    public int anIntArray73[];
    public int anInt75;
    public int anIntArray76[];
    public int anInt77;
    public long interfaceType;
    public int anInt79;
    public static EntityDef cache[];
    public static Client clientInstance;
    public int anInt83;
    public boolean aBoolean84;
    public int anInt85;
    public int anInt86;
    public boolean aBoolean87;
    public int childrenIDs[];
    public byte description[];
    public int anInt91;
    public int anInt92;
    public boolean aBoolean93;
    public int anIntArray94[];
    public static MRUNodes mruNodes = new MRUNodes(30);

    public static EntityDef forID(int i)
    {
        for(int j = 0; j < 20; j++)
        {
            if(cache[j].interfaceType == (long)i)
            {
                return cache[j];
            }
        }

        anInt56 = (anInt56 + 1) % 20;
        EntityDef entitydef = cache[anInt56] = new EntityDef();
        stream.currentOffset = streamIndices[i];
        entitydef.interfaceType = i;
entitydef.readValues(stream);
        if(i == 1118) {
        stream.currentOffset = streamIndices[630]; 
        entitydef.aByte68 = 1;
        entitydef.readValues(stream); // Collect original NPC data
entitydef.itemActions = new String[5];
entitydef.itemActions[0] = "Talk To";
	entitydef.anIntArray94 = new int[9];
	entitydef.anIntArray94[0] = 65300;//Npc's Cape
	entitydef.anIntArray94[1] = 65291;//Npc's Head
	entitydef.anIntArray94[2] = 62746;//Npc's Plate
	entitydef.anIntArray94[3] = 62743;//Npc's Legs
	entitydef.anIntArray94[5] = 13319;//Npc's Gloves
	entitydef.anIntArray94[6] = 53327;//Npc's Boots
	entitydef.anIntArray94[7] = 53327;//Npc's Darkness(Shadow)
	entitydef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
		entitydef.anIntArray94[4] = 53327;//Weapon
entitydef.anInt67 = 7046; //Walk anim
entitydef.anInt58 = 7046; //Walkback anim
entitydef.anInt83 = 7046; //Walk left anim
entitydef.anInt55 = 7046; //walk right anim
entitydef.name = "Max";
entitydef.combatLevel = 138;//Combat Level
entitydef.description = "Aggroth's completionist master.".getBytes();
}
//NPC names
        if(i == 56) {
        stream.currentOffset = streamIndices[630]; 
        entitydef.aByte68 = 1;
        entitydef.readValues(stream); // Collect original NPC data
entitydef.itemActions = new String[5];
entitydef.itemActions[0] = "Talk To";
	entitydef.anIntArray94 = new int[9];
	entitydef.anIntArray94[0] = 65300;//Npc's Cape
	entitydef.anIntArray94[1] = 62738;//Npc's Head
	entitydef.anIntArray94[2] = 62746;//Npc's Plate
	entitydef.anIntArray94[3] = 62743;//Npc's Legs
	entitydef.anIntArray94[4] = 5324;//Weapon
	entitydef.anIntArray94[5] = 13319;//Npc's Gloves
	entitydef.anIntArray94[6] = 53327;//Npc's Boots
	entitydef.anIntArray94[7] = 53327;//Npc's Darkness(Shadow)
	entitydef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
entitydef.anInt77 = 7047; //Stand anim
entitydef.anInt67 = 7046; //Walk anim
entitydef.anInt58 = 7046; //Walkback anim
entitydef.anInt83 = 7046; //Walk left anim
entitydef.anInt55 = 7046; //walk right anim
entitydef.name = "Shaloxis";
entitydef.combatLevel = 783;//Combat Level
entitydef.description = "Aggroth's strongest warrior.".getBytes();
}
        if(i == 57) {
        stream.currentOffset = streamIndices[630]; 
        entitydef.aByte68 = 1;
        entitydef.readValues(stream); // Collect original NPC data
entitydef.itemActions = new String[5];
entitydef.itemActions[1] = "Attack";
	entitydef.anIntArray94 = new int[9];
	entitydef.anIntArray94[0] = 65300;//Npc's Cape
	entitydef.anIntArray94[1] = 62738;//Npc's Head
	entitydef.anIntArray94[2] = 62746;//Npc's Plate
	entitydef.anIntArray94[3] = 62743;//Npc's Legs
	entitydef.anIntArray94[4] = 5324;//Weapon
	entitydef.anIntArray94[5] = 13319;//Npc's Gloves
	entitydef.anIntArray94[6] = 53327;//Npc's Boots
	entitydef.anIntArray94[7] = 53327;//Npc's Darkness(Shadow)
	entitydef.anIntArray94[8] = 20147;//Npc's Arms(You Dont Have To Edit)
entitydef.anInt77 = 7047; //Stand anim
entitydef.anInt67 = 7046; //Walk anim
entitydef.anInt58 = 7046; //Walkback anim
entitydef.anInt83 = 7046; //Walk left anim
entitydef.anInt55 = 7046; //walk right anim
entitydef.name = "Shaloxis";
entitydef.combatLevel = 783;//Combat Level
entitydef.description = "Aggroth's strongest warrior.".getBytes();
}
if(i == 598) {
        entitydef.itemActions = new String[5];
        entitydef.itemActions[0] = "Talk-to";
		        entitydef.itemActions[2] = "Teleport";
        entitydef.name = "Boss teleporter";
        entitydef.combatLevel = 138;//Combat Level
        entitydef.description = "This person will teleport you to bosses, you may pick.".getBytes();
        }
if(i == 597) {
        entitydef.itemActions = new String[5];
        entitydef.itemActions[0] = "Talk-to";
		        entitydef.itemActions[2] = "Teleport";
        entitydef.name = "Boss teleporter";
        entitydef.combatLevel = 138;//Combat Level
        entitydef.description = "This person will teleport you to bosses, you may pick.".getBytes();
        }
if(i == 2244) {
        entitydef.itemActions = new String[5];
        entitydef.itemActions[0] = "Talk-to";
		        entitydef.itemActions[2] = "PlayerStats";
        entitydef.name = "Old John";
        entitydef.combatLevel = 138;//Combat Level
        entitydef.description = "Our oldest NPC.".getBytes();
        }
if(i == 6032) {
        stream.currentOffset = streamIndices[630]; 
        entitydef.aByte68 = 1;
        entitydef.readValues(stream); // Collect original NPC data
        entitydef.itemActions = new String[5];
        entitydef.itemActions[1] = "Attack";
        entitydef.anIntArray94 = new int[1];
        entitydef.anIntArray94[0] = 62717;//Npc's plate
        entitydef.name = "Nex";
        entitydef.combatLevel = 1001;//Combat Level
        entitydef.description = "Even the gods fear it.".getBytes();
        entitydef.anInt77 = 6320;//stand animation
        entitydef.anInt67 = 6319;//walk animation
        }
     if (i == 251) { //Npc id
	 entitydef.itemActions = new String[5];
entitydef.itemActions[2] = "Trade with";
         entitydef.name = "Milestone seller"; //Npc name
         entitydef.description = "This NPC will sell you the capes you earned.".getBytes(); //Npc description
      }
     if (i == 2830) { //Npc id
	 entitydef.itemActions = new String[5];
entitydef.itemActions[0] = "Talk to";
         entitydef.name = "Points shop"; //Npc name
         entitydef.description = "This NPC will sell you items for points.".getBytes(); //Npc description
      }
     if (i == 304) { //Npc id
	 entitydef.itemActions = new String[5];
entitydef.itemActions[0] = "Talk to";
         entitydef.name = "Skillcape shopkeeper"; //Npc name
         entitydef.description = "This NPC sells skillcapes.".getBytes(); //Npc description
      }
     if (i == 541) { //Npc id
	 entitydef.itemActions = new String[5];
entitydef.itemActions[0] = "Talk to";
         entitydef.name = "Fight Shopkeeper"; //Npc name
         entitydef.description = "This npc sells weapons and equipment for melee.".getBytes(); //Npc description
      }
     if (i == 2258) { //Npc id
	 entitydef.itemActions = new String[5];
entitydef.itemActions[0] = "Talk to";
entitydef.itemActions[2] = "Teleport";
         entitydef.name = "RuneCrafting Mage"; //Npc name
         entitydef.description = "This NPC will teleport you to runecrafting locations.".getBytes(); //Npc description
      }
     if (i == 252) { //Npc id
	 entitydef.itemActions = new String[5];
entitydef.itemActions[0] = "Talk to";
entitydef.itemActions[2] = "GodWars";
         entitydef.name = "Weirdo"; //Npc name
         entitydef.description = "That's a weirdo... keep walking.".getBytes(); //Npc description
      }
     if (i == 8275) { //Npc id
	 entitydef.itemActions = new String[5];
entitydef.itemActions[0] = "Talk to";
entitydef.itemActions[2] = "Rewards";
         entitydef.name = "Slayer Master"; //Npc name
         entitydef.description = "The slayer master NPC Duradel will handle ur Slayer Skill.".getBytes(); //Npc description
      }
     if (i == 683) { //Npc id
	 entitydef.itemActions = new String[5];
entitydef.itemActions[0] = "Talk to";
         entitydef.name = "Shopkeeper"; //Npc name
         entitydef.description = "This npc sells weapons and equipment for ranging and magic.".getBytes(); //Npc description
      }
     if (i == 519) { //Npc id
	 entitydef.itemActions = new String[5];
entitydef.itemActions[0] = "Talk to";
         entitydef.name = "Bob"; //Npc name
         entitydef.description = "This npc sells weapons and equipment for skilling.".getBytes(); //Npc description
      }
if(i == 1683) {
entitydef.name = "Financial Ghost";
entitydef.itemActions[0] = "Talk with";
entitydef.itemActions[2] = "Trade with";
entitydef.combatLevel = 0;
}
if(i == 409) {
entitydef.name = "Reset Genie";
entitydef.itemActions[0] = "Reset Stat";
entitydef.description = "This person will reset your stats for a fee of 30M.".getBytes(); //Npc description
entitydef.combatLevel = 1;
}
return entitydef;
}

    public Model method160()
    {
        if(childrenIDs != null)
        {
            EntityDef entitydef = method161();
            if(entitydef == null)
            {
                return null;
            } else
            {
                return entitydef.method160();
            }
        }
        if(anIntArray73 == null)
        {
            return null;
        }
        boolean flag = false;
        for(int i = 0; i < anIntArray73.length; i++)
        {
            if(!Model.method463(anIntArray73[i]))
            {
                flag = true;
            }
        }

        if(flag)
        {
            return null;
        }
        Model amodel[] = new Model[anIntArray73.length];
        for(int j = 0; j < anIntArray73.length; j++)
        {
            amodel[j] = Model.method462(anIntArray73[j]);
        }

        Model model;
        if(amodel.length == 1)
        {
            model = amodel[0];
        } else
        {
            model = new Model(amodel.length, amodel);
        }
        if(anIntArray76 != null)
        {
            for(int k = 0; k < anIntArray76.length; k++)
            {
                model.method476(anIntArray76[k], anIntArray70[k]);
            }

        }
        return model;
    }

    public EntityDef method161() {
		try {
			int j = -1;
			if(anInt57 != -1)
			{
				VarBit varBit = VarBit.cache[anInt57];
				int k = varBit.anInt648;
				int l = varBit.anInt649;
				int i1 = varBit.anInt650;
				int j1 = Client.anIntArray1232[i1 - l];
				j = clientInstance.variousSettings[k] >> l & j1;
			} else
			if(anInt59 != -1) {
				j = clientInstance.variousSettings[anInt59];
			}
			if(j < 0 || j >= childrenIDs.length || childrenIDs[j] == -1) {
				return null;
			} else {
				return forID(childrenIDs[j]);
			}
		} catch (Exception e) {
			return null;
		}
	}

    public static byte[] getData(String s)
    {
        return FileOperations.ReadFile(s);
    }

    public static void unpackConfig(NamedArchive namedarchive)
    {
        stream = new Stream(getData((new StringBuilder()).append(signlink.findcachedir()).append("npc.dat").toString()));
        Stream bytebuffer = new Stream(getData((new StringBuilder()).append(signlink.findcachedir()).append("npc.idx").toString()));
        int i = bytebuffer.readUnsignedWord();
        System.out.println((new StringBuilder()).append("602 NPC Amount: ").append(i).toString());
        streamIndices = new int[i];
        int j = 2;
        for(int k = 0; k < i; k++)
        {
            streamIndices[k] = j;
            j += bytebuffer.readUnsignedWord();
        }

        cache = new EntityDef[20];
        for(int l = 0; l < 20; l++)
        {
            cache[l] = new EntityDef();
        }

    }

    public static void nullLoader()
    {
        mruNodes = null;
        streamIndices = null;
        cache = null;
        stream = null;
    }

    public Model method164(int i, int j, int ai[])
    {
        if(childrenIDs != null)
        {
            EntityDef entitydef = method161();
            if(entitydef == null)
            {
                return null;
            } else
            {
                return entitydef.method164(i, j, ai);
            }
        }
        Model model = (Model)mruNodes.insertFromCache(interfaceType);
        if(model == null)
        {
            boolean flag = false;
            for(int k = 0; k < anIntArray94.length; k++)
            {
                if(!Model.method463(anIntArray94[k]))
                {
                    flag = true;
                }
            }

            if(flag)
            {
                return null;
            }
            Model amodel[] = new Model[anIntArray94.length];
            for(int l = 0; l < anIntArray94.length; l++)
            {
                amodel[l] = Model.method462(anIntArray94[l]);
            }

            if(amodel.length == 1)
            {
                model = amodel[0];
            } else
            {
                model = new Model(amodel.length, amodel);
            }
            if(anIntArray76 != null)
            {
                for(int i1 = 0; i1 < anIntArray76.length; i1++)
                {
                    model.method476(anIntArray76[i1], anIntArray70[i1]);
                }

            }
            model.method469();
            model.method479(84 + anInt85, 1000 + anInt92, -90, -580, -90, true);
            mruNodes.removeFromCache(model, interfaceType);
        }
        Model model1 = Model.aModel_1621;
        model1.method464(model, Class36.method532(j) & Class36.method532(i));
        if(j != -1 && i != -1)
        {
            model1.method471(ai, i, j);
        } else
        if(j != -1)
        {
            model1.method470(j);
        }
        if(anInt91 != 128 || anInt86 != 128)
        {
            model1.method478(anInt91, anInt91, anInt86);
        }
        model1.method466();
        model1.anIntArrayArray1658 = (int[][])null;
        model1.anIntArrayArray1657 = (int[][])null;
        if(aByte68 == 1)
        {
            model1.aBoolean1659 = true;
        }
        return model1;
    }

    public void readValues(Stream bytebuffer)
    {
        do
        {
            int i = bytebuffer.readUnsignedByte();
            if(i == 0)
            {
                return;
            }
            if(i == 1)
            {
                int j = bytebuffer.readUnsignedByte();
                anIntArray94 = new int[j];
                int j1 = 0;
                while(j1 < j) 
                {
                    anIntArray94[j1] = bytebuffer.readUnsignedWord();
                    j1++;
                }
            } else
            if(i == 2)
            {
                name = bytebuffer.readString();
            } else
            if(i == 3)
            {
                description = bytebuffer.readBytes();
            } else
            if(i == 12)
            {
                aByte68 = bytebuffer.readSignedByte();
            } else
            if(i == 13)
            {
                anInt77 = bytebuffer.readUnsignedWord();
            } else
            if(i == 14)
            {
                anInt67 = bytebuffer.readUnsignedWord();
            } else
            if(i == 17)
            {
                anInt67 = bytebuffer.readUnsignedWord();
                anInt58 = bytebuffer.readUnsignedWord();
                anInt83 = bytebuffer.readUnsignedWord();
                anInt55 = bytebuffer.readUnsignedWord();
                if(anInt67 == 65535)
                {
                    anInt67 = -1;
                }
                if(anInt58 == 65535)
                {
                    anInt58 = -1;
                }
                if(anInt83 == 65535)
                {
                    anInt83 = -1;
                }
                if(anInt55 == 65535)
                {
                    anInt55 = -1;
                }
            } else
            if(i >= 30 && i < 40)
            {
                if(itemActions == null)
                {
                    itemActions = new String[5];
                }
                itemActions[i - 30] = bytebuffer.readString();
                if(itemActions[i - 30].equalsIgnoreCase("hidden"))
                {
                    itemActions[i - 30] = null;
                }
            } else
            if(i == 40)
            {
                int k = bytebuffer.readUnsignedByte();
                anIntArray70 = new int[k];
                anIntArray76 = new int[k];
                int k1 = 0;
                while(k1 < k) 
                {
                    anIntArray76[k1] = bytebuffer.readUnsignedWord();
                    anIntArray70[k1] = bytebuffer.readUnsignedWord();
                    k1++;
                }
            } else
            if(i == 60)
            {
                int l = bytebuffer.readUnsignedByte();
                anIntArray73 = new int[l];
                int l1 = 0;
                while(l1 < l) 
                {
                    anIntArray73[l1] = bytebuffer.readUnsignedWord();
                    l1++;
                }
            } else
            if(i == 90)
            {
                bytebuffer.readUnsignedWord();
            } else
            if(i == 91)
            {
                bytebuffer.readUnsignedWord();
            } else
            if(i == 92)
            {
                bytebuffer.readUnsignedWord();
            } else
            if(i == 93)
            {
                aBoolean87 = false;
            } else
            if(i == 95)
            {
                combatLevel = bytebuffer.readUnsignedWord();
            } else
            if(i == 97)
            {
                anInt91 = bytebuffer.readUnsignedWord();
            } else
            if(i == 98)
            {
                anInt86 = bytebuffer.readUnsignedWord();
            } else
            if(i == 99)
            {
                aBoolean93 = true;
            } else
            if(i == 100)
            {
                anInt85 = bytebuffer.readSignedByte();
            } else
            if(i == 101)
            {
                anInt92 = bytebuffer.readSignedByte() * 5;
            } else
            if(i == 102)
            {
                anInt75 = bytebuffer.readUnsignedWord();
            } else
            if(i == 103)
            {
                anInt79 = bytebuffer.readUnsignedWord();
            } else
            if(i == 106)
            {
                anInt57 = bytebuffer.readUnsignedWord();
                if(anInt57 == 65535)
                {
                    anInt57 = -1;
                }
                anInt59 = bytebuffer.readUnsignedWord();
                if(anInt59 == 65535)
                {
                    anInt59 = -1;
                }
                int i1 = bytebuffer.readUnsignedByte();
                childrenIDs = new int[i1 + 1];
                int i2 = 0;
                while(i2 <= i1) 
                {
                    childrenIDs[i2] = bytebuffer.readUnsignedWord();
                    if(childrenIDs[i2] == 65535)
                    {
                        childrenIDs[i2] = -1;
                    }
                    i2++;
                }
            } else
            if(i == 107)
            {
                aBoolean84 = false;
            }
        } while(true);
    }

    public EntityDef()
    {
        anInt55 = -1;
        anInt57 = -1;
        anInt58 = -1;
        anInt59 = -1;
        combatLevel = -1;
        anInt67 = -1;
        aByte68 = 1;
        anInt75 = -1;
        anInt77 = -1;
        interfaceType = -1L;
        anInt79 = 32;
        anInt83 = -1;
        aBoolean84 = true;
        anInt86 = 128;
        aBoolean87 = true;
        anInt91 = 128;
        aBoolean93 = false;
    }

}