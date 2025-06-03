package testlib.utils.handlers;

import java.sql.*;

public class SQLHandler implements AutoCloseable {

    private static final String POSTGRES_CONN_STRING="jdbc:postgresql://%s:5432/";
    private static final String ORACLE_CONN_STRING="jdbc:oracle:thin:@%s:1521:wsoft";

    private final Connection connection;
    private final Statement statement;
    private final String dbType;

    public SQLHandler(String dbScheme){

        String username=null, password=null;

        switch (dbScheme){
            case "msg":{
                username=PropertyHandler.getProperty("db.msg.user");
                password=PropertyHandler.getProperty("db.msg.password");
                break;
            }
            case "msg_stat":{
                username=PropertyHandler.getProperty("db.msg.stat.user");
                password=PropertyHandler.getProperty("db.msg.stat.password");
                break;
            }
            case "msg_cdp":{
                username=PropertyHandler.getProperty("db.msg.cdp.user");
                password=PropertyHandler.getProperty("db.msg.cdp.password");
                break;
            }
        }

        Connection tempConnection = null;
        Statement tempStatement = null;
        String tempDbType = null;

        try{

            String url=String.format(ORACLE_CONN_STRING,PropertyHandler.getProperty("base.url"));
            tempConnection=DriverManager.getConnection(url,username,password);
            tempDbType="oracle";
        } catch (SQLException oraEx){
            try{

                String url=String.format(POSTGRES_CONN_STRING,PropertyHandler.getProperty("base.url"))+"_"+dbScheme;
                tempConnection=DriverManager.getConnection(url,username,password);
                tempDbType="postgresql";
            } catch (SQLException postgresEx){
                System.err.println();
                throw new RuntimeException("Ошибка подключения к БД", postgresEx);
            }
        }

        try{
            tempStatement=tempConnection.createStatement();
        } catch (SQLException e){
            closeResources(tempConnection);
            throw new RuntimeException("Не удалось создать Statement", e);
        }

        this.connection=tempConnection;
        this.statement=tempStatement;
        this.dbType=tempDbType;
    }

    public ResultSet query(String query) throws SQLException {

        try{
            if(query.toLowerCase().contains("insert"))
                try{
                    statement.executeUpdate(query);
                } catch (SQLException e){
                    throw new RuntimeException("Ошибка при выполнении запроса: "+query, e);
                }
            else return statement.executeQuery(query);
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return null;
    }

    @Override
    public void close(){
        closeResources(statement,connection);
    }

    private void closeResources(AutoCloseable... resources){
        for(AutoCloseable resource:resources){
            if(resource!=null){
                try{
                    resource.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
