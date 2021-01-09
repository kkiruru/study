package main

import (
	"fmt"
)

func main() {

	animal := &Animal{
		NoOfLegs: 2,
	}

	cat := &Cat{
		Animal: Animal{
			NoOfLegs: 4,
		},
		color: "white",
	}

	fmt.Println("1: ", animal.GetNoOfLegs())
	fmt.Println("2: ", cat.Color())
	fmt.Println("3: ", cat.GetNoOfLegs())
	fmt.Println("4: ", cat.Animal.GetNoOfLegs())
	fmt.Println("5: ", cat.sound)

}
