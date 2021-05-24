package controller;

import com.europea.listphy.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author crisd
 */
public class UserController {
    
    private static UserController instance;
    
    private List<User> usuarios;
    
    private Gson gson;
    
    private String baseDir;
    
    private String adminUser = "admin";
    
    private String adminPassword = "admin1234";
    
    private boolean isAdmin = false;
    
    private User logedUser;

    private UserController() {
        usuarios = new ArrayList();
        baseDir = System.getProperty("user.dir"); //esto me coge donde estoy
        gson = new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss").create();
        loadUsers();
        User.setTotalUsersCreated(usuarios.size());
    }
    
    public static UserController getInstance() {
        if (instance==null){
            instance = new UserController();
        }
        return instance;
    }

    public User getLogedUser() {
        return logedUser;
    }

    public void setLogedUser(User logedUser) {
        this.logedUser = logedUser;
    }
    
    
    
    public void saveUsers (){
        JsonArray jUsers = new JsonArray();
        for (User u: usuarios){ //u es de tipo user y lo convierto en json porque es lo que admite
            jUsers.add(u.toJson());
        }
        try {
            FileWriter fw = new FileWriter(this.baseDir + "\\resources\\users.json");
            gson.toJson(jUsers, fw);
            fw.flush(); //escribe
            fw.close(); //cierra
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
    public boolean addUser (User usuario){
        List<User> users = new ArrayList();
        for (User u: usuarios){
            if (usuario.getUsuario().equals(u.getUsuario())){
                return false;
            } else {
                users.add(u);
            }
        }
        users.add(usuario);
        this.usuarios = users;
        saveUsers();
        return true;
    }
    
    public void loadUsers(){
        this.usuarios = new ArrayList();
        try {
            JsonArray jArrayData = gson.fromJson(new FileReader(this.baseDir + "\\resources\\users.json"), JsonArray.class);
            
            for (JsonElement je : jArrayData) {
                User user = new User(je.getAsJsonObject());
                usuarios.add(user); //en este momento los usuarios estan cargados
            }
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public User doLogin(String user, String password){
        if (user.equals(adminUser)&& password.equals(adminPassword)){
            isAdmin=true;
            User u = new User(adminUser, adminPassword);
            logedUser = u ;
            return u;
        }
        
        for (User u: this.usuarios){
            if (user.equals(u.getUsuario())&& password.equals(u.getPassword())){
                logedUser = u;
                return u;
            }
        }
        return null;
    }
}