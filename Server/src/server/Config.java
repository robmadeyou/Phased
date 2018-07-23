package server;

public class Config {

    public static final boolean SERVER_DEBUG = false;

    public static final int ITEM_LIMIT = 20500;
    public static final int MAXITEM_AMOUNT = Integer.MAX_VALUE;
    public static final int BANK_SIZE = 700;
    public static final int MAX_PLAYERS = 1024;

    public static final int CONNECTION_DELAY = 100; // how long one ip can keep connecting
    public static final int IPS_ALLOWED = 2; // how many ips are allowed

    public static final boolean WORLD_LIST_FIX = false; // change to true if you want to stop that world--8 thing, but it can cause the screen to freeze on silabsoft client

    public static final int[] ITEM_SELLABLE = {18509, 15332, 15333, 15334, 15335, 15308, 15312, 15316, 15320, 15324, 3842, 12513, 12515, 12517, 12519, 12521, 12523, 12476, 12470, 12472, 12474, 3844, 3840, 8844, 8845, 8846, 8847, 8848, 8849, 8850, 10551, 6570, 7462, 7461, 7460, 7459, 7458, 7457, 7456, 7455, 7454, 7453, 8839, 8840, 8842, 11663, 11664, 11665, 10499, 9748, 9948, 9949, 9754, 9751, 9769, 9757, 9760, 9763, 9802, 9808, 9784, 9799, 9805, 9781, 9796, 9793, 9775, 9772, 9778, 9787, 9811, 9766, 9749, 9755, 9752, 9770, 9758, 9761, 9764, 9803, 9809, 9785, 9800, 9806, 9782, 9797, 9794, 9776, 9773, 9779, 9788, 9812, 9767, 9747, 9753, 9750, 9768, 9756, 9759, 9762, 9801, 9807, 9783, 9798, 9804, 9780, 9795, 9792, 9774, 9771, 9777, 9786, 9810, 9765, 995}; // what items can't be sold in any store
    public static final int[] ITEM_TRADEABLE = {18509, 15808, 15914, 15925, 15936, 16013, 16035, 16127, 16262, 19893, 19892, 15786, 15797, 15837, 15892, 16185, 16153, 16002, 16046, 16057, 16068, 16105, 15332, 15333, 15334, 15335, 15308, 15312, 15316, 15320, 15324, 19785, 19786, 19787, 19788, 19789, 19790, 13351, 12513, 12515, 12517, 12519, 12521, 12523, 12476, 12470, 12472, 12474, 8850, 9948, 9949, 10551, 8839, 8840, 8842, 11663, 11664, 11665, 3842, 3844, 3840, 8844, 8845, 8846, 8847, 8848, 8849, 8850, 10551, 6570, 7462, 7461, 7460, 7459, 7458, 7457, 7456, 7455, 7454, 7453, 8839, 8840, 8842, 11663, 11664, 11665, 10499, 9748, 9754, 9751, 9769, 9757, 9760, 9763, 9802, 9808, 9784, 9799, 9805, 9781, 9796, 9793, 9775, 9772, 9778, 9787, 9811, 9766, 9749, 9755, 9752, 9770, 9758, 9761, 9764, 9803, 9809, 9785, 9800, 9806, 9782, 9797, 9794, 9776, 9773, 9779, 9788, 9812, 9767, 9747, 9753, 9750, 9768, 9756, 9759, 9762, 9801, 9807, 9783, 9798, 9804, 9780, 9795, 9792, 9774, 9771, 9777, 9786, 9810, 9765, 12163, 12170, 12169, 12158, 12159, 12160}; // what items can't be traded or staked
    public static final int[] UNDROPPABLE_ITEMS = {18509, 15808, 15914, 15925, 15936, 16013, 16035, 16127, 16262, 19893, 19892, 15786, 15797, 15837, 15892, 16185, 16153, 16002, 16046, 16057, 16068, 16105, 15308, 18328, 18312, 18296, 18280, 15312, 15316, 15320, 15324, 12470, 12472, 12474}; // what items can't be dropped

