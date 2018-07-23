package server.util;

import server.model.players.Client;

import java.sql.*;

public class MadTurnipConnection extends Thread {

    public static Connection con = null;
    public static Statement stm;

    public MadTurnipConnection() {

    }

    public static void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://www.aggroth.com/aggrothc_donate", "aggrothc_donate", "aggrothpassword");
            stm = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            con = null;
            stm = null;
        }
    }

    public static void ping() {
        try {
            String query = "SELECT * FROM donation WHERE username = 'null'";
            query(query);
        } catch (Exception e) {
            e.printStackTrace();
            con = null;
            stm = null;
        }
    }

    public static void addDonateItems(final Client c, final String name) {
        if (con == null) {
            if (stm != null) {
                try {
                    stm = con.createStatement();
                } catch (Exception e) {
                    con = null;
                    stm = null;
                    //put a sendmessage here telling them to relog in 30 seconds
                    return;
                }
            } else {
                //put a sendmessage here telling them to relog in 30 seconds
                return;
            }
        }
        new Thread() {
            @Override
            public void run() {
                try {
                    String name2 = name.replaceAll(" ", "_");
                    String query = "SELECT * FROM donation WHERE username = '" + name2 + "'";
                    ResultSet rs = query(query);
                    boolean b = false;
                    while (rs.next()) {
                        int prod = Integer.parseInt(rs.getString("productid"));
                        int price = Integer.parseInt(rs.getString("price"));
                        if (prod == 1 && price == 5) {
                            c.playerRights = 1;
                            c.loyaltyTitle = 13;
                            c.donatorPoints = 5;
                            c.getPlayerAssistant().yell("<col=255>[DONATE] " + c.playerName + " has just donated for Bronze Donator!</col>");
                            c.sendMessage("<col=255>[DONATE] Please relog for your rank, title, and points!</col>");
                            c.sendMessage("<col=255>[DONATE] Package 1 found! - Bronze donator</col>");
                            b = true;
                        } else if (prod == 2 && price == 7) {
                            c.playerRights = 2;
                            c.loyaltyTitle = 14;
                            c.donatorPoints = 10;
                            c.getPlayerAssistant().yell("<col=255>[DONATE] " + c.playerName + " has just donated for Iron Donator!</col>");
                            c.sendMessage("<col=255>[DONATE] Please relog for your rank, title, and points!</col>");
                            c.sendMessage("<col=255>[DONATE] Package 2 found! - Iron donator</col>");
                            b = true;
                        } else if (prod == 3 && price == 10) {
                            c.playerRights = 3;
                            c.loyaltyTitle = 15;
                            c.donatorPoints = 15;
                            c.getPlayerAssistant().yell("<col=255>[DONATE] " + c.playerName + " has just donated for Adamant Donator!</col>");
                            c.sendMessage("<col=255>[DONATE] Please relog for your rank, title, and points!</col>");
                            c.sendMessage("<col=255>[DONATE] Package 3 found! - Adamant donator</col>");
                            b = true;
                        } else if (prod == 4 && price == 12) {
                            c.playerRights = 4;
                            c.loyaltyTitle = 16;
                            c.donatorPoints = 20;
                            c.getPlayerAssistant().yell("<col=255>[DONATE] " + c.playerName + " has just donated for Bronze Donator!</col>");
                            c.sendMessage("<col=255>[DONATE] Please relog for your rank, title, and points!</col>");
                            c.sendMessage("<col=255>[DONATE] Package 4 found! - Rune donator</col>");
                            b = true;
                        } else if (prod == 5 && price == 15) {
                            c.playerRights = 5;
                            c.donatorPoints = 25;
                            c.getPlayerAssistant().yell("<col=255>[DONATE] " + c.playerName + " has just donated for Dragon Donator!</col></col>");
                            c.sendMessage("<col=255>[DONATE] Please relog for your rank, title, and points!</col>");
                            c.sendMessage("<col=255>[DONATE] Package 5 found! - Dragon Donator</col>");
                            b = true;
                        } else if (prod == 6 && price == 20) {
                            c.playerRights = 6;
                            c.loyaltyTitle = 18;
                            c.donatorPoints = 18;
                            c.getPlayerAssistant().yell("<col=255>[DONATE] " + c.playerName + " has just donated for Dicer!</col>");
                            c.sendMessage("<col=255>[DONATE] Please relog for your rank, title, and points!</col>");
                            c.sendMessage("<col=255>[DONATE] Package 6 found! - Dicer</col>");
                            b = true;
                        } else if (prod == 7 && price == 1) {
                            c.donatorPoints = 5;
                            c.getPlayerAssistant().yell("<col=255>[DONATE] " + c.playerName + " has just donated for 5 Donator Points!</col>");
                            c.sendMessage("<col=255>[DONATE] Package 7 found!</col>");
                            c.sendMessage("<col=255>[DONATE] You have gained 5 donator points.</col>");
                            b = true;
                        } else if (prod == 8 && price == 9) {
                            c.donatorPoints = 50;
                            c.getPlayerAssistant().yell("<col=255>[DONATE] " + c.playerName + " has just donated for 50 Donator Points!</col>");
                            c.sendMessage("<col=255>[DONATE] You have gained 50 donator points.</col>");
                            c.sendMessage("<col=255>[DONATE] Package 8 found!</col>");
                            b = true;
                        } else if (prod == 9 && price == 18) {
                            c.donatorPoints = 100;
                            c.getPlayerAssistant().yell("<col=255>[DONATE] " + c.playerName + " has just donated for 100 Donator Points!</col>");
                            c.sendMessage("<col=255>[DONATE] You have gained 100 donator points.</col>");
                            c.sendMessage("<col=255>[DONATE] Package 9 found!</col>");
                            b = true;
                        }
                    }
                    if (b) {
                        query("DELETE FROM `donation` WHERE `username` = '" + name2 + "';");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    con = null;
                    stm = null;
                }
            }
        }.start();
    }

    public static ResultSet query(String s) throws SQLException {
        try {
            if (s.toLowerCase().startsWith("select")) {
                ResultSet rs = stm.executeQuery(s);
                return rs;
            } else {
                stm.executeUpdate(s);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            con = null;
            stm = null;
        }
        return null;
    }

    public void run() {
        while (true) {
            try {
                if (con == null)
                    createConnection();
                else
                    ping();
                Thread.sleep(10000);//10 seconds
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}