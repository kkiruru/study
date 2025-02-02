package ceilingfan

import "fmt"

type speed int

const (
	OFF    speed = 0
	LOW    speed = 1
	MEDIUM speed = 2
	HIGH   speed = 3
)

type CeilingFan struct {
	Location string
	Speed    speed
}

func GetCeilingFanInstance(location string) *CeilingFan {
	c := &CeilingFan{Location: location, Speed: OFF}
	return c
}

func (c *CeilingFan) High() {
	c.Speed = HIGH
	fmt.Println(c.Location, "Ceiling Fan HIGH")
}

func (c *CeilingFan) Medium() {
	c.Speed = MEDIUM
	fmt.Println(c.Location, "Ceiling Fan MEDIUM")
}

func (c *CeilingFan) Low() {
	c.Speed = LOW
	fmt.Println(c.Location, "Ceiling Fan LOW")
}

func (c *CeilingFan) Off() {
	c.Speed = OFF
	fmt.Println(c.Location, "Ceiling Fan OFF")
}

type CeilingFanHighCommand struct {
	ceilinfFan *CeilingFan
	prevSpeed  speed
}

func GetCeilingFanHighCommandInstance(ceilingFan *CeilingFan) *CeilingFanHighCommand {
	c := &CeilingFanHighCommand{ceilinfFan: ceilingFan}
	return c
}

func (c *CeilingFanHighCommand) Execute() {
	c.prevSpeed = c.ceilinfFan.Speed
	c.ceilinfFan.High()
}

func (c *CeilingFanHighCommand) Undo() {
	fmt.Println("Undo>")

	if c.prevSpeed == HIGH {
		c.ceilinfFan.High()
	} else if c.prevSpeed == MEDIUM {
		c.ceilinfFan.Medium()
	} else if c.prevSpeed == LOW {
		c.ceilinfFan.Low()
	} else if c.prevSpeed == OFF {
		c.ceilinfFan.Off()
	}
}

type CeilingFanOffCommand struct {
	ceilinfFan *CeilingFan
	prevSpeed  speed
}

func GetCeilingFanOffCommandInstance(ceilingFan *CeilingFan) *CeilingFanOffCommand {
	c := &CeilingFanOffCommand{ceilinfFan: ceilingFan}
	return c
}

func (c *CeilingFanOffCommand) Execute() {
	c.prevSpeed = c.ceilinfFan.Speed
	c.ceilinfFan.Off()
}

func (c *CeilingFanOffCommand) Undo() {
	fmt.Println("Undo>")

	if c.prevSpeed == HIGH {
		c.ceilinfFan.High()
	} else if c.prevSpeed == MEDIUM {
		c.ceilinfFan.Medium()
	} else if c.prevSpeed == LOW {
		c.ceilinfFan.Low()
	} else if c.prevSpeed == OFF {
		c.ceilinfFan.Off()
	}
}
