package com.productmanager.productmanagerapi;

import com.productmanager.productmanagerapi.models.Role;
import com.productmanager.productmanagerapi.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class testMethod {

    public static void main(String[] args) {
        // Creating a String array
        String[] arr = {"Geeks", "for", "Geeks"};

        //map: The map method is used to returns a stream consisting of the results of
        //applying the given function to the elements of this stream.
        List<Integer> number = Arrays.asList(2, 3, 4, 5);
        List square = number.stream().map(x -> x * x).collect(Collectors.toList());
        System.out.println(number);
        System.out.println(square);
        System.out.println("-------------------------------");
        System.out.println("-------------------------------");
        List<String> names = Arrays.asList("Reflection", "Collection", "Stream");
        List result = names.stream().filter(s -> s.startsWith("S")).collect(Collectors.toList());
        System.out.println(result);
        System.out.println("-------------------------------");
        System.out.println("-------------------------------");


        User user = new User();
        Role role_1 = new Role("ADMIN");
        Role role_2 = new Role("USER");
        user.setUserName("jawad");
        user.setEmail("jawad@mail.com");
        user.setPassword("123");
        user.setActive(true);
        user.addRole(role_1);
        user.addRole(role_2);

        List<GrantedAuthority> authorities;
        authorities = Arrays.stream("ADMIN,USER".split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

//
System.out.println(authorities);
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        System.out.println(authorities);
        System.out.println("-------------------------------");
        System.out.println("-------------------------------");

        Role[] arrayOfRoles = {
                new Role("Jeff Bezos"),
                new Role("Bill Gates"),
                new Role("Mark Zuckerberg")
        };


        List result1 = Stream.of(arrayOfRoles)
                .map(Role::getName)
                .collect(Collectors.toList());
        System.out.println(result1);
    }
}
