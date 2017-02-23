package factorial

import "fmt"

func ExampleFactorial() {
	testFunc := Factorial

	value := testFunc(10)
	fmt.Println(value)

	// Output:
	// 3628800
}
