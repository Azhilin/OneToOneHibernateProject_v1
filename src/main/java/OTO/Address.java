package OTO;

import javax.persistence.*;

/**
 * Created by azhilin on 26.01.2017.
 */
@Entity(name = "my_addresses")
public class Address {
    @Id
    @GeneratedValue
    int id;
    @Column(name = "client_address")
    String clientAddress;

    public Address() {
    }

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    MyClient myClient = new MyClient();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public MyClient getMyClient() {
        return myClient;
    }

    public void setMyClient(MyClient myClient) {
        this.myClient = myClient;
    }
}
