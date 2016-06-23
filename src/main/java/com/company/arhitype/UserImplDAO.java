package com.company.arhitype;

import com.company.arhitype.presistens.model.User;
import com.google.inject.Inject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Uzed on 19.06.2016.
 */
public class UserImplDAO implements UserDAO {


    private final ConnectionManager connectionManager;

    @Inject
    public UserImplDAO(ConnectionManager connectionManager){
        this.connectionManager = connectionManager;
    }

    @Override
    public List<User> getAll() throws SQLException {
        Connection conn = connectionManager.getConnection();
        List<User> users = new ArrayList<>();

        try(Statement st = conn.createStatement()){
            ResultSet rs = st.executeQuery("SELECT * FROM TEST ");
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setPassword(rs.getString(3));
                users.add(user);
            }
        }catch (Throwable e){

        }
        return users;
    }

    @Override
    public void save(User user) {
        Connection conn = connectionManager.getConnection();
        try(PreparedStatement st = conn.prepareStatement("INSERT INTO test(id,name,password) VALUES (?,?,?)")){
            st.setInt(1,user.getId());
            st.setString(2,user.getName());
            st.setString(3,user.getPassword());
            st.executeUpdate();

        }catch (Throwable e){

        }
    }

    @Override
    public void remove(User user) {
        Connection conn = connectionManager.getConnection();
        try(PreparedStatement st = conn.prepareStatement("DELETE FROM test WHERE id = ?")){

            st.setInt(1,user.getId());
            st.executeUpdate();

        }catch (SQLException e){
            System.err.println("Error");
        }
    }

    @Override
    public void update(User user) {
        Connection conn = connectionManager.getConnection();
        try(PreparedStatement st = conn.prepareStatement("UPDATE test SET id = ?,name = ?,password = ? WHERE id = ?")){

            st.setInt(1,user.getId());
            st.setString(2,user.getName());
            st.setString(3,user.getPassword());
            st.setInt(4,user.getId());
            st.executeUpdate();

        }catch (SQLException e){

        }
    }

    @Override
    public User findByName(String name) {
        try {
            List<User> users = getAll();
            for (User user:users){
                if(name.equals(user.getName())){
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findById(int id) {
        try {
            List<User> users = getAll();
            for (User user:users){
                if(id == user.getId()){
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
