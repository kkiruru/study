package beverage

type Beverage interface {
	Cost() float32
	Description() string
}

type Espresso struct{}

func (e Espresso) Cost() (cost float32) {
	return 1.99
}

func (e Espresso) Description() string {
	return "에스프레소"
}

func NewEspresso() Beverage {
	b := Espresso{}
	return b
}

type HouseBlend struct{}

func (h HouseBlend) Cost() (cost float32) {
	return 0.89
}

func (e HouseBlend) Description() string {
	return "하우스 블랜드 커피"
}

type DarkRoast struct{}

func (d DarkRoast) Cost() (cost float32) {
	return 0.99
}

func (e DarkRoast) Description() string {
	return "다크 로스트 커피"
}

type Decaf struct{}

func (d Decaf) Cost() (cost float32) {
	return 1.05
}

func (e Decaf) Description() string {
	return "디카페인 커피"
}
