package org.jdbc1;

import org.jdbc1.model.dao.DaoFactory;
import org.jdbc1.model.dao.SellerDao;
import org.jdbc1.model.entities.Department;
import org.jdbc1.model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("Seller - findByID");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);
        System.out.println();

        System.out.println();
        System.out.println("Seller - findByDepartment");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);
        for (Seller s : list){
            System.out.println(s);
        }
        System.out.println();

        System.out.println();
        System.out.println("Seller - findAll");
        List<Seller> all = sellerDao.findAll();
        for (Seller s : all){
            System.out.println(s);
        }
        System.out.println();

        System.out.println();
        System.out.println("Seller - Insert");
        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
        sellerDao.insert(newSeller);
        System.out.println("Registrado! Novo id: " + newSeller.getId());
        System.out.println();

        System.out.println();
        System.out.println("Seller - Update");
        seller = sellerDao.findById(1);
        seller.setName("Martha Waine");
        sellerDao.update(seller);
        System.out.println("Atualização realizada");
        System.out.println();

        System.out.println();
        System.out.println("Seller - Delete");
        System.out.print("Informe o ID para apagar da base: ");
        int id = sc.nextInt();
        sellerDao.deleteById(id);
        System.out.println("Apagado com sucesso!");

        sc.close();
    }
}
