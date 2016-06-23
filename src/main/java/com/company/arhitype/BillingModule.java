package com.company.arhitype;


import com.google.inject.AbstractModule;

public class BillingModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(UserDAO.class).to(UserImplDAO.class);
    }
}
