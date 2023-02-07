package Models.TransferObjects;

import java.io.Serializable;
import java.util.Arrays;

public class Type implements Serializable {
     private String key;
     private Property[] propertise;

     public Type() {
          throw new RuntimeException("ID has not been empty");
     }

     public Type(String key, Property... propertise) {
          this.key = key;
          this.propertise = propertise;
     }

     public String getKey() {
          return key;
     }

     public void setKey(String key) {
          this.key = key;
     }

     public Property[] getPropertise() {
          return propertise;
     }

     public void setPropertise(Property[] propertise) {
          this.propertise = propertise;
     }

     @Override
     public String toString() {
          return key + ": " + Arrays.toString(propertise);
     }

     @Override
     public int hashCode() {
          final int prime = 31;
          int result = 1;
          result = prime * result + ((key == null) ? 0 : key.hashCode());
          result = prime * result + Arrays.hashCode(propertise);
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
          Type other = (Type) obj;
          if (key == null) {
               if (other.key != null) {
                    return false;
               }
          } else if (!key.equals(other.key)) {
               return false;
          }
          if (!Arrays.equals(propertise, other.propertise)) {
               return false;
          }
          return true;
     }

}
