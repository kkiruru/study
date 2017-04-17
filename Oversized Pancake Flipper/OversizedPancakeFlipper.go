package main

import (
	"bufio"
	"fmt"
	"os"
)

func main() {

	scanner := bufio.NewScanner(os.Stdin)

	scanner.Scan()
	line := scanner.Text()
	fmt.Print("line ", line, "\n")

	i := 1
	for scanner.Scan() {
		line = scanner.Text()
		fmt.Print("Case #", i, " : "+flipping(""), " line ", line, "\n")
		i++
	}

}

func flipping(pancake string) string {
	return "IMPOSIBBLE"
}
