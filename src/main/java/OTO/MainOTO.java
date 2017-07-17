package OTO;

import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by azhilin on 26.01.2017.
 */
public class MainOTO {
    public static void main(String[] args) {
        SessionFactory sessionFactory = (SessionFactory) Persistence.createEntityManagerFactory("org.hibernate.jpa");
        EntityManager entityManager = sessionFactory.createEntityManager();

        entityManager.getTransaction().begin();

        MyClient client1 = new MyClient();
        client1.setName("Vasia");
        client1.setSurname("Pupkin");

        MyClient client2 = new MyClient();
        client2.setName("Petya");
        client2.setSurname("Petrov");

        Address adr1 = new Address();
        adr1.setMyClient(client1);
        adr1.setClientAddress("Kiev");

        Address adr2 = new Address();
        adr2.setMyClient(client2);
        adr2.setClientAddress("Kharkov");

        entityManager.persist(adr1);
        entityManager.persist(adr2);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();

        client1.setAddress(adr1);
        client2.setAddress(adr2);

        entityManager.persist(client1);
        entityManager.persist(client2);

        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();

        MyClient client3 = entityManager
                .createQuery("FROM MyClient c WHERE name=:client_name", MyClient.class)
                .setParameter("client_name", "Vasia")
                .getSingleResult();

        entityManager.getTransaction().commit();

        System.out.println(client3.getName());
        System.out.println(client3.getAddress().getClientAddress());

        entityManager.getTransaction().begin();

        Address addr1 = entityManager
                .createQuery("FROM Address c WHERE client_address=:address", Address.class)
                .setParameter("address", "Kiev")
                .getSingleResult();

        entityManager.getTransaction().commit();

        System.out.println(addr1.getClientAddress());
        System.out.println(addr1.getMyClient().getName());

        entityManager.close();
        sessionFactory.close();
    }
}
