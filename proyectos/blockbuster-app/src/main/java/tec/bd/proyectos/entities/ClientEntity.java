package tec.bd.proyectos.entities;

import java.util.List;

public class ClientEntity extends Entity {

    private String name;
    private String lastname;
    private String email;
    private String phone_number;

    public ClientEntity() {
        super(4, "client", List.of("name", "lastname", "email", "phone_number"));
    }
 
    public ClientEntity(int id, String name, String lastname, String email, String phone_number) {
        super(id, 4, "client", List.of("name", "lastname", "email", "phone_number"));
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone_number = phone_number;
    }

    public ClientEntity(String name, String lastname, String email, String phone_number) {
        super(4, "client", List.of("name", "lastname", "email", "phone_number"));
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone_number = phone_number;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail(){
        return email;
    }

    public String getPhoneNumber(){
        return phone_number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastname){
        this.lastname = lastname;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPhoneNumber(String phone_number){
        this.phone_number = phone_number;
    }

    @Override
    public String serialize() {
        return serializeID() + ", " + advancedSerialize();
    }

    @Override
    public String basicSerialize() {
        return String.format("'%s', '%s', '%s', '%s'", this.name, this.lastname, this.email, this.phone_number);
    }

    @Override
    public String advancedSerialize() {
        return String.format("name = '%s', lastname = '%s', email = '%s', phone_number = '%s'", this.name, this.lastname, this.email, this.phone_number);
    }
}
