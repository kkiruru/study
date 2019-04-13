package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
* https://code.google.com/codejam/contest/1403486/dashboard
 */

func main() {

	scanner := bufio.NewScanner(os.Stdin)

	scanner.Scan()
	// lineCount := scanner.Text()
	// fmt.Print("lineCount ", lineCount, "\n")

	i := 1
	for scanner.Scan() {
		line := scanner.Text()
		parts := strings.Split(line, " ")

		totalMonth, _ := strconv.ParseInt(parts[0], 10, 64)
		daysOfMonth, _ := strconv.ParseInt(parts[1], 10, 64)
		daysOfWeek, _ := strconv.ParseInt(parts[2], 10, 64)

		fmt.Print("Case #", i, ": ", lineOfCalendar(totalMonth, daysOfMonth, daysOfWeek), "\n")
		i++
	}
}

func lineOfCalendar(totalMonth int64, daysOfMonth int64, daysOfWeek int64) int64 {
	var lines int64 = 0
	var prev int64 = 0
	var i int64 = 0
	for ; i < totalMonth; i++ {
		lines += ((prev + daysOfMonth) + (daysOfWeek - 1)) / daysOfWeek
		prev = (prev + daysOfMonth) % daysOfWeek
	}

	return lines
}
