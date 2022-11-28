import Entities.DataBase;
import Entities.SQL;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Scanner;

public class PrincialOOP {
    private static Scanner sc = new Scanner(System.in);
    private static ResultSet rs;
    public static void main(String[] args) throws Exception {
        SQL SQL = new SQL();
        DataBase db = new DataBase();
        System.out.print("Insira o nome da base de dados que você deseja iniciar: ");
        db.setDataBase(sc.next());
        db.Initialize();
        SQL.CreateTable(db.getDataBase());
        SQL.Insert(db.getDataBase());
        System.out.println("Informe o campo e valor que você quer buscar (separados por virgula): ");
        String splitt[] = sc.next().split(",");
        SQL.Select("Alunos", splitt[0], splitt[1]);
        rs = SQL.Select("Alunos");//criando ponteiro para a tabela
        System.out.println("Informe o cpf do aluno que você quer deletar: ");
    }
}
