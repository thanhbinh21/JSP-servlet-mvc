package fit.se.librarysession.util;

import javax.sql.DataSource;

public class DBUtil {
    private DataSource dataSource;

    public DBUtil(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
