package main

import (
	"bufio"
	"os"
)

func main() {

	scanner := bufio.NewScanner(os.Stdin)

	scanner.Scan()

	i := 1
	for scanner.Scan() {

		i++
	}
}
