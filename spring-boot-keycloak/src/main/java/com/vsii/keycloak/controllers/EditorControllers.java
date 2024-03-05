package com.vsii.keycloak.controllers;

import com.vsii.keycloak.entities.Customer;
import com.vsii.keycloak.repositories.CustomerRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/v3/editor/project")
public class EditorControllers {

    @Autowired
    private CustomerRepositories customerDAO;

    @GetMapping("/{projectId}")
    public String getProjectById(Principal principal, Model model) {
        final DefaultOidcUser user = (DefaultOidcUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        String userId = "";
        OidcIdToken token = user.getIdToken();
        Map<String, Object> customClaims = token.getClaims();
        System.out.println("customClaims: " + customClaims);
        if (customClaims.containsKey("sub")) {
            userId = String.valueOf(customClaims.get("sub"));
            System.out.println("userId: " + userId);
        } else {
            System.out.println("userId not found");
        }


        Customer customer1 = new Customer();
        customer1.setAddress("Project 1");
        customer1.setName("Facebook");
        customer1.setServiceRendered("Important services");
        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        model.addAttribute("customers", customers);
        model.addAttribute("username", principal.getName());
        return "ProjectID";
    }

    @GetMapping
    public String customers(Principal principal, Model model) {
        Customer customer1 = new Customer();
        customer1.setAddress("Project 1");
        customer1.setName("Facebook");
        customer1.setServiceRendered("Important services");


        Customer customer2 = new Customer();
        customer2.setAddress("Project 2");
        customer2.setName("tiktok");
        customer2.setServiceRendered("Important services");

        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);
        model.addAttribute("customers", customers);
        model.addAttribute("username", principal.getName());
        return "Project";
    }

    // add customers for demonstration
    public void addCustomers() {
        Customer customer1 = new Customer();
        customer1.setAddress("Project 1");
        customer1.setName("Facebook");
        customer1.setServiceRendered("Important services");
        customerDAO.save(customer1);

        Customer customer2 = new Customer();
        customer2.setAddress("Project 2");
        customer2.setName("Tiktok");
        customer2.setServiceRendered("Important services");
        customerDAO.save(customer2);

        Customer customer3 = new Customer();
        customer2.setAddress("Project 3");
        customer3.setName("Big LLC");
        customer3.setServiceRendered("Important services");
        customerDAO.save(customer3);
    }
}
