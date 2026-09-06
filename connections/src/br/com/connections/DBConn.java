package br.com.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConn implements AutoCloseable{

    private final Connection conn;

    public DBConn(Connection conn) {
        this.conn = conn;
    }

    public PreparedStatement statement(String sql) {
        try {
            return conn.prepareStatement(sql);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void close() throws Exception {
        conn.close();
    }

    //TODO parei em num1960, continuar em num 1961.
}
