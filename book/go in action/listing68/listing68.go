// 다른 패키지에서 비노출 식별자에 대한 접근이 가능하다는 것을
// 보여주기 위한 예제 프로그램
package main

import (
	"fmt"

	"example.com/goinaction/listing68/counters"
)

func main() {
	counter := counters.New(10)

	fmt.Printf("카운터: %d\n", counter)
}
