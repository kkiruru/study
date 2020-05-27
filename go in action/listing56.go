// 인터페이스와 타입 임베딩의 관계를 설명하기 위한 예제
package main

import (
	"fmt"
)

// 알림 동작을 정의하는
// notifier  인터페이스를 선언한다.
type notifier interface {
	notify()
}

// 사용자를 표현하는 user 타입을 선언한다.
type user struct {
	name  string
	email string
}

// user 타입의 값을 통해 호출할 수 있는
// notify 메서드를 구현한다.
func (u *user) notify() {
	fmt.Printf("사용자에게 메일을 전송합니다.: %s<%s>\n",
		u.name,
		u.email)
}

// 더 많은 권한을 가진 관리자를 표현하는 admin 타입을 선언한다.
type admin struct {
	user
	level string
}

func main() {
	ad := admin{
		user: user{
			name:  "john smith",
			email: "john@yahoo.com",
		},
		level: "super",
	}

	// 관리자에게 알림을 전송한다.
	// 이 경우 포함된 내부 타입이 구현한 인터페이스가
	// 외부 타입으로 승격된다.
	sendNotification(&ad)
}

func sendNotification(n notifier) {
	n.notify()
}
