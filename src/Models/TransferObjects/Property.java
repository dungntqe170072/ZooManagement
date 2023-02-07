package Models.TransferObjects;

import java.io.Serializable;
import java.util.Arrays;

public class Property implements Serializable {
     // private static final long serialVersionUID = 4622532070391995400L;
     private static final long serialVersionUID = -6237419739767494322L;

     private String key;
     private String[] values;

     public Property() {
     }

     public Property(String key, String... values) {
          if (key.equals("") || key == null) {
               throw new RuntimeException("Key has not been empty");
          }
          this.key = key;
          this.values = values;
     }

     @Override
     public String toString() {
          String values = "";
          if (this.values == null || this.values.length == 0)
               return "";
          for (String value : this.values) {
               values += "\"" + value + "\", ";
          }
          return ("[" + key + "]: " + values.subSequence(0, values.length() - 2));
     }

     public String getKey() {
          return key;
     }

     public void setKey(String key) {
          this.key = key;
     }

     public String[] getValues() {
          return values;
     }

     public void setValues(String... value) {
          this.values = value;
     }

     public void set(String key, String... value) {
          setKey(key);
          setValues(value);
     }

     public Property set(String key, String value) {
          setKey(key);
          setValues(value);
          return this;
     }

     @Override
     public int hashCode() {
          final int prime = 31;
          int result = 1;
          result = prime * result + ((key == null) ? 0 : key.hashCode());
          result = prime * result + Arrays.hashCode(values);
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
          Property other = (Property) obj;
          if (key == null) {
               if (other.key != null) {
                    return false;
               }
          } else if (!key.equals(other.key)) {
               return false;
          }
          if (!Arrays.equals(values, other.values)) {
               return false;
          }
          return true;
     }
}
