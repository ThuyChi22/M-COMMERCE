package com.nguyenthuychi.models;

import java.util.ArrayList;

public class ListEmployee {
    private ArrayList<Employee> employees;

    public ListEmployee() {
        employees=new ArrayList<>();
    }
    public void gen_dataset(){
        Employee e1=new Employee();
        e1.setId(1);
        e1.setName("John");
        e1.setEmail("john@gmail.com");
        e1.setPhone("0975621823");
        e1.setUsername("john");
        e1.setPassword("123456");
        employees.add(e1);

        Employee e2=new Employee();
        e2.setId(2);
        e2.setName("Marry");
        e2.setEmail("marry@gmail.com");
        e2.setPhone("097523423");
        e2.setUsername("marry");
        e2.setPassword("123456");
        employees.add(e2);

        Employee e3=new Employee();
        e3.setId(3);
        e3.setName("Peter");
        e3.setEmail("peter@gmail.com");
        e3.setPhone("082683423");
        e3.setUsername("peter");
        e3.setPassword("123456");
        employees.add(e3);
    }

    public Employee[] getEmployees() {
        return new Employee[0];
    }
}
