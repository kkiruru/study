package duck

import (
	"fmt"
)

type Duck interface {
	Quack()
	Fly()
}

type Turkey interface {
	Gobble()
	Fly()
}

type MallardDuck struct{}

func (t *MallardDuck) Quack() {
	fmt.Println("Quack")
}

func (t *MallardDuck) Fly() {
	fmt.Println("I'm flying")
}

type TurkeyAdapter struct {
	turkey Turkey
}

func GetTurkeyAdapterInstance(turkey Turkey) *TurkeyAdapter {
	t := &TurkeyAdapter{turkey: turkey}
	return t
}

func (t *TurkeyAdapter) Quack() {
	t.turkey.Gobble()
}

func (t *TurkeyAdapter) Fly() {
	i := 0
	for i < 5 {
		t.turkey.Fly()
		i++
	}
}

type WildTurkey struct{}

func (w *WildTurkey) Gobble() {
	fmt.Println("Gobble Gobble")
}

func (w *WildTurkey) Fly() {
	fmt.Println("I'm flying a short distance")
}
