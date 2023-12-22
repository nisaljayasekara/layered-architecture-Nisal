package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.ItemDAO;
import com.example.layeredarchitecture.dao.custom.impl.CrudUtil;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public class ItemDaoImpl implements ItemDAO {


    public ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rst = CrudUtil.executeUpdate("SELECT * FROM Item");

        ArrayList<ItemDTO> dtoList = new ArrayList<>();
        while (rst.next()) {
            dtoList.add(new ItemDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getBigDecimal(3),
                    rst.getInt(4)
            ));
        }
        return dtoList;
    }

    public boolean delete(String code) throws SQLException, ClassNotFoundException {

        return CrudUtil.executeUpdate("DELETE FROM Item WHERE code=?",code);
    }


    public boolean save(ItemDTO dto) throws SQLException, ClassNotFoundException {

        return CrudUtil.executeUpdate("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",dto.getCode(), dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand());
    }

    public boolean update(ItemDTO dto) throws SQLException, ClassNotFoundException {

        return CrudUtil.executeUpdate("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?", dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand(), dto.getCode());
    }

    public boolean exist(String code) throws SQLException, ClassNotFoundException {

        ResultSet rst = CrudUtil.executeUpdate("SELECT code FROM Item WHERE code=?",code);
        return rst.next();

    }


    public String generateId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = CrudUtil.executeUpdate("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }

    public ItemDTO search(String s) throws SQLException, ClassNotFoundException {

        ResultSet rst =CrudUtil.executeUpdate("SELECT * FROM Item WHERE code=?",s);
        rst.next();
        return new ItemDTO(rst.getString(1),rst.getString(2),rst.getBigDecimal(3),rst.getInt(4));
    }
}
