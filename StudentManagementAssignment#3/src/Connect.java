import java.sql.*;

public class Connect {

    public Statement stat;
    public ResultSet rs;
    public ResultSetMetaData rsm;
    public Connection con;
    public PreparedStatement pStat;

    public Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3307/student", "root", "");
            stat = con.createStatement();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public ResultSet getStudentData(){
        String query = "SELECT * FROM student";

        try{
            pStat = con.prepareStatement(query);
            rs = pStat.executeQuery();
            rsm = rs.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    public void inserData(String nim, String nama, String jenis_kelamin, String tempat_lahir, String tanggal_lahir) {

        try {
            String query = "INSERT INTO student(NIM, NAMA, JENIS_KELAMIN, TEMPAT_LAHIR, TANGGAL_LAHIR)"
                    + "VALUES(?,?,?,?,?)";

            pStat = con.prepareStatement(query);
            pStat.setString(1,nim);
            pStat.setString(2, nama);
            pStat.setString(3,jenis_kelamin);
            pStat.setString(4,tempat_lahir);
            pStat.setString(5, tanggal_lahir);
            pStat.execute();
            getStudentData();
            System.out.println("aye");

        } catch (SQLException e) {
            System.out.println("nah");
            e.printStackTrace();
        }
    }

    public void deleteData(String value) {

        try {
            String query = "DELETE FROM student WHERE NIM =?";
            pStat = con.prepareStatement(query);
            pStat.setString(1, value);
            pStat.execute();
            System.out.println("aye");

        } catch (Exception e) {
            System.out.println("nah");

            // TODO: handle exception
        }
    }











    public void updateData(String newNIM, String newNama, String newJenisKelamin, String newTempatLahir, String newTanggalLahir, String value) {

        try {
            String query = "UPDATE student SET NIM = ?, NAMA=?, JENIS_KELAMIN=?, TEMPAT_LAHIR=?, TANGGAL_LAHIR=? "
                    + "  WHERE NIM =?";
            pStat = con.prepareStatement(query);
            pStat.setString(1,newNIM);
            pStat.setString(2,newNama);
            pStat.setString(3,newJenisKelamin);
            pStat.setString(4,newTempatLahir);
            pStat.setString(5,newTanggalLahir);
            pStat.setString(6,value);

            pStat.execute();
            System.out.println("Aue");
        } catch (SQLException e) {
            System.out.println("Nah");
        }
    }





    }

