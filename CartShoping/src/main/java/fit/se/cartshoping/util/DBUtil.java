package fit.se.cartshoping.util;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DBUtil {
    private DataSource dataSource;

    public DBUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() throws SQLException {
        return dataSource;
    }
}
