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
		// value := calculate(line)
		value := calculate(line)

		fmt.Println(value)
	}
}


func calculate(line string) int {
	numbers := strings.SplitN(line, "-", 2)
	if 1 < len(numbers) {
		return add(numbers[0]) - add(numbers[1])
	}
	return add(numbers[0])
}

func add(arr string) int {
	arr = strings.Replace(arr, "-", "+", -1)
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
