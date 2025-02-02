package chocolateboiler

import (
	"fmt"
	"sync"
)

type chocolateBoiler struct {
	empty  bool
	boiled bool
	Val    int
}

var instance *chocolateBoiler
var once sync.Once

func GetInstance() *chocolateBoiler {
	once.Do(func() {
		instance = &chocolateBoiler{empty: true, boiled: false, Val: 0}
	})
	return instance
}

var mutex sync.Mutex

func GetInstance1() *chocolateBoiler {

	mutex.Lock()
	defer mutex.Unlock()
	if instance == nil {
		instance = &chocolateBoiler{empty: true, boiled: false, Val: 0}
	}
	return instance
}

func (c *chocolateBoiler) Fill() {
	if c.empty {
		c.empty = false
		c.boiled = false
		fmt.Println("초콜릿을 채운다")
	}
}

func (c *chocolateBoiler) Drain() {
	if c.empty == false && c.boiled {
		c.empty = true
		fmt.Println("초콜릿을 붓는다")
	}
}

func (c *chocolateBoiler) Boil() {

	if c.empty == false && c.boiled == false {
		c.boiled = true
		fmt.Println("초콜릿을 끓인다")
	}
}
