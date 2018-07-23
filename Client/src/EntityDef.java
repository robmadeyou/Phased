import sign.signlink;

public final class EntityDef {

    public static int NPCAMOUNT = 11599;
    public static int anInt56;
    public static Stream stream;
    public static int streamIndices[];
    public static EntityDef cache[];
    public static Client clientInstance;
    public static MRUNodes mruNodes = new MRUNodes(30);
    public final int anInt64 = 1834;
    public int walkRightAnim;
    public int anInt57;
    public int walkBackAnim;
    public int anInt59;
    public int combatLevel;
    public String name;
    public String itemActions[];
    public int walkAnim;
    public byte aByte68;
    public int anIntArray70[];
    public int anIntArray73[];
    public int anInt75;
    public int anIntArray76[];
    public int standAnim;
    public long interfaceType;
    public int anInt79;
    public int walkLeftAnim;
    public boolean aBoolean84;
    public int anInt85;
    public int anInt86;
    public boolean aBoolean87;
    public int childrenIDs[];
    public byte description[];
    public int anInt91;
    public int anInt92;
    public boolean aBoolean93;
    public int armour[];

    public EntityDef() {
        walkRightAnim = -1;
        anInt57 = -1;
        walkBackAnim = -1;
        anInt59 = -1;
        combatLevel = -1;
        walkAnim = -1;
        aByte68 = 1;
        anInt75 = -1;
        standAnim = -1;
        interfaceType = -1L;
        anInt79 = 32;
        walkLeftAnim = -1;
        aBoolean84 = true;
        anInt86 = 128;
        aBoolean87 = true;
        anInt91 = 128;
        aBoolean93 = false;
    }

    public static EntityDef forID(int i) {
        for (int j = 0; j < 20; j++) {
            if (cache[j].interfaceType == (long) i) {
                return cache[j];
            }
        }

        anInt56 = (anInt56 + 1) % 20;
        EntityDef entitydef = cache[anInt56] = new EntityDef();
        stream.currentOffset = streamIndices[i];
        entitydef.interfaceType = i;
        entitydef.readValues(stream);
        if (i == 1118) {
            stream.currentOffset = streamIndices[630];
            entitydef.aByte68 = 1;
            entitydef.readValues(stream); // Collect original NPC data
            entitydef.itemActions = new String[5];
            entitydef.itemActions[0] = "Talk To";
            entitydef.armour = new int[9];
            entitydef.armour[0] = 65300;//Npc's Cape
            entitydef.armour[1] = 65291;//Npc's Head
            entitydef.armour[2] = 62746;//Npc's Plate
            entitydef.armour[3] = 62743;//Npc's Legs
            entitydef.armour[5] = 13319;//Npc's Gloves
            entitydef.armour[6] = 53327;//Npc's Boots
            entitydef.armour[7] = 53327;//Npc's Darkness(Shadow)
            entitydef.armour[8] = 20147;//Npc's Arms(You Dont Have To Edit)
            entitydef.armour[4] = 53327;//Weapon
            entitydef.walkAnim = 7046; //Walk anim
            entitydef.walkBackAnim = 7046; //Walkback anim
            entitydef.walkLeftAnim = 7046; //Walk left anim
            entitydef.walkRightAnim = 7046; //walk right anim
            entitydef.name = "Max";
            entitydef.combatLevel = 138;//Combat Level
            entitydef.description = "Aggroth's completionist master.".getBytes();
        }

        return entitydef;
    }

    public static byte[] getData(String s) {
        return FileOperations.ReadFile(s);
    }

    public static void unpackConfig(NamedArchive namedarchive) {
        stream = new Stream(getData((new StringBuilder()).append(signlink.findcachedir()).append("npc.dat").toString()));
        Stream bytebuffer = new Stream(getData((new StringBuilder()).append(signlink.findcachedir()).append("npc.idx").toString()));
        int i = bytebuffer.readUnsignedWord();
        System.out.println((new StringBuilder()).append("602 NPC Amount: ").append(i).toString());
        streamIndices = new int[i];
        int j = 2;
        for (int k = 0; k < i; k++) {
            streamIndices[k] = j;
            j += bytebuffer.readUnsignedWord();
        }

        cache = new EntityDef[20];
        for (int l = 0; l < 20; l++) {
            cache[l] = new EntityDef();
        }

    }

