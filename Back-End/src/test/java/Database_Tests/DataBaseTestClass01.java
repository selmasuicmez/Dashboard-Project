package Database_Tests;

import org.Dashboard.DataBaseUtility;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DataBaseTestClass01 {

    //Check database connection
    @Test()
    @DisplayName("DataBaseConnectionTest")
    public void testDataBase() throws SQLException {
        DataBaseUtility.connectionDB();
        ResultSet rs = DataBaseUtility.getDataBase("Select * from issues");
        ResultSetMetaData rand = rs.getMetaData();
        int colums = rand.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= colums; i++) {
                System.out.println(rand.getColumnName(i));
                System.out.println(rs.getString(i));
            }
            System.out.println();
        }
        DataBaseUtility.closeDB();
    }
    //Check data base if it shows all in database
    @Test()
    @DisplayName("showDataFromDBTest")
    public void showDataBase() throws SQLException {
        DataBaseUtility.connectionDB();
        DataBaseUtility.showDataBase("Select * from issues");
        DataBaseUtility.closeDB();
    }
    //Inserting data into datase and checking if it is in database
    @Test()
    @DisplayName("insertDataToDBTest")
    public void insertDataBase() throws SQLException {
        DataBaseUtility.connectionDB();
        DataBaseUtility.showDataBase("Select * from issues");
        DataBaseUtility.insertDataBase("insert into issues values('COOL', 500)");
        DataBaseUtility.showDataBase("Select * from issues");
        DataBaseUtility.closeDB();
    }
    // Aserting if inserted data is in database
    @Test()
    @DisplayName("assertingDataFromDatabase")
    public void AssertInsertion() throws SQLException {
        String issueType = "Nog doen";
        int storyPoint2 = 8;

        DataBaseUtility.connectionDB();
        ResultSet rs = DataBaseUtility.getDataBase("Select * from issues where issueType='Nog doen'");
        try {
            System.out.println("Asserting values database");
            Assert.assertEquals(rs.getString(1), issueType);
            Assert.assertEquals(rs.getInt(2), storyPoint2);

        } catch (SQLException e) {
            e.printStackTrace();
            DataBaseUtility.closeDB();
        }
    }
}

