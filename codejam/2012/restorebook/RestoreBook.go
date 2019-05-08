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

	addition([]byte("?494"), []byte("69??"), []byte("1?422"))

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
			fmt.Print("Case #", i, ": ", formula, "\n")
		} else {
			formula = subtraction(parts[0], parts[2], parts[4])
		}

		i++
	}
}

func convert2int(a byte, b byte, c byte) (int, int, int) {

	first, err := strconv.Atoi(string(a))
	if err != nil {
		first = -1
	}

	second, err := strconv.Atoi(string(b))
	if err != nil {
		second = -1
	}

	third, err := strconv.Atoi(string(c))
	if err != nil {
		third = -1
	}

	return first, second, third

}

func load(a []byte, index int) byte {
	if index < len(a) {
		if (len(a) - index - 1) == 0 {
			return '1'
		}
		return a[len(a)-index-1]
	}

	return '0'
}

type State int

const (
	Digit State = iota
	Empty
	Unknown
)

func parse(a []byte, index int) (int, State) {
	if index <= len(a) || index < 0 {
		return -1, Empty
	}

	d, err := strconv.Atoi(string(a[len(a)-index-1]))
	if err != nil {

		return -1, Unknown
	}

	return d, 0
}

func isFirst(a []byte, index int) bool {
	return index-1 == len(a)
}

func addition(augend []byte, addend []byte, sum []byte) string {

	count := int(math.Max(math.Max(float64(len(augend)), float64(len(addend))), float64(len(sum))))

	var carry int

	for j := 0; j < count; j++ {

		a, stateOfA := parse(augend, j)
		b, stateOfB := parse(addend, j)
		s, stateOfS := parse(sum, j)

		if stateOfA == Digit && stateOfB == Digit && stateOfS == Digit {
			continue
		}

		if stateOfA == Digit && stateOfB == Digit {
			s = (a + b + carry) % 10
			carry = (a + b + carry) / 10
		}

		if stateOfA == Digit && stateOfS == Digit {
			if s < a+carry {
				s = s + 10
			}
			b = s - (a + s)
			carry = s / 10
		}





		
		if 0 <= len(augend)-j-1 {
			a = a + int('0')
			augend[len(augend)-j-1] = byte(a)
		}

		if 0 <= len(addend)-j-1 {
			b = b + int('0')
			addend[len(addend)-j-1] = byte(b)
		}

		if 0 <= len(sum)-j-1 {
			s = s + int('0')
			sum[len(sum)-j-1] = byte(s)
		}
	}

	return (string(augend) + " + " + string(addend) + " = " + string(sum))
}

func subtraction(minuend string, subtrahend string, difference string) string {
	return (minuend + " - " + subtrahend + " = " + difference)
}

func pop(before []byte) ([]byte, byte) {
	var last byte
	if before == nil || len(before) == 0 {
		fmt.Print("in pop return nil, '?'", "\n")
		return nil, '?'
	}
	last = before[len(before)-1]
	before = before[:len(before)-1]
	fmt.Print("in pop return ", last, "\n")

	return before, last
}
