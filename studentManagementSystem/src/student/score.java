
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

public class score {
      Connection con = MyConnection.getConnection();
    PreparedStatement ps; 
      public int getMax() {
        int id = 0;
        Statement st;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select max(id) from score");
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(score.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id + 1;

    }
        public boolean getdetails(int sid,int semesterno) {
        try {
            ps = con.prepareStatement("select * from course where student_id = ? and semester = ?");
            ps.setInt(1, sid);
             ps.setInt(2, semesterno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                safe.jTextField24.setText(String.valueOf(rs.getInt(2)));
                safe.jTextField25.setText(String.valueOf(rs.getInt(3)));
                safe.jTextField26.setText(rs.getString(4));
                safe.jTextField22.setText(rs.getString(5));
                safe.jTextField27.setText(rs.getString(6));
                safe.jTextField16.setText(rs.getString(7));
                safe.jTextField17.setText(rs.getString(8));
                
                return true; 
                
            }
            else
            {
                JOptionPane.showMessageDialog(null, "student id or semester number doesn't exist");
            }
        } catch (SQLException ex) {
            Logger.getLogger(score.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
         public boolean isIDExist(int id) {
        try {
            ps = con.prepareStatement("select * from score where id =?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(score.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
            public boolean issidsemesternoexist(int sid,int semesterno) {
        try {
            ps = con.prepareStatement("select * from score where student_id =? and semester = ?");
            ps.setInt(1, sid);
             ps.setInt(2, semesterno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(course.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
              public void getScoreValue(JTable table, String searchValue) {
        String sql = "select * from score where concat(id,student_id,semester)like ? order by id desc";

        try{
            ps = con.prepareStatement(sql);
           
             ps.setString(1, "%" + searchValue + "%");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[14];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getInt(3);
                row[3] = rs.getString(4);
                row[4] = rs.getDouble(5);
                row[5] = rs.getString(6);
                row[6] = rs.getDouble(7);
                row[7] = rs.getString(8);
                row[8] = rs.getDouble(9);
                row[9] = rs.getString(10);
                row[10] = rs.getDouble(11);
                row[11] = rs.getString(12);
                row[12] = rs.getDouble(13);
                row[13] = rs.getDouble(14);
               
                model.addRow(row);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(score.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
                    public void getScoresValue(JTable table,int sid) {
        String sql = "select * from score where student_id =?";

        try{
            ps = con.prepareStatement(sql);
           
             ps.setInt(1, sid);
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[14];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getInt(3);
                row[3] = rs.getString(4);
                row[4] = rs.getDouble(5);
                row[5] = rs.getString(6);
                row[6] = rs.getDouble(7);
                row[7] = rs.getString(8);
                row[8] = rs.getDouble(9);
                row[9] = rs.getString(10);
                row[10] = rs.getDouble(11);
                row[11] = rs.getString(12);
                row[12] = rs.getDouble(13);
                row[13] = rs.getDouble(14);
               
                model.addRow(row);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(score.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
            
               public void insert(int id, int sid,int semester,String course1, String course2, String course3, String course4, String course5,
                       double score1,double score2,double score3,double score4,double score5,double average) {
        String sql = "insert into score values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, sid);
            ps.setInt(3, semester);
            ps.setString(4, course1);
            ps.setString(6, course2);
            ps.setString(8, course3);
            ps.setString(10, course4);
            ps.setString(12, course5);
            ps.setDouble(5, score1);
            ps.setDouble(7, score2);
            ps.setDouble(9, score3);
            ps.setDouble(11, score3);
            ps.setDouble(13, score3);
            ps.setDouble(14, average);
          
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "course score added successfully");
            }

        } catch (SQLException ex) {
            Logger.getLogger(score.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       public void update(int id ,double score1,double score2,double score3,double score4,double score5,double average) {
                String sql="update score set score1=?,score2=?,score3=?,score4=?,score5=?,average=?where id=?";
        try {
            ps=con.prepareStatement(sql);
            ps.setDouble(1, score1);
            ps.setDouble(2, score2);
            ps.setDouble(3, score3); 
            ps.setDouble(4, score4);
            ps.setDouble(5, score5);
            ps.setDouble(6,average);
           
            ps.setInt(7, id);
            if(ps.executeUpdate()>0)
            {
                JOptionPane.showMessageDialog(null, "score data updated successfully");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(score.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
