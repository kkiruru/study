package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
* https://www.acmicpc.net/problem/1541
 */

func main() {
	scanner := bufio.NewScanner(os.Stdin)

	for scanner.Scan() {
		line := scanner.Text()
		value := calculate(line)

		fmt.Println(value)
	}
}

func calculate(line string) int {
	var sum int
	toggle := 1

	parts := strings.Split(line, "-")
	for _, part := range parts {
		sum = sum + (add(part) * toggle)
		if toggle == 1 {
			toggle = -1
		}
	}

	return sum
}

func add(arr string) int {
	numbers := strings.Split(arr, "+")

	var value int
	for _, num := range numbers {
		n, err := strconv.Atoi(num)
		if err == nil {
			value = value + n
		}
	}
	return value
}
