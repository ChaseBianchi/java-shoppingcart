package com.lambdaschool.shoppingcart.services;

import com.lambdaschool.shoppingcart.ShoppingCartTestApplication;
import com.lambdaschool.shoppingcart.models.*;
import com.lambdaschool.shoppingcart.repository.CartItemRepository;
import com.lambdaschool.shoppingcart.repository.ProductRepository;
import com.lambdaschool.shoppingcart.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingCartTestApplication.class, properties = {"command.line.runner.enabled=false"})
public class CartItemServiceImplUnitTestNoDB {

    @Autowired
    CartItemService cartitemserv;

    @MockBean
    private UserRepository userrepos;

    @MockBean
    private ProductRepository prodrepos;

    @MockBean
    private CartItemRepository cartitemrepos;

    private List<User> userList = new ArrayList<>();

    private List<Product> productList = new ArrayList<>();


    @Before
    public void setUp() throws Exception {
        // Adding users
        Role r1 = new Role("admin");
        Role r2 = new Role("user");

        r1.setRoleid(1);
        r2.setRoleid(2);

        User u1 = new User("barnbarn",
                "LambdaLlama",
                "barnbarn@host.local",
                "added via seed data");
        u1.setUserid(1);
        u1.getRoles()
                .add(new UserRoles(u1,
                        r1));
        u1.getRoles()
                .add(new UserRoles(u1,
                        r2));


        User u2 = new User("cinnamon",
                "LambdaLlama",
                "cinnamon@host.local",
                "added via seed data");
        u2.setUserid(2);
        u2.getRoles()
                .add(new UserRoles(u2,
                        r2));


        User u3 = new User("stumps",
                "LambdaLlama",
                "stumps@host.local",
                "added via seed data");
        u3.setUserid(3);
        u3.getRoles()
                .add(new UserRoles(u3,
                        r2));

        // Adding Products

        Product p1 = new Product();
        p1.setName("PEN");
        p1.setDescription("MAKES WORDS");
        p1.setPrice(2.50);
        p1.setComments("added via seed data");
        p1.setProductid(1);

        Product p2 = new Product();
        p2.setName("PENCIL");
        p2.setDescription("DOES MATH");
        p2.setPrice(1.50);
        p2.setComments("added via seed data");
        p2.setProductid(2);

        Product p3 = new Product();
        p3.setName("COFFEE");
        p3.setDescription("EVERYONE NEEDS COFFEE");
        p3.setPrice(4.00);
        p3.setComments("added via seed data");
        p3.setProductid(3);

        productList.add(p1);
        productList.add(p2);
        productList.add(p3);

        // Creating Carts
        CartItem c1 = new CartItem();
        c1.setComments("blah1");
        c1.setQuantity(1);
        c1.setProduct(p1);
        c1.setUser(u1);
        u1.getCarts().add(c1);

        CartItem c2 = new CartItem();
        c1.setComments("blah2");
        c1.setQuantity(2);
        c1.setProduct(p2);
        c1.setUser(u2);
        u1.getCarts().add(c2);

        CartItem c3 = new CartItem();
        c1.setComments("blah3");
        c1.setQuantity(3);
        c1.setProduct(p3);
        c1.setUser(u3);
        u1.getCarts().add(c3);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addToCart() {
    }

    @Test
    public void removeFromCart() {
    }
}