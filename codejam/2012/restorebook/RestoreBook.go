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

	var formula string

	formula = addition([]byte("2?679"), []byte("?31?"), []byte("??9?6"))
	fmt.Print("Case #0 : ", formula, "\n")

	scanner := bufio.NewScanner(os.Stdin)
	scanner.Scan()

	i := 1

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

func isLast(a []byte, index int) bool {

	_, state := parse(a, index+1)

	return state == Empty

}

func addition(augend []byte, addend []byte, sum []byte) string {

	count := int(math.Max(math.Max(float64(len(augend)), float64(len(addend))), float64(len(sum))))

	var carry int
	// var last bool

	for j := 0; j < count; j++ {

		a, stateOfA := parse(augend, j)
		b, stateOfB := parse(addend, j)
		s, stateOfS := parse(sum, j)

		// d, d, d
		if stateOfA == Digit && stateOfB == Digit && stateOfS == Digit {
			carry = (a + b) / 10
			continue
		}

		// * + E = *
		if stateOfB == Empty {
			// ? + E = (?,D)
			if stateOfA == Unknown {
				if stateOfS == Unknown {
					if isFirst(addend, j) {
						a = 1
					} else {
						a = 0
					}
				} else {
					a = s - carry
				}
				stateOfA = Digit
			}

			s = a + carry
			carry = 0
			stateOfS = Digit

		} else if isFirst(addend, j) && !isFirst(sum, j) {
			if stateOfA == Unknown {
				if isFirst(augend, j) {
					if stateOfB == Unknown {
						a = 1
					} else {
						if stateOfS == Unknown {
							a = 10 - b - carry
						} else {
							a = s + 10 - b - carry
						}
					}
				} else {
					s1, stateOfS1 := parse(sum, j+1)
					if stateOfS1 == Unknown {

						if stateOfB == Unknown {
							a = 1
						} else {
							if stateOfS == Unknown {
								a = 10 - b - carry
							} else {
								a = s + 10 - b - carry
							}
						}

					} else {
						a1, stateOfA1 := parse(augend, j+1)

						if stateOfA1 == Digit {
							if s1 == a1 {
								if stateOfB == Unknown {
									a = 0
								} else {
									if stateOfS == Unknown {
										a = 0
									} else {
										a = s + b - carry
									}
								}
							} else {
								if stateOfB == Unknown {
									if stateOfS == Unknown {
										a = 1 - carry
									} else {
										a = s - 9 - carry
									}
								} else {
									if stateOfS == Unknown {
										a = 0
									} else {
										a = s + b - carry
									}
								}
							}
						}

					}
				}
				stateOfA = Digit
			}

			if stateOfB == Unknown {
				b = 10 - (a + carry)
				stateOfB = Digit
			}

			if stateOfS == Unknown {
				s = (a + b + carry) % 10
				stateOfS = Digit
			}

			carry = (a + b + carry) / 10
		} else {

			// ? + ? = ?
			if stateOfA == Unknown && stateOfB == Unknown && stateOfS == Unknown {
				if isFirst(augend, j) {
					a = 1
				} else {
					a = 0
				}
				stateOfA = Digit
			}

			// ? + ? = d
			if stateOfA == Unknown && stateOfB == Unknown {
				if isFirst(augend, j) {
					a = 1
				} else {
					a = 0
				}
				stateOfA = Digit
			}

			// ? + d = ?
			if stateOfA == Unknown && stateOfS == Unknown {
				if isFirst(augend, j) {
					a = 1
				} else {
					a = 0
				}
				stateOfA = Digit
			}

			// d + ? = ?
			if stateOfB == Unknown && stateOfS == Unknown {
				if isFirst(addend, j) {
					b = 1
				} else {
					b = 0
				}
				stateOfB = Digit
			}

			// d + d = ?
			if stateOfA == Digit && stateOfB == Digit {
				s = (a + b + carry) % 10
				carry = (a + b + carry) / 10
			}

			// d + ? = d
			if stateOfA == Digit && stateOfS == Digit {
				if s < a+carry {
					s = s + 10
				}
				b = s - (a + carry)

				carry = s / 10
				s = s % 10
			}

			// ? + d = d
			if stateOfB == Digit && stateOfS == Digit {
				if s < b+carry {
					a = s + 10 - (b + carry)
				} else {
					a = s - (b + carry)
				}
				stateOfA = Digit
				carry = (a + b + carry) / 10
			}

		}

		if stateOfA != Empty {
			a = a + int('0')
			augend[len(augend)-j-1] = byte(a)
		}

		if stateOfB != Empty {
			b = b + int('0')
			addend[len(addend)-j-1] = byte(b)
		}

		if stateOfS != Empty {
			s = s + int('0')
			sum[len(sum)-j-1] = byte(s)
		}
	}

	// fmt.Print("> " + (string(augend) + " + " + string(addend) + " = " + string(sum)) + "\n")

	return (string(augend) + " + " + string(addend) + " = " + string(sum))
}

func subtraction(minuend string, subtrahend string, difference string) string {
	return (minuend + " - " + subtrahend + " = " + difference)
}

// func pop(before []byte) ([]byte, byte) {
// 	var last byte
// 	if before == nil || len(before) == 0 {
// 		fmt.Print("in pop return nil, '?'", "\n")
// 		return nil, '?'
// 	}
// 	last = before[len(before)-1]
// 	before = before[:len(before)-1]
// 	fmt.Print("in pop return ", last, "\n")

// 	return before, last
// }
