package main

import (
	"fmt"
)

/*
* https://www.acmicpc.net/problem/2960
 */

func main() {

	var n, k int
	_, err := fmt.Scan(&n, &k)

	if err == nil {

		var a = [1000]int{0}
		a[0] = 1
		a[1] = 1
		var prime = 2
		for count := 0; count < k; {
			for j := 1; (j+1)*prime <= n; j++ {
				if a[j*prime] == 0 {
					a[j*prime] = 1
					count++

					if count == k {
						fmt.Println(j * prime)
						return
					}
				}
			}

			beforePrime := prime
			for j := prime + 1; j < n; j++ {
				if a[j] == 0 {
					prime = j
					break
				}
			}

			if beforePrime == prime || n <= prime {
				return
			}
		}
	}

	fmt.Println("not found")
}
