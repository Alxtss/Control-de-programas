package Conexion;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    private Connection con;
    private Statement st,st2;
    private ResultSet res;
    
    public Conexion(String server, String user, String pass, String bd) throws ClassNotFoundException, SQLException{
        
        String url = "jdbc:mysql://"+server+"/"+bd+"?user="+user+"&password="+pass+"&useSSL=false";
        
        Class.forName("com.mysql.jdbc.Driver");
        
        con = DriverManager.getConnection(url);
    }
    
    public void ejecutar(String sql) throws SQLException{
        st = con.createStatement();
        st.execute(sql);
        
        desconectar();
    }
    
    public ResultSet ejecutarSelect(String select) throws SQLException{
        st = con.createStatement();
        st2 = con.createStatement();
        res = st2.executeQuery("set lc_time_names = 'es_ES';");
        res = st.executeQuery(select);
          
        return res;
    }
    
    public void desconectar() throws SQLException{
        st.close();
    }
}






























