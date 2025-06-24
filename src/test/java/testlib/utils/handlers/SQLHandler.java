package testlib.utils.handlers;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLHandler implements AutoCloseable {

    private static final String ORACLE_JDBC_URL = "jdbc:oracle:thin:@%s:1521:wsoft";
    private static final String POSTGRES_JDBC_URL = "jdbc:postgresql://%s:5432/msg";

    private static Connection connection=null;
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
                    System.err.println("Не удалось подключиться к БД");
                    throw new RuntimeException(postgresEx);
                }
            }

            this.connection=tempConnection;
            this.dbType=tempDbType;
            this.dbName=dbName;
        }
    }

    public String queryForString(String query){
        try(Statement statement= connection.createStatement();
        ResultSet resultSet=statement.executeQuery(query)){
            if(resultSet.next())
                return resultSet.getString(1);
            throw new RuntimeException("Данные отсутствуют");
        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Ошибка запроса: " + query, e);
        }
    }

    public void executeQueryNonResult(String query){
        try(Statement statement=connection.createStatement()){
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
            System.err.println("Ошибка при закрытии соединения: " + e.getMessage());
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
