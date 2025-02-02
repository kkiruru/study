package pizza

import (
	"fmt"
)

type abstract interface {
	Prepare()
}

type property struct {
	Name string
	Price float64
}

type Pizza struct {
	abstract
	property
}


func (p Pizza) Bake() {
	fmt.Println("Bake for 25 minutes at 350")
}


func (p Pizza) Cut() {
	fmt.Println("Cutting the pizza into diagonal slices")
}


func (p Pizza) Box() {
	fmt.Printf("Place pizza in official PizzaStore box : $%0.2f", p.Price)
}
