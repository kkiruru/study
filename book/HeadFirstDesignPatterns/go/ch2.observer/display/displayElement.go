package display

import (
	"fmt"

	"headfirst.design.patterns/observer/weatherdata"
)

type DisplayElement interface {
	Display()
}

type CurrentConditionsDisplay struct {
	temperature float32
	humidity    float32
	pressure	float32
	weatherData *weatherdata.Subject
}

func NewCurrentConditionsDisplay(subject weatherdata.Subject) *CurrentConditionsDisplay {
	instance := CurrentConditionsDisplay{}
	subject.RegisterObserver(&instance)
	instance.weatherData = &subject
	return &instance
}

func (d *CurrentConditionsDisplay) Update(temp float32, humidity float32, pressure float32) {
	d.temperature = temp
	d.humidity = humidity
	d.pressure = pressure
	d.Display()
}

func (d CurrentConditionsDisplay) Display() {
	fmt.Printf("Current conditions: %f F degreess and  %f %% humidity \n", d.temperature, d.humidity)
}
