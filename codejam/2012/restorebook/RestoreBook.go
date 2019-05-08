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

	addition([]byte("????"), []byte("????"), []byte("?????"))

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

type State int

const (
	Digit State = iota
	Empty
	Unknown
)

func parse(a []byte, index int) (int, State) {
	if len(a) <= index || index < 0 {
		return 0, Empty
	}

	d, err := strconv.Atoi(string(a[len(a)-index-1]))
	if err != nil {

		return -1, Unknown
	}

	return d, Digit
}

func isFirst(a []byte, index int) bool {
	return index+1 >= len(a)
}

func addition(augend []byte, addend []byte, sum []byte) string {

	count := int(math.Max(math.Max(float64(len(augend)), float64(len(addend))), float64(len(sum))))

	var carry int

	for j := 0; j < count; j++ {

		a, stateOfA := parse(augend, j)
		b, stateOfB := parse(addend, j)
		s, stateOfS := parse(sum, j)

		// d, d, d
		if stateOfA == Digit && stateOfB == Digit && stateOfS == Digit {
			carry = (a + b) / 10
			continue
		}

		// d , ( d, e ), ?
		if stateOfA == Digit && (stateOfB == Digit || stateOfB == Empty) {
			s = (a + b + carry) % 10
			carry = (a + b + carry) / 10
		}

		// d, ?, d
		if stateOfA == Digit && stateOfS == Digit {
			if s < a+carry {
				s = s + 10
			}
			b = s - (a + carry)
			carry = s / 10
		}

		//d, ? , ?
		if stateOfA == Digit && stateOfB == Unknown && stateOfS == Unknown {
			if isFirst(addend, j) {
				b = 1
			} else {
				b = 0
			}
			s = (a + b + carry) % 10
			carry = (a + b + carry) / 10
		}

		if stateOfA == Unknown {
			//?, (d, e), _
			if stateOfB == Digit || stateOfB == Empty {

				if stateOfS == Unknown {
					if isFirst(augend, j) {
						a = 1
					} else {
						a = 0
					}
					//d , ( d, e ), ?
					s = (a + b + carry) % 10
					carry = (a + b + carry) / 10
				} else {
					// ?, (d,e), (d, e )
					if s < b+carry {
						s = s + 10
					}
					a = s - (b + carry)
					carry = s / 10
				}
			} else if stateOfB == Unknown { //?, ?, _
				if isFirst(augend, j) {
					a = 1
				} else {
					a = 0
				}

				if stateOfS == Unknown {
						if isFirst(addend, j) {
							b = 1
						} else {
							b = 0
						}
						s = (a + b + carry) % 10
						carry = (a + b + carry) / 10
					
				} else {
					if s < a+carry {
						s = s + 10
					}
					b = s - (a + carry)
					carry = s / 10
				}

			}

		} else if stateOfA == Empty {
			if stateOfB == Unknown {
				if isFirst(addend, j) {
					b = 1
				} else {
					b = 0
				}
			s = (a + b + carry) % 10
			carry = s / 10
		}

		if stateOfA == Unknown {
			a = a + int('0')
			augend[len(augend)-j-1] = byte(a)
		}

		if stateOfB == Unknown {
			b = b + int('0')
			addend[len(addend)-j-1] = byte(b)
		}

		if stateOfS == Unknown {
			s = s + int('0')
			sum[len(sum)-j-1] = byte(s)
		}
	}

	fmt.Print("> " + (string(augend) + " + " + string(addend) + " = " + string(sum)) + "\n")

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
