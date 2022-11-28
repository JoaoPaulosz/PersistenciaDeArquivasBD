package Entities;

import java.sql.*;
import java.util.Scanner;
public class SQL {
    private Scanner leia = new Scanner(System.in);
    private Class aClass = Class.forName("com.mysql.jdbc.Driver");
    public Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","Dmk32@dad");//cria conex�o com o servidor
    private Statement s= Conn.createStatement();
    private DataBase db;

    private String query = null;

    public SQL() throws SQLException, ClassNotFoundException {
    }
    public void DropDB(String database) throws SQLException{
        s.executeUpdate("DROP DATABASE " + database);
        System.out.println("Database Droped");
    }
    public void CreateDB(String database) throws SQLException{
        s.executeUpdate("CREATE DATABASE " + database);
        System.out.println("Database Criada");
    }
    public String ClearSQL(){
        query = new String();
        return query;
    }
    public ResultSet Select(String table) throws SQLException {
        ClearSQL();
        query = "SELECT * FROM " + table;
        return s.executeQuery(query);

    }
    public void Select(String table, String campo, String valor) throws SQLException {
        ClearSQL();
        query = "SELECT * FROM " + table + " WHERE " + campo + " = " + valor;
        ResultSet rs = s.executeQuery(query);
        while (rs.next()){

        }

    }
    public void CreateTable(String database) throws SQLException {
        ClearSQL();
        Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database,"root","Dmk32@dad");//cria conex�o com a base de dados criada
        s= Conn.createStatement(); //cria objeto Statement
        System.out.println("Conectado � base de dados "+database);
        query =  "CREATE TABLE Alunos (nome varchar(50),curso varchar(50),fase int,cpf varchar(50) PRIMARY KEY)";
        s.executeUpdate(query);
    }
    public void Insert(String database) throws SQLException {
        Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ database,"root","Dmk32@dad");//cria conex�o com a base de dados criada
        ClearSQL();
        String tabela = "Alunos";
        String valores = "";
        String campos = "";
        ResultSet rset = Select(tabela);
        ResultSetMetaData rsmd = rset.getMetaData(); //recupera informacoes da tabela

        // retorna o numero total de colunas
        int numColumns = rsmd.getColumnCount();
        System.out.println("Total de Colunas = " + numColumns);

        // loop para recuperar os metadados de cada coluna (nome da coluna, tipo do campo, etc)
        for (int i=0; i<numColumns; i++) {

            System.out.print("Insira o dado para a coluna " + rsmd.getColumnName (i + 1)+": ");

            if((rsmd.getColumnTypeName (i + 1)).equals("INT")){ //se o campo for INT
                if(i!=numColumns-1){ //se n�o for a �ltima coluna
                    valores = valores+leia.next()+",";//concatena apenas uma virgula(tipo INT nao precisa de aspas)
                    campos = campos+rsmd.getColumnName (i + 1)+",";//recupera nome do campo (coluna) e insere uma virgula
                }
                else{//se for a ultima coluna
                    valores = valores+leia.next();//le o dado
                    campos = campos+rsmd.getColumnName (i + 1);//recupera nome da coluna
                }
            }else{ //se nao for INT
                if(i!=numColumns-1){//se n�o for a ultima coluna
                    valores = valores+"'"+leia.nextLine()+"',"; //concatena uma aspa simples seguida de virgula
                    campos = campos+rsmd.getColumnName (i + 1)+",";//recupera nome da coluna e insere virgula
                }
                else{ //se for ultimo valor
                    valores = valores+"'"+leia.next()+"'"; //concatena somente aspas simples, sem virgula
                    campos = campos+rsmd.getColumnName (i + 1);//recupera nome da coluna
                }
            }
        }
        query = "INSERT INTO "+tabela+" ("+campos+") VALUES ("+valores+")";//INSERT
        System.out.println(query);//'DEBUG'
        s.executeUpdate(query);

    }

    public void Delete(String database,String talbe, String value) throws SQLException{
        ClearSQL();
        Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ database,"root","Dmk32@dad");//cria conex�o com a base de dados criada
        query = "DELETE FROM " + talbe + " WHERE " + "CPF" + " = " + value;
        s.executeUpdate(query);
    }
}
