package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {


    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeUpdate("SELECT oid FROM Orders ORDER BY oid DESC LIMIT 1");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";

    }

    @Override
    public OrderDTO search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }


    @Override
    public boolean exist(String orderId) throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.executeUpdate("SELECT oid FROM Orders WHERE oid=?",orderId);
        return rst.next();
    }


    @Override
    public ArrayList<OrderDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Orders (oid, date, customerID) VALUES (?,?,?)",dto.getOrderId(),Date.valueOf(dto.getOrderDate()),dto.getCustomerId());
    }

    @Override
    public boolean update(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }


}



