// 노출된 구조체 타입의 비노출 타입 필드에
// 접근이 불가능하다는 것으 설명하기 위한 예졔
package main

import (
	"fmt"

	"example.com/goinaction/listing71/entities"
)

func main() {
	// entities 패키지의 User 타입의 값을 생성한다.
	u := entities.User{
		Name: "Bill",
		// email : "bill@email.com",
	}

	fmt.Printf("사용자: %v\n", u)
}