    public static final int[] FUN_WEAPONS = {2460, 2461, 2462, 2463, 2464, 2465, 2466, 2467, 2468, 2469, 2470, 2471, 2471, 2473, 2474, 2475, 2476, 2477}; // fun weapons for dueling
    public static final int START_LOCATION_X = 3086; // start here
    public static final int START_LOCATION_Y = 3488;
    public static final int RESPAWN_X = 3086; // when dead respawn here
    public static final int RESPAWN_Y = 3488;
    public static final int DUELING_RESPAWN_X = 3362; // when dead in duel area spawn here
    public static final int DUELING_RESPAWN_Y = 3263;
    public static final int RANDOM_DUELING_RESPAWN = 5; // random coords
    public static final int NO_TELEPORT_WILD_LEVEL = 20; // level you can't tele on and above
    public static final int SKULL_TIMER = 5000; // how long does the skull last? seconds x 2
    public static final boolean SINGLE_AND_MULTI_ZONES = true; // multi and single zones?
    public static final boolean COMBAT_LEVEL_DIFFERENCE = true; // wildy levels and combat level differences matters
    public static final boolean itemRequirements = true; // attack, def, str, range or magic levels required to wield weapons or wear items?
    public static final int MELEE_EXP_RATE = 1200; // damage * exp rate
    public static final int RANGE_EXP_RATE = 1200;
    public static final int MAGIC_EXP_RATE = 1000;
    public static final double SERVER_EXP_BONUS = 1;
    public static final int INCREASE_SPECIAL_AMOUNT = 17500; // how fast your special bar refills
    public static final int INCREASE_SPECIAL_AMOUNT_WITH_RING = 10000; // how fast your special bar refills with ring of vigour
    public static final boolean PRAYER_POINTS_REQUIRED = true; // you need prayer points to use prayer
    public static final boolean PRAYER_LEVEL_REQUIRED = true; // need prayer level to use different prayers
    public static final boolean MAGIC_LEVEL_REQUIRED = true; // need magic level to cast spell
    public static final int GOD_SPELL_CHARGE = 300000; // how long does god spell charge last?
    public static final boolean RUNES_REQUIRED = true; // magic rune required?
    public static final boolean CORRECT_ARROWS = true; // correct arrows for bows?
    public static final boolean CRYSTAL_BOW_DEGRADES = false; // magic rune required?
    public static final int SAVE_TIMER = 30; // save every 1 minute
    public static final int NPC_RANDOM_WALK_DISTANCE = 1; // the square created , 3x3 so npc can't move out of that box when randomly walking
    public static final int NPC_FOLLOW_DISTANCE = 20; // how far can the npc follow you from it's spawn point,
    public static final int[] UNDEAD_NPCS = {90, 91, 92, 93, 94, 103, 104, 73, 74, 75, 76, 77}; // undead npcs
    public static final int EDGEVILLE_X = 3087;
    public static final int EDGEVILLE_Y = 3492;
    public static final int AL_KHARID_X = 3293;
    public static final int AL_KHARID_Y = 3174;
    public static final int KARAMJA_X = 3087;
    public static final int KARAMJA_Y = 3500;
    public static final int MAGEBANK_X = 2538;
    public static final int MAGEBANK_Y = 4716;
    public static final int TIMEOUT = 20;
    public static final int BUFFER_SIZE = 10000;
    public static final int MAX_PROCESS_PACKETS = 7;
    /**
     * Skill Experience Multipliers
     */
    public static final int WOODCUTTING_EXPERIENCE = 30;
    public static final int MINING_EXPERIENCE = 50;
    public static final int SMITHING_EXPERIENCE = 50;
    public static final int FARMING_EXPERIENCE = 60;
    public static final int FIREMAKING_EXPERIENCE = 10;
    public static final int HERBLORE_EXPERIENCE = 35;
    public static final int FISHING_EXPERIENCE = 35;
    public static final int PRAYER_EXPERIENCE = 35;
    public static final int RUNECRAFTING_EXPERIENCE = 40;
    public static final int CRAFTING_EXPERIENCE = 40;
    public static final int THIEVING_EXPERIENCE = 40;
    public static final int SLAYER_EXPERIENCE = 20;
    public static final int COOKING_EXPERIENCE = 30;
    public static final int FLETCHING_EXPERIENCE = 35;
    //public static final boolean ADMIN_CAN_TRADE = true; //can admins trade?
    //public static boolean ADMIN_CAN_SELL_ITEMS = true; // can admins sell items?
    public static boolean ADMIN_DROP_ITEMS = true; // can admin drop items?
    public static int MAX_NPCS = Server.npcHandler.maxNPCs;
    public static boolean LOCK_EXPERIENCE = false;
    public static boolean MINI_GAMES = true;
    public static String LOGOUT_MESSAGE = "Click here to logout!";
    public static String DEATH_MESSAGE = "Oh dear you are dead!";
    public static boolean DOUBLE_EXP = true;
}
