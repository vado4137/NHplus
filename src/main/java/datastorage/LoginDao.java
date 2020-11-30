package datastorage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDao {

    private Connection conn;

    public boolean login(String benutzername, String passwort) throws SQLException {
        conn = ConnectionBuilder.getConnection();

        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(String.format("SELECT passwort FROM login WHERE benutzer = '%s'", benutzername));
        String pw = null;

        if(result.next()) {
            pw = result.getString(1);

            if(pw.equals(passwort)) {
                 return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
