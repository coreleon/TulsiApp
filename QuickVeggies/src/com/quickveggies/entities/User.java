package com.quickveggies.entities;

import com.quickveggies.controller.SessionDataController;
import com.quickveggies.db.DatabaseClient;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    
    private static User currentUser;
    
    public static User getCurrentUser()
    {
        return currentUser;
    }
    
    public static User registerCurrentUser(Accountant newlyRegisterdAccountant)
    {
        if(currentUser == null)
        {
            currentUser = newlyRegisterdAccountant;
            Company.registerCurrentCompany(newlyRegisterdAccountant);
            SessionDataController.getInstance().setCurrentUser(currentUser);
        }
        return currentUser;
    }
    
    public static User registerCurrentUser(User newlyRegisterdUser)
    {
        try
        {
            if(currentUser == null)
            {
                currentUser = DatabaseClient.getInstance().getUserByName(newlyRegisterdUser.getName());
                Company.registerCurrentCompany(currentUser);
                SessionDataController.getInstance().setCurrentUser(currentUser);
            }
        }
        catch(SQLException exc)
        {
            exc.printStackTrace();
        }
        return currentUser;
    }
    
    public Company worksInACompany() 
    {
        if(this.companyId == -1)
            return null;
        return DatabaseClient.getInstance().getCompanyById(this.companyId);
    }
    
    public Company ownsACompany() 
    {
//        return DatabaseClient.getInstance().getCompanyByFounderId(this.id);
        Company c = Company.getCurrentCompany();
        
        if(c != null && c.getFounderId() == this.id)
            return c;
        return null;
    }
    
    public boolean canCreateCompany() 
    {
        if(!(this instanceof Accountant)) //Must NOT be an Accountant AND must NOT already own a company!
        {
            try
            {
                ResultSet res = DatabaseClient.getInstance().getResult("select * from companyInfo where founderId=" + this.id + ";");
                return !res.next();
            }
            catch(Exception exc)
            {
                exc.printStackTrace();
            }
        }
        // !!! caution !!!
        return false;
    }
    
    public boolean canEditCompany() 
    {
        User loggedIn = User.getCurrentUser();
        
        if(loggedIn != null)
        {
            try
            {
                ResultSet res = DatabaseClient.getInstance().getResult("select * from companyInfo where founderId=" + loggedIn.getId() + ";");
                res.next();
                return res.getInt("founderId") == loggedIn.getId();
            }
            catch(Exception exc)
            {
                exc.printStackTrace();
            }
        }
        return false;
    }
    
    public boolean isAdmin() 
    {
        return this.role == Role.ADMIN;
    }

    private int id;
    private String name;
    private String password;
    private String email;
    private int companyId;  // If the role == USER the companyId represents the companyId which this user is assign to as an Accountant.
    private final Role role;

    public User(String name, String password, String email, int companyId, String role) {
        this.id = -1;
        this.name = name;
        this.password = password;
        this.email = email;
        this.companyId = companyId;
        this.role = Enum.valueOf(Role.class, role);
    }

    public User(int id, String name, String password, String email, int companyId, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.companyId = companyId;
        this.role = Enum.valueOf(Role.class, role);
    }

    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCompanyId() {
        return this.companyId;
    }

    public void setCompany(int companyId) {
        this.companyId = companyId;
    }

    public final Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", companyId=" + companyId +
                ", role=" + role.name() +
                '}';
    }
    
    public static enum Role
    {
        ADMIN,
        USER;
    }
}
