package main.java.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UserService {

    // SECURITY ISSUE: Hardcoded credentials
    private String password = "admin123";

    // VULNERABILITY: SQL Injection
    public void findUser(String username) throws Exception {

        try (Connection conn =
            DriverManager.getConnection("jdbc:mysql://localhost/db",
                    "root", password);
             PreparedStatement pstmt = conn.prepareStatement("SELECT id, name, email FROM users WHERE name = ?")) {
            
            pstmt.setString(1, username);
            pstmt.executeQuery();
        }
    }

    // SMELL: Unused method
    public void notUsed() {
        System.out.println("I am never called");
    }

    // EVEN WORSE: another SQL injection 
public void deleteUser(String username) throws Exception { 
try (Connection conn = 
DriverManager.getConnection("jdbc:mysql://localhost/db", 
"root", password);
     PreparedStatement pstmt = conn.prepareStatement("DELETE FROM users WHERE name = ?")) {
    
    pstmt.setString(1, username);
    pstmt.execute();
}
} 
}
