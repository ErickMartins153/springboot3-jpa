package com.springproject.course.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_user") // a palavra User é reservada na tabela H2, por isso renomeamos
public class User implements Serializable {
    @Id //setar quem é a primary key(PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //fazer com que haja autoincremento
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;

    @JsonIgnore //o user possui Order que possuem users, ao fazer uma requisição o JSON recebido entra num looping
    //para resolver isso precisamos dar o @JsonIgnore em ao menos uma das classes que participam da associação
    //de mão dupla, então quando damos um GET para users/1, por exemplo, não recebemos as Orders, para mudar isso
    //precisariamos remover o JsonIgnore daqui e colocá-lo na classe Order, assim orders/1 não retornaria o cliente
    //mas ao dar users receberiamos as orders associadas a ela
    @OneToMany(mappedBy = "client") //O equivalente ao que fizemos na classe Order, 1 usuário terá muitos pedidos
    //entre parenteses precisamos passar o nome do atributo que tem no outro lado da associação
    //que está na classe order, no caso private User _client_.
    private List<Order> orders = new ArrayList<>();

    public User() {}
    //por estarmos usando framework é obrigatório criar o construtor vazio


    public User(Long id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
