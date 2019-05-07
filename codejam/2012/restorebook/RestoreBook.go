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

func covert2int(first []byte, second []byte, third []byte, index int) (int, int, int) {

	var a, b, c int

	a, errA := strconv.Atoi(string(load(first, index)))
	b, errB := strconv.Atoi(string(load(second, index)))
	c, errC := strconv.Atoi(string(load(third, index)))

	if errA != nil {

	}

	return a, b, c
}

func loadByte(numbers []byte, count int) byte {
	if 0 <= len(numbers)-count-1 {
		return numbers[len(numbers)-count-1]
	}
	return '0'
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

		au = loadByte(augend, j)
		ad = loadByte(addend, j)
		su = loadByte(sum, j)

		a, b, s = convert2int(au, ad, su)

		if a == -1 && b == -1 {
			a = 0
			if s == -1 {
				b = 0
			}
		} else if a == -1 && s == -1 {
			a = 0
		} else if b == -1 && s == -1 {
			b = 0
		}

		if a == -1 {
			if s < b+carry {
				s = s + 10
			}
			a = (s - (b + carry)) % 10
			carry = s / 10
			s = s % 10
		} else if b == -1 {
			if s < a+carry {
				s = s + 10
			}
			b = (s - (a + carry)) % 10
			carry = s / 10
			s = s % 10
		} else {
			s = (a + b + carry) % 10
			carry = (a + b + carry) / 10
		}

		// if au != '?' {
		// 	if ad != '?' {
		// 		a, _ = strconv.Atoi(string(au))
		// 		b, _ = strconv.Atoi(string(ad))
		// 		s = (a + b + carry) % 10
		// 		carry = (a + b + carry) / 10
		// 	} else if su != '?' {
		// 		a, _ = strconv.Atoi(string(au))
		// 		s, _ = strconv.Atoi(string(su))
		// 		if s < a+carry {
		// 			s = s + 10
		// 		}
		// 		b = (s - (a + carry)) % 10
		// 		carry = s / 10
		// 		s = s % 10
		// 	} else {
		// 		a, _ = strconv.Atoi(string(au))
		// 		b = 0
		// 		s = (a + b + carry) % 10
		// 		carry = (a + b + carry) / 10
		// 	}
		// } else {
		// 	if ad != '?' {
		// 		b, _ = strconv.Atoi(string(ad))

		// 		if su != '?' {
		// 			s, _ = strconv.Atoi(string(su))

		// 			if s < b+carry {
		// 				s = s + 10
		// 			}
		// 			a = (s - (b + carry)) % 10
		// 			carry = s / 10
		// 			s = s % 10
		// 		} else {
		// 			a = 0

		// 			s = (a + carry + b) % 10
		// 			carry = s / 10
		// 		}
		// 	} else {
		// 		if su != '?' {
		// 			s, _ = strconv.Atoi(string(su))
		// 			a = 0
		// 			b = s - carry
		// 			carry = 0
		// 		} else {
		// 			a = 0
		// 			b = carry
		// 			carry = 0
		// 			s = a + b
		// 		}
		// 	}
		// }

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
