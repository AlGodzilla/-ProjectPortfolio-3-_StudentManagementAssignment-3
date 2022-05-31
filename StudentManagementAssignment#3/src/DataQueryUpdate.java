import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class DataQueryUpdate extends JInternalFrame implements MouseListener {

    public static Connect con = new Connect();
    private JTable studTbl;
    private JInternalFrame internalFrame;
    private JPanel leftP, rightP, rightMostP;
    private DefaultTableModel studDtm;
    private JScrollPane scrollPane;
    private JLabel nim, nama, jenisKelamin, tempatLahir, tanggalLahir;
    private JTextField textNIM, textNama, textJenisKelamin, textTempatLahir, textTanggalLahir;
    private JButton tombolUpdate;
    private String NIM, NAMA, Jenis_Kelamin, Tempat_Lahir, SelectedRowID, Tanggal_Lahir;
    public Statement stat;
    public ResultSet rs;
    public ResultSetMetaData rsm;
    public PreparedStatement pStat;

    private boolean dataSelected = false;

    
    
    
    public DataQueryUpdate(Menu menu){

      
       
       
       
        internalFrame = new JInternalFrame();
        leftP = new JPanel();
        rightMostP = new JPanel();
        rightP = new JPanel(new GridLayout(11,4));


        String student[] = {"NIM", "NAMA", "JENIS KELAMIN", "TEMPAT LAHIR", "TANGGAL LAHIR"};
        studDtm = new DefaultTableModel(student, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        studTbl = new JTable(studDtm);
        studTbl.addMouseListener(this);
        scrollPane = new JScrollPane(studTbl);

        nim = new JLabel("NIM");
        nama = new JLabel("NAMA");
        jenisKelamin = new JLabel("JENIS KELAMIN");
        tempatLahir = new JLabel("TEMPAT LAHIR");
        tanggalLahir = new JLabel("TANGGAL LAHIR");

        textNIM = new JTextField();
        textNama = new JTextField();
        textJenisKelamin = new JTextField();
        textTempatLahir = new JTextField();
        textTanggalLahir = new JTextField();

        tombolUpdate = new JButton("Update");

        internalFrame.add(leftP);
        internalFrame.add(rightP);
        internalFrame.add(rightMostP);
        this.add(leftP, BorderLayout.WEST);
        this.add(rightP, BorderLayout.CENTER);
        this.add(rightMostP, BorderLayout.SOUTH);

        rightP.setBorder(new EmptyBorder(30,30,30,30));
        leftP.add(scrollPane);
        rightP.add(nim);
        rightP.add(textNIM);
        rightP.add(nama);
        rightP.add(textNama);
        rightP.add(jenisKelamin);
        rightP.add(textJenisKelamin);
        rightP.add(tempatLahir);
        rightP.add(textTempatLahir);
        rightP.add(tanggalLahir);
        rightP.add(textTanggalLahir);
        rightMostP.add(tombolUpdate);


        tombolUpdate.addMouseListener(this);
        getData();


        this.setClosable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setSize(900,600);
        this.setTitle("Data Query & Update");
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
    }

    public void clearTBL(){
        studDtm.getDataVector().removeAllElements();
        revalidate();
    }

    public void getData() {
        try {
            DataEntryDelete.con.getStudentData();
            while (DataEntryDelete.con.rs.next()) {
                Vector<String> Tabledata = new Vector<String>();
                for (int i = 1; i <= DataEntryDelete.con.rsm.getColumnCount(); i++) {
                    String str = DataEntryDelete.con.rs.getObject(i).toString();
                    Tabledata.add(str);
                }
                studDtm.addRow(Tabledata);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }





    }


















    @Override
    public void mouseClicked(MouseEvent e) {



        if(e.getSource()== tombolUpdate) {

            if(studTbl.getSelectedRowCount()==0) {


            } else if(textNama.getText().isEmpty()) {


            }else if(studTbl.getSelectedRowCount()==1) {
                int row = studTbl.getSelectedRow();
                String value = studTbl.getModel().getValueAt(row, 0).toString();
                String newNIM, newNama, newJenisKelamin, newTempatLahir, newTanggalLahir;

                if (!value.isEmpty()) {
                    dataSelected = true;
                }
                newNIM = textNIM.getText();
                newNama = textNama.getText();
                newJenisKelamin = textJenisKelamin.getText();
                newTempatLahir = textTempatLahir.getText();
                newTanggalLahir = textTanggalLahir.getText();


                DataQueryUpdate.con.updateData(newNIM, newNama, newJenisKelamin, newTempatLahir,  newTanggalLahir, value);
                studTbl.getSelectionModel().clearSelection();


                clearTBL();
                getData();

            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
