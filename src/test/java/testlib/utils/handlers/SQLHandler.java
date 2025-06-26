package testlib.utils.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLHandler implements AutoCloseable {

    private static final Logger log= LoggerFactory.getLogger(SQLHandler.class);
    private static final String ORACLE_JDBC_URL = "jdbc:oracle:thin:@%s:1521:wsoft";
    private static final String POSTGRES_JDBC_URL = "jdbc:postgresql://%s:5432/msg";

    private Connection connection=null;
    public String dbType=null;
    private static String dbName="";

    public SQLHandler(String dbType) {
        this(dbType.equals("msg") ? PropertyHandler.getProperty("db.msg.user") : PropertyHandler.getProperty("db.msg.user") + "_" + dbType,
                dbType.equals("msg") ? PropertyHandler.getProperty("db.msg.password") : PropertyHandler.getProperty("db.msg.password") + "_" + dbType,
                dbType);
    }

    private SQLHandler(String username,String password,String dbName){
        if(this.connection==null && !this.dbName.equals(dbName)){
            Connection tempConnection=null;
            String tempDbType=null;

            try{
                tempConnection=tryOracleConnection(username,password,dbName);
                tempDbType="oracle";
            } catch (SQLException oracleEx){
                try{
                    tempConnection=tryPostgresConnection(username, password, dbName);
                    tempDbType="postgresql";
                } catch (SQLException postgresEx){
                    oracleEx.printStackTrace();
                    postgresEx.printStackTrace();
                    log.info("Не удалось подключиться к БД", oracleEx, postgresEx);
                    throw new RuntimeException(postgresEx);
                }
            }

            this.connection=tempConnection;
            this.dbType=tempDbType;
            this.dbName=dbName;
        }
    }

    public ResultSet query(String query) throws SQLException {
        Statement statement= connection.createStatement();
        try {
            if (query.toLowerCase().contains("insert")) {
                log.info("Отправляем запрос без возврата ResultSet: "+query);
                executeQueryNonResult(query);
            }
            else {
                log.info("Отправляем запрос с возвращением ResultSet: "+query);
                return statement.executeQuery(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void executeQueryNonResult(String query){
        try(Statement statement=connection.createStatement()){
            log.info("Отправляем запрос без возврата ResultSet: "+query);
            statement.executeUpdate(query);
        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Ошибка при выполнении запроса без возвращения результата: " + query, e);
        }
    }

    public void close(){
        try{
            if(connection!=null)
                connection.close();
        } catch (SQLException e){
            e.printStackTrace();
            log.info("Ошибка при закрытии соединения: " + e.getMessage());
        }
    }

    private Connection tryOracleConnection(String username, String password, String dbName) throws SQLException {
        String url = String.format(ORACLE_JDBC_URL, PropertyHandler.getProperty("cache.address"));
        return DriverManager.getConnection(url, username, password);
    }

    private Connection tryPostgresConnection(String username, String password, String dbName) throws SQLException {
        String url = String.format(POSTGRES_JDBC_URL, PropertyHandler.getProperty("cache.address")) + (dbName.equals("msg") ? "" : "_" + dbName);
        return DriverManager.getConnection(url, username, password);
    }
}
