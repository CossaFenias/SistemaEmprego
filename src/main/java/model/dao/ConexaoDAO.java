package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/sistema_emprego";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";
    private Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver do banco de dados não encontrado: " + e.getMessage());
            throw new RuntimeException("Erro ao carregar o driver do banco de dados.", e);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            throw new RuntimeException("Erro de conexão com o banco de dados.", e);
        }
    }
}
