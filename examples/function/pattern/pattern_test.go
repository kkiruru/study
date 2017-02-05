package pattern

import "fmt"

func Example_Sqrt() {
	type aaa int
	type bbb aaa

	fmt.Printf("%.5f\n", Sqrt(2))
	// Output:
	// 1.41421
}
