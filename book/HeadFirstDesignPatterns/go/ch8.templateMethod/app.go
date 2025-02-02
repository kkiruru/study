package main

func main() {
	var beverage *CaffeineBeverage

	beverage = NewCoffee()
	beverage.PrepareRecipe()

	beverage = NewTea()
	beverage.PrepareRecipe()
}
