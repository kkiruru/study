package pizzastore

import (
	"fmt"
	pizza "headfirst.design.patterns/factory/pizza"
)

func init() {
	fmt.Println("pizzastore package의 init 함수")
}


type Abstract interface {
	createPizza(pizzaType string) pizza.Pizza
}


type PizzaStore struct {
	Abstract
}


func (store PizzaStore) OrderPizza(pizzaType string) pizza.Pizza {
	pizza := store.createPizza(pizzaType)

	pizza.Prepare();
	pizza.Bake();
	pizza.Cut();
	pizza.Box();

	return pizza
}


type NYPizzaStore struct {}


func (pizzaStore NYPizzaStore) createPizza(pizzaType string) pizza.Pizza {
	pizza := pizza.NewNYStyleCheesePizza()
	return *pizza
}
