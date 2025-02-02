package main

import (
	pizzastore "headfirst.design.patterns/factory/pizzastore"
)


func main() {
	var pizzaStore pizzastore.PizzaStore = pizzastore.PizzaStore{Abstract: pizzastore.NYPizzaStore{}}
	pizzaStore.OrderPizza("cheese")
}