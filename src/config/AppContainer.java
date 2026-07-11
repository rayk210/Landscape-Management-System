/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import dao.CustomerDB;
import dao.CustomerRepository;
import service.CustomerServiceImpl;
import service.CustomerService;
import view.LandscapeGUI;

/**
 *
 * @author rayk2
 */

// Centralized composition root class to wire up dependencies
public class AppContainer {
    
    private final CustomerRepository customerRepository;
    private final CustomerService service;
    private final LandscapeGUI gui;
    
    private AppContainer() {
        this.customerRepository = new CustomerDB();
        this.service = new CustomerServiceImpl(this.customerRepository);
        this.gui = new LandscapeGUI(this.service);
    }
    
    public static void start() {
        AppContainer appContainer = new AppContainer();
        appContainer.gui.setVisible(true);
    }
}
