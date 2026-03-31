package org.jdbc1.model.dao;

import org.jdbc1.db.DB;
import org.jdbc1.model.dao.impl.DepartmentDaoJDBC;
import org.jdbc1.model.dao.impl.SellerDaoJDBC;

public class DaoFactory {

    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC(DB.getConnection());
    }

    public static DepartmentDao createDepartmentDao(){
        return new DepartmentDaoJDBC(DB.getConnection());
    }
}
