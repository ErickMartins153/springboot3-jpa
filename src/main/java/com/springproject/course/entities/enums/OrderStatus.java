package com.springproject.course.entities.enums;


public enum OrderStatus {
    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    private int code;

    //o construtor do ENUM é um caso especial, pois é private!
    private OrderStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static OrderStatus valueOf(int code) {
        //ao receber o código retorna o OrderStatus correspondente à este código

        for (OrderStatus value: OrderStatus.values()) {
            //uma forma de percorrer todos os valores possiveis do enum
            if (value.getCode() == code) {
                return value;
            }
        }
        //caso o código não esteja dentro dos valores
        throw new IllegalArgumentException("Invalid OrderStatus code");
    }
}
