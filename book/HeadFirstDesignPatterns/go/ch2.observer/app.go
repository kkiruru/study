package main

import (
	"fmt"

	"headfirst.design.patterns/observer/display"
	"headfirst.design.patterns/observer/weatherdata"
)

func main() {
	weatherData := weatherdata.WeatherData{}

	d1 := display.NewCurrentConditionsDisplay(&weatherData)
	display.NewCurrentConditionsDisplay(&weatherData)

	weatherData.SetMeasurements(80, 65, 30.4)
	weatherData.SetMeasurements(82, 70, 29.2)
	weatherData.SetMeasurements(78, 90, 29.2)

	fmt.Println("remove d1")
	weatherData.RemoveObserver(d1)
	weatherData.SetMeasurements(80, 65, 30.4)

	fmt.Println("end")
}
