package main

import (
	"bufio"
	"os"
)

/*
* https://code.google.com/codejam/contest/1403486/dashboard
 */

func main() {

	scanner := bufio.NewScanner(os.Stdin)

	scanner.Scan()

	i := 1
	for scanner.Scan() {

		i++
	}
}
