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
	for scanner.Scan() {
		line := scanner.Text()
		parts := strings.Split(line, " ")

		fmt.Print("Case #", i, ": ", parts, "\n")

		i++
	}
}
