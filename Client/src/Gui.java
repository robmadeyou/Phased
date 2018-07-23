import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.net.InetAddress;
import java.net.URL;


public class Gui extends Client implements ActionListener, FocusListener {

    private static final String newline = "\n";
    public static String browserPath = "C:/Program Files/Internet Explorer/iexplore.exe";
    public static String url = "";
    protected static JTextArea textArea;
    private static boolean isApplet = false;
    public int frameTheme = 1;
    public int theme;
    public JFrame frame;
    protected JScrollPane scrollPane;
    private JTabbedPane jTabbedPane1;
    private String frameTitle = "Aggroth - By Shaloxis! - Enjoy!";

    public Gui(String args[]) {
        super();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            sign.signlink.startpriv(InetAddress.getByName("localhost"));
            initUI();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new Gui(args);
    }

    public static void Launch(String launch) {
        String operatingSystem = System.getProperty("os.name");
        try {
            if (operatingSystem.startsWith("Windows")) {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + launch);
            } else {
            }
        } catch (Exception exception) {
            System.err.println("Error launching url.");
            exception.printStackTrace();
        }
    }

    public void launchURL(String s) {
        String s1 = System.getProperty("os.name");
        try {

            if (s1.startsWith("Windows")) {
                Runtime.getRuntime().exec((new java.lang.StringBuilder()).append("rundll32 url.dll,FileProtocolHandler ").append(s).toString());
            } else {
                String as[] = {"firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape"};
                String s2 = null;
                for (int i = 0; i < as.length && s2 == null; i++)
                    if (Runtime.getRuntime().exec(new String[]{"which", as[i]}).waitFor() == 0)
                        s2 = as[i];

                if (s2 == null)
                    throw new Exception("Could not find web browser");
                Runtime.getRuntime().exec(new String[]{s2, s});
            }
        } catch (Exception exception) {
            System.out.println("An error occured while trying to open the web browser!\n");
        }
    }

    private void initUI() {
        try {
            JFrame.setDefaultLookAndFeelDecorated(true);
            JPopupMenu.setDefaultLightWeightPopupEnabled(false);
            frame = new JFrame(frameTitle);


            frame.setLayout(new BorderLayout());
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel gamePanel = new JPanel();
            gamePanel.setLayout(new BorderLayout());
            setFocusTraversalKeysEnabled(false);
            gamePanel.add(this);
            gamePanel.setPreferredSize(new Dimension(765, 503));
            JCheckBox CheckButton;
            JMenu fileMenu = new JMenu("Aggroth");

            String[] mainButtons = new String[]{"Website", "Item list", "Vote", "Donate", "Forums", "-", "Exit"};

            for (String name : mainButtons) {
                JMenuItem menuItem = new JMenuItem(name);
                if (name.equalsIgnoreCase("-"))
                    fileMenu.addSeparator();
                else {
                    menuItem.addActionListener(this);
                    fileMenu.add(menuItem);
                }
            }

            JMenuBar menuBar = new JMenuBar();
            JMenuBar jmenubar = new JMenuBar();
            frame.add(jmenubar);
            frame.getContentPane().add(jmenubar, "South");
            menuBar.add(fileMenu);

            frame.getContentPane().add(menuBar, BorderLayout.NORTH);
            frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
            frame.pack();

            frame.setVisible(true);
            frame.setResizable(false);
            init();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void WorldSelect() {
        try {
            String s1 = JOptionPane.showInputDialog(this, (new StringBuilder()).append("Current server:"), "Enter Server", 3);
            if (s1 == null) {
                System.out.println("Canceled");
            } else if (s1.equalsIgnoreCase("")) {
                System.out.println("Sorry, have to have some input");
            } else {
                System.out.println((new StringBuilder()).append("Set world to: ").append(s1).toString());
            }
        } catch (Exception e) {
            System.out.println((new StringBuilder()).append("You must enter a numeric value!: ").append(e).toString());
        }
    }

    public URL getCodeBase() {
        try {
            return new URL("http://77.171.122.218/cache");
        } catch (Exception e) {
            return super.getCodeBase();
        }
    }

    public URL getDocumentBase() {
        return getCodeBase();
    }

    public void loadError(String s) {
        System.out.println("loadError: " + s);
    }

    public String getParameter(String key) {
        if (key.equals("nodeid"))
            return "10";
        else if (key.equals("portoff"))
            return "0";
        else if (key.equals("lowmem"))
            return "1";
        else if (key.equals("free"))
            return "0";
        else
            return "";
    }

    //ACTIONS DONE
    public void actionPerformed(ActionEvent evt) {
        String cmd = evt.getActionCommand();
        if (cmd != null) {
            if (cmd.equalsIgnoreCase("Website")) {
                launchURL("http://aggroth.com/");
            }
            if (cmd.equalsIgnoreCase("Forums")) {
                launchURL("http://aggroth.com/forums");
            }
            if (cmd.equalsIgnoreCase("Vote")) {
                launchURL("http://aggroth.com/vote");
            }
            if (cmd.equalsIgnoreCase("Donate")) {
                launchURL("http://aggroth.com/donate");
            }
            if (cmd.equalsIgnoreCase("Exit")) {
                int i1;
                if ((i1 = JOptionPane.showConfirmDialog(this, "Do you really want to exit the client?")) == 0)
                    System.exit(0);
                return;
            }
            if (cmd.equalsIgnoreCase("Item List")) {
                launchURL("IL.bat");
                System.out.println("The Item List is loading...");

            }

        }


    }
}