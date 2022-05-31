import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Menu extends JFrame implements ActionListener {

private JPanel mainP;
private JMenuBar menuBar;
private JMenu menu1, exit;
private JMenuItem dataEntryDelete;
private JMenuItem dataQueryUpdate;
private JDesktopPane desktopPane;
private DataEntryDelete DED;
private DataQueryUpdate DQU;

public Connect con = new Connect();

public Menu(){

    desktopPane = new JDesktopPane();
    desktopPane.setBackground(Color.WHITE);
    setContentPane(desktopPane);

    mainP = new JPanel(new BorderLayout());
    mainP.setBackground(Color.WHITE);
    mainP.setBorder(new EmptyBorder(30,30,30,30));
    add(mainP);


    menuBar = new JMenuBar();
    setJMenuBar(menuBar);


    menu1 = new JMenu("MENU");
    exit = new JMenu("EXIT");
    menuBar.add(menu1);
    menuBar.add(exit);

    dataEntryDelete = new JMenuItem("Data Entry & Delete");
    menu1.add(dataEntryDelete);
    dataQueryUpdate = new JMenuItem("Data Query & Update");
    menu1.add(dataQueryUpdate);

    dataEntryDelete.addActionListener(this);
    dataQueryUpdate.addActionListener(this);
    exit.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.exit(0);
        }
    });

    this.setVisible(true);
    this.setSize(1000,800);
    this.setTitle("Student Master Data");
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



}






    @Override

    public void actionPerformed(ActionEvent e) {


    if(e.getSource()== dataEntryDelete) {

            DED = new DataEntryDelete(this);
            DED.setVisible(true);
            desktopPane.add(DED);

        }else if(e.getSource()== dataQueryUpdate) {

            DQU = new DataQueryUpdate(this);
            DQU.setVisible(true);
            desktopPane.add(DQU);

        }
    }

    }




