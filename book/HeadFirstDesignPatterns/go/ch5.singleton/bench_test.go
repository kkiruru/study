package main

import (
	"sync"
	"testing"

	"headfirst.design.patterns/singleton/chocolateboiler"
)

func BenchmarkSimpleSingleton(*testing.B) {
	var wg sync.WaitGroup
	for i := 0; i < try; i++ {
		wg.Add(1)
		go func() {
			defer wg.Done()
			_ = chocolateboiler.GetInstance1().Val + 1
		}()
	}
	wg.Wait()
}

func BenchmarkOnce(*testing.B) {
	var wg sync.WaitGroup
	for i := 0; i < try; i++ {
		wg.Add(1)
		go func() {
			defer wg.Done()
			_ = chocolateboiler.GetInstance().Val + 1
		}()
	}
	wg.Wait()
}
