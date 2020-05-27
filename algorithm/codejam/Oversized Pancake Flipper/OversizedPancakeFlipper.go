package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {

	scanner := bufio.NewScanner(os.Stdin)

	scanner.Scan()

	i := 1
	for scanner.Scan() {
		line := scanner.Text()
		parts := strings.Split(line, " ")

		pancake := parts[0]
		sizeOfFlipper, _ := strconv.Atoi(parts[1])

		fmt.Print("Case #", i, ": "+flipping([]rune(pancake), sizeOfFlipper), "\n")
		i++
	}
}

func flipping(pancakes []rune, sizeOfFlipper int) string {

	var limit int = len(pancakes) - sizeOfFlipper
	var flippingCount = 0

	for i := 0; i <= limit; i++ {
		if pancakes[i] == '-' {
			flippingCount++
			for j := 0; j < sizeOfFlipper; j++ {
				if pancakes[i+j] == '-' {
					pancakes[i+j] = '+'
				} else {
					pancakes[i+j] = '-'
				}
			}
		}
	}

	if isSunnySideUp(pancakes) {
		return strconv.Itoa(flippingCount)
	} else {
		return "IMPOSSIBLE"
	}
}

func isSunnySideUp(pancakes []rune) bool {
	for _, r := range pancakes {
		if r == '-' {
			return false
		}
	}
	return true
}
