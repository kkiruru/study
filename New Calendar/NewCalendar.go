package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

/*
* https://code.google.com/codejam/contest/1403486/dashboard
 */

func main() {

	scanner := bufio.NewScanner(os.Stdin)

	scanner.Scan()
	lineCount := scanner.Text()
	fmt.Print("lineCount ", lineCount, "\n")

	i := 1
	for scanner.Scan() {
		line := scanner.Text()
		parts := strings.Split(line, " ")
		fmt.Print("[", i, "] ", parts, "\n")
		i++
	}
}
