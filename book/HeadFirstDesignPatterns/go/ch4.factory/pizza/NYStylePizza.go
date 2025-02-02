package pizza

import (
	"fmt"
)

type NYStyleCheesePizza struct {
	Pizza
}

func (n NYStyleCheesePizza) Prepare() {
	fmt.Println("Prepare " + n.Name )
}


func NewNYStyleCheesePizza() (*Pizza) {
	pizza := NYStyleCheesePizza{Pizza{property: property{Name:"뉴욕치즈피자", Price:25.98}}}
	pizza.Pizza.abstract = pizza
	return &pizza.Pizza
}
