package Models.TransferObjects;

import java.io.Serializable;
import java.util.Arrays;

public class Animal implements Serializable {
     private static final long serialVersionUID = -8069518949878166160L;

     private String ID;
     private String name;
     private Type type;
     private boolean state;
     private Property[] properties;

     public Animal() {
          state = true;
     }

     public Animal(String iD, String name, Type type, Property... properties) {
          this();
          ID = iD;
          this.name = name;
          this.type = type;
          this.properties = properties;
     }

     public Animal(String iD, String name, Type type, boolean state, Property... properties) {
          ID = iD;
          this.name = name;
          this.type = type;
          this.properties = properties;
          this.state = state;
     }

     @Override
     public String toString() {
          String propertise = "";
          for (Property property : type.getPropertise()) {
               propertise += property.toString() + ", ";
          }
          for (Property property : this.properties) {
               propertise += property.toString() + ", ";
          }
          return "ID=" + ID
                    + ", Name=" + name
                    + ", Type=" + type.getKey()
                    + ", Property=" + propertise
                    + "State=" + (state ? "Alive" : "Death");
     }

     public String getID() {
          return ID;
     }

     public void setID(String iD) {
          ID = iD;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public Type getType() {
          return type;
     }

     public void setType(Type type) {
          this.type = type;
     }

     public Property[] getProperties() {
          return properties;
     }

     public void setProperties(Property... property) {
          this.properties = property;
     }

     public boolean getState() {
          return state;
     }

     public void setState(boolean state) {
          this.state = state;
     }

     public Animal addProperty(Property property) {
          Property[] nProperties = new Property[this.properties.length + 1];
          if (this.properties.length > 0) {
               System.arraycopy(this.properties, 0, nProperties, 0, 1);
          }
          this.properties = nProperties;
          this.properties[this.properties.length - 1] = property;
          return this;
     }

     @Override
     public int hashCode() {
          final int prime = 31;
          int result = 1;
          result = prime * result + ((ID == null) ? 0 : ID.hashCode());
          result = prime * result + ((name == null) ? 0 : name.hashCode());
          result = prime * result + ((type == null) ? 0 : type.hashCode());
          result = prime * result + (state ? 1231 : 1237);
          result = prime * result + Arrays.hashCode(properties);
          return result;
     }

     @Override
     public boolean equals(Object obj) {
          if (this == obj) {
               return true;
          }
          if (obj == null) {
               return false;
          }
          if (getClass() != obj.getClass()) {
               return false;
          }
          Animal other = (Animal) obj;
          if (ID == null) {
               if (other.ID != null) {
                    return false;
               }
          } else if (!ID.equalsIgnoreCase(other.ID)) {
               return false;
          }
          if (name == null) {
               if (other.name != null) {
                    return false;
               }
          } else if (!name.equals(other.name)) {
               return false;
          }
          if (type == null) {
               if (other.type != null) {
                    return false;
               }
          } else if (!type.equals(other.type)) {
               return false;
          }
          if (state != other.state) {
               return false;
          }
          if (!Arrays.equals(properties, other.properties)) {
               return false;
          }
          return true;
     }
}
