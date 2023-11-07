
package student;

import db.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class fee {
     Connection con = MyConnection.getConnection();
    PreparedStatement ps; 
     public int getMax() {
        int id = 0;
        Statement st;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select max(id) from fee");
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(fee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id + 1;

    }
      public boolean isPhoneExist(String phone) {
        try {
            ps = con.prepareStatement("select * from fee where phone =?");
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(fee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     public int countSemester(int id)
       {
           int total=0;
         try {
             ps=con.prepareStatement("select count(*) as 'total' from fee where student_id = ?");
             ps.setInt(1, id);
             ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total=rs.getInt(1);
            }
            if(total==8)
            {
                 JOptionPane.showMessageDialog(null, "The student has completed all the courses");
                 return -1;
            }
         } catch (SQLException ex) {
             Logger.getLogger(fee.class.getName()).log(Level.SEVERE, null, ex);
         }
         return total;
       }
        
      public void insert(int id,int student_id, String sname, int semester,String phone,
            int amount, String date, String paid) {
        String sql = "insert into fee values(?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, student_id);
            ps.setString(3, sname);
            ps.setInt(4, semester);
            ps.setString(5, phone);
          
             ps.setInt(6, amount);

            ps.setString(7, date);
            ps.setString(8, paid);
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "New student added successfully");
            }

        } catch (SQLException ex) {
            Logger.getLogger(fee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public boolean getId(int id) {
        try {
            ps = con.prepareStatement("select * from course where student_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                 safe.jTextField40.setText(String.valueOf(id));
                return true;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "student id doesn't exist");
            }
        } catch (SQLException ex) {
            Logger.getLogger(fee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     public boolean isSemesterexit (int sid,int semesterno) {
        try {
            ps = con.prepareStatement("select * from fee where student_id = ? and semester = ?");
            ps.setInt(1, sid);
             ps.setInt(2, semesterno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(fee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     public void getfeeValue(JTable table, int sid) {
        String sql = "select * from fee where student_id = ?";

        try{
            ps = con.prepareStatement(sql);
           
              ps.setInt(1, sid);
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[8];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getString(3);
               
                row[3] = rs.getInt(4);
                row[4] = rs.getString(5);
                row[5] = rs.getInt(6);
               row[6] = rs.getString(7);
                row[7] = rs.getString(8);
                model.addRow(row);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(fee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
      public void getfeesValue(JTable table, String searchValue) {
        String sql = "select * from fee where concat(id,student_id,semester)like ? order by id desc";

        try{
            ps = con.prepareStatement(sql);
           
              ps.setString(1, "%" + searchValue + "%");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[8];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getString(3);
               
                row[3] = rs.getInt(4);
                row[4] = rs.getString(5);
                row[5] = rs.getInt(6);
               row[6] = rs.getString(7);
                row[7] = rs.getString(8);
                model.addRow(row);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(fee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
       public boolean isIDExist(int id) {
        try {
            ps = con.prepareStatement("select * from fee where id =?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(fee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     public void update(int id,int student_id, String sname, int semester,String phone,
            int amount, String date, String paid) {
        String sql="update fee set student_id=?, Student_Name=?, semester=?, phone_number=?, Amount=?, Date=?, Status=? where id=? ";
        try {
            ps=con.prepareStatement(sql);
            
            ps.setInt(1, student_id);
            ps.setString(2, sname);
            ps.setInt(3, semester);
            ps.setString(4, phone);
          
             ps.setInt(5, amount);

            ps.setString(6, date);
            ps.setString(7, paid);
             ps.setInt(8, id);
            if(ps.executeUpdate()>0)
            {
                JOptionPane.showMessageDialog(null, "fee data updated successfully");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(fee.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
