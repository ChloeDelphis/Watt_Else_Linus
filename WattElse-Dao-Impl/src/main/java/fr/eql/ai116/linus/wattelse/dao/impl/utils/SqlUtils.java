package fr.eql.ai116.linus.wattelse.dao.impl.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlUtils {
    public static Long readLong(String name, ResultSet resultSet) throws SQLException {
        Long result = resultSet.getLong(name);
        if (resultSet.wasNull()) return null;
        else return result;
    }

    public static String ifNullString(String IfullString) {
        if (IfullString == null) return "";
        else return IfullString;
    }

    public static Long ifNullLong(Long ifNullLong) {
        if (ifNullLong == null) return null;
        else return ifNullLong;
    }



}
