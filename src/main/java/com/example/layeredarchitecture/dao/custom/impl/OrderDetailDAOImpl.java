package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.OrderDetailDAO;
import com.example.layeredarchitecture.dao.custom.impl.CrudUtil;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    public boolean saveOrderDetail(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO OrderDetails VALUES (?,?,?,?)", dto.getOrderId(), dto.getItemCode(), dto.getQty(), dto.getUnitPrice());
    }

}
