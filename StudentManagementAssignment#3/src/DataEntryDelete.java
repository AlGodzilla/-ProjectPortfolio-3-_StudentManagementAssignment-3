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


public class DataEntryDelete extends JInternalFrame implements MouseListener {

    private JTable studTbl;
    private JInternalFrame internalFrame;
    private JPanel leftP, rightP, rightMostP;
    private DefaultTableModel studDtm;
    private JScrollPane scrollPane;
    private JLabel nim, nama, jenisKelamin, tempatLahir, tanggalLahir;
    private JTextField textNIM, textNama, textJenisKelamin, textTempatLahir, textTanggalLahir;
    private JButton tombolAdd, tombolDelete;
    private String NIM, NAMA, Jenis_Kelamin, Tempat_Lahir, SelectedRowID, Tanggal_Lahir;
    public static Connect con = new Connect();
    public Statement stat;
    public ResultSet rs;
    public ResultSetMetaData rsm;
    public PreparedStatement pStat;

    private boolean dataSelected = false;

    public DataEntryDelete(Menu menu){
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

        tombolAdd = new JButton("Add");
        tombolDelete = new JButton("Delete");

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
        rightMostP.add(tombolAdd);
        rightMostP.add(tombolDelete);


        tombolAdd.addMouseListener(this);
        tombolDelete.addMouseListener(this);
        getData();


        this.setClosable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setSize(900,600);
        this.setTitle("Data Entry & Delete");
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

        if(e.getSource()== tombolAdd){
            try{
                NIM = textNIM.getText();
                NAMA = textNama.getText();
                Jenis_Kelamin = textJenisKelamin.getText();
                Tempat_Lahir = textTempatLahir.getText();
                Tanggal_Lahir = textTanggalLahir.getText();

                DataEntryDelete.con.inserData(NIM, NAMA, Jenis_Kelamin, Tempat_Lahir, Tanggal_Lahir);

                studTbl.clearSelection();
                clearTBL();
                textNIM.setText("");
                textNama.setText("");
                textJenisKelamin.setText("");
                textTempatLahir.setText("");
                textTanggalLahir.setText("");
                DataEntryDelete.con.rs.next();
                clearTBL();
                getData();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


        if(e.getSource()== tombolDelete) {

            if(studTbl.getSelectedRowCount()==0) {

                return;
            }else if(studTbl.getSelectedRowCount()==1) {
                int row = studTbl.getSelectedRow();
                String value = studTbl.getModel().getValueAt(row, 0).toString();

                if (!value.isEmpty()) {
                    dataSelected = true;
                }

                DataEntryDelete.con.deleteData(value);
                studDtm.removeRow(studTbl.getSelectedRow());
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
