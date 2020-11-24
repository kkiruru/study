package main

type Animal struct {
	NoOfLegs int
}

type A interface {
	sound() (string)
}



func (a *Animal) GetNoOfLegs() int {
	return a.NoOfLegs
}