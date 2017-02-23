package factorial

import "fmt"

func ExampleFactorial() {
	testFunc := Factorial

	value := testFunc(10)
	fmt.Println(value)

	testFunc2 := Factorial1
	value1 := testFunc2(10)
	fmt.Println(value1)
	// Output:
	// 3628800
	// 3628800
}
