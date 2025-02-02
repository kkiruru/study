package ingredient

import "fmt"

func init() {
	fmt.Println("Ingredient의 init 함수")
}


type PizzaIngredientFactory interface {
    createDough() Dough
    // Sauce createSauce();
    // Cheese createCheese();
    // Veggie[] createVeggies();
    // Clam createClam();
}


type Dough struct{}