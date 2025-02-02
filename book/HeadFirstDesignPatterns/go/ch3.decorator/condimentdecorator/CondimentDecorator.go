package condimentdecorator

import (
	"headfirst.design.patterns/decorator/beverage"
)

type Mocha struct {
	decorator beverage.Beverage
}

func AddMocha(b beverage.Beverage) beverage.Beverage {
	c := Mocha{decorator: b}
	return c
}

func (b Mocha) Cost() (cost float32) {
	return 0.20 + b.decorator.Cost()
}

func (b Mocha) Description() string {
	return (b.decorator.Description() + ", 모카")
}

type Whip struct {
	decorator beverage.Beverage
}

func AddWhip(b beverage.Beverage) beverage.Beverage {
	c := Whip{decorator: b}
	return c
}

func (b Whip) Cost() (cost float32) {
	return 0.10 + b.decorator.Cost()
}

func (b Whip) Description() string {
	return (b.decorator.Description() + ", 휘핑 크림")
}

type Soy struct {
	decorator beverage.Beverage
}

func AddSoy(b beverage.Beverage) beverage.Beverage {
	c := Soy{decorator: b}
	return c
}

func (e Soy) Cost() (cost float32) {
	return 0.15 + e.decorator.Cost()
}

func (e Soy) Description() string {
	return (e.decorator.Description() + ", 두유")
}

type SteamMilk struct {
	decorator beverage.Beverage
}

func AddSteamMilk(b beverage.Beverage) beverage.Beverage {
	c := SteamMilk{decorator: b}
	return c
}

func (b SteamMilk) Cost() (cost float32) {
	return 0.10 + b.decorator.Cost()
}

func (b SteamMilk) Description() string {
	return (b.decorator.Description() + ", 스팀 밀크")
}
