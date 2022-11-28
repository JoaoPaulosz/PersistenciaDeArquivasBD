package Entities;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    private Class aClass = Class.forName("com.mysql.jdbc.Driver");
    public Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","Dmk32@dad");//cria conex�o com o servidor
    private Statement s= Conn.createStatement();
    private String dataBase;

    SQL sql = new SQL();

    public DataBase() throws ClassNotFoundException, SQLException {}

    public void setDataBase(String dataBase) {
        this.dataBase = dataBase;
    }
    public String getDataBase(){
        return this.dataBase;
    }
    public void Initialize() throws SQLException {
        String database = getDataBase();
        ArrayList<String> list = new ArrayList<>();
        DatabaseMetaData meta = Conn.getMetaData();//Seleciona todas as bases de dados do servidor
        ResultSet rs = meta.getCatalogs(); //faz um ponteiro para cada database
        while (rs.next()) { //enquanto houverem databases
            String listofDatabases=rs.getString("TABLE_CAT"); //resgata o nome da database
            list.add(listofDatabases); //adiciona a uma lista
        }
        if(list.contains(database)){ //se a base de dados 'teste' j� existir
            sql.DropDB(database);
            sql.CreateDB(database);
            System.out.println("DataBase Criada!!");
        }
        else{
            sql.CreateDB(database);
            System.out.println("DataBase Criada!!");//cria uma nova database de nome definido pelo usuario
        }
        rs.close();//fecha conex�o com a lista de bases de dados
    }
}
