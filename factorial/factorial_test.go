package factorial

import "fmt"

func ExampleFactorial(){
	f := Factorial

	value := f(10)
	fmt.Fprintln(value)

	// Output:
	// 5
}
