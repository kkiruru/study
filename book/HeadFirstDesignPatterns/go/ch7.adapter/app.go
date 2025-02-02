package main

import (
	"fmt"

	"headfirst.design.patterns/adapter/duck"
)

func main() {

	mallardDuck := &duck.MallardDuck{}

	fmt.Println("The Duck says...")
	testDuck(mallardDuck)

	wildTurkey := &duck.WildTurkey{}

	fmt.Println("The Turkey says...")
	wildTurkey.Gobble()
	wildTurkey.Fly()

	turkeyAdaper := duck.GetTurkeyAdapterInstance(wildTurkey)
	fmt.Println("The TurkeyAdaper says...")
	testDuck(turkeyAdaper)
}

func testDuck(d duck.Duck) {
	d.Quack()
	d.Fly()
}
