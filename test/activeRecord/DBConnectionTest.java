package activeRecord;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class DBConnectionTest {

    @Test
    void getConnection() {
        DBConnection dbConnection = DBConnection.getConnection();
        assertNotNull(dbConnection);

        DBConnection dbConnection2 = DBConnection.getConnection();
        assertEquals(dbConnection, dbConnection2);
    }

    @Test
    void setNomDB() {
        DBConnection dbConnection = DBConnection.getConnection();
        assertNotNull(dbConnection);

        DBConnection.setNomDB("testpersonne2");
        DBConnection dbConnection2 = DBConnection.getConnection();
        assertNotEquals(dbConnection, dbConnection2);
    }
}