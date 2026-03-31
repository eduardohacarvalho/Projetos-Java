package org.jpa;

import org.jpa.dominio.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
        EntityManager em = emf.createEntityManager();

        //Create
        Pessoa p1 = new Pessoa (null, "Carlos da Silva", "carlos@gmail.com");
        Pessoa p3 = new Pessoa (null, "Ana Maria", "ana@gmail.com");
        Pessoa p2 = new Pessoa (null, "Joaquim Torres", "joaquim@gmail.com");

        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.getTransaction().commit();
        System.out.println("Registro salvo");

        //Read
        Pessoa p = em.find(Pessoa.class, 3);
        System.out.println(p);

        //Update
        p = em.find(Pessoa.class, 2);
        em.getTransaction().begin();
        p.setNome("Carlos Silva Atualizado");
        p.setEmail("carlos.novo@gmail.com");
        em.getTransaction().commit();
        System.out.println("ATUALIZADO COM SUCESSO: " + p);


        //Delete
        p = em.find(Pessoa.class, 1);
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
        System.out.println("APAGADO COM SUCESSO: " + p);

        em.close();
        emf.close();
    }
}
