    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package Model;

    import javafx.beans.property.SimpleIntegerProperty;
    import javafx.beans.property.SimpleStringProperty;

    /**
     *
     * @author Adittya
     */
    public class table4 {
        private final SimpleStringProperty name;
            private final SimpleStringProperty email;
        private final SimpleStringProperty dname;
        private final SimpleIntegerProperty id;
        private final SimpleStringProperty rm;

        public table4(String name, String email, String dname, Integer id, String rm ) {
            this.name = new SimpleStringProperty(name);
            this.email = new SimpleStringProperty(email);
            this.dname = new SimpleStringProperty (dname);
            this.id = new SimpleIntegerProperty(id);
            this.rm = new SimpleStringProperty(rm);
        }

        //getters

        public String getName() {
            return name.get();
        }

        public String getEmail() {
            return email.get();
        }

        public String getDname() {
            return dname.get();
        }

        public Integer getId() {
            return id.get();
        }

        public String getRm() {
            return rm.get();
        }

        //setters

        public void setName(String n)
        {
        name.set(n);
        }
        public void setDname(String d)
        {
           dname.set(d);
        }
        public void setEmail(String e)
        {
            email.set(e);
        }
        public void setId(int i)
        {
            id.set(i);
        }
        public void setRm(String r)
        {
            rm.set(r);
        }



    }
