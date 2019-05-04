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

	var a int
	var b int
	var s int

	var carry int

	for j := 0; j < count; j++ {
		var au byte
		var ad byte
		var su byte

		if 0 <= len(augend)-j-1 {
			au = augend[len(augend)-j-1]
		} else {
			au = '?'
		}

		if 0 <= len(addend)-j-1 {
			ad = addend[len(addend)-j-1]
		} else {
			ad = '?'
		}

		if 0 <= len(sum)-j-1 {
			su = sum[len(sum)-j-1]
		} else {
			su = '?'
		}

		if au != '?' {
			if ad != '?' {
				a, _ = strconv.Atoi(string(au))
				b, _ = strconv.Atoi(string(ad))
				s = (a + b + carry) % 10
				carry = (a + b + carry) / 10

				// fmt.Print("addition[", j, "] 1 : ", a, "+", b, "=", s, ", ", carry, "\n")

			} else if su != '?' {
				a, _ = strconv.Atoi(string(au))
				s, _ = strconv.Atoi(string(su))
				if s < a+carry {
					s = s + 10
				}
				b = (s - (a + carry)) % 10
				carry = s / 10
				s = s % 10

				// fmt.Print("addition[", j, "] 2 : ", a, "+", b, "=", s, ", ", carry, "\n")

			} else {
				a, _ = strconv.Atoi(string(au))
				b = 0
				s = (a + b + carry) % 10
				carry = (a + b + carry) / 10

				// fmt.Print("addition[", j, "] 3 : ", a, "+", b, "=", s, ", ", carry, "\n")
			}
		} else {
			carry = 0

			if ad != '?' {
				b, _ = strconv.Atoi(string(ad))

				if su != '?' {
					s, _ = strconv.Atoi(string(su))

					if s < b+carry {
						s = s + 10
					}
					a = (s - (b + carry)) % 10
					carry = s / 10
					s = s % 10

					// fmt.Print("addition[", j, "] 4 : ", a, "+", b, "=", s, ", ", carry, "\n")

				} else {
					a = 0

					s = (a + carry + b) % 10
					carry = s / 10
					// fmt.Print("addition[", j, "] 5 : ", a, "+", b, "=", s, ", ", carry, "\n")
				}
			} else {
				if su != '?' {
					s, _ = strconv.Atoi(string(su))
					a = 0
					b = s - carry
					carry = 0
				} else {
					a = 0
					b = carry
					carry = 0
					s = a + b
				}
				// fmt.Print("addition[", j, "] 6 : ", a, "+", b, "=", s, ", ", carry, "\n")
			}
		}

		if 0 <= len(augend)-j-1 {
			fmt.Print("add0[", j, "], augend[len(augend)-j-1]= ", augend[len(augend)-j-1], ", ", string(augend), "\n")
			fmt.Print("add0[", j, "], len= ", len(augend), ", Position ", len(augend)-j-1, ", byte(a)=", byte(a), "\n")

			augend[len(augend)-j-1] = byte(a)
			fmt.Print("add1[", j, "], augend[len(augend)-j-1]= ", augend[len(augend)-j-1], ", ", string(augend), "\n")
		}

		// if 0 <= len(addend)-j-1 {
		// 	addend[len(addend)-j-1] = byte(b)
		// }

		// if 0 <= len(sum)-j-1 {
		// 	sum[len(sum)-j-1] = byte(s)
		// }
	}

	// summValue := make([]byte, count, count)

	// var au byte
	// var ad byte
	// var su byte

	// var carry int = 0

	// for i := count; 0 < i; i-- {
	// 	augend, au = pop(augend)
	// 	addend, ad = pop(addend)
	// 	sum, su = pop(sum)

	// 	var a int
	// 	var b int
	// 	var s int

	// 	if au != '?' {
	// 		if ad != '?' {
	// 			a, _ = strconv.Atoi(string(au))
	// 			b, _ = strconv.Atoi(string(ad))
	// 			s = a + b + carry

	// 			summValue[i] = byte(strconv.Itoa(string(s)))
	// 		} else {

	// 		}

	// 	} else {

	// 	}

	// 	aa, err := strconv.Atoi(string(au))

	// 	if err == nil {
	// 		fmt.Print("augend[", i, "] ", au, " > ", aa, "\n")
	// 	} else {
	// 		fmt.Print("error augend[", i, "] ", au, " > ", aa, "\n")
	// 	}

	// }

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
