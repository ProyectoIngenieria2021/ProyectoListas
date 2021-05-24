package model;

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

public class UserList {
	
	private static int totalListCreated;

	private int id;
	private String name;
        private String description;
	private Date creationDate;
	private boolean favourite;
        private boolean completed;
	private String type;
	private String icon;
	private List<Item> itemList;
	
	public UserList() {
                this.id = ++totalListCreated;
                this.itemList = new ArrayList<Item>();
		this.creationDate = new Date();
                
	}

	public UserList(String name, String description, String icon) {
		super();
		totalListCreated++;
		this.id = totalListCreated;
		this.name = name;
                this.description = description;
		this.icon = icon;
		this.itemList = new ArrayList<Item>();
		this.creationDate = new Date();
		
	}
        
        public UserList(JsonObject jUserList) {
            this.itemList = new ArrayList();
            this.id = jUserList.get("id").getAsInt();
            if (this.id>totalListCreated){
                totalListCreated = this.id; //actualiza valor de los id de userlist
            }
            this.name = jUserList.get("name").getAsString();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
            try {
                this.creationDate = formatter.parse(jUserList.get("creationDate").getAsString());
            } catch (ParseException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.favourite = jUserList.get("favourite").getAsBoolean();
            this.icon = jUserList.get("icon").getAsString();
            this.completed = jUserList.get("completed").getAsBoolean();
            this.description = jUserList.get("description").getAsString();
            JsonArray jItemList = jUserList.get("itemList").getAsJsonArray();
            for (JsonElement je : jItemList){
                Item i = new Item(je.getAsJsonObject());
                itemList.add(i);
            }
        }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public boolean isFavourite() {
		return favourite;
	}

	public void setFavourite(boolean favourite) {
		this.favourite = favourite;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	
	public boolean addItem(Item item) {
		
		itemList.add(item);
		
		return true;
	}
	
	
	public static int getTotalListCreated() {
		return totalListCreated;
	}

	public static void setTotalListCreated(int totalListCreated) {
		UserList.totalListCreated = totalListCreated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void loadTotalListsCreated(int total) {
		
		totalListCreated = total;
	}

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
        
	
	
	@Override
	public String toString() {
		return "\nname: " + name + "\ncreationDate: " + creationDate + "\nfavourite: " + favourite + "\ntype: "
				+ type + "\nicon: " + icon + "\nitemList: " + itemList + "\nisCompleted: " + completed + "\ndescription: " + description;
	}
	
        public JsonObject toJson(){
            JsonObject j = new JsonObject();
            j.addProperty("id", this.id);
            j.addProperty("name", this.name);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
            String strDate= formatter.format(this.creationDate);  
            j.addProperty("creationDate", strDate);
            j.addProperty("favourite", this.favourite);
            j.addProperty("type", this.type);
            j.addProperty("icon", this.icon);
            JsonArray ja = new JsonArray();
            for (Item i:this.itemList){
                ja.add(i.toJson());
            }
            j.add("itemList", ja);
            j.addProperty("completed", this.completed);
            j.addProperty("description", this.description);
            return j;
        }
	
}
