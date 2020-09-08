
package Model;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Adittya
 */
public class Semester {
       private final SimpleStringProperty id;
        private final SimpleStringProperty year;
    
        private final SimpleStringProperty session;
 
        public Semester(String idx, String year,String session)
        {
           this.id = new SimpleStringProperty(idx);
           this.year=new SimpleStringProperty(year);
           this.session= new SimpleStringProperty(session);
        }
       
        public String getId() {
            return id.get();
        }
 
        public void setId(String id) {
            this.id.set(id);
        }
 
        public String getYear() {
            return year.get();
        }
 
        public void setYear(String year) {
            this.year.set(year);
        }
        
        public String getSession() {
            return session.get();
        }
 
        public void setSession(String session) {
            this.session.set(session);
        }
}
