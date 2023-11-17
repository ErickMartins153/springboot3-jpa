package com.springproject.course.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springproject.course.entities.enums.OrderStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_order") //Order é uma palavra reservada do SQL, por isso dá erro caso não mudemos o nome
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    //annotation para formatar
    private Instant moment;

    private Integer orderStatus;
    //apesar de ser um enum, internamente, trataremos como um int, para explicitarmos que no DB
    //estaremos guardando um número inteiro. Pro mundo externo ainda manteremos o Orderstatus, inclusive no construtor
    //e no getter e setter
    @ManyToOne //pois um cliente pode ter muitos pedidos
    @JoinColumn(name = "client_id") // client_id será a foreign key
    private User client;

    @OneToMany(mappedBy = "id.order") //no OrderItem tem-se o id composto que possui o pedido
    private Set<OrderItem> items = new HashSet<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)//nome do atributo na classe Payment, por ser 1to1
    // temos que colocar outro atributo
    // por ser 1to1 estamos mapeando as duas entidades para ter o mesmo Id, se o pedido for código 5,
    // o pagamento também. E nesse caso é obrigatório colocarmos o cascade
    private Payment payment;

    public Order() {}

    public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
        this.id = id;
        this.moment = moment;
        setOrderStatus(orderStatus);
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null) {
            this.orderStatus = orderStatus.getCode();
        }
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
