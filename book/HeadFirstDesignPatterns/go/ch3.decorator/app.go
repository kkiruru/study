package main

import (
	"fmt"

	"headfirst.design.patterns/decorator/beverage"

	decorator "headfirst.design.patterns/decorator/condimentdecorator"
)

func main() {
	fmt.Println("main")

	var b beverage.Beverage

	b = beverage.Espresso{}
	print(b)

	b = beverage.DarkRoast{}
	b = decorator.AddMocha(b)
	b = decorator.AddMocha(b)
	b = decorator.AddWhip(b)
	print(b)

	b = beverage.HouseBlend{}
	b = decorator.AddSoy(b)
	b = decorator.AddMocha(b)
	b = decorator.AddWhip(b)
	print(b)
}

func print(b beverage.Beverage) {
	fmt.Printf(b.Description()+" $%.2f\n", b.Cost())
}