    public static void nullLoader() {
        mruNodes = null;
        streamIndices = null;
        cache = null;
        stream = null;
    }

    public Model method160() {
        if (childrenIDs != null) {
            EntityDef entitydef = method161();
            if (entitydef == null) {
                return null;
            } else {
                return entitydef.method160();
            }
        }
        if (anIntArray73 == null) {
            return null;
        }
        boolean flag = false;
        for (int i = 0; i < anIntArray73.length; i++) {
            if (!Model.method463(anIntArray73[i])) {
                flag = true;
            }
        }

        if (flag) {
            return null;
        }
        Model amodel[] = new Model[anIntArray73.length];
        for (int j = 0; j < anIntArray73.length; j++) {
            amodel[j] = Model.method462(anIntArray73[j]);
        }

        Model model;
        if (amodel.length == 1) {
            model = amodel[0];
        } else {
            model = new Model(amodel.length, amodel);
        }
        if (anIntArray76 != null) {
            for (int k = 0; k < anIntArray76.length; k++) {
                model.method476(anIntArray76[k], anIntArray70[k]);
            }

        }
        return model;
    }

    public EntityDef method161() {
        try {
            int j = -1;
            if (anInt57 != -1) {
                VarBit varBit = VarBit.cache[anInt57];
                int k = varBit.anInt648;
                int l = varBit.anInt649;
                int i1 = varBit.anInt650;
                int j1 = Client.anIntArray1232[i1 - l];
                j = clientInstance.variousSettings[k] >> l & j1;
            } else if (anInt59 != -1) {
                j = clientInstance.variousSettings[anInt59];
            }
            if (j < 0 || j >= childrenIDs.length || childrenIDs[j] == -1) {
                return null;
            } else {
                return forID(childrenIDs[j]);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public Model method164(int i, int j, int ai[]) {
        if (childrenIDs != null) {
            EntityDef entitydef = method161();
            if (entitydef == null) {
                return null;
            } else {
                return entitydef.method164(i, j, ai);
            }
        }
        Model model = (Model) mruNodes.insertFromCache(interfaceType);
        if (model == null) {
            boolean flag = false;
            for (int k = 0; k < armour.length; k++) {
                if (!Model.method463(armour[k])) {
                    flag = true;
                }
            }

            if (flag) {
                return null;
            }
            Model amodel[] = new Model[armour.length];
            for (int l = 0; l < armour.length; l++) {
                amodel[l] = Model.method462(armour[l]);
            }

            if (amodel.length == 1) {
                model = amodel[0];
            } else {
                model = new Model(amodel.length, amodel);
            }
            if (anIntArray76 != null) {
                for (int i1 = 0; i1 < anIntArray76.length; i1++) {
                    model.method476(anIntArray76[i1], anIntArray70[i1]);
                }

            }
            model.method469();
            model.method479(84 + anInt85, 1000 + anInt92, -90, -580, -90, true);
            mruNodes.removeFromCache(model, interfaceType);
        }
        Model model1 = Model.aModel_1621;
        model1.method464(model, Class36.method532(j) & Class36.method532(i));
        if (j != -1 && i != -1) {
            model1.method471(ai, i, j);
        } else if (j != -1) {
            model1.method470(j);
        }
        if (anInt91 != 128 || anInt86 != 128) {
            model1.method478(anInt91, anInt91, anInt86);
        }
        model1.method466();
        model1.anIntArrayArray1658 = (int[][]) null;
        model1.anIntArrayArray1657 = (int[][]) null;
        if (aByte68 == 1) {
            model1.aBoolean1659 = true;
        }
        return model1;
    }

    public void readValues(Stream bytebuffer) {
        do {
            int i = bytebuffer.readUnsignedByte();
            if (i == 0) {
                return;
            }
            if (i == 1) {
                int j = bytebuffer.readUnsignedByte();
                armour = new int[j];
                int j1 = 0;
                while (j1 < j) {
                    armour[j1] = bytebuffer.readUnsignedWord();
                    j1++;
                }
            } else if (i == 2) {
                name = bytebuffer.readString();
            } else if (i == 3) {
                description = bytebuffer.readBytes();
            } else if (i == 12) {
                aByte68 = bytebuffer.readSignedByte();
            } else if (i == 13) {
                standAnim = bytebuffer.readUnsignedWord();
            } else if (i == 14) {
                walkAnim = bytebuffer.readUnsignedWord();
            } else if (i == 17) {
                walkAnim = bytebuffer.readUnsignedWord();
                walkBackAnim = bytebuffer.readUnsignedWord();
                walkLeftAnim = bytebuffer.readUnsignedWord();
                walkRightAnim = bytebuffer.readUnsignedWord();
                if (walkAnim == 65535) {
                    walkAnim = -1;
                }
                if (walkBackAnim == 65535) {
                    walkBackAnim = -1;
                }
                if (walkLeftAnim == 65535) {
                    walkLeftAnim = -1;
                }
                if (walkRightAnim == 65535) {
                    walkRightAnim = -1;
                }
            } else if (i >= 30 && i < 40) {
                if (itemActions == null) {
                    itemActions = new String[5];
                }
                itemActions[i - 30] = bytebuffer.readString();
                if (itemActions[i - 30].equalsIgnoreCase("hidden")) {
                    itemActions[i - 30] = null;
                }
            } else if (i == 40) {
                int k = bytebuffer.readUnsignedByte();
                anIntArray70 = new int[k];
                anIntArray76 = new int[k];
                int k1 = 0;
                while (k1 < k) {
                    anIntArray76[k1] = bytebuffer.readUnsignedWord();
                    anIntArray70[k1] = bytebuffer.readUnsignedWord();
                    k1++;
                }
            } else if (i == 60) {
                int l = bytebuffer.readUnsignedByte();
                anIntArray73 = new int[l];
                int l1 = 0;
                while (l1 < l) {
                    anIntArray73[l1] = bytebuffer.readUnsignedWord();
                    l1++;
                }
            } else if (i == 90) {
                bytebuffer.readUnsignedWord();
            } else if (i == 91) {
                bytebuffer.readUnsignedWord();
            } else if (i == 92) {
                bytebuffer.readUnsignedWord();
            } else if (i == 93) {
                aBoolean87 = false;
            } else if (i == 95) {
                combatLevel = bytebuffer.readUnsignedWord();
            } else if (i == 97) {
                anInt91 = bytebuffer.readUnsignedWord();
            } else if (i == 98) {
                anInt86 = bytebuffer.readUnsignedWord();
            } else if (i == 99) {
                aBoolean93 = true;
            } else if (i == 100) {
                anInt85 = bytebuffer.readSignedByte();
            } else if (i == 101) {
                anInt92 = bytebuffer.readSignedByte() * 5;
            } else if (i == 102) {
                anInt75 = bytebuffer.readUnsignedWord();
            } else if (i == 103) {
                anInt79 = bytebuffer.readUnsignedWord();
            } else if (i == 106) {
                anInt57 = bytebuffer.readUnsignedWord();
                if (anInt57 == 65535) {
                    anInt57 = -1;
                }
                anInt59 = bytebuffer.readUnsignedWord();
                if (anInt59 == 65535) {
                    anInt59 = -1;
                }
                int i1 = bytebuffer.readUnsignedByte();
                childrenIDs = new int[i1 + 1];
                int i2 = 0;
                while (i2 <= i1) {
                    childrenIDs[i2] = bytebuffer.readUnsignedWord();
                    if (childrenIDs[i2] == 65535) {
                        childrenIDs[i2] = -1;
                    }
                    i2++;
                }
            } else if (i == 107) {
                aBoolean84 = false;
            }
        } while (true);
    }

}