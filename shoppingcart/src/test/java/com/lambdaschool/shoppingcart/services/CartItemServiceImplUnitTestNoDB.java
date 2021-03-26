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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

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

        userList.add(u1);
        userList.add(u2);
        userList.add(u3);

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
        c1.setQuantity(4);
        c1.setProduct(p1);
        c1.setUser(u1);
        u1.getCarts().add(c1);

        CartItem c2 = new CartItem();
        c2.setComments("blah2");
        c2.setQuantity(4);
        c2.setProduct(p2);
        c2.setUser(u2);
        u2.getCarts().add(c2);

        CartItem c3 = new CartItem();
        c3.setComments("blah3");
        c3.setQuantity(4);
        c3.setProduct(p3);
        c3.setUser(u3);
        u3.getCarts().add(c3);

        CartItem c4 = new CartItem();
        c4.setComments("blah4");
        c4.setQuantity(4);
        c4.setProduct(p3);
        c4.setUser(u1);
        u1.getCarts().add(c4);

        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addToCart() {
        CartItemId cartItemId = new CartItemId(1,1);
        CartItem newItem = new CartItem();
        newItem.setComments("blah4");
        newItem.setQuantity(1);
        newItem.setProduct(productList.get(0));
        newItem.setUser(userList.get(0));

        Mockito.when(prodrepos.findById(1L)).thenReturn(Optional.of(productList.get(0)));
        Mockito.when(userrepos.findById(1L)).thenReturn(Optional.of(userList.get(0)));
        Mockito.when(cartitemrepos.findById(any(CartItemId.class))).thenReturn(Optional.of(newItem));
        Mockito.when(cartitemrepos.save(any(CartItem.class))).thenReturn(newItem);

        assertEquals(2, cartitemserv.addToCart(1L, 1L, "Blah5").getQuantity());

    }

    @Test
    public void removeFromCart() {
        CartItemId cartItemId = new CartItemId(1,1);
        CartItem cartItem = new CartItem();
        cartItem.setComments("blah4");
        cartItem.setQuantity(1);
        cartItem.setProduct(productList.get(0));
        cartItem.setUser(userList.get(0));

        Mockito.when(userrepos.findById(1L)).thenReturn(Optional.of(userList.get(0)));
        Mockito.when(prodrepos.findById(1L)).thenReturn(Optional.of(productList.get(0)));
        Mockito.when(cartitemrepos.findById(any(CartItemId.class))).thenReturn(Optional.of(cartItem));
        Mockito.when(cartitemrepos.save(any(CartItem.class))).thenReturn(cartItem);

        assertEquals(3, cartitemserv.removeFromCart(1L, 1L, "comment").getQuantity());

    }
}