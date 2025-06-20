package testlib.utils.handlers;

import java.sql.*;

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
                tempConnection=DriverManager.getConnection(String.format(ORACLE_JDBC_URL,PropertyHandler.getProperty("cache.address")),username,password);
                tempDbType="oracle";
            } catch (SQLException oracleEx){
                try{
                    tempConnection=DriverManager.getConnection(String.format(POSTGRES_JDBC_URL,PropertyHandler.getProperty("cache.address"))+(dbName.equals("msg")?"":"_"+dbName),
                            username,password);
                    tempDbType="postgresql";
                } catch (SQLException postgresEx){
                    oracleEx.printStackTrace();
                    postgresEx.printStackTrace();
                    System.err.println("Не удалось подключиться к БД");
                }
            }

            this.connection=tempConnection;
            this.dbType=tempDbType;
            this.dbName=dbName;
        }
    }

    public ResultSet query(String query){
        try(Statement statement=connection.createStatement()){
            if(query.toLowerCase().contains("insert"))
                executeQueryNonResult(query);
            else return statement.executeQuery(query);
        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Не удалось выполнить запрос к БД",e);
        }
        return null;
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
}
