package org.jdbc1;

import org.jdbc1.model.dao.DaoFactory;
import org.jdbc1.model.dao.DepartmentDao;
import org.jdbc1.model.dao.SellerDao;
import org.jdbc1.model.entities.Department;
import org.jdbc1.model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainDepartment {
    static void main() {
        Scanner sc = new Scanner(System.in);

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("Department - Insert");
        Department newDepartment = new Department(null, "Food");
        departmentDao.insert(newDepartment);
        System.out.println("Registrado! Novo id: " + newDepartment.getId());
        System.out.println();

        System.out.println();
        System.out.println("Seller - findByID");
        Department department = departmentDao.findById(3);
        System.out.println(department);
        System.out.println();

        System.out.println();
        System.out.println("Department - findAll");
        List<Department> all = departmentDao.findAll();
        for (Department s : all){
            System.out.println(s);
        }
        System.out.println();

        System.out.println();
        System.out.println("Department - Update");
        department = departmentDao.findById(1);
        department.setDepName("Martha Waine");
        departmentDao.update(department);
        System.out.println("Atualização realizada");
        System.out.println();

        System.out.println();
        System.out.println("Department - Delete");
        System.out.print("Informe o ID para apagar da base: ");
        int id = sc.nextInt();
        departmentDao.deleteById(id);
        System.out.println("Apagado com sucesso!");

        sc.close();
    }
}
