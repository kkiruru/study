package main

import (
	"fmt"
	"math/rand"
	"sync"
	"time"
)

var wg sync.WaitGroup

func init() {
	rand.Seed(time.Now().UnixNano())
}

func main() {
	task := make(chan int, 3)

	wg.Add(2)

	go producer(task)
	go consumer(task)

	fmt.Println("프로그램 대기")
	wg.Wait()
	fmt.Println("프로그램 종료")
}

func producer(task chan int) {
	defer wg.Done()
	defer fmt.Println("생산 중지")

	for i := 0; i < 10; i++ {
		fmt.Printf("작업 지시한다 %d\n", i)
		task <- i
	}
	close(task)
}

func consumer(task <-chan int) {
	defer wg.Done()

	for {
		id, ok := <-task
		if !ok {
			fmt.Println("모든 소비가 끝났다")
			return
		}
		// 작업이 완료되었다는 메시지를 출력한다.
		fmt.Printf("작업 완료 %d\n", id)

		sleep := rand.Int63n(100)
		time.Sleep(time.Duration(sleep) * time.Millisecond)
	}
}
