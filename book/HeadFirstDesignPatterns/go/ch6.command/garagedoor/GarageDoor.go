package garagedoor

import "fmt"

type GarageDoor struct{}

func (g *GarageDoor) Open() {
	fmt.Println("Garage Door가 열린다.")
}

func (g *GarageDoor) Close() {
	fmt.Println("Garage Door가 닫힌다.")
}

type GarageDoorOpenCommand struct {
	garageDoor *GarageDoor
}

func GetGarageDoorOpenInstance(l *GarageDoor) *GarageDoorOpenCommand {
	c := &GarageDoorOpenCommand{garageDoor: l}
	return c
}

func (g *GarageDoorOpenCommand) Execute() {
	g.garageDoor.Open()
}

func (g *GarageDoorOpenCommand) Undo() {
	g.garageDoor.Close()
}

type GarageDoorCloseCommand struct {
	garageDoor *GarageDoor
}

func GetGarageDoorCloseInstance(l *GarageDoor) *GarageDoorOpenCommand {
	c := &GarageDoorOpenCommand{garageDoor: l}
	return c
}

func (g *GarageDoorCloseCommand) Execute() {
	g.garageDoor.Close()
}

func (g *GarageDoorCloseCommand) Undo() {
	g.garageDoor.Open()
}
