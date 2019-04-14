package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

/*
* https://code.google.com/codejam/contest/1403486/dashboard#s=p1
 */

func main() {

	scanner := bufio.NewScanner(os.Stdin)
	scanner.Scan()

	i := 1
	var formula string

	for scanner.Scan() {
		line := scanner.Text()
		parts := strings.Split(line, " ")
		if parts[1] == "+" {
			formula = addition(parts[0], parts[2], parts[4])
		} else {
			formula = subtraction(parts[0], parts[2], parts[4])
		}

		fmt.Print("Case #", i, ": ", formula, "\n")

		i++
	}
}

func addition(augend string, addend string, sum string) string {
	return (augend + " + " + addend + " = " + sum)
}

func subtraction(minuend string, subtrahend string, difference string) string {
	return (minuend + " - " + subtrahend + " = " + difference)
}
