package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
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
		var parts []string
		parts = strings.Split(line, " ")
		if parts[1] == "+" {
			formula = addition([]byte(parts[0]), []byte(parts[2]), []byte(parts[4]))
		} else {
			formula = subtraction(parts[0], parts[2], parts[4])
		}

		fmt.Print("Case #", i, ": ", formula, "\n")

		i++
	}
}

func addition(augend []byte, addend []byte, sum []byte) string {

	count := int(math.Max(math.Max(float64(len(augend)), float64(len(addend))), float64(len(sum))))

	pop := func(before []byte) ([]byte, byte) {
		var last byte
		if before == nil || len(before) == 0 {
			return nil, '0'
		}
		last = before[len(before)-1]
		before = before[:len(before)-1]
		return before, last
	}

	var au byte

	for i := count; 0 < i; i-- {
		augend, au = pop(augend)
		aa, _ := strconv.Atoi(string(au))
		fmt.Print("augend[", i, "] ", au, " > ", aa, "\n")

		// fmt.Print(ad)
		// fmt.Print(su)
	}

	return (string(augend) + " + " + string(addend) + " = " + string(sum))
}

func subtraction(minuend string, subtrahend string, difference string) string {
	return (minuend + " - " + subtrahend + " = " + difference)
}
