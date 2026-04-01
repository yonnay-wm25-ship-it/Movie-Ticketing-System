package model;

public abstract class User {
    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // login method
    public boolean login(String inputUser, String inputPass) {
        return this.username.equals(inputUser) && this.password.equals(inputPass);
    }

    // get the upside
    public String getRole() { return role; }
    
    public String getUsername() { return username; }

    // Adding a generic method that Staff and Admin will change (Override)
    public void displayMenu() {
        System.out.println("Welcome, " + username + ". Access level: " + role);
    }
}
