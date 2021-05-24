package model;

package com.europea.listphy.model;

import com.europea.listphy.controller.UserController;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author crisd
 */
public class User {
    
    private static int totalUsersCreated;
    
    private int id;
    private String usuario;
    private String password;
    private UserType type;
    private String email;
    private String name;
    private String lastName;
    private Date creationDate;
    private List<UserList> lists;

    public User(String name, String lastName, String usuario, String password, UserType type, String email) {
       
        this.id = ++totalUsersCreated;
        this.name = name;
        this.lastName = lastName;
        this.usuario = usuario;
        this.password = password;
        this.type = type;
        this.email = email;
        this.creationDate = new Date();
        this.lists = new ArrayList<UserList>();
    }
    
    public User(String usuario, String password){
        this.id = ++totalUsersCreated;
        this.usuario = usuario;
        this.password = password;
        this.type = UserType.ADMIN;
        this.creationDate = new Date();
        this.lists = new ArrayList<UserList>();
    }
    
    public User(){
    }
    
    //traduce Json a una instancia de usuario
    public User(JsonObject jUser){
        this.lists = new ArrayList();
        this.id = jUser.get("id").getAsInt();
        if (this.id>totalUsersCreated){
            totalUsersCreated = this.id; //actualiza valor de los id de users
        }
        this.name = jUser.get("name").getAsString();
        this.lastName = jUser.get("lastName").getAsString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
        try {
            this.creationDate = formatter.parse(jUser.get("creationDate").getAsString());
        } catch (ParseException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.usuario = jUser.get("usuario").getAsString();
        this.password = jUser.get("password").getAsString();
        String tipo = jUser.get("type").getAsString();
        if (tipo.equals("ADMIN")){
            this.type = UserType.ADMIN;
        } else {
            this.type = UserType.USUARIO;
        }
        this.email = jUser.get("email").getAsString();
        JsonArray jLists = jUser.get("lists").getAsJsonArray();
        for (JsonElement je : jLists){
            UserList ul = new UserList(je.getAsJsonObject());
            lists.add(ul);
        }
        
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static int getTotalUsersCreated() {
        return totalUsersCreated;
    }

    public static void setTotalUsersCreated(int totalUsersCreated) {
        User.totalUsersCreated = totalUsersCreated;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public String getFullName() {
        if (type != UserType.ADMIN){
            return name + " " + lastName;
        }else{
           return "Adminstrador";
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public List<UserList> getLists() {
        return lists;
    }

    public void setLists(List<UserList> lists) {
        this.lists = lists;
    }
    
    public boolean addList(UserList list){
        int index=-1;
        for(int i = 0; i < this.lists.size(); i++){
            if (list.getId() == this.lists.get(i).getId()){
                index = i;
                break;
            }
        }

        if (index>0) { // lo encontre
            this.lists.set(index, list);
        } else{
            lists.add(list);
        } 
        UserController.getInstance().saveUsers();
        return true;
    }
    
    public boolean removeList(UserList list){
        lists.remove(list);
        return true;
    }

    @Override
    public String toString() {
        return 	"id: " + id + "\ntype: " + type + "\nusername: " + usuario + "\npassword: " + password + "\nemail: "
                        + email + "\nname: " + name + "\nlastName: " + lastName + "\ncreationDate: " + creationDate + "\nlists: "
                        + lists;
    }
    
    //crear objeto Json para poder guardar
    //traduccion de objeto a json
    public JsonObject toJson(){
        JsonObject j = new JsonObject();
        j.addProperty("id", this.id);
        j.addProperty("type", this.type.toString());
        j.addProperty("usuario", this.usuario);
        j.addProperty("password", this.password);
        j.addProperty("email", this.email);
        j.addProperty("name", this.name);
        j.addProperty("lastName", this.lastName);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
        String strDate= formatter.format(this.creationDate);  
        j.addProperty("creationDate", strDate);
        JsonArray ja = new JsonArray();
            for (UserList ul:this.lists){
                ja.add(ul.toJson());
            }
            j.add("lists", ja);
        return j;
        
    }
    
    public List<UserList> getCompleted(){
        List<UserList> aux = new ArrayList();
        for(UserList ul:this.lists){
            if (ul.isCompleted()){
                aux.add(ul);
            }
        }
        return aux;
    }
    
    public List<UserList> getFavourites(){
        List<UserList> aux = new ArrayList();
        for(UserList ul:this.lists){
            if (ul.isFavourite()){
                aux.add(ul);
            }
        }
        return aux;
    }
    
    public List<UserList> getNotCompleted(){
        List<UserList> aux = new ArrayList();
        for(UserList ul:this.lists){
            if (!ul.isCompleted()){
                aux.add(ul);
            }
        }
        return aux;
    }
}
