package main

import (
	"fmt"
	"example.com/listing64/counters"
)

func main() {
	// 비노출 타입의 변수를 생성하고
	// 10이라는 값으로 초기화 한다.
	counter := counters.alertCounter(10)

	fmt.Printf("카운너: %d\n", counter)
}
