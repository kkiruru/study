package main

import "fmt"

type Tea struct {
	CaffeineBeverage
}

func NewTea() *CaffeineBeverage {
	tea := Tea{}
	tea.CaffeineBeverage.Abstract = &tea
	return &tea.CaffeineBeverage
}

func (c *Tea) brew() {
	fmt.Println("차를 우려내는 중")
}

func (c *Tea) addCondiments() {
	fmt.Println("레몬을 추가하는 중")
}
