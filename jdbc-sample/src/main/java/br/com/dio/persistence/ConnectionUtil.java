package br.com.dio.persistence;

import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ConnectionUtil {

    public static Connection getConnection() throws SQLException {
        // Busca as credenciais das variáveis de ambiente do sistema
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        // Fallback para o padrão caso as variáveis não estejam configuradas localmente
        if (url == null) url = "jdbc:mysql://localhost:3306/jdbc-sample";
        if (user == null) user = "developer";

        return DriverManager.getConnection(url, user, password);
    }

}