package main

import (
	"headfirst.design.patterns/singleton/chocolateboiler"
)

const try = 100_0000

func main() {
	c := chocolateboiler.GetInstance()
	c.Fill()
	c.Boil()
	c.Drain()
}
