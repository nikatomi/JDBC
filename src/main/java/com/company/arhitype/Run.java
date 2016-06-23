package com.company.arhitype;


        import com.company.arhitype.presistens.model.User;
        import com.google.inject.Guice;
        import com.google.inject.Inject;
        import com.google.inject.Injector;

        import java.sql.SQLException;
        import java.util.List;

public class Run {
    public static void main(String[] args) throws SQLException {
//        DataSource dataSource = new DataSource();
//        ConnectionManager connectionManager = new ConnectionManager(dataSource);
//        UserDAO userDAO = new UserImplDAO(connectionManager);

//        List<User> userList = userDAO.getAll();
//        for(User user: userList){
//            System.out.println(user);
//        }
        Injector injector = Guice.createInjector(new BillingModule());
        UserDAO userDAO = injector.getInstance(UserDAO.class);


        // add user
        User user = new User(3,"Igor","777");
        userDAO.save(user);

        show(userDAO);

        // find user by ID
        System.out.println("ID - "+userDAO.findById(1));

        // find use by NAME
        System.out.println("NAME - "+userDAO.findByName("Igor"));

        // update user
        User u = userDAO.findById(2);
        u.setName("Chack Noriss");
        userDAO.update(u);

        show(userDAO);

        //delete user
        User userDelete = userDAO.findByName("Igor");
        if(userDelete != null){
            userDAO.remove(userDelete);
        }else{
            System.out.println("user not found");
        }

        show(userDAO);
    }

    public static void show(UserDAO userDAO) throws SQLException {
        List<User> userList = userDAO.getAll();
        for(User users:userList){
            System.out.println(users);
        }
    }
}
