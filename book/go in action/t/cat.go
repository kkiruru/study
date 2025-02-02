package main

import(
	"fmt"
)


type Cat struct {
	color string

	Animal
	A
}

func (c *Cat) Color() string {
	return c.color
}

func (c *Cat) sound() {
	fmt.Println("mue")
}