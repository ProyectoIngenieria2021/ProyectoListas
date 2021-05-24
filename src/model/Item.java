package model;

import com.google.gson.JsonObject;

public class Item {
	
	private static int totalItemsCreated;


	private int id;
	private String name;
	private String description;
	
	public Item() {
		// TODO Auto-generated constructor stub
	}

	public Item(String name, String description) {
		super();
		
		totalItemsCreated++;
		
		this.id = totalItemsCreated;
		this.name = name;
		this.description = description;
	}
        
        public Item(JsonObject jItem) {
            this.id = jItem.get("id").getAsInt();
            if (this.id>totalItemsCreated){
                totalItemsCreated = this.id; //actualiza valor de los id de items
            }
            this.name = jItem.get("name").getAsString();
            this.description = jItem.get("description").getAsString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	public static int getTotalItemsCreated() {
		return totalItemsCreated;
	}

	public static void setTotalItemsCreated(int totalItemsCreated) {
		Item.totalItemsCreated = totalItemsCreated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void loadTotalItemsCreated(int total) {
		
		totalItemsCreated = total;
	}

	@Override
	public String toString() {
		return "name: " + name + "\ndescription: " + description;
	}
        
        public JsonObject toJson(){
            JsonObject j = new JsonObject();
            j.addProperty("id", this.id);
            j.addProperty("name", this.name);
            j.addProperty("descripcion", this.description);
            return j;
        }
	
	
}