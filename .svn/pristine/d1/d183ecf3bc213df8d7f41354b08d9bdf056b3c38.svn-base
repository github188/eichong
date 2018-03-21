package com.wanma.ims.util;

import com.wanma.ims.core.GlobalSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Created by xyc on 2018/3/20.
 * jdbc工具，用于需要单独操作数据库时使用
 */
public class JdbcUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcUtil.class);

    //TODO:还需要进一步重构成批量，并直接返回解析结果List
    public static ResultSet doQuerySql(Connection con, Statement stmt, String sql) throws Exception {
        ResultSet resultSet;
        try {
            LOGGER.info("JdbcUtil-doQuerySql execute sql|sql={}", sql);
            resultSet = stmt.executeQuery(sql);
        } catch (Exception e) {
            LOGGER.error(con.getCatalog() + "|JdbcUtil-doQuerySql is error|sql={}", sql, e);
            throw e;
        }

        return resultSet;
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(GlobalSystem.getConfig("jdbc.url"),
                    GlobalSystem.getConfig("jdbc.username"), GlobalSystem.getConfig("jdbc.password"));
        } catch (Exception e) {
            LOGGER.error("JdbcUtil-getConnection error", e);
        }
        return conn;
    }

    public static void closeConnection(Statement stmt, PreparedStatement ps, Connection con) {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUtil-closeConnection is error", e);
        }
    }
}
