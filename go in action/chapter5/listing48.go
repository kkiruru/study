package main

import (
	"fmt"
)

type notifier interface {
	notify()
}

type user struct {
	name  string
	email string
}

func (u *user) notify() {
	fmt.Printf("사용자에게 메일을 전송합니다.: %s<%s>\n",
		u.name,
		u.email)
}

type admin struct {
	name  string
	email string
}

func (a admin) notify() {
	fmt.Printf("관리자에게 메일을 전송합니다.: %s<%s>\n",
		a.name,
		a.email)
}

func main() {
	bill := user{"Bill", "bill@email.com"}
	sendNotification(&bill)

	lisa := admin{"Lisa", "lisa@email.com"}
	sendNotification(&lisa)
}

func sendNotification(n notifier) {
	n.notify()
}
