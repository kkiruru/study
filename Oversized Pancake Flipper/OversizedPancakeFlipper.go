package main

import "fmt"

func main() {

	for i := 0; i < 100; i++ {
		fmt.Print("Case #", i, " : "+flipping(""), "\n")
	}
}

func flipping(pancake string) string {
	return "IMPOSIBBLE"
}
